// 207767542 Ofek Baribi
package Graphics;

import BasicShapes.Point;
import GameObjects.Ball;
import GameObjects.Block;
import GameObjects.Collidable;
import GameObjects.GameEnvironment;
import GameObjects.Paddle;
import GameObjects.ScoreIndicator;
import GameObjects.Sprite;
import GameObjects.SpriteCollection;
import Listeners.BallAdder;
import Listeners.BallRemover;
import Listeners.BallRemoverBlock;
import Listeners.BlockRemover;
import Listeners.ScoreTrackingListener;
import Mechanics.Counter;
import Mechanics.Velocity;
import biuoop.DrawSurface;

import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The GameLevel class represents a level in the game.
 * It implements the Animation interface and provides
 * methods to manage the game elements, run the game loop, and handle user input during gameplay.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private SpriteCollection sprites = new SpriteCollection();

    private GameEnvironment environment = new GameEnvironment();
    private LevelInformation levelInformation;

    private Counter remainedBlocks = new Counter();

    private Counter availabeBalls = new Counter();

    private Counter score;

    /**
     * Creates a new GameLevel with the specified level information, animation runner,
     * keyboard sensor, and score counter.
     *
     * @param levelInformation the level information for the game level
     * @param ar               the animation runner
     * @param ks               the keyboard sensor
     * @param score            the score counter
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner ar, KeyboardSensor ks, Counter score) {
        this.levelInformation = levelInformation;
        this.runner = ar;
        this.keyboard = ks;
        this.score = score;
    }

    /**
     * Checks if the game level should stop.
     *
     * @return true if the game level should stop, false otherwise
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Performs one frame of the game level animation.
     *
     * @param d the DrawSurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }
        if (this.remainedBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        if (this.availabeBalls.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * Returns the number of remaining balls in the game level.
     *
     * @return the number of remaining balls
     */
    public int getRemainingBalls() {
        return this.availabeBalls.getValue();
    }

    /**
     * Adds a collidable to the game environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * remove a collidable from the game environment.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Adds a sprite to the SpriteCollection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove a sprite from the SpriteCollection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    /**
     * Get the game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Initializes the game level by setting up the game elements
     * according to the level information.
     */
    public void initialize() {
        //set listeners
        BlockRemover blockRemover = new BlockRemover(this, this.remainedBlocks);
        BallRemover ballRemover = new BallRemover(this, this.availabeBalls);
        BallRemoverBlock ballRemoverBlock = new BallRemoverBlock(this, this.availabeBalls);
        BallAdder ballAdder = new BallAdder(this, this.availabeBalls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        //set borders
        Block upperBound = new Block(new Point(0, 25), 800, 25);
        Block leftBound = new Block(new Point(0, 0), 25, 600);
        Block rightBound = new Block(new Point(775, 0), 25, 600);
        Block deathRegion = new Block(new Point(0, 600), 800, 25);
        deathRegion.addHitListener(ballRemover);
        upperBound.setColor(Color.GRAY);
        leftBound.setColor(Color.GRAY);
        rightBound.setColor(Color.GRAY);
        upperBound.addToGame(this);
        leftBound.addToGame(this);
        rightBound.addToGame(this);
        deathRegion.addToGame(this);
        //set background
        addSprite(levelInformation.getBackground());
        //set blocks
        for (Block b : this.levelInformation.blocks()) {
            b.addToGame(this);
            if (b.getColor() == Color.white) {
                b.addHitListener(ballAdder);
            } else if (b.getColor() == Color.BLACK) {
                b.addHitListener(ballRemoverBlock);
            } else {
                b.addHitListener(blockRemover);
                b.addHitListener(scoreTrackingListener);
                this.remainedBlocks.increase(1);
            }
        }
        //set balls
        for (Velocity v : levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(400, 500, 5, Color.WHITE);
            ball.setVelocity(v);
            ball.addToGame(this);
            this.availabeBalls.increase(1);
            ball.setGameEnvironment(environment);
        }
        //set paddle
        Paddle paddle = new Paddle(levelInformation.paddleWidth(), levelInformation.paddleSpeed(), this.keyboard);
        paddle.setColor(Color.white);
        paddle.addToGame(this);
        //set score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);
    }

    /**
     * Runs a count-down animation and then the game level.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);

    }
}