package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import java.awt.Color;
import java.util.Random;

public class Ball {
    public static final int X_VELOCITY = 2;
    public static final int Y_VELOCITY = 2;
    public static final int RADIUS = 10;

    private Random random;
    private int dy = 3;
    private int dx;
    private Ellipse ball;
    private Color color = Color.RED;
    private double centerX;
    private double centerY;

    public Ball(CanvasWindow canvas) {
        // Starts the ball with a random direction
        random = new Random();
        int duplicate = random.nextInt(6) - 3;
        dx = (duplicate == 0) ? 1 : duplicate;

        ball = new Ellipse(0, 0, RADIUS*2, RADIUS*2);
        ball.setCenter(canvas.getCenter());
        ball.setFillColor(color);
        centerX = ball.getCenter().getX();
        centerY = ball.getCenter().getY();
        canvas.add(ball);
    }

    /**
     * Moves the ball while also checking for collisions with the sides of the canvas
     * 
     * @param canvas The window in which the game is played
     */
    public void move(CanvasWindow canvas) {
        centerX += X_VELOCITY * dx;
        centerY += Y_VELOCITY * dy;
        if (centerX - RADIUS > 0 && centerY - RADIUS > 0 && centerX + RADIUS < canvas.getWidth() && centerY + RADIUS < canvas.getHeight()) {
            ball.setCenter(centerX, centerY);
        }
        else if (collidesWithTop()) {
            setDy();
        }
        else if (collidesWithLeftOrRight(canvas)) {
            setDx();
        }
    }

    /**
     * @return Whether ball has collided with the top or not
     */
    private boolean collidesWithTop() {
        if (centerY + dy - RADIUS < 0) {
            return true;
        }
        return false;
    }

    /**
     * @return Whether ball has collided with the bottom or not
     */
    public boolean collidesWithBottom(CanvasWindow canvas) {
        if (centerY + dy + RADIUS > canvas.getHeight()) {
            return true;
        }
        return false;
    }

    /**
     * @return Whether ball has collided with the left and right sides
     */
    private boolean collidesWithLeftOrRight(CanvasWindow canvas) {
        if (centerX + dx + RADIUS > canvas.getWidth() || centerX + dx - RADIUS < 0) {
            return true;
        }
        return false;
    }

    public double getCenterX() {
        return centerX;
    }
    
    public double getCenterY() {
        return centerY;
    }

    public void setDy() {
        dy = -dy;
    }

    public void setDx() {
        dx = -dx;
    }

    public void remove(CanvasWindow canvas) {
        canvas.remove(ball);
    }

    public String toString() {
        return "Ball{radius=" + RADIUS + ", dy=" + dy + "dx" + dx + "}";
    }
}
