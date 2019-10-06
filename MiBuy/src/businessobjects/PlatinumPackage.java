package businessobjects;

import exceptions.InvalidMemberNumberException;
import exceptions.NullOrEmptyStringException;
import exceptions.NullParameterException;

/**
 * PlatinumPackage
 *
 * This class represents a platinum package
 * @LiangyuNie
 * @version2.0
 */
public class PlatinumPackage extends Package {
    private String memberNumber;

    /**
     * Constructor
     * @param c customer
     * @param p product
     * @param memberNumber member number
     * @throws InvalidMemberNumberException
     * @throws NullParameterException
     * @throws NullOrEmptyStringException
     */
    public PlatinumPackage(Customer c, Product p, String memberNumber) throws InvalidMemberNumberException, NullParameterException, NullOrEmptyStringException {
        super(c, p);
        if (memberNumber == null || memberNumber.equals("")) {
            throw new NullOrEmptyStringException("The String parameter cannot be null or empty");
        }

        if (!isValidMemberNumber(memberNumber)) {
            throw new InvalidMemberNumberException("The member number is invalid");
        }
        this.memberNumber = memberNumber;
    }

    /**
     * Check if the member number is valid
     * @param memberNumber member number
     * @return true iff the number is valid
     */
    private boolean isValidMemberNumber(String memberNumber) {
        if (memberNumber == null || memberNumber.length() != 10) {
            return false;
        }
        for (int i = 0; i < 10; i++) {
            if ((i % 2 == 0 && !Character.isLetter(memberNumber.charAt(i))) || (i % 2 == 1 && !Character.isDigit(memberNumber.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Update the member number
     * @param memberNumber a new member number
     * @throws InvalidMemberNumberException
     */
    public void updateMemberNumber(String memberNumber) throws InvalidMemberNumberException {
        if (!isValidMemberNumber(memberNumber)) {
            throw new InvalidMemberNumberException("The member number is invalid");
        }
        this.memberNumber = memberNumber;
    }

    /**
     * Getter of the member number
     * @return the member number
     */
    public String getMemberNumber() {
        return memberNumber;
    }

    public String getDetails() {
        return super.getDetails() + "," + memberNumber;
    }

    public String toString() {
        String s = String.format("%-15s", "Name:") + getCustomer().getFullName() + " (" + memberNumber + ")\n"
                + getCustomer().getFirstAddress().toString() + "\n"
                + "Products Ordered:\n"
                + getProducts().get(0).toString();
        for (int i = 1; i < getProducts().length; i++) {
            s += "\n\n" + getProducts().get(i).toString();
        }
        return s;
    }
}
