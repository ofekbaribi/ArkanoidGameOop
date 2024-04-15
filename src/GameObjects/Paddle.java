// 207767542 Ofek Baribi
package GameObjects;

import BasicShapes.Line;
import BasicShapes.Point;
import BasicShapes.Rectangle;
import Graphics.GameLevel;
import Mechanics.Velocity;
import biuoop.DrawSurface;

import biuoop.KeyboardSensor;

/**
 * This class represents a paddle in our game.
 * A paddle is a sprite that can be moved by the user via the keyboard.
 * and also can collide with balls It implements the Sprite and Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private static final int NO_SIDE_COLLISION = 0;
    private static final int UPPER_SIDE_COLLISION = 1;
    private static final int BOTTOM_SIDE_COLLISION = 2;
    private static final int LEFT_SIDE_COLLISION = 3;
    private static final int RIGHT_SIDE_COLLISION = 4;
    private static final int DEFAULT_SPEED = 5;
    private static final int NUM_OF_REGIONS = 5;
    private static final int MAX_ANGLE = 60;
    private static final int MID_ANGLE = 30;
    private static final int MAX_PADDLE_LEFT = 25;
    private static final int MAX_PADDLE_RIGHT = 775;
    private int paddleSpeed;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRectangle;
    private double width;
    private double regionSize;
    private java.awt.Color color = Ball.getRandomColor();

    /**
     * Constructor function for creating a new Paddle object.
     *
     * @param width       the width of the paddle's rectangle
     * @param paddleSpeed the speed of the paddle's
     * @param keyboard    the KeyboardSensor used for detecting key presses
     */
    public Paddle(double width, int paddleSpeed, biuoop.KeyboardSensor keyboard) {
        int middleX = 400 - (int) (width / 2);
        this.paddleRectangle = new Rectangle(new Point(middleX, 570), width, 25);
        this.paddleSpeed = paddleSpeed;
        this.width = width;
        this.regionSize = width / NUM_OF_REGIONS;
        this.keyboard = keyboard;

    }

    /**
     * Moves the paddle left by 8 pixels.
     * The paddle's leftmost point cannot go below the x-coordinate 25.
     */
    public void moveLeft() {
        double oldX = getUpperLeftX();
        if (oldX <= MAX_PADDLE_LEFT) {
            return;
        } else {
            double newX = oldX - paddleSpeed;
            this.getCollisionRectangle().getUpperLeft().setX(newX);
        }
    }

    /**
     * Moves the paddle right by 8 pixels.
     * The paddle's rightmost point cannot go above the x-coordinate 775.
     */
    public void moveRight() {
        double oldX = getUpperLeftX();
        if (getUpperRightX() >= MAX_PADDLE_RIGHT) {
            return;
        } else {
            double newX = oldX + paddleSpeed;
            this.getCollisionRectangle().getUpperLeft().setX(newX);
        }
    }

    /**
     * Implementation of the timePassed method from the Sprite interface.
     * If the left or right arrow keys are pressed, the paddle moves accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Setter function for the KeyboardSensor.
     *
     * @param keyboard the new KeyboardSensor to be set
     */
    public void setKeyboard(biuoop.KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * Sets the color of the paddle.
     *
     * @param color the new color of the block.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * Getter function for the paddle color.
     *
     * @return the paddle's color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Getter function for the x value of the paddle's upper left point.
     *
     * @return the x value of the paddle's upper left point
     */
    public double getUpperLeftX() {
        return this.getCollisionRectangle().getUpperLeft().getX();
    }

    /**
     * Getter function for the x value of the upper left point of the second region of the paddle.
     *
     * @return the x value of the upper left point of the second region of the paddle.
     */
    public double getRegion2UpperLeftX() {
        return getUpperLeftX() + this.regionSize;
    }

    /**
     * Getter function for the x value of the upper left point of the third region of the paddle.
     *
     * @return the x value of the upper left point of the third region of the paddle.
     */
    public double getRegion3UpperLeftX() {
        return getUpperLeftX() + (2 * this.regionSize);
    }

    /**
     * Getter function for the x value of the upper left point of the fourth region of the paddle.
     *
     * @return the x value of the upper left point of the fourth region of the paddle.
     */
    public double getRegion4UpperLeftX() {
        return getUpperLeftX() + (3 * this.regionSize);
    }

    /**
     * Getter function for the x value of the upper left point of the fifth region of the paddle.
     *
     * @return the x value of the upper left point of the fifth region of the paddle.
     */
    public double getRegion5UpperLeftX() {
        return getUpperLeftX() + (4 * this.regionSize);
    }

    /**
     * Getter function for the x value of the paddle's upper right point.
     *
     * @return the x value of the paddle's upper right point
     */
    public double getUpperRightX() {
        return getUpperLeftX() + this.width;
    }

    /**
     * Implementation of the drawOn method from the Sprite interface.
     * Draw the paddle on a given surface.
     *
     * @param d the surface to draw the paddle on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * Get the rectangle of the paddle.
     *
     * @return the rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }

    /**
     * Set the new velocity of the ball according to which region of the paddle it collided with.
     *
     * @param regionsX        the x coordinate of the point where the ball hit the paddle
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision
     */
    public Velocity setByRegion(double regionsX, Velocity currentVelocity) {
        //region 1
        if (regionsX >= getUpperLeftX() && regionsX < getRegion2UpperLeftX()) {
            return Velocity.fromAngleAndSpeed(-MAX_ANGLE, DEFAULT_SPEED);
        }
        //region 2
        if (regionsX >= getRegion2UpperLeftX() && regionsX < getRegion3UpperLeftX()) {
            return Velocity.fromAngleAndSpeed(-MID_ANGLE, DEFAULT_SPEED);
        }
        //region 3
        if (regionsX >= getRegion3UpperLeftX() && regionsX < getRegion4UpperLeftX()) {
            return new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
        }
        //region 4
        if (regionsX >= getRegion4UpperLeftX() && regionsX < getRegion5UpperLeftX()) {
            return Velocity.fromAngleAndSpeed(MID_ANGLE, DEFAULT_SPEED);
        }
        //region 5
        if (regionsX >= getRegion5UpperLeftX() && regionsX <= getUpperRightX()) {
            return Velocity.fromAngleAndSpeed(MAX_ANGLE, DEFAULT_SPEED);
        }
        return new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
    }

    /**
     * Get the side of the paddle that a collision occurred on, if any.
     *
     * @param collisionLine the line of collision between the ball and the paddle
     * @return an integer representing the side of the paddle that was collided with
     */
    public int paddleCollisionSide(Line collisionLine) {
        Line upperWidth = this.paddleRectangle.getUpperWidth();
        Line bottomWidth = this.paddleRectangle.getBottomWidth();
        Line leftHeight = this.paddleRectangle.getLeftHeight();
        Line rightHeight = this.paddleRectangle.getRightHeight();
        if (collisionLine.isIntersecting(upperWidth)) {
            return UPPER_SIDE_COLLISION;
        }
        if (collisionLine.isIntersecting(bottomWidth)) {
            return BOTTOM_SIDE_COLLISION;
        }
        if (collisionLine.isIntersecting(leftHeight)) {
            return LEFT_SIDE_COLLISION;
        }
        if (collisionLine.isIntersecting(rightHeight)) {
            return RIGHT_SIDE_COLLISION;
        }
        return NO_SIDE_COLLISION;
    }

    /**
     * This method handles a collision between the paddle and another object.
     * It calculates the collision side and sets the new velocity accordingly.
     *
     * @param hitter          the ball that made the collision
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the object before the collision
     * @return the new velocity of the object after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double epsilon = 0.000001d;
        Point collisionPointStart = new Point(collisionPoint.getX() - epsilon, collisionPoint.getY() - epsilon);
        Point collisionPointEnd = new Point(collisionPoint.getX() + epsilon, collisionPoint.getY() + epsilon);
        Line collisionLine = new Line(collisionPointStart, collisionPointEnd);
        int collisionSide = paddleCollisionSide(collisionLine);
        Velocity newVelocity = currentVelocity;
        switch (collisionSide) {
            case UPPER_SIDE_COLLISION:
                double regionsX = collisionPoint.getX();
                newVelocity = setByRegion(regionsX, currentVelocity);
                break;
            case BOTTOM_SIDE_COLLISION:
                newVelocity = new Velocity(currentVelocity.getDx(), Math.abs(currentVelocity.getDy()));
                break;
            case LEFT_SIDE_COLLISION:
                newVelocity = new Velocity(-Math.abs(currentVelocity.getDx()), currentVelocity.getDy());
                break;
            case RIGHT_SIDE_COLLISION:
                newVelocity = new Velocity(Math.abs(currentVelocity.getDx()), currentVelocity.getDy());
                break;
            default:
                break;
        }
        return newVelocity;
    }

    /**
     * Adds the paddle to the game by adding it as both a collidable object and a sprite.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}