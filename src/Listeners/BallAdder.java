// 207767542 Ofek Baribi
package Listeners;

import GameObjects.Ball;
import GameObjects.Block;
import Graphics.GameLevel;
import Mechanics.Counter;

import java.awt.Color;

/**
 * The BallAdder class is responsible for adding new balls to the game
 * and updating the remaining balls counter.
 * It implements the HitListener interface to be notified of hit events.
 */
public class BallAdder implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructs a new BallAdder.
     *
     * @param gameLevel           the game object
     * @param remainingBalls the counter for remaining balls
     */
    public BallAdder(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called when a block is hit by a ball.
     * The method adds new ball to the game and increases the remaining balls count.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        Ball ball = new Ball(beingHit.getCollisionRectangle().getUpperLeft(), 5, Color.WHITE);
        ball.setVelocity(3, 2);
        ball.addToGame(this.gameLevel);
        this.remainingBalls.increase(1);
        ball.setGameEnvironment(this.gameLevel.getEnvironment());
    }
}
