package PongGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    private static final int GAME_WIDTH = 1000;
    private static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    private static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    private static final int BALL_DIAMETER = 20;
    private static final int PADDLE_WIDTH = 25;
    private static final int PADDLE_HEIGHT = 100;

    private Thread gameThread;
    private Image image;
    private Graphics graphics;
    private Random random;
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;
    private Score score;


    public GamePanel() {
        newPaddle();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddle() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision() {
        // Check ball collision
        if (ball.y <= 0) {
            ball.setYDirection(-ball.getYVelocity());
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.getYVelocity());
        }

        // Bounces ball off paddles
        if (ball.intersects(paddle1)) {
            ball.setXVelocity(Math.abs(ball.getXVelocity()));
            ball.setXVelocity(ball.getXVelocity() + 1);     // Optional
            if (ball.getYVelocity() > 0) {
                ball.setYVelocity(ball.getYVelocity() + 1);
            } else {
                ball.setYVelocity(ball.getYVelocity() - 1);
            }
            ball.setXDirection(ball.getXVelocity());
            ball.setYDirection(ball.getYVelocity());
        }

        if (ball.intersects(paddle2)) {
            ball.setXVelocity(Math.abs(ball.getXVelocity()));
            ball.setXVelocity(ball.getXVelocity() + 1);     // Optional
            if (ball.getYVelocity() > 0) {
                ball.setYVelocity(ball.getYVelocity() + 1);
            } else {
                ball.setYVelocity(ball.getYVelocity() - 1);
            }
            ball.setXDirection(-ball.getXVelocity());
            ball.setYDirection(ball.getYVelocity());
        }


        // Stops paddles at window edges
        if (paddle1.y <= 0) {
            paddle1.y = 0;
        }
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        if (paddle2.y <= 0) {
            paddle2.y = 0;
        }
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        // Give a player 1 point and create new paddles and ball
        if (ball.x <= 0) {
            score.setPlayer2(score.getPlayer2() + 1);
            newPaddle();
            newBall();
        }

        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.setPlayer1(score.getPlayer1() + 1);
            newPaddle();
            newBall();
        }
    }

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
                checkCollision();
                repaint();
                delta--;
            }
        }
    }


    // Inner Class
    public class ActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
