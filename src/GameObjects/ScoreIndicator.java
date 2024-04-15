// 207767542 Ofek Baribi
package GameObjects;

import Graphics.GameLevel;
import Mechanics.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The ScoreIndicator class is responsible for displaying the current score on the game screen.
 * It implements the Sprite interface to be displayed and updated by the game.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructs a new ScoreIndicator with the given score counter.
     *
     * @param score the score counter
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Adds the ScoreIndicator to the specified game by adding it as a sprite.
     *
     * @param gameLevel the game to add the ScoreIndicator to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Draws the ScoreIndicator on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(365, 20, "Score: " + this.score.getValue(), 20);
    }

    /**
     * Does nothing in the ScoreIndicator class implementation.
     * The method is used in the Sprite interface implementation.
     */
    @Override
    public void timePassed() {

    }
}
