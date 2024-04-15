// 207767542 Ofek Baribi
package Listeners;

import GameObjects.Ball;
import GameObjects.Block;
import Graphics.GameLevel;
import Mechanics.Counter;

/**
 * The BallRemover class is responsible for removing balls from the game
 * and updating the remaining balls counter.
 * It implements the HitListener interface to be notified of hit events.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructs a new BallRemover.
     *
     * @param gameLevel           the game object
     * @param remainingBalls the counter for remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called when a block is hit by a ball.
     * The method removes the ball from the game and decreases the remaining balls count.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);
    }
}
