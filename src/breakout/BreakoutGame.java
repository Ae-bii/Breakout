package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;

/**
 * The game of Breakout.
 * @author Anupam Bhakta
 */
public class BreakoutGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;

    private Paddle paddle;
    private Ball ball;
    private BrickWall wall;
    private int count = 3;
    private GraphicsText displayCount;
    private GraphicsText gameOver;
    private GraphicsText gameWin;

    private CanvasWindow canvas;

    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        run();
    }

    /**
     * Runs the entire game by containing all the logic
     */
    private void run() {
        paddle = new Paddle(canvas);
        ball = new Ball(canvas);
        wall = new BrickWall(canvas);
        displayCount = new GraphicsText(String.valueOf(count) + " lives remain", canvas.getWidth()-100, canvas.getHeight()-20);
        gameOver = new GraphicsText("You lose!", canvas.getCenter().getX(), canvas.getCenter().getY());
        gameWin = new GraphicsText("You win!", canvas.getCenter().getX(), canvas.getCenter().getY());
        canvas.add(displayCount);

        animateGame();
    }

    /**
     * Allows for the the movement of the ball as well as the paddle
     */
    private void animateGame() {
        canvas.onMouseMove(event -> movePaddle(event.getPosition()));

        // Click to start game
        canvas.onMouseDown(even -> 
            canvas.animate(() -> {
                ball.move(canvas);
                
                brickHit();
                paddleHit();

                losingLives();

                if (wall.checkNoBricks()) {
                    canvas.add(gameWin);
                    canvas.draw();
                    canvas.pause(60000);
                }
            })
        );

    }

    /**
     * Checks whether ball collides with bottom and then subtracts lives
     */
    private void losingLives() {
        if (ball.collidesWithBottom(canvas) && count < 2) {
            canvas.add(gameOver);
            displayCount.setText("0 lives remain");
            canvas.draw();
        }

        if (ball.collidesWithBottom(canvas) && count >= 2) {
            ball.remove(canvas);
            ball = new Ball(canvas);
            displayCount.setText(Integer.toString(--count) + " lives remain");
            canvas.draw();
        }
    }

    /**
     * Tests whether a brick has been hit by the ball
     */
    private void brickHit() {
        if (wall.testHitTopBottom(canvas, ball)) {
            ball.setDy();
        }
        else if (wall.testHitSides(canvas, ball)) {
            ball.setDx();
        }
    }

    /**
     * Checks whether the paddle has been hit by the ball
     */
    private void paddleHit() {
        if (paddle.ballCollidesTopBottom(canvas, ball)) {
            ball.setDy();
        }
        else if (paddle.ballCollidesSides(canvas, ball)) {
            ball.setDx();
        }
    }

    /**
     * Allows for the movement of the paddle
     * 
     * @param location Defines where the mouse is
     */
    private void movePaddle(Point location) {
        paddle.move(location, canvas);
    }

    public static void main(String[] args){
        new BreakoutGame();
    }
}
