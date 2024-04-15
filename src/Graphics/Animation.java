// 207767542 Ofek Baribi
package Graphics;

import biuoop.DrawSurface;

/**
 * The Animation interface represents an animation in the game.
 * It provides methods to perform a single frame of the animation
 * and determine if the animation should stop.
 */
public interface Animation {
    /**
     * Performs one frame of the animation.
     *
     * @param d the DrawSurface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}