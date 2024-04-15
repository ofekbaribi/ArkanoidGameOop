// 207767542 Ofek Baribi
package Listeners;

import GameObjects.Ball;
import GameObjects.Block;
import Graphics.GameLevel;
import Mechanics.Counter;

/**
 * The BlockRemover class is responsible for removing blocks from the game
 * and updating the remaining blocks counter.
 * It implements the HitListener interface to be notified of hit events.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructs a new BlockRemover.
     *
     * @param gameLevel            the game object
     * @param remainingBlocks the counter for remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called when a block is hit by a ball.
     * The method removes the block from the game and decreases the remaining blocks count.
     * It also removes this listener from the block being removed from the game.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}