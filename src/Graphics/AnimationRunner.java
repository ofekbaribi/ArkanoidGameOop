// 207767542 Ofek Baribi
package Graphics;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * The AnimationRunner class is responsible for running animations in the game.
 * It initializes a GUI and provides a method to run an animation at a specified frame rate.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper = new biuoop.Sleeper();

    /**
     * Creates a new AnimationRunner with the specified frame rate.
     *
     * @param framesPerSecond the desired frame rate
     */
    public AnimationRunner(int framesPerSecond) {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Returns the keyboard sensor associated with the GUI.
     *
     * @return the keyboard sensor
     */
    public biuoop.KeyboardSensor getKeyboardSensor() {
        return this.gui.getKeyboardSensor();
    }

    /**
     * Runs the specified animation.
     * The animation is run at the specified frame rate until it should stop.
     *
     * @param animation the animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Closes the GUI.
     */
    public void close() {
        this.gui.close();
    }
}
