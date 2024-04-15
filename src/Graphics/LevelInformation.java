// 207767542 Ofek Baribi
package Graphics;

import GameObjects.Block;
import GameObjects.Sprite;
import Mechanics.Velocity;

import java.util.List;

/**
 * The LevelInformation interface represents the information and behavior of a game level.
 * It provides methods to retrieve various details about the level such as the number of balls,
 * their initial velocities, paddle speed and width, level name, background sprite, blocks,
 * and the number of blocks that need to be removed to clear the level.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Returns a list of velocities representing the initial velocities of the balls.
     *
     * @return a list of initial ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Returns the name of the level, which will be displayed at the top of the screen.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Returns the sprite representing the background of the level.
     *
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * Returns a list of blocks that make up this level.
     * Each block contains its size, color, and location.
     *
     * @return a list of blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that need to be removed
     * before the level is considered to be "cleared".
     * This number should be less than or equal to the total number of blocks in the level.
     *
     * @return the number of blocks to be removed
     */
    int numberOfBlocksToRemove();
}
