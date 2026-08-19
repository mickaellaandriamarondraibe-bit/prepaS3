package mg.avion;

import javax.swing.SwingUtilities;
import mg.avion.ui.FenetrePrincipale;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FenetrePrincipale fenetre = new FenetrePrincipale();
            fenetre.setVisible(true);
        });
    }
}
