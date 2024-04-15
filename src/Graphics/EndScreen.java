package Graphics;

import biuoop.DrawSurface;

/**
 * The EndScreen class represents an animation that displays the end screen of the game.
 * It implements the Animation interface.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private int score;

    private boolean winerr;

    /**
     * Constructs an EndScreen object with the given parameters.
     *
     * @param winner indicates whether the player is a winner or not
     * @param score  the final score of the game
     */
    public EndScreen(boolean winner, int score) {
        this.stop = false;
        this.winerr = winner;
        this.score = score;
    }

    /**
     * Draws one frame of the end screen animation on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the frame
     */
    public void doOneFrame(DrawSurface d) {
        if (!winerr) {
            d.drawText(200, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
        } else {
            d.drawText(200, d.getHeight() / 2, "You Win! Your score is " + score, 32);
        }
    }

    /**
     * Determines whether the animation should stop or continue.
     *
     * @return true if the animation should stop, false otherwise
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
