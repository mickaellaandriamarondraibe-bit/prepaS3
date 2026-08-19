package mg.avion.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mg.avion.model.Avion;
import mg.avion.model.VueAvion;
import mg.avion.service.ServiceSimulation;

public class PanneauTableauBord extends JPanel {
    private final ServiceSimulation serviceSimulation;
    private final PanneauVol panneauVol;
    private final JLabel vitesseXLabel;
    private final JLabel vitesseYLabel;
    private final JLabel labelAltitude;
    private final JLabel labelDistance;
    private final JLabel freinageXLabel;
    private final JLabel freinageYLabel;
    private final JLabel labelTimer;
    private final JLabel labelStatut;

    public PanneauTableauBord(ServiceSimulation serviceSimulation, PanneauVol panneauVol) {
        this.serviceSimulation = serviceSimulation;
        this.panneauVol = panneauVol;
        this.vitesseXLabel = new JLabel();
        this.vitesseYLabel = new JLabel();
        this.labelAltitude = new JLabel();
        this.labelDistance = new JLabel();
        this.freinageXLabel = new JLabel();
        this.freinageYLabel = new JLabel();
        this.labelTimer = new JLabel();
        this.labelStatut = new JLabel();

        setLayout(new GridBagLayout());
        construire();
        rafraichir();
    }

    private void construire() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 10, 6, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        add(new JLabel("Tableau de bord"), c);
        ajouterLigne("Vitesse X", vitesseXLabel, ++c.gridy);
        ajouterLigne("Vitesse Y", vitesseYLabel, ++c.gridy);
        ajouterLigne("Altitude", labelAltitude, ++c.gridy);
        ajouterLigne("Distance piste", labelDistance, ++c.gridy);
        ajouterLigne("Freinage X", freinageXLabel, ++c.gridy);
        ajouterLigne("Freinage Y", freinageYLabel, ++c.gridy);
        ajouterLigne("Timer", labelTimer, ++c.gridy);
        ajouterLigne("Etat", labelStatut, ++c.gridy);

        ajouterBouton("Jouer", ++c.gridy, serviceSimulation::jouer);
        ajouterBouton("Pause", ++c.gridy, serviceSimulation::mettreEnPause);
        ajouterBouton("Reinitialiser", ++c.gridy, serviceSimulation::reinitialiser);
        ajouterBouton("Frein X +", ++c.gridy, serviceSimulation::augmenterFreinageX);
        ajouterBouton("Frein X -", ++c.gridy, serviceSimulation::diminuerFreinageX);
        ajouterBouton("Frein Y +", ++c.gridy, serviceSimulation::augmenterFreinageY);
        ajouterBouton("Frein Y -", ++c.gridy, serviceSimulation::diminuerFreinageY);
        ajouterBouton("Vue droite", ++c.gridy, () -> changerVue(VueAvion.PROFIL_DROITE));
        ajouterBouton("Vue gauche", ++c.gridy, () -> changerVue(VueAvion.PROFIL_GAUCHE));
        ajouterBouton("Vue arriere", ++c.gridy, () -> changerVue(VueAvion.ARRIERE));
    }

    private void ajouterLigne(String title, JLabel value, int y) {
        GridBagConstraints left = new GridBagConstraints();
        left.insets = new Insets(6, 10, 6, 10);
        left.fill = GridBagConstraints.HORIZONTAL;
        left.gridx = 0;
        left.gridy = y;
        add(new JLabel(title), left);

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(6, 10, 6, 10);
        right.fill = GridBagConstraints.HORIZONTAL;
        right.gridx = 1;
        right.gridy = y;
        add(value, right);
    }

    private void ajouterBouton(String text, int y, Runnable action) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 10, 6, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = y;
        c.gridwidth = 2;

        JButton bouton = new JButton(text);
        bouton.addActionListener(evenement -> {
            action.run();
            rafraichir();
            panneauVol.repaint();
        });
        add(bouton, c);
    }

    private void changerVue(VueAvion vue) {
        serviceSimulation.getEtat().getAvion().setVue(vue);
    }

    public void rafraichir() {
        Avion avion = serviceSimulation.getEtat().getAvion();
        vitesseXLabel.setText(formater(avion.getVitesseX()) + " m/s");
        vitesseYLabel.setText(formater(avion.getVitesseY()) + " m/s");
        labelAltitude.setText(formater(avion.getAltitude()) + " m");
        labelDistance.setText(formater(avion.getDistancePiste()) + " m");
        freinageXLabel.setText(formater(avion.getFreinageX()) + " m/s2");
        freinageYLabel.setText(formater(avion.getFreinageY()) + " m/s2");
        labelTimer.setText(formater(serviceSimulation.getEtat().getTempsEcouleSecondes()) + " s");
        labelStatut.setText(serviceSimulation.getEtat().getStatut().name());
    }

    private String formater(double valeur) {
        return String.format("%.1f", valeur);
    }
}
