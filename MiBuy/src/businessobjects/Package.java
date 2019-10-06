package businessobjects;

import exceptions.EmptyArrayException;
import exceptions.NullParameterException;

/**
 * Package
 * <p>
 * This class represents a package
 *
 * @LiangyuNie
 * @version2.0
 */
public class Package {
    private Customer customer;
    private ProductArray products;

    /**
     * Constructor
     *
     * @param c customer
     * @param p product
     * @throws NullParameterException
     */
    public Package(Customer c, Product p) throws NullParameterException {
        if (c == null || p == null) {
            throw new NullParameterException("Parameter cannot be null");
        }
        this.customer = c;
        products = new ProductArray();
        products.add(p);
    }

    /**
     * Add a new product
     *
     * @param p a new product
     */
    public void addProduct(Product p) {
        products.add(p);
    }

    /**
     * Remove the product
     *
     * @param p the product to remove
     * @throws EmptyArrayException
     */
    public void removeProduct(Product p) throws EmptyArrayException {
        if (products.length == 1) {
            throw new EmptyArrayException("A package needs at least one product");
        } else {
            products.remove(p);
        }
    }

    /**
     * Getter of the customer
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Getter of the products
     *
     * @return the products
     */
    public ProductArray getProducts() {
        return products;
    }

    public String getDetails() {
        String s = customer.getDetails();
        for (int i = 0; i < products.length; i++) {
            s += "," + products.get(i).getDetails();
        }
        return s;
    }

    public String toString() {
        String s = customer.toString() + "\n"
                + "Products Ordered:\n"
                + products.get(0).toString();
        for (int i = 1; i < products.length; i++) {
            s += "\n\n" + products.get(i).toString();
        }
        return s;
    }
}

