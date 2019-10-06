package businessobjects;

import exceptions.AddThirdAddressException;
import exceptions.NullOrEmptyStringException;

/**
 * Customer
 * <p>
 * This class represents a customer
 *
 * @LiangyuNie
 * @version1.2
 */
public class Customer {
    private String firstName;
    private String lastName;
    private Address firstAddress;
    private Address secondAddress;

    /**
     * Constructor
     *
     * @param firstName    first name
     * @param lastName     last name
     * @param firstAddress address
     * @throws NullOrEmptyStringException
     */
    public Customer(String firstName, String lastName, Address firstAddress) throws NullOrEmptyStringException {
        if (firstName == null || firstName.equals("") || lastName == null || lastName.equals("")) {
            throw new NullOrEmptyStringException("The String parameter cannot be null or empty");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.firstAddress = firstAddress;
    }

    /**
     * Set a second address
     *
     * @param secondAddress a second address
     */
    public void setSecondAddress(Address secondAddress) {
        this.secondAddress = secondAddress;
    }

    /**
     * Add a new address
     *
     * @param secondAddress a second address
     * @throws AddThirdAddressException
     */
    public void addSecondAddress(Address secondAddress) throws AddThirdAddressException {
        if (this.secondAddress == null) {
            this.secondAddress = secondAddress;
        } else {
            throw new AddThirdAddressException("Attempt to add a third address");
        }
    }

    /**
     * Get the first address
     *
     * @return the first address
     */
    public Address getFirstAddress() {
        return firstAddress;
    }

    /**
     * Get the last name
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the full name
     *
     * @return the full name of the customer
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDetails() {
        String s = firstName + "," + lastName + "," + firstAddress.getDetails();
        if (secondAddress != null) {
            s += "," + secondAddress.getDetails();
        }
        return s;
    }

    public String toString() {
        return "Name:          " + firstAddress + " " + lastName + "\n"
                + firstAddress.toString();
    }
}
