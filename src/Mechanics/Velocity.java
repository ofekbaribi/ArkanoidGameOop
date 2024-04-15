// 207767542 Ofek Baribi
package Mechanics;

import BasicShapes.Point;

/**
 * The Velocity class Represents a two-dimensional vector that
 * is the change in position on the x and the y coordinates.
 * This class provides methods for constructing a Velocity object from angle and speed,
 * or from dx and dy and applying the velocity to a point.
 */
public class Velocity {
    // Fields
    private double dx;
    private double dy;

    /**
     * Constructs a new Velocity object with the specified horizontal and vertical components.
     *
     * @param dx the horizontal component of the velocity.
     * @param dy the vertical component of the velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructs a new Velocity object from the given angle and speed.
     *
     * @param angle the angle (in degrees) between the velocity vector and the top boundary.
     * @param speed the speed of the object.
     * @return a new Velocity object with the specified angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Convert angle to radians
        double angleInRadians = Math.toRadians(angle);
        //calculate with trigonometry
        double dx = speed * Math.sin(angleInRadians);
        double dy = -speed * Math.cos(angleInRadians);
        return new Velocity(dx, dy);
    }


    /**
     * Applies this velocity to the given point and returns a new point with the updated position.
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     *
     * @param p the Point to apply the velocity to.
     * @return a new Point with the updated position.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }

    // Access methods

    /**
     * Returns the horizontal component of this velocity.
     *
     * @return the horizontal component of this velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the vertical component of this velocity.
     *
     * @return the vertical component of this velocity.
     */
    public double getDy() {
        return this.dy;
    }
}