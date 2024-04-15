// 207767542 Ofek Baribi
package Listeners;

import GameObjects.Ball;
import GameObjects.Block;

/**
 * The HitListener interface represents a listener for hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}