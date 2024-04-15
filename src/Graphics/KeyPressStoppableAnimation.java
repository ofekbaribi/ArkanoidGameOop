package Graphics;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The KeyPressStoppableAnimation class represents an animation that can be stopped by a key press.
 * It implements the Animation interface.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private KeyboardSensor keyboard;
    private boolean stop;
    private String key;
    private boolean isAlreadyPressed;

    /**
     * Constructs a KeyPressStoppableAnimation object with the given parameters.
     *
     * @param sensor    the keyboard sensor used to detect key presses
     * @param key       the key that can stop the animation
     * @param animation the animation to be stopped
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.stop = false;
        this.animation = animation;
        this.key = key;
        this.isAlreadyPressed = true;
    }

    /**
     * Performs one frame of the animation.
     *
     * @param d the DrawSurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        this.isAlreadyPressed = false;
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
