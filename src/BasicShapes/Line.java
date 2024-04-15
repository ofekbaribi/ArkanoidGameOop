// 207767542 Ofek Baribi
package BasicShapes;

/**
 * The Line class represents a line in a 2D coordinate system.
 * A line is defined by its start and end points.
 * It contains information about the start and end points, as well as methods to calculate
 * the intersection values with other lines (if there is)
 */
public class Line {
    // Fields
    private Point start;
    private Point end;
    private Point originalStart;

    /**
     * Constructs a new Line object with the given start and end points.
     * The start point will be the one with the lowest x value of the two points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        if (start.getX() < end.getX()) {
            this.start = start;
            this.end = end;
        }
        if (start.getX() > end.getX()) {
            this.start = end;
            this.end = start;
        }
        //in vertical line the start point will be the lowest y value.
        if (start.getX() == end.getX()) {
            if (start.getY() <= end.getY()) {
                this.start = start;
                this.end = end;
            } else {
                this.start = end;
                this.end = start;
            }
        }
        this.originalStart = start;
    }

    /**
     * Constructs a new Line object with the given coordinates for the start and end points.
     *
     * @param x1 the x coordinate of the start point
     * @param y1 the y coordinate of the start point
     * @param x2 the x coordinate of the end point
     * @param y2 the y coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Returns the length of the line using the distance method of two points.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start().distance(this.end());
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double middleX = (this.start().getX() + this.end().getX()) / 2;
        double middleY = (this.start().getY() + this.end().getY()) / 2;
        return (new Point(middleX, middleY));
    }

    /**
     * Returns the incline of the line.
     * If the line is vertical (deltaX equals 0), the method returns Integer.MAX_VALUE.
     * deltaX considered equals to 0 equal if x1 and x2 coordinates are within a small epsilon value of each other
     *
     * @return the incline of the line
     */
    public double incline() {
        double epsilon = 0.000001d;
        boolean isVertical = Math.abs(this.end().getX() - this.start().getX()) < epsilon;
        double deltaX = this.end().getX() - this.start().getX();
        double deltaY = this.end().getY() - this.start().getY();
        if (!isVertical) {
            return deltaY / deltaX;
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Build the linear equations of the line.
     *
     * @return the linear equations of the line
     */
    public double linearEquations() {
        //build linear equations
        double m1 = this.incline();
        double m1X1 = m1 * this.start().getX();
        return m1X1 - this.start().getY();
    }

    /**
     * Returns the x value of the intersection point between this line and another line
     * by building two linear equations and find their intersection Value of X.
     *
     * @param other the other line
     * @return the x value of the intersection point
     */
    public double intersectionValueOfX(Line other) {
        //build two linear equations and find their intersection Value of X
        double linearThis = this.linearEquations();
        double linearOther = other.linearEquations();
        return (linearThis - linearOther) / (this.incline() - other.incline());
    }

    /**
     * Return true if this line and other line are inclusion lines or not.
     * two lines considered inclusion lines if their equations are within a small epsilon value of each other.
     * and one of the lines start point is between the other start and end points.
     *
     * @param other the other line to compare to.
     * @return true if this line and the specified line are inclusion lines, false otherwise.
     */
    public boolean inclusionLines(Line other) {
        boolean isInclusion = false;
        //check if one of the lines start point is between the other start and end points.
        if (this.start().getX() <= other.end().getX() && this.start().getX() >= other.start().getX()
                || other.start().getX() <= this.end().getX() && other.start().getX() >= this.start().getX()) {
            //check if the two equations are equal
            double epsilon = 0.000001d;
            isInclusion = Math.abs(this.linearEquations() - other.linearEquations()) < epsilon;
        }
        return isInclusion;
    }


    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     * if the two lines parallels it will return null.
     * two lines considered parallels if their incline are within a small epsilon value of each other.
     * if one line point is the end of the other it will return the mutual point.
     *
     * @param other the other line to check for intersection with.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double m1 = this.incline();
        double m2 = other.incline();
        double epsilon = 0.000001d;
        boolean sameIncline = Math.abs(m1 - m2) < epsilon;
        //if one line point is the end of the other it will return the mutual point.
        if (this.start().equals(other.end())) {
            return this.start();
        }
        if (this.end().equals(other.start())) {
            return this.end();
        }
        //if the two lines parallels return null
        if (sameIncline) {
            return null;
        }
        double intersectionValueX = this.intersectionValueOfX(other);
        // calculate the intersection value of y
        double intersectionValueY = this.incline() * (intersectionValueX - this.start().getX()) + this.start().getY();
        //check if one of the lines is vertical
        boolean thisIsVertical = Math.abs(this.end().getX() - this.start().getX()) < epsilon;
        boolean otherIsVertical = Math.abs(other.end().getX() - other.start().getX()) < epsilon;
        //check if the intersection value of x when one of the line is vertical
        // is really between the values of the other line x values and if the
        // intersection value of y is really between the y value of the vertical one
        if (thisIsVertical
                && intersectionValueX - epsilon <= other.end().getX()
                && intersectionValueX + epsilon >= other.start().getX()
                && intersectionValueY + epsilon >= this.start().getY()
                && intersectionValueY - epsilon <= this.end().getY()) {
            return (new Point(intersectionValueX, intersectionValueY));
        }
        if (otherIsVertical
                && intersectionValueX - epsilon <= this.end().getX()
                && intersectionValueX + epsilon >= this.start().getX()
                && intersectionValueY + epsilon >= other.start().getY()
                && intersectionValueY - epsilon <= other.end().getY()) {
            return (new Point(intersectionValueX, intersectionValueY));
        }
        //check if the intersection value of x is really between the values of the two lines x values
        //and check if at least one of the lines is vertical
        if (intersectionValueX > this.end().getX() || intersectionValueX < this.start().getX()
                || intersectionValueX > other.end().getX() || intersectionValueX < other.start().getX()) {
            return null;
        }
        return (new Point(intersectionValueX, intersectionValueY));
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line to check for intersection with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point check = this.intersectionWith(other);
        //if the lines are equal it will return true although the intersection point will be null.
        if (this.equals(other)) {
            return true;
        }
        //if the lines are inclusion lines it will return true although the intersection point will be null.
        if (inclusionLines(other)) {
            return true;
        }
        if (check != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the lines are equal, false otherwise.
     *
     * @param other the other line to check equality with.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start().equals(other.start()) && this.end().equals(other.end()));
    }

    /**
     * Calculates and returns the closest intersection point of a given rectangle with the line of this object,
     * measured from the start point of the line.
     *
     * @param rect the rectangle to check intersection points with
     * @return the closest intersection point, measured from the start point of the line, or null if there are no
     * intersection points.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersectionPoints = rect.intersectionPoints(this);
        Point closest = null;
        double lowestDistance = 0;
        boolean firstPoint = true;
        for (Point p : intersectionPoints) {
            if (firstPoint) {
                lowestDistance = p.distance(this.getOriginalStart());
                closest = p;
                firstPoint = false;
            }
            if (p.distance(this.getOriginalStart()) < lowestDistance) {
                lowestDistance = p.distance(this.getOriginalStart());
                closest = p;
            }
        }
        return closest;
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns the original start point of the line.
     *
     * @return the original start point of the line.
     */
    public Point getOriginalStart() {
        return this.originalStart;
    }
}