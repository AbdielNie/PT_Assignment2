package businessobjects;

import exceptions.InvalidValueException;
import exceptions.NullOrEmptyStringException;

/**
 * Product
 * <p>
 * This class represents a product
 *
 * @LiangyuNie
 * @version2.0
 */
public class Product {
    private String name;
    private double weight;
    private double cost;

    /**
     * Constructor
     *
     * @param name   name
     * @param weight weight
     * @param cost   cost
     * @throws NullOrEmptyStringException
     * @throws InvalidValueException
     */
    public Product(String name, double weight, double cost) throws NullOrEmptyStringException, InvalidValueException {
        if (name == null || name.equals("")) {
            throw new NullOrEmptyStringException("The String parameter cannot be null or empty");
        }
        if (weight <= 0) {
            throw new InvalidValueException("The weight must be positive");
        }
        if (cost <= 1.0) {
            throw new InvalidValueException("The cost must be bigger than 1.0");
        }
        this.name = name;
        this.weight = weight;
        this.cost = cost;
    }

    /**
     * Getter of the name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    public String getDetails() {
        return name + "," + weight + "," + cost;
    }

    public String toString() {
        return String.format("%-15s", "Name:") + name + "\n"
                + String.format("%-15s", "Weight:") + weight + "g\n"
                + String.format("%-15s%.2f", "Cost:", cost);
    }
}
