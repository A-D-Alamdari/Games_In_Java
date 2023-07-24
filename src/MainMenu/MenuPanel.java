package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel implements ActionListener {
    private static final int SCREEN_DIMENSION = 600;
    private static final Dimension SCREEN_SIZE = new Dimension(SCREEN_DIMENSION, SCREEN_DIMENSION);

    public MenuPanel() {
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);
    }

    private class ActionListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
