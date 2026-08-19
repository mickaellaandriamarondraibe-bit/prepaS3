package mg.avion.ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.Timer;
import mg.avion.config.ConfigurationSimulation;
import mg.avion.service.ServiceSimulation;

public class FenetrePrincipale extends JFrame {
    private final ServiceSimulation serviceSimulation;
    private final PanneauVol panneauVol;
    private final PanneauTableauBord dashboardPanel;
    private final Timer timer;

    public FenetrePrincipale() {
        super("Simulation d'atterrissage");
        this.serviceSimulation = new ServiceSimulation();
        this.panneauVol = new PanneauVol(serviceSimulation.getEtat());
        this.dashboardPanel = new PanneauTableauBord(serviceSimulation, panneauVol);
        this.timer = new Timer((int) (ConfigurationSimulation.PAS_TEMPS_SECONDES * 1000), evenement -> rafraichirSimulation());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(panneauVol, BorderLayout.CENTER);
        add(dashboardPanel, BorderLayout.EAST);
        add(new PanneauPiste(), BorderLayout.SOUTH);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        timer.start();
    }

    private void rafraichirSimulation() {
        serviceSimulation.mettreAJour(ConfigurationSimulation.PAS_TEMPS_SECONDES);
        dashboardPanel.rafraichir();
        panneauVol.repaint();
    }
}
