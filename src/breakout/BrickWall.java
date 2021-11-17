package breakout;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import java.awt.Color;

public class BrickWall {
    private List<Brick> brickWall = new ArrayList<>();
    private Color[] color = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.GRAY};
    private double brickWidth = 30;
    private double brickHeight = 10;
    private double spacing = 5;

    public BrickWall(CanvasWindow canvas) {
        for (int positionX = 5; positionX < canvas.getWidth() - brickWidth; positionX+=brickWidth+spacing) {
            double positionY = 50;
            int colorCount = 0;
            for (int i = 0; i < 7; i++) {
                Brick brick = new Brick(positionX, positionY, brickWidth, brickHeight, canvas);
                brick.setBrickColor(color[colorCount++]);
                brickWall.add(brick);
                positionY += brickHeight + spacing;
            }
        }

    }

    /**
     * @param canvas The window in which the game is played
     * @param ball The ball that moves
     * @return Whether ball has collided top or bottom of the bricks
     */
    public boolean testHitTopBottom(CanvasWindow canvas, Ball ball) {
        for (Brick brick: brickWall) {
            if (brick.ballCollidesTopBottom(canvas, ball)) {
                brickWall.remove(brick);
                brick.remove(canvas);
                return true;
            }
        }
        return false;
    }

        /**
     * @param canvas The window in which the game is played
     * @param ball The ball that moves
     * @return Whether ball has collided with the left and right sides of the bricks
     */
    public boolean testHitSides(CanvasWindow canvas, Ball ball) {
        for (Brick brick: brickWall) {
            if (brick.ballCollidesSides(canvas, ball)) {
                brickWall.remove(brick);
                brick.remove(canvas);
                return true;
            }
        }

        return false;
    }

    /**
     * @return Whether there are bricks or not on screen
     */
    public boolean checkNoBricks() {
        return brickWall.isEmpty();
    }

    public String toString() {
        return "BrickWall{brickWidth=" + brickWidth + ", brickHeight=" + brickHeight + ", brickArray=" + brickWall.toString() + "}";
    }
}
