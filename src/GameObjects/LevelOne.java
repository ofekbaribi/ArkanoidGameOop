// 207767542 Ofek Baribi
package GameObjects;

import BasicShapes.Point;
import Graphics.LevelInformation;
import Graphics.LevelOneBackground;
import Mechanics.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The LevelOne class implements the LevelInformation interface
 * and represents the details of Level 1 in the game.
 * It provides the specific information and behavior for Level 1,
 * including the number of balls, initial ball velocities,
 * paddle speed and width, level name, background sprite,
 * blocks, and the number of blocks to be removed to clear the level.
 */
public class LevelOne implements LevelInformation {
    private List<Velocity> velocityList = new ArrayList<>();
    private List<Block> blocksList = new ArrayList<>();

    /**
     * Returns the number of balls in this Level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Returns a list of velocities representing the initial velocity of the ball in this Level.
     *
     * @return a list containing the initial ball velocity
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        this.velocityList.add(new Velocity(0, 5));
        return this.velocityList;
    }

    /**
     * Returns the speed of the paddle in this Level.
     *
     * @return the paddle speed
     */
    @Override
    public int paddleSpeed() {
        return 6;
    }

    /**
     * Returns the width of the paddle in this Level.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return 130;
    }

    /**
     * Returns the name of this Level.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns the background sprite for this Level.
     *
     * @return the background sprite for this Level
     */
    @Override
    public Sprite getBackground() {
        return new LevelOneBackground(levelName());
    }

    /**
     * Returns a list of blocks that make up this Level.
     *
     * @return a list containing the block for this Level
     */
    @Override
    public List<Block> blocks() {
        Block block = new Block(new Point(375, 200), 50, 25);
        block.setColor(Color.RED);
        this.blocksList.add(block);
        return this.blocksList;
    }

    /**
     * Returns the number of blocks to be removed in this Level.
     *
     * @return the number of blocks to be removed
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksList.size();
    }
}
