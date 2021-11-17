package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Brick{
    private Rectangle brick;
    private Color color = Color.BLACK;

    public Brick(double x, double y, double width, double height, CanvasWindow canvas) {
        brick = new Rectangle(x, y, width, height);
        brick.setFillColor(color);
        canvas.add(brick);
    }

    /**
     * @param canvas The window in which the game is played
     * @param ball The ball that moves
     * @return Whether ball has collided top or bottom of the bricks
     */
    public boolean ballCollidesTopBottom(CanvasWindow canvas, Ball ball) {
        if (canvas.getElementAt(ball.getCenterX(), ball.getCenterY() + Ball.RADIUS) == brick) {
            return true;
        }
        else if (canvas.getElementAt(ball.getCenterX(), ball.getCenterY() - Ball.RADIUS) == brick) {
            return true;
        }

        return false;
    }

    /**
     * @param canvas The window in which the game is played
     * @param ball The ball that moves
     * @return Whether ball has collided with sides of the bricks
     */
    public boolean ballCollidesSides(CanvasWindow canvas, Ball ball) {
        if (canvas.getElementAt(ball.getCenterX() + Ball.RADIUS, ball.getCenterY()) == brick) {
            return true;
        }
        else if (canvas.getElementAt(ball.getCenterX() - Ball.RADIUS, ball.getCenterY()) == brick) {
            return true;
        }

        return false;
    }

    public void setBrickColor(Color color) {
        brick.setFillColor(color);
    }

    public void remove(CanvasWindow canvas) {
        canvas.remove(brick);
    }

    public String toString() {
        return "Brick{brick_details=" + brick.toString() + "}";
    }
}
