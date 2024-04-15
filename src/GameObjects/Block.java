// 207767542 Ofek Baribi
package GameObjects;

import java.util.ArrayList;

import BasicShapes.Line;
import BasicShapes.Point;
import BasicShapes.Rectangle;
import Graphics.GameLevel;
import Listeners.HitListener;
import Mechanics.HitNotifier;
import Mechanics.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * The Block class represents a block in our game.
 * A Block implements the Collidable and Sprite interfaces.
 * It has a rectangle shape and a color and also
 * can collide with balls It implements the Sprite and Collidable interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final int NO_SIDE_COLLISION = 0;
    private static final int UPPER_SIDE_COLLISION = 1;
    private static final int BOTTOM_SIDE_COLLISION = 2;
    private static final int LEFT_SIDE_COLLISION = 3;
    private static final int RIGHT_SIDE_COLLISION = 4;
    private Rectangle blockRectangle;

    private List<HitListener> hitListeners = new ArrayList<>();
    private java.awt.Color color = Ball.getRandomColor();

    /**
     * Constructor for creating a new Block.
     *
     * @param upperLeft the upper-left point of the block's rectangle.
     * @param width     the width of the block.
     * @param height    the height of the block.
     */
    public Block(Point upperLeft, double width, double height) {
        this.blockRectangle = new Rectangle(upperLeft, width, height);
    }

    /**
     * Returns the rectangle of the block.
     *
     * @return the rectangle of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.blockRectangle;
    }

    /**
     * Returns the color of the block.
     *
     * @return the color of the block.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the block.
     *
     * @param color the new color of the block.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * Implementation of the drawOn method from the Sprite interface.
     * Draw the block on a given surface.
     *
     * @param surface the surface to draw the block on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * Does nothing in the Block class implementation.
     * The method is used in the Sprite interface implementation.
     */
    public void timePassed() {
    }

    /**
     * Get the side of the block that a collision occurred on, if any.
     *
     * @param collisionLine the line of collision between the ball and the block
     * @return an integer representing the side of the block that was collided with
     */
    public int blockCollisionSide(Line collisionLine) {
        Line upperWidth = this.blockRectangle.getUpperWidth();
        Line bottomWidth = this.blockRectangle.getBottomWidth();
        Line leftHeight = this.blockRectangle.getLeftHeight();
        Line rightHeight = this.blockRectangle.getRightHeight();
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
     * This method handles a collision between the block and another object.
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
        int collisionSide = blockCollisionSide(collisionLine);
        Velocity newVelocity = currentVelocity;
        switch (collisionSide) {
            case UPPER_SIDE_COLLISION:
                newVelocity = new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
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
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * Notify the blocks hit listeners that collision has occurred with the hitter ball.
     *
     * @param hitter the ball that made the collision
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Adds the block to the game by adding it as both a collidable object and a sprite.
     *
     * @param gameLevel the game to add the paddle to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Removes the block from the game sprites and environment.
     *
     * @param gameLevel the game to remove the ball from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add a new hit listener object to the block hitListeners list.
     *
     * @param hl the HitListener object to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * remove hit listener object from the block hitListeners list.
     *
     * @param hl the HitListener object to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
