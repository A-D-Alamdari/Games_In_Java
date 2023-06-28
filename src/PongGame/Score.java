package PongGame;

import java.awt.*;

public class Score extends Rectangle {

    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    private int player1;
    private int player2;

    public Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.setFont(new Font("Noteworthy", Font.BOLD, 30));
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);

        g.drawString(String.valueOf(player1/10) + String.valueOf(player1 % 10), (GAME_WIDTH / 2) - 70, 40);
        g.drawString(String.valueOf(player2 / 10) + String.valueOf(player2 % 10), (GAME_WIDTH / 2) + 25, 40);
    }
}
