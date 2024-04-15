package Graphics;

import GameObjects.Sprite;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The LevelThreeBackground class represents the background of level three.
 * It implements the Sprite interface.
 */
public class LevelThreeBackground implements Sprite {
    private String levelName;

    /**
     * Constructs a LevelThreeBackground object with the given level name.
     *
     * @param levelName the name of the level
     */
    public LevelThreeBackground(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Draws the background on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.green);
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
        // Draw Eiffel Tower
        d.setColor(new Color(47, 79, 79));
        drawEiffelTower(d, 50, 400, 150, 300);
    }

    /**
     * Performs the time-based logic of the background (here does nothing).
     */
    @Override
    public void timePassed() {

    }

    /**
     * Draws the Eiffel Tower on the given DrawSurface.
     *
     * @param d      the DrawSurface to draw on
     * @param x      the x-coordinate of the top-left corner of the tower's base
     * @param y      the y-coordinate of the top-left corner of the tower's base
     * @param width  the width of the tower's base
     * @param height the height of the tower's base
     */

    public static void drawEiffelTower(DrawSurface d, int x, int y, int width, int height) {
        // Draw the base
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.white);
        d.fillRectangle(x + 15, y + 15, width - 30, height);
        d.setColor(new Color(47, 79, 79));
        for (int i = 1; i < 5; i++) {
            d.fillRectangle(x + 15, y + (i * 40), width - 30, 15);
        }
        for (int i = 1; i < 4; i++) {
            d.fillRectangle(x + (i * 35), y, 15, height);
        }
        // Draw the first section
        int sectionWidth = width / 5;
        int sectionHeight = height / 10;
        int sectionX = x + (width - sectionWidth) / 2;
        int sectionY = y - sectionHeight;
        d.fillRectangle(sectionX, sectionY, sectionWidth, sectionHeight);

        // Draw the second section
        int section2Width = sectionWidth * 3 / 5;
        int section2Height = height / 2;
        int section2X = x + (width - section2Width) / 2;
        int section2Y = sectionY - section2Height;
        d.fillRectangle(section2X, section2Y, section2Width, section2Height);

        // Draw the top section
        int topWidth = section2Width * 3 / 5;
        int topHeight = height / 4;
        int topX = x + (width - topWidth) / 2;
        int topY = section2Y - topHeight;
        d.fillRectangle(topX, topY, topWidth, topHeight);

        d.setColor(Color.yellow);
        d.fillCircle(topX + 5, topY + 3, topWidth + 4);
        d.setColor(new Color(220, 20, 60));
        d.fillCircle(topX + 5, topY + 3, topWidth);
        d.setColor(Color.white);
        d.fillCircle(topX + 5, topY + 3, topWidth - 6);
    }
}

