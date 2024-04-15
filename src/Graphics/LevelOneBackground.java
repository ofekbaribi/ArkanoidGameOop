package Graphics;

import GameObjects.Sprite;

import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Polygon;

/**
 * The LevelOneBackground class represents the background of level one.
 * It implements the Sprite interface.
 */
public class LevelOneBackground implements Sprite {
    private String levelName;

    /**
     * Constructs a LevelOneBackground object with the given level name.
     *
     * @param levelName the name of the level
     */
    public LevelOneBackground(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Draws the background on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(25, 25, 775, 575);
        //draw borders
        d.setColor(Color.gray);
        d.fillRectangle(0, 0, 800, 25);
        d.fillRectangle(0, 25, 800, 25);
        d.fillRectangle(0, 0, 25, 600);
        d.fillRectangle(775, 0, 25, 600);
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 25);
        d.setColor(Color.black);
        d.drawText(550, 20, "Level Name: " + levelName, 20);
        //draw star
        d.setColor(new Color(255, 105, 180));
        drawStar(d, 400, 215, 150, 11);
    }

    /**
     * Performs the time-based logic of the background (here does nothing).
     */
    @Override
    public void timePassed() {

    }

    /**
     * Draws a star on the given DrawSurface.
     *
     * @param d         the DrawSurface to draw on
     * @param x         the x-coordinate of the center of the star
     * @param y         the y-coordinate of the center of the star
     * @param radius    the radius of the star
     * @param numPoints the number of points of the star
     */
    public static void drawStar(DrawSurface d, int x, int y, int radius, int numPoints) {
        double angleIncrement = 2 * Math.PI / numPoints;
        int[] xPoints = new int[2 * numPoints];
        int[] yPoints = new int[2 * numPoints];

        for (int i = 0; i < 2 * numPoints; i++) {
            double angle = i * angleIncrement;
            int r = (i % 2 == 0) ? radius : radius / 2;
            xPoints[i] = x + (int) (r * Math.cos(angle));
            yPoints[i] = y + (int) (r * Math.sin(angle));
        }

        d.fillPolygon(new Polygon(xPoints, yPoints, 2 * numPoints));
    }
}

