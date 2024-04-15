package Graphics;

import biuoop.DrawSurface;

/**
 * The PauseScreen class represents an animation that displays a pause message on the screen.
 * It implements the Animation interface.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Constructs a PauseScreen object.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * Performs one frame of the animation.
     *
     * @param d the DrawSurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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
