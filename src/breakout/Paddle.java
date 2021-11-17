package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Paddle {
    public static final double WIDTH = 100;
    public static final double HEIGHT = 20;

    private Rectangle paddle;
    private Color color = Color.BLACK;

    public Paddle(CanvasWindow canvas) {
        paddle = new Rectangle(0, 0, WIDTH, HEIGHT);
        paddle.setCenter(canvas.getCenter().getX(), canvas.getHeight()*0.9);
        paddle.setFillColor(color);
        canvas.add(paddle);
    }

    /**
     * Moves the paddle left or right depending on where the mouse is
     * 
     * @param location Takes the location of the mouse
     * @param canvas The window in which the game is played
     */
    public void move(Point location, CanvasWindow canvas) {
        if (location.getX() - 50 > 0 && location.getX() + 50 < canvas.getWidth()) {
            paddle.setCenter(location.getX(), canvas.getHeight()*0.9);
        }
        canvas.add(paddle);
    }

    /**
     * @param canvas The window in which the game is played
     * @param ball The ball that moves
     * @return Whether ball has collided top or bottom of the paddle
     */
    public boolean ballCollidesTopBottom(CanvasWindow canvas, Ball ball) {
        if (canvas.getElementAt(ball.getCenterX(), ball.getCenterY() + Ball.RADIUS) == paddle) {
            return true;
        }
        else if (canvas.getElementAt(ball.getCenterX(), ball.getCenterY() - Ball.RADIUS) == paddle) {
            return true;
        }

        return false;
    }

    /**
     * @param canvas The window in which the game is played
     * @param ball The ball that moves
     * @return Whether ball has collided with sides of the paddle
     */
    public boolean ballCollidesSides(CanvasWindow canvas, Ball ball) {
        if (canvas.getElementAt(ball.getCenterX() + Ball.RADIUS, ball.getCenterY()) == paddle) {
            return true;
        }
        else if (canvas.getElementAt(ball.getCenterX() - Ball.RADIUS, ball.getCenterY()) == paddle) {
            return true;
        }

        return false;
    }

    public String toString() {
        return "Paddle{width=" + WIDTH + ", height=" + HEIGHT + "}";
    }
}
