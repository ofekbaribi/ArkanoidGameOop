// 207767542 Ofek Baribi

import GameObjects.LevelOne;
import GameObjects.LevelThree;
import GameObjects.LevelTwo;
import Graphics.AnimationRunner;
import Graphics.GameFlow;
import Graphics.LevelInformation;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;


/**
 * The Ass6Game class is responsible for starting and running the game based on command line arguments.
 * It creates a new GameFlow instance, initializes it, and runs the game levels specified by the arguments.
 * The available command line arguments are "1", "2", and "3", representing different levels of the game.
 * If no arguments are provided, all levels will be played in order.
 */
public class Ass6Game {
    /**
     * The main method of the class, used to start the game based on command line arguments.
     * Creates a new GameFlow instance, initializes it, and runs the game levels specified by the arguments.
     * The available command line arguments are "1", "2", and "3", representing different levels of the game.
     * If no arguments are provided, all levels will be played in order.
     *
     * @param args command line arguments representing the levels to be played
     */
    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner(60);
        KeyboardSensor keyboard = runner.getKeyboardSensor();
        List<LevelInformation> levels = new ArrayList<>();
        LevelOne l1 = new LevelOne();
        LevelTwo l2 = new LevelTwo();
        LevelThree l3 = new LevelThree();
        if (args.length > 0) {
            for (String s : args) {
                if (s.equals("1")) {
                    levels.add(l1);
                }
                if (s.equals("2")) {
                    levels.add(l2);
                }
                if (s.equals("3")) {
                    levels.add(l3);
                }
            }
        }
        if (levels.isEmpty()) {
            levels.add(l1);
            levels.add(l2);
            levels.add(l3);
        }
        GameFlow gameFlow = new GameFlow(runner, keyboard);
        gameFlow.runLevels(levels);
    }
}
