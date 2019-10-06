package businessobjects;

import exceptions.EmptyArrayException;

/**
 * DeliveryArray
 * <p>
 * This class represents an array of deliveries
 *
 * @LiangyuNie
 * @version2.0
 */
public class DeliveryArray {
    private Delivery[] array;
    public int length;

    /**
     * Constructor
     */
    public DeliveryArray() {
        array = new Delivery[0];
        length = 0;
    }

    /**
     * Add a new delivery
     *
     * @param d a new delivery
     */
    public void add(Delivery d) {
        Delivery[] newArr = new Delivery[array.length + 1];
        System.arraycopy(array, 0, newArr, 0, array.length);
        newArr[array.length] = d;
        array = newArr;
        length++;
    }

    /**
     * Remove the delivery
     *
     * @param d the delivery to remove
     * @throws EmptyArrayException
     */
    public void remove(Delivery d) throws EmptyArrayException {
        // find the index
        int removeIdx = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == d) {
                removeIdx = i;
                break;
            }
        }
        // remove the delivery
        if (removeIdx != -1) {
            Delivery[] newArr = new Delivery[array.length - 1];
            System.arraycopy(array, 0, newArr, 0, removeIdx);
            System.arraycopy(array, removeIdx + 1, newArr, removeIdx, array.length - removeIdx - 1);
            array = newArr;
            length--;
        }
    }

    /**
     * Get the delivery
     *
     * @param i the index
     * @return the delivery
     */
    public Delivery get(int i) {
        return array[i];
    }
}
