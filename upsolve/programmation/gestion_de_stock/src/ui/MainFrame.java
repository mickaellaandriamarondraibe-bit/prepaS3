package ui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Gestion de Stock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Articles", new ArticlePanel());
        tabbedPane.addTab("Mouvements", new MouvementPanel());

        setContentPane(tabbedPane);
    }
}
