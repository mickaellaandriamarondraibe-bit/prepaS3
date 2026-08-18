package ui;

import model.Article;
import model.MethodeSortie;
import model.MouvementStock;
import service.ArticleService;
import service.MouvementService;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MouvementPanel extends JPanel {
    private final ArticleService articleService;
    private final MouvementService mouvementService;
    private final JComboBox<Article> articleComboBox;
    private final JComboBox<String> typeComboBox;
    private final JComboBox<MethodeSortie> methodeComboBox;
    private final JTextField quantiteField;
    private final JTextField prixField;
    private final JTextField dateField;
    private final DefaultTableModel tableModel;
    private final JTable mouvementTable;
    private final JButton enregistrerButton;
    private final JButton actualiserButton;

    public MouvementPanel() {
        this.articleService = new ArticleService();
        this.mouvementService = new MouvementService();
        this.articleComboBox = new JComboBox<>();
        this.typeComboBox = new JComboBox<>(new String[]{MouvementStock.TYPE_ENTREE, MouvementStock.TYPE_SORTIE});
        this.methodeComboBox = new JComboBox<>(MethodeSortie.values());
        this.quantiteField = new JTextField(8);
        this.prixField = new JTextField(10);
        this.dateField = new JTextField(10);
        this.tableModel = creerModeleTable();
        this.mouvementTable = new JTable(tableModel);
        this.enregistrerButton = new JButton("Enregistrer");
        this.actualiserButton = new JButton("Actualiser");

        initialiserInterface();
        chargerArticles();
        chargerMouvements();
    }

    private void initialiserInterface() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        dateField.setText(LocalDate.now().toString());
        mouvementTable.getTableHeader().setReorderingAllowed(false);

        JPanel formulairePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formulairePanel.add(new JLabel("Article :"));
        formulairePanel.add(articleComboBox);
        formulairePanel.add(new JLabel("Type :"));
        formulairePanel.add(typeComboBox);
        formulairePanel.add(new JLabel("Quantite :"));
        formulairePanel.add(quantiteField);
        formulairePanel.add(new JLabel("Prix unitaire :"));
        formulairePanel.add(prixField);
        formulairePanel.add(new JLabel("Date :"));
        formulairePanel.add(dateField);
        formulairePanel.add(new JLabel("Methode :"));
        formulairePanel.add(methodeComboBox);
        formulairePanel.add(enregistrerButton);
        formulairePanel.add(actualiserButton);

        add(formulairePanel, BorderLayout.NORTH);
        add(new JScrollPane(mouvementTable), BorderLayout.CENTER);

        typeComboBox.addActionListener(event -> actualiserEtatMethode());
        enregistrerButton.addActionListener(event -> enregistrerMouvement());
        actualiserButton.addActionListener(event -> {
            chargerArticles();
            chargerMouvements();
        });

        actualiserEtatMethode();
    }

    private DefaultTableModel creerModeleTable() {
        return new DefaultTableModel(new Object[]{
                "ID", "Type", "Methode", "Article", "Quantite", "Reste", "Prix", "Date"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void enregistrerMouvement() {
        Article article = (Article) articleComboBox.getSelectedItem();
        String type = (String) typeComboBox.getSelectedItem();

        try {
            if (MouvementStock.TYPE_ENTREE.equals(type)) {
                mouvementService.enregistrerEntree(
                        article,
                        quantiteField.getText(),
                        prixField.getText(),
                        LocalDate.parse(dateField.getText().trim())
                );
                afficherMessage("Entree de stock enregistree avec succes.");
            } else {
                int quantiteSortie = mouvementService.enregistrerSortie(
                        article,
                        quantiteField.getText(),
                        prixField.getText(),
                        LocalDate.parse(dateField.getText().trim()),
                        (MethodeSortie) methodeComboBox.getSelectedItem()
                );
                afficherMessage("Sortie enregistree. Quantite sortie : " + quantiteSortie);
            }

            viderFormulaire();
            chargerMouvements();
        } catch (DateTimeParseException e) {
            afficherErreur("La date doit respecter le format yyyy-MM-dd.");
        } catch (IllegalArgumentException | SQLException e) {
            afficherErreur(e.getMessage());
        }
    }

    private void chargerArticles() {
        try {
            List<Article> articles = articleService.listerArticles();
            articleComboBox.removeAllItems();

            for (Article article : articles) {
                articleComboBox.addItem(article);
            }
        } catch (SQLException e) {
            afficherErreur("Impossible de charger les articles : " + e.getMessage());
        }
    }

    private void chargerMouvements() {
        try {
            List<MouvementStock> mouvements = mouvementService.listerMouvements();
            tableModel.setRowCount(0);

            for (MouvementStock mouvement : mouvements) {
                tableModel.addRow(new Object[]{
                        mouvement.getId(),
                        mouvement.getType(),
                        mouvement.getMethode() == null ? "" : mouvement.getMethode(),
                        mouvement.getArticle().getNom(),
                        mouvement.getQuantite() == 0 ? "" : mouvement.getQuantite(),
                        mouvement.getQuantiteRestante() == 0 ? "" : mouvement.getQuantiteRestante(),
                        mouvement.getPrixUnitaire(),
                        mouvement.getDateMouvement()
                });
            }
        } catch (SQLException e) {
            afficherErreur("Impossible de charger les mouvements : " + e.getMessage());
        }
    }

    private void actualiserEtatMethode() {
        boolean sortie = MouvementStock.TYPE_SORTIE.equals(typeComboBox.getSelectedItem());
        methodeComboBox.setEnabled(sortie);
    }

    private void viderFormulaire() {
        quantiteField.setText("");
        prixField.setText("");
        dateField.setText(LocalDate.now().toString());
    }

    private void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void afficherErreur(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
