package businessobjects;

import exceptions.EmptyArrayException;

/**
 * CustomerArray
 * <p>
 * This class represents an array of customer
 *
 * @LiangyuNie
 * @version1.2
 */
public class CustomerArray {
    private Customer[] array;
    public int length;

    /**
     * Constructor
     */
    public CustomerArray() {
        array = new Customer[0];
        length = 0;
    }

    /**
     * Add a new customer
     *
     * @param c a new customer
     */
    public void add(Customer c) {
        Customer[] newArr = new Customer[array.length + 1];
        System.arraycopy(array, 0, newArr, 0, array.length);
        newArr[array.length] = c;
        array = newArr;
        length++;
    }

    /**
     * Remove the customer
     *
     * @param c the customer to remove
     * @throws EmptyArrayException
     */
    public void remove(Customer c) throws EmptyArrayException {
        // find the customer
        int removeIdx = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                removeIdx = i;
                break;
            }
        }
        if (removeIdx != -1) {
            Customer[] newArr = new Customer[array.length - 1];
            System.arraycopy(array, 0, newArr, 0, removeIdx);
            System.arraycopy(array, removeIdx + 1, newArr, removeIdx, array.length - removeIdx - 1);
            array = newArr;
            length--;
        }
    }

    /**
     * Get the customer
     *
     * @param i the index
     * @return the customer
     */
    public Customer get(int i) {
        return array[i];
    }
}
