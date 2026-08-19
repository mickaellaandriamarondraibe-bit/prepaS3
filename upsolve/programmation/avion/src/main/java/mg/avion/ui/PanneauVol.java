package mg.avion.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import mg.avion.config.ConfigurationSimulation;
import mg.avion.model.Avion;
import mg.avion.model.EtatSimulation;
import mg.avion.model.StatutSimulation;
import mg.avion.model.VueAvion;

public class PanneauVol extends JPanel {
    private final EtatSimulation etat;

    public PanneauVol(EtatSimulation etat) {
        this.etat = etat;
        setPreferredSize(new Dimension(780, 580));
    }

    @Override
    protected void paintComponent(Graphics graphiques) {
        super.paintComponent(graphiques);
        Graphics2D g = (Graphics2D) graphiques;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        dessinerFond(g);
        dessinerAvion(g);
        dessinerStatut(g);
    }

    private void dessinerFond(Graphics2D g) {
        Avion avion = etat.getAvion();
        if (avion.getVue() == VueAvion.PROFIL_GAUCHE) {
            g.setColor(new Color(255, 220, 120));
            g.fillOval(50, 45, 90, 90);
        } else if (avion.getVue() == VueAvion.ARRIERE) {
            g.setColor(new Color(95, 115, 130));
            int[] xs = {60, 170, 280, 420, 560, 710};
            int[] ys = {430, 230, 420, 210, 430, 250};
            g.fillPolygon(xs, ys, xs.length);
        } else {
            g.setColor(new Color(88, 160, 80));
            g.fillRect(0, getHeight() - 120, getWidth(), 120);
        }

        g.setColor(new Color(130, 200, 245));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(78, 155, 86));
        g.fillRect(0, getHeight() - 110, getWidth(), 110);

        if (avion.getVue() == VueAvion.PROFIL_GAUCHE) {
            g.setColor(new Color(255, 210, 80));
            g.fillOval(55, 50, 95, 95);
        } else if (avion.getVue() == VueAvion.ARRIERE) {
            g.setColor(new Color(90, 105, 120));
            int[] xs = {20, 160, 300, 450, 610, 760};
            int[] ys = {450, 220, 450, 200, 450, 250};
            g.fillPolygon(xs, ys, xs.length);
        }
    }

    private void dessinerAvion(Graphics2D g) {
        Avion avion = etat.getAvion();
        int x = calculerPositionVisuelleX(avion);
        int y = calculerAltitudeVisuelleY(avion);

        g.setColor(new Color(235, 238, 242));
        g.fillRoundRect(x, y, 170, 34, 18, 18);
        g.setColor(new Color(45, 70, 95));
        g.drawRoundRect(x, y, 170, 34, 18, 18);

        if (avion.getVue() == VueAvion.ARRIERE) {
            g.setColor(new Color(210, 215, 225));
            g.fillPolygon(new int[] {190, 95, 285}, new int[] {y + 10, y + 60, y + 60}, 3);
        } else {
            g.setColor(new Color(210, 215, 225));
            g.fillPolygon(new int[] {180, 250, 210}, new int[] {y + 12, y + 75, y + 25}, 3);
            g.fillPolygon(new int[] {245, 285, 248}, new int[] {y, y - 42, y + 12}, 3);
        }

        g.setColor(Color.BLACK);
        g.fillOval(x + 38, y + 31, 18, 18);
        g.fillOval(x + 118, y + 31, 18, 18);
    }

    private int calculerPositionVisuelleX(Avion avion) {
        double distanceTotale = Math.abs(ConfigurationSimulation.DISTANCE_INITIALE);
        double distanceParcourue = avion.getDistancePiste() - ConfigurationSimulation.DISTANCE_INITIALE;
        double progression = distanceParcourue / distanceTotale;
        progression = Math.max(0.0, Math.min(1.25, progression));

        int departX = 50;
        int arriveeX = getWidth() - 260;
        return departX + (int) ((arriveeX - departX) * progression);
    }

    private int calculerAltitudeVisuelleY(Avion avion) {
        double ratio = avion.getAltitude() / ConfigurationSimulation.ALTITUDE_INITIALE;
        int minY = 80;
        int maxY = getHeight() - 160;
        return maxY - (int) ((maxY - minY) * ratio);
    }

    private void dessinerStatut(Graphics2D g) {
        if (etat.getStatut() != StatutSimulation.CRASH && etat.getStatut() != StatutSimulation.REUSSI) {
            return;
        }

        g.setFont(new Font("SansSerif", Font.BOLD, 34));
        if (etat.getStatut() == StatutSimulation.CRASH) {
            g.setColor(new Color(180, 35, 35));
            g.drawString("CRASH", 40, 60);
        } else {
            g.setColor(new Color(20, 120, 70));
            g.drawString("ATTERRISSAGE REUSSI", 40, 60);
        }
    }
}
