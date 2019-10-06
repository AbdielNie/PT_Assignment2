package businessobjects;

import java.util.Date;

/**
 * Delivery
 * <p>
 * This class represents a delivery
 *
 * @LiangyuNie
 * @version2.0
 */
public class Delivery {
    private Package pkg;
    private Date date;

    /**
     * Constructor
     *
     * @param pkg  the package
     * @param date the date
     */
    public Delivery(Package pkg, Date date) {
        this.pkg = pkg;
        this.date = date;
    }

    /**
     * Getter of the date
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter of the package
     *
     * @return package
     */
    public Package getPkg() {
        return pkg;
    }

    public String toString() {
        String s = String.format("%-15s", "Name:") + pkg.getCustomer().getFullName();
        if (pkg instanceof PlatinumPackage) {
            s += " (" + ((PlatinumPackage) pkg).getMemberNumber() + ")";
        }
        s += "\n";
        s += pkg.getCustomer().getFirstAddress().toString() + "\n"
                + String.format("%-15s%02d/%02d/%04d\n", "businessobjects.Delivery Date:", date.getDate(), date.getMonth() + 1, date.getYear() + 1900)
                + "Products Ordered:\n"
                + pkg.getProducts().get(0).toString();
        for (int i = 1; i < pkg.getProducts().length; i++) {
            s += "\n\n" + pkg.getProducts().get(i).toString();
        }
        return s;
    }
}
