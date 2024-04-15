// 207767542 Ofek Baribi
package GameObjects;

import BasicShapes.Line;
import BasicShapes.Point;
import Graphics.GameLevel;
import Mechanics.CollisionInfo;
import Mechanics.Velocity;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;


/**
 * The Ball class represents a ball object, which has a center point, radius, color and velocity.
 * The ball can move around in a given space and bounce off the edges.
 * It contains methods that move ball in a given space
 * and draw the ball with his color.
 */
public class Ball implements Sprite {
    // Fields
    private static final int DEFAULT_R = 30;
    private static final int MIN_R = 1;
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    // constructors

    /**
     * Constructor for a Ball object with a given center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        //check if the radius is positive
        if (r < MIN_R) {
            r = DEFAULT_R;
        }
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Constructor for a Ball object with a given x and y coordinates, radius, and color.
     *
     * @param x     the x coordinate of the center point of the ball
     * @param y     the y coordinate of the center point of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * Constructor for a Ball object with a given x and y coordinates, and radius.
     * The color of the ball is generated randomly.
     *
     * @param x the x coordinate of the center point of the ball
     * @param y the y coordinate of the center point of the ball
     * @param r the radius of the ball
     */
    public Ball(double x, double y, int r) {
        this(new Point(x, y), r, getRandomColor());
    }

    /**
     * Set the velocity of the ball with a given velocity.
     *
     * @param v the velocity to set for the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Set the velocity of the ball with a given dx and dy values.
     *
     * @param dx the dx value of the velocity to set for the ball
     * @param dy the dy value of the velocity to set for the ball
     */
    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));
    }

    /**
     * Sets the game environment.
     *
     * @param gameEnvironment the game environment to set
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }
    // accessors

    /**
     * Get the x coordinate of the center point of the ball.
     *
     * @return the x coordinate of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get the y coordinate of the center point of the ball.
     *
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get the size (radius) of the ball.
     *
     * @return the size (radius) of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Get the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Returns the velocity of the ball.
     * If the velocity has not been set, it sets a default velocity of 3 unit per step
     * in both the x and y directions and returns it.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        //if the ball have no velocity it will get default value
        if (velocity == null) {
            this.setVelocity(0, 0);
        }
        return this.velocity;
    }

    /**
     * Returns the game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Draws the ball on a given DrawSurface using its color, position, and radius.
     *
     * @param surface the DrawSurface on which to draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), r);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Returns the x value of the end point with the radius, based on the velocity.
     *
     * @return the x value of the end point with the radius
     */
    public int xEndPointWithRadius() {
        if (this.getVelocity().getDx() > 0) {
            return this.getSize();
        } else if (this.getVelocity().getDx() < 0) {
            return -(this.getSize());
        } else {
            return 0;
        }
    }

    /**
     * Returns the y coordinate of the end point with the radius, based on the velocity.
     *
     * @return the y coordinate of the end point with the radius
     */
    public int yEndPointWithRadius() {
        if (this.getVelocity().getDy() > 0) {
            return this.getSize();
        } else if (this.getVelocity().getDy() < 0) {
            return -(this.getSize());
        } else {
            return 0;
        }
    }

    /**
     * Move the ball one step according to its current velocity,
     * and handle collisions with collidables in the game environment.
     */
    public void moveOneStep() {
        double x1 = this.center.getX();
        double y1 = this.center.getY();
        double x2 = this.center.getX() + this.getVelocity().getDx() + this.xEndPointWithRadius();
        double y2 = this.center.getY() + this.getVelocity().getDy() + this.yEndPointWithRadius();
        Line trajectory = new Line(x1, y1, x2, y2);
        CollisionInfo closestCollision = gameEnvironment.getClosestCollision(trajectory);
        if (closestCollision != null) {
            Point collisionPoint = closestCollision.collisionPoint();
            this.setVelocity(closestCollision.collisionObject().hit(this, collisionPoint, this.velocity));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * create random color with random numbers and rgb.
     *
     * @return random color
     */
    public static java.awt.Color getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

    /**
     * Adds the ball to the game by adding it as a sprite.
     *
     * @param gameLevel the game to add the ball to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Removes the ball from the game sprites.
     *
     * @param gameLevel the game to remove the ball from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}