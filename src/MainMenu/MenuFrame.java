package MainMenu;

import javax.swing.*;

public class MenuFrame extends JFrame {

    private MenuPanel panel;

    public MenuFrame() {
        this.add(new MenuPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Games");
        this.pack();

        this.setVisible(true);
    }
}
