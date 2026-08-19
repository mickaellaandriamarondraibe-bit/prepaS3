package mg.avion.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class PanneauPiste extends JPanel {
    public PanneauPiste() {
        setPreferredSize(new Dimension(1000, 90));
    }

    @Override
    protected void paintComponent(Graphics graphiques) {
        super.paintComponent(graphiques);
        Graphics2D g = (Graphics2D) graphiques;

        g.setColor(new Color(55, 58, 62));
        g.fillRect(0, 12, getWidth(), 66);

        g.setColor(Color.WHITE);
        for (int x = 20; x < getWidth(); x += 80) {
            g.fillRect(x, 43, 42, 6);
        }
    }
}
