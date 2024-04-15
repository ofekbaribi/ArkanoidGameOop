// 207767542 Ofek Baribi
package GameObjects;

import BasicShapes.Point;
import Graphics.LevelInformation;
import Graphics.LevelThreeBackground;
import Mechanics.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The LevelThree class implements the LevelInformation interface
 * and represents the details of Level 3 in the game.
 * It provides the specific information and behavior for Level 3,
 * including the number of balls, initial ball velocities,
 * paddle speed and width, level name, background sprite,
 * blocks, and the number of blocks to be removed to clear the level.
 */
public class LevelThree implements LevelInformation {
    private List<Velocity> velocityList = new ArrayList<>();
    private List<Block> blocksList = new ArrayList<>();

    /**
     * Returns the number of balls in this Level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    /**
     * Returns a list of velocities representing the initial velocity of the ball in this Level.
     *
     * @return a list containing the initial ball velocity
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < numberOfBalls(); i++) {
            this.velocityList.add(new Velocity(-1 + i, 5));
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
        return 8;
    }

    /**
     * Returns the width of the paddle in this Level.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return 300;
    }

    /**
     * Returns the name of this Level.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Pro Only";
    }

    /**
     * Returns the background sprite for this Level.
     *
     * @return the background sprite for this Level
     */
    @Override
    public Sprite getBackground() {
        return new LevelThreeBackground(levelName());
    }

    /**
     * Returns a list of blocks that make up this Level.
     *
     * @return a list containing the block for this Level
     */
    @Override
    public List<Block> blocks() {
        for (int i = 0, j = 175; i < 12; i++) {
            Block block = new Block(new Point(j, 190), 50, 25);
            block.setColor(Color.GRAY);
            this.blocksList.add(block);
            j += 50;
        }
        for (int i = 0, j = 225; i < 11; i++) {
            Block block = new Block(new Point(j, 215), 50, 25);
            block.setColor(Color.RED);
            this.blocksList.add(block);
            j += 50;
        }
        for (int i = 0, j = 275; i < 10; i++) {
            //special kind of block that will introduce
            //a new ball whenever it is being hit.
            if (j == 425) {
                Block ballAdderBlock = new Block(new Point(j, 240), 50, 25);
                ballAdderBlock.setColor(Color.white);
                this.blocksList.add(ballAdderBlock);
            } else {
                Block block = new Block(new Point(j, 240), 50, 25);
                block.setColor(Color.YELLOW);
                this.blocksList.add(block);
            }
            j += 50;
        }
        for (int i = 0, j = 325; i < 9; i++) {
            Block block = new Block(new Point(j, 265), 50, 25);
            block.setColor(Color.BLUE);
            this.blocksList.add(block);
            j += 50;
        }
        for (int i = 0, j = 375; i < 8; i++) {
            //special kind of block that will
            // remove balls that hit it.
            if (j == 525) {
                Block ballsRemoverBlock = new Block(new Point(j, 290), 50, 25);
                ballsRemoverBlock.setColor(Color.black);
                this.blocksList.add(ballsRemoverBlock);
            } else {
                Block block = new Block(new Point(j, 290), 50, 25);
                block.setColor(Color.PINK);
                this.blocksList.add(block);
            }
            j += 50;
        }
        for (int i = 0, j = 425; i < 7; i++) {
            Block block = new Block(new Point(j, 315), 50, 25);
            block.setColor(Color.GREEN);
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

