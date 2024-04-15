// 207767542 Ofek Baribi
package GameObjects;

import BasicShapes.Point;
import BasicShapes.Rectangle;
import Mechanics.Velocity;

/**
 * The Collidable interface represents an object that can be collided with.
 * It defines methods for get the collision shape of the object and handling collisions.
 */
public interface Collidable {
    /**
     * Returns the collision shape of the object as a Rectangle.
     *
     * @return the collision rectangle of the object
     */
    Rectangle getCollisionRectangle();


    /**
     * Notifies the object that a collision has occurred with it at the given collision point
     * with a given velocity. The method returns the new velocity expected after the hit
     *
     * @param hitter          the ball that made the collision
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the current velocity of the object before the collision
     * @return the new velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
