// 207767542 Ofek Baribi
package Mechanics;

/**
 * The Counter class represents a simple counter that can be increased or decreased.
 */
public class Counter {
    private int value = 0;

    /**
     * Increases the counter by the specified number.
     *
     * @param number the number to increase the counter by
     */

    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decreases the counter by the specified number.
     *
     * @param number the number to decrease the counter by
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the current value of the counter
     */
    public int getValue() {
        return this.value;
    }
}