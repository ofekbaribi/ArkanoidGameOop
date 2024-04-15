package Graphics;

import GameObjects.Sprite;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The LevelTwoBackground class represents the background of level two.
 * It implements the Sprite interface.
 */
public class LevelTwoBackground implements Sprite {
    private String levelName;

    /**
     * Constructs a LevelTwoBackground object with the given level name.
     *
     * @param levelName the name of the level
     */
    public LevelTwoBackground(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Draws the background on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0, 204, 204));
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
        //draw clouds
        d.setColor(Color.white);
        d.fillOval(500, 145, 50, 50);
        d.fillOval(547, 150, 37, 37);
        d.fillOval(510, 115, 70, 70);
        d.fillOval(526, 140, 75, 75);
        d.fillOval(570, 160, 40, 40);
        d.fillOval(564, 120, 45, 45);

        d.fillOval(270, 95, 50, 50);
        d.fillOval(317, 100, 37, 37);
        d.fillOval(280, 65, 70, 70);
        d.fillOval(296, 90, 75, 75);
        d.fillOval(340, 110, 40, 40);
        d.fillOval(334, 70, 45, 45);
        //draw sun
        d.setColor(Color.orange);
        d.fillCircle(130, 125, 60);
        for (int i = 0; i < 58; i++) {
            d.drawLine(130, 125, 200 + (10 * i), 260);
        }
        for (int i = 0; i < 18; i++) {
            d.drawLine(130, 125, 200 - (10 * i), 260);
        }
        //draw inner sun
        d.setColor(new Color(255, 210, 0));
        d.fillCircle(130, 125, 40);
        d.setColor(new Color(255, 220, 0));
        d.fillCircle(130, 125, 20);
    }

    /**
     * Performs the time-based logic of the background (here does nothing).
     */
    @Override
    public void timePassed() {
    }

}

