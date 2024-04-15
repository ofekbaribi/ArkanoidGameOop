// 207767542 Ofek Baribi
package Listeners;

import GameObjects.Ball;
import GameObjects.Block;
import Mechanics.Counter;

/**
 * The ScoreTrackingListener class is responsible for tracking hits on blocks and updating the score accordingly.
 * It implements the HitListener interface to be notified of hit events.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener with the given score counter.
     *
     * @param scoreCounter the score counter to track
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called when a block is hit by a ball.
     * It removes the listener from the block being hit and increases the score by 5.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.currentScore.increase(5);
    }
}