package Graphics;

import GameObjects.SpriteCollection;
import biuoop.DrawSurface;


import java.awt.Color;

/**
 * The CountdownAnimation class represents an animation that displays a countdown on the screen.
 * It implements the Animation interface.
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private boolean stop;
    private double numOfSeconds;
    private int countFrom;
    private long milliSecForDigit;
    private boolean firstIterartion;

    /**
     * Constructs a CountdownAnimation object with the given parameters.
     *
     * @param numOfSeconds the total duration of the countdown in seconds
     * @param countFrom    the starting value of the countdown
     * @param gameScreen   the SpriteCollection representing the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.stop = false;
        this.firstIterartion = true;
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.milliSecForDigit = (long) (numOfSeconds * 1000) / countFrom;
    }

    /**
     * Performs one frame of the countdown animation,
     * drawing the current countdown value on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the frame
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        this.gameScreen.drawAllOn(d);
        if (firstIterartion) {
            this.firstIterartion = false;
            return;
        }
        if (countFrom >= 0) {
            d.setColor(Color.white);
            d.drawText(385, d.getHeight() / 5, "" + countFrom + "", 60);
            sleeper.sleepFor(milliSecForDigit);
            this.countFrom--;
        } else {
            this.stop = true;
        }
    }

    /**
     * Determines whether the animation should stop or continue.
     *
     * @return true if the animation should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
