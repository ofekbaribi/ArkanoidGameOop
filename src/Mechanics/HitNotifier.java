// 207767542 Ofek Baribi
package Mechanics;

import Listeners.HitListener;

/**
 * The HitNotifier interface represents an object that can notify hit events to registered listeners.
 */
public interface HitNotifier {
    /**
     * Adds a HitListener to the list of listeners for hit events.
     *
     * @param hl the HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners for hit events.
     *
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}