// 207767542 Ofek Baribi
package BasicShapes;

import java.util.Random;

/**
 * The Point class represents a point in a 2D coordinate system.
 * It contains information about the x and y coordinates of the point, as well as methods to calculate
 * the distance to another point and check if two points are equal.
 */
public class Point {

    /**
     * The x coordinate of the point.
     */
    private double x;

    /**
     * The y coordinate of the point.
     */
    private double y;

    /**
     * Creates a new Point object with the given x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new random Point object in specific range.
     *
     * @param minX the minimum value of the x value
     * @param maxX the maximum value of the x value
     * @param minY the minimum value of the y value
     * @param maxY the maximum value of the y value
     * @return a new Velocity object with the specified angle and speed.
     */
    public static Point randomPoint(int minX, int maxX, int minY, int maxY) {
        Random rand = new Random();
        double x = minX + (maxX - minX) * rand.nextDouble();
        double y = minY + (maxY - minY) * rand.nextDouble();
        return new Point(x, y);
    }

    /**
     * Calculates and returns the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between the two points
     */
    public double distance(Point other) {
        double squareHypotenuse, perpendicularA, perpendicularB;
        perpendicularA = this.getX() - other.getX();
        perpendicularB = this.getY() - other.getY();
        squareHypotenuse = Math.pow(perpendicularA, 2) + Math.pow(perpendicularB, 2);
        return Math.sqrt(squareHypotenuse);
    }

    /**
     * Determines if this point is equal to another point.
     * Two points are considered equal if their x and y coordinates are within a small epsilon value of each other.
     *
     * @param other the other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double epsilon = 0.0001d;
        boolean resultX = Math.abs(this.getX() - other.getX()) < epsilon;
        boolean resultY = Math.abs(this.getY() - other.getY()) < epsilon;
        return (resultX && resultY);
    }

    /**
     * Set the x value of the point.
     *
     * @param x the x value to set for the point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returns the x coordinate of this point.
     *
     * @return the x coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate of this point.
     *
     * @return the y coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}


