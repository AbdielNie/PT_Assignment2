package businessobjects;

import exceptions.NullOrEmptyStringException;

/**
 * Address
 * This class represents an address
 *
 * @LiangyuNie
 * @version2.0
 */
public class Address {
    private String number;
    private String name;
    private String suburb;
    private String postcode;

    /**
     * Constructor
     *
     * @param number   number
     * @param name     name
     * @param suburb   suburb
     * @param postcode postcode
     * @throws NullOrEmptyStringException
     */
    public Address(String number, String name, String suburb, String postcode) throws NullOrEmptyStringException {
        if (number == null || number.equals("") || name == null || name.equals("")
                || suburb == null || suburb.equals("") || postcode == null || postcode.equals("")) {
            throw new NullOrEmptyStringException("The String parameter cannot be null or empty");
        }
        this.number = number;
        this.name = name;
        this.suburb = suburb;
        this.postcode = postcode;
    }

    public String getDetails() {
        return number + "," + name + "," + suburb + "," + postcode;
    }

    public String toString() {
        return "Address:       " + number + " " + name + "\n"
                + "               " + suburb + " " + postcode;
    }
}
