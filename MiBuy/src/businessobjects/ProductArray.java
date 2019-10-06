package businessobjects;

/**
 * ProductArray
 * <p>
 * This class represents an array of products
 *
 *@LiangyuNie
 * @version2.0
 */
public class ProductArray {
    private Product[] array;
    public int length;

    /**
     * Constructor
     */
    public ProductArray() {
        array = new Product[0];
        length = 0;
    }

    /**
     * Add a new product
     *
     * @param p a new product
     */
    public void add(Product p) {
        Product[] newArr = new Product[array.length + 1];
        System.arraycopy(array, 0, newArr, 0, array.length);
        newArr[array.length] = p;
        array = newArr;
        length++;
    }

    /**
     * Remove the product
     *
     * @param p the product to remove
     */
    public void remove(Product p) {
        int removeIdx = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == p) {
                removeIdx = i;
                break;
            }
        }
        if (removeIdx != -1) {
            Product[] newArr = new Product[array.length - 1];
            System.arraycopy(array, 0, newArr, 0, removeIdx);
            System.arraycopy(array, removeIdx + 1, newArr, removeIdx, array.length - removeIdx - 1);
            array = newArr;
            length--;
        }
    }

    /**
     * Get the i-th product
     *
     * @param i the index
     * @return the product
     */
    public Product get(int i) {
        return array[i];
    }
}
