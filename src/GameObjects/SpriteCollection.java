// 207767542 Ofek Baribi
package GameObjects;

import java.util.ArrayList;

import biuoop.DrawSurface;

/**
 * The SpriteCollection class represents a collection of sprites in a game environment.
 * It manages a list of sprites and provides methods for adding new sprites to the collection,
 * calling the timePassed() method on all sprites, and calling the drawOn() method on all sprites
 * to draw them on a DrawSurface.
 */
public class SpriteCollection {
    /**
     * The list of sprites managed by this collection.
     */
    private ArrayList<Sprite> spriteList = new ArrayList<>();

    /**
     * Adds the given sprite to the collection.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Remove the given sprite from the collection.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }


    /**
     * Calls the timePassed() method on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> currentSpriteList = new ArrayList<>(this.spriteList);
        for (Sprite s : currentSpriteList) {
            s.timePassed();
        }
    }

    /**
     * Calls the drawOn(d) method on all sprites in the collection to draw them on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spriteList) {
            s.drawOn(d);
        }
    }
}