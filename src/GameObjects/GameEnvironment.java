// 207767542 Ofek Baribi
package GameObjects;

import BasicShapes.Line;
import BasicShapes.Point;
import BasicShapes.Rectangle;
import Mechanics.CollisionInfo;

import java.util.ArrayList;

/**
 * The GameEnvironment class represents the collection of collidables in a game.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidableList = new ArrayList<>();

    /**
     * Add a new collidable object to the game environment.
     *
     * @param c the collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * remove a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * Get the list of collidable objects in the environment.
     *
     * @return the list of collidable objects.
     */
    public ArrayList<Collidable> getCollidableList() {
        return new ArrayList<Collidable>(this.collidableList);
    }


    /**
     * Find the closest collision that is going to occur between a moving object and the collidables in the environment.
     *
     * @param trajectory the line representing the path of the moving object.
     * @return the information about the closest collision that is going to occur, or null if no collision will occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double lowestDistance = 0;
        boolean firstPoint = true;
        Point currentCollidableCollisionPoint;
        Point closestCollisionPoint = null;
        Collidable closestCollidable = null;
        for (Collidable c : getCollidableList()) {
            Rectangle currentCollidableRectangle = c.getCollisionRectangle();
            currentCollidableCollisionPoint = trajectory.closestIntersectionToStartOfLine(currentCollidableRectangle);
            if (currentCollidableCollisionPoint != null) {
                if (firstPoint) {
                    lowestDistance = currentCollidableCollisionPoint.distance(trajectory.getOriginalStart());
                    closestCollidable = c;
                    closestCollisionPoint = currentCollidableCollisionPoint;
                    firstPoint = false;
                }
                if (currentCollidableCollisionPoint.distance(trajectory.getOriginalStart()) < lowestDistance) {
                    lowestDistance = currentCollidableCollisionPoint.distance(trajectory.getOriginalStart());
                    closestCollidable = c;
                    closestCollisionPoint = currentCollidableCollisionPoint;
                }
            }
        }
        if (closestCollisionPoint != null && closestCollidable != null) {
            return new CollisionInfo(closestCollisionPoint, closestCollidable);
        } else {
            return null;
        }
    }
}