// 207767542 Ofek Baribi
package GameObjects;

import BasicShapes.Point;
import Graphics.LevelInformation;
import Graphics.LevelTwoBackground;
import Mechanics.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The LevelTwo class implements the LevelInformation interface
 * and represents the details of Level 2 in the game.
 * It provides the specific information and behavior for Level 2,
 * including the number of balls, initial ball velocities,
 * paddle speed and width, level name, background sprite,
 * blocks, and the number of blocks to be removed to clear the level.
 */
public class LevelTwo implements LevelInformation {
    private List<Velocity> velocityList = new ArrayList<>();
    private List<Block> blocksList = new ArrayList<>();

    /**
     * Returns the number of balls in this Level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 13;
    }

    /**
     * Returns a list of velocities representing the initial velocity of the ball in this Level.
     *
     * @return a list containing the initial ball velocity
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < numberOfBalls(); i++) {
            this.velocityList.add(new Velocity(-4 + (i * 0.5), 6));
        }
        return this.velocityList;
    }

    /**
     * Returns the speed of the paddle in this Level.
     *
     * @return the paddle speed
     */
    @Override
    public int paddleSpeed() {
        return 7;
    }

    /**
     * Returns the width of the paddle in this Level.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return 600;
    }

    /**
     * Returns the name of this Level.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns the background sprite for this Level.
     *
     * @return the background sprite for this Level
     */
    @Override
    public Sprite getBackground() {
        return new LevelTwoBackground(levelName());
    }

    /**
     * Returns a list of blocks that make up this Level.
     *
     * @return a list containing the block for this Level
     */
    @Override
    public List<Block> blocks() {
        for (int i = 0, j = 25; i < 15; i++) {
            Block block = new Block(new Point(j, 260), 50, 25);
            block.setColor(new Color(255, 17 * i, 255 - (10 * i)));
            this.blocksList.add(block);
            j += 50;
        }
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

