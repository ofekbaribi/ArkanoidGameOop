// 207767542 Ofek Baribi
package GameObjects;

import biuoop.DrawSurface;

/**
 * The Sprite interface represents a game object that can be drawn to the screen and updated over time.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     * This method should be called once per frame to update the state of the sprite.
     */
    void timePassed();
}