package Graphics;

import Mechanics.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The GameFlow class manages the flow of the game,
 * including running multiple levels and handling game over conditions.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboard;
    private Counter score = new Counter();

    /**
     * Constructs a GameFlow object with the given AnimationRunner and KeyboardSensor.
     *
     * @param ar the AnimationRunner responsible for running animations
     * @param ks the KeyboardSensor for user input
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboard = ks;
    }

    /**
     * Runs the given list of levels in sequence.
     *
     * @param levels the list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboard, this.score);
            level.initialize();
            level.run();
            if (level.getRemainingBalls() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                        new EndScreen(false, score.getValue())));
                this.animationRunner.close();
                return;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                new EndScreen(true, score.getValue())));
        this.animationRunner.close();
    }
}