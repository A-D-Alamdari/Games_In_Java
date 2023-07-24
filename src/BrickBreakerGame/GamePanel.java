package BrickBreakerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    private static final int GAME_WIDTH = 700;
    private static final int GAME_HEIGHT = 600;
    private static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    private static final int BALL_DIAMETER = 20;
    private static final int PADDLE_WIDTH = 20;
    private static final int PADDLE_HEIGHT = 100;

    private Thread gameThread;
    private Image image;
    private Graphics graphics;
    private Random random;
    private Paddle paddle;
    private Ball ball;
    private Score score;


    public GamePanel() {
        newPaddle();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new GamePanel.ActionListener());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

        public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddle() {
        paddle = new Paddle((GAME_WIDTH / 2) - (PADDLE_HEIGHT / 2), (GAME_HEIGHT) - (PADDLE_HEIGHT / 2), PADDLE_HEIGHT, PADDLE_WIDTH, 1);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move() {
        paddle.move();
        ball.move();
    }



    @Override
    public void run() {
        // Game Loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanoSec = 1000000000 / amountOfTicks;
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSec;
            lastTime = now;
            if (delta >= 1) {
                move();
//                checkCollision();
                repaint();
                delta--;
            }
        }
    }



    // Inner Class
    public class ActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }
    }
}
