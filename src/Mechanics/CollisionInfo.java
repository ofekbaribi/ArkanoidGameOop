// 207767542 Ofek Baribi
package Mechanics;

import BasicShapes.Point;
import GameObjects.Collidable;

/**
 * The CollisionInfo class represents information about a collision with collidable object.
 * It contains the collision point and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable object;

    /**
     * Constructs a CollisionInfo object with the given collision point and collidable object.
     *
     * @param collisionPoint the point at which the collision occurs.
     * @param object         the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable object) {
        this.collisionPoint = collisionPoint;
        this.object = object;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object.
     */

    public Collidable collisionObject() {
        return this.object;
    }
}