// 207767542 Ofek Baribi
package Listeners;

import GameObjects.Ball;
import GameObjects.Block;
import Graphics.GameLevel;
import Mechanics.Counter;

/**
 * The BallRemoverBlock class is responsible for removing balls from the game
 * after they hit special blocks and updating the remaining balls counter.
 * It implements the HitListener interface to be notified of hit events.
 */
public class BallRemoverBlock implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructs a new BallRemoverBlock.
     *
     * @param gameLevel           the game object
     * @param remainingBalls the counter for remaining balls
     */
    public BallRemoverBlock(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called when a block is hit by a ball.
     * The method removes the ball from the game and decreases the remaining balls count.
     * It also removes the block from the game
     * and removes this listener from the block that being removed from the game.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
    }
}
