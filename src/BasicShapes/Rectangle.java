// 207767542 Ofek Baribi
package BasicShapes;

import java.util.ArrayList;

/**
 * The Rectangle class represents a rectangle shape in 2D space, defined by its upper-left point,
 * width, and height. It provides methods for getting the dimensions of the rectangle and for
 * finding intersection points with a given line.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;


    /**
     * Creates a new rectangle with the given upper-left point, width, and height.
     *
     * @param upperLeft the upper-left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a (possibly empty) list of intersection points between the rectangle and the given line.
     *
     * @param line the line to check for intersection.
     * @return a list of intersection points with the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPoints = new ArrayList<>();
        Line[] rectangleSides = {getUpperWidth(), getBottomWidth(), getLeftHeight(), getRightHeight()};
        for (int i = 0; i < rectangleSides.length; i++) {
            Point intersectionPoint = line.intersectionWith(rectangleSides[i]);
            if (intersectionPoint != null) {
                intersectionPoints.add(intersectionPoint);
            }
        }
        return intersectionPoints;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper left point of the rectangle.
     *
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper right point of the rectangle.
     *
     * @return the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point((this.upperLeft.getX() + this.getWidth()), this.upperLeft.getY());
    }

    /**
     * Returns the bottom left point of the rectangle.
     *
     * @return the bottom left point of the rectangle.
     */
    public Point getBottomLeft() {
        return new Point(this.upperLeft.getX(), (this.upperLeft.getY() + this.getHeight()));
    }

    /**
     * Returns the bottom right point of the rectangle.
     *
     * @return the bottom right point of the rectangle.
     */
    public Point getBottomRight() {
        return new Point((this.upperLeft.getX() + this.getWidth()), (this.upperLeft.getY() + this.getHeight()));
    }

    /**
     * Returns the upper side line of the rectangle.
     *
     * @return the upper side line of the rectangle.
     */
    public Line getUpperWidth() {
        return new Line(this.getUpperLeft(), this.getUpperRight());
    }

    /**
     * Returns the bottom side line of the rectangle.
     *
     * @return the bottom side line of the rectangle.
     */
    public Line getBottomWidth() {
        return new Line(this.getBottomLeft(), this.getBottomRight());
    }

    /**
     * Returns the left side line of the rectangle.
     *
     * @return the left side line of the rectangle.
     */
    public Line getLeftHeight() {
        return new Line(this.getUpperLeft(), this.getBottomLeft());
    }

    /**
     * Returns the right side line of the rectangle.
     *
     * @return the right side line of the rectangle.
     */
    public Line getRightHeight() {
        return new Line(this.getUpperRight(), this.getBottomRight());
    }
}