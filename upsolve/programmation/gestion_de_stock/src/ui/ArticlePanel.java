package ui;

import model.Article;
import model.MouvementStock;
import service.ArticleService;
import service.MouvementService;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ArticlePanel extends JPanel {
    private final ArticleService articleService;
    private final MouvementService mouvementService;
    private final JTable articleTable;
    private final DefaultTableModel tableModel;
    private final JTable mouvementTable;
    private final DefaultTableModel mouvementTableModel;
    private final JTextField nomField;
    private final JButton ajouterButton;
    private final JButton modifierButton;
    private final JButton supprimerButton;
    private final JButton actualiserButton;

    public ArticlePanel() {
        this.articleService = new ArticleService();
        this.mouvementService = new MouvementService();
        this.tableModel = creerModeleTable();
        this.mouvementTableModel = creerModeleMouvementTable();
        this.articleTable = new JTable(tableModel);
        this.mouvementTable = new JTable(mouvementTableModel);
        this.nomField = new JTextField(24);
        this.ajouterButton = new JButton("Ajouter");
        this.modifierButton = new JButton("Modifier");
        this.supprimerButton = new JButton("Supprimer");
        this.actualiserButton = new JButton("Actualiser");

        initialiserInterface();
        chargerArticles();
    }

    private void initialiserInterface() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formulairePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formulairePanel.add(new JLabel("Nom de l'article :"));
        formulairePanel.add(nomField);
        formulairePanel.add(ajouterButton);
        formulairePanel.add(modifierButton);
        formulairePanel.add(supprimerButton);
        formulairePanel.add(actualiserButton);

        articleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        articleTable.getTableHeader().setReorderingAllowed(false);
        mouvementTable.getTableHeader().setReorderingAllowed(false);

        add(formulairePanel, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(articleTable),
                new JScrollPane(mouvementTable)
        );
        splitPane.setResizeWeight(0.45);
        add(splitPane, BorderLayout.CENTER);

        ajouterButton.addActionListener(event -> ajouterArticle());
        modifierButton.addActionListener(event -> modifierArticle());
        supprimerButton.addActionListener(event -> supprimerArticle());
        actualiserButton.addActionListener(event -> {
            chargerArticles();
            chargerMouvementsArticleSelectionne();
        });

        articleTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                remplirChampDepuisSelection();
                chargerMouvementsArticleSelectionne();
            }
        });
    }

    private DefaultTableModel creerModeleTable() {
        return new DefaultTableModel(new Object[]{"ID", "Nom", "Date creation"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private DefaultTableModel creerModeleMouvementTable() {
        return new DefaultTableModel(new Object[]{
                "ID", "Type", "Methode", "Quantite", "Reste", "Prix", "Date"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void ajouterArticle() {
        try {
            articleService.ajouterArticle(nomField.getText());
            viderFormulaire();
            chargerArticles();
            afficherMessage("Article ajoute avec succes.");
        } catch (IllegalArgumentException | SQLException e) {
            afficherErreur(e.getMessage());
        }
    }

    private void modifierArticle() {
        int id = getIdSelectionne();

        try {
            articleService.modifierArticle(id, nomField.getText());
            viderFormulaire();
            chargerArticles();
            afficherMessage("Article modifie avec succes.");
        } catch (IllegalArgumentException | SQLException e) {
            afficherErreur(e.getMessage());
        }
    }

    private void supprimerArticle() {
        int id = getIdSelectionne();

        if (id <= 0) {
            afficherErreur("Veuillez selectionner un article.");
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment supprimer cet article ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            articleService.supprimerArticle(id);
            viderFormulaire();
            chargerArticles();
            afficherMessage("Article supprime avec succes.");
        } catch (IllegalArgumentException | SQLException e) {
            afficherErreur(e.getMessage());
        }
    }

    public void chargerArticles() {
        try {
            List<Article> articles = articleService.listerArticles();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            tableModel.setRowCount(0);

            for (Article article : articles) {
                String dateCreation = article.getDateCreation() == null
                        ? ""
                        : article.getDateCreation().format(formatter);

                tableModel.addRow(new Object[]{
                        article.getId(),
                        article.getNom(),
                        dateCreation
                });
            }
        } catch (SQLException e) {
            afficherErreur("Impossible de charger les articles : " + e.getMessage());
        }
    }

    private void remplirChampDepuisSelection() {
        int selectedRow = articleTable.getSelectedRow();

        if (selectedRow >= 0) {
            nomField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 1)));
        }
    }

    private void chargerMouvementsArticleSelectionne() {
        int articleId = getIdSelectionne();
        mouvementTableModel.setRowCount(0);

        if (articleId <= 0) {
            return;
        }

        try {
            List<MouvementStock> mouvements = mouvementService.listerMouvementsParArticle(articleId);

            for (MouvementStock mouvement : mouvements) {
                mouvementTableModel.addRow(new Object[]{
                        mouvement.getId(),
                        mouvement.getType(),
                        mouvement.getMethode() == null ? "" : mouvement.getMethode(),
                        mouvement.getQuantite(),
                        mouvement.getQuantiteRestante() == 0 ? "" : mouvement.getQuantiteRestante(),
                        mouvement.getPrixUnitaire(),
                        mouvement.getDateMouvement()
                });
            }
        } catch (SQLException e) {
            afficherErreur("Impossible de charger les mouvements de l'article : " + e.getMessage());
        }
    }

    private int getIdSelectionne() {
        int selectedRow = articleTable.getSelectedRow();

        if (selectedRow < 0) {
            return -1;
        }

        return (int) tableModel.getValueAt(selectedRow, 0);
    }

    private void viderFormulaire() {
        nomField.setText("");
        articleTable.clearSelection();
        mouvementTableModel.setRowCount(0);
    }

    private void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void afficherErreur(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
