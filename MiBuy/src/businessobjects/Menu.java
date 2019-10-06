package businessobjects;

import exceptions.InvalidMemberNumberException;
import exceptions.InvalidValueException;
import exceptions.NullOrEmptyStringException;
import exceptions.NullParameterException;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Menu
 * <p>
 * This class contains all input and output
 *
 * @LiangyuNie
 * @version1.2
 */
public class Menu {
    private static boolean hasSeededData;
    private static CustomerArray seedCustomers;
    private static ProductArray seedProducts;

    // static initialization
    static {
        try {
            seedCustomers = new CustomerArray();
            seedProducts = new ProductArray();

            seedCustomers.add(new Customer("Henry", "Cavill", new Address("83", "Dalgliesh Street", "South Yarra", "3141")));
            seedCustomers.add(new Customer("Whoopi", "Goldberg", new Address("57", "Elaine Court", "St Albans", "3021")));
            seedCustomers.add(new Customer("Harry", "Black", new Address("43", "Dalgliesh Street", "South Yarra", "3141")));
            seedCustomers.add(new Customer("Fred", "Smith", new Address("27", "Elaine Court", "St Albans", "3021")));

            seedProducts.add(new Product("Man of Steel DVD", 300.0, 24.99));
            seedProducts.add(new Product("Spider Man", 300.0, 24.99));
            seedProducts.add(new Product("Magic Mike", 336.0, 26.99));
            seedProducts.add(new Product("Harry Potter", 450.0, 39.99));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print the main menu
     */
    public void mainMenu() {
        System.out.println("*** MiBayApplication System Menu ***");
        System.out.printf("%-50s%s\n", "Add Customer", "AC");
        System.out.printf("%-50s%s\n", "Add Product", "AP");
        System.out.printf("%-50s%s\n", "Prepare Order", "PP");
        System.out.printf("%-50s%s\n", "Display ALL Deliveries (Sorted by Name)", "DA");
        System.out.printf("%-50s%s\n", "Delivery Search (display deliveries on date)", "DS");
        System.out.printf("%-50s%s\n", "Seed Data", "SD");
        System.out.printf("%-50s%s\n", "Exit Program", "EX");
        System.out.print("Enter selection: ");
    }

    /**
     * Add a new Customer
     *
     * @return a new Customer
     * @throws NullOrEmptyStringException
     */
    public Customer addCustomer() throws NullOrEmptyStringException {
        // input the details of the customer
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer details:");
        System.out.printf("%-25s", "Enter first name:");
        String firstName = scanner.nextLine();
        System.out.printf("%-25s", "Enter last name:");
        String lastName = scanner.nextLine();
        System.out.printf("%-25s", "Enter street number:");
        String streetNumber = scanner.nextLine();
        System.out.printf("%-25s", "Enter street name:");
        String streetName = scanner.nextLine();
        System.out.printf("%-25s", "Enter suburb:");
        String suburb = scanner.nextLine();
        System.out.printf("%-25s", "Enter postcode:");
        String postcode = scanner.nextLine();

        // create a new customer
        Customer customer = new Customer(firstName, lastName, new Address(streetNumber, streetName, suburb, postcode));
        System.out.println();
        System.out.println(customer.getFullName() + " was successfully added to the system.");
        return customer;
    }

    /**
     * Add a new product
     *
     * @return a new product
     * @throws NullOrEmptyStringException
     * @throws InvalidValueException
     * @throws InputMismatchException
     */
    public Product addProduct() throws NullOrEmptyStringException, InvalidValueException, InputMismatchException {
        // input the details of the product
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product details:");
        System.out.printf("%-25s", "Enter name:");
        String name = scanner.nextLine();
        System.out.printf("%-25s", "Enter weight:");
        double weight = scanner.nextDouble();
        scanner.nextLine();
        System.out.printf("%-25s", "Enter cost:");
        double cost = scanner.nextDouble();
        scanner.nextLine();

        // create a product and return
        Product product = new Product(name, weight, cost);
        System.out.println();
        System.out.println(product.getName() + " was successfully added to the system.");

        return product;
    }

    /**
     * Prepare an order
     *
     * @param customerArray customerArray
     * @param productArray  productArray
     * @return a delivery
     * @throws InputMismatchException
     * @throws NullParameterException
     * @throws InvalidValueException
     * @throws InvalidMemberNumberException
     * @throws NullOrEmptyStringException
     */
    public Delivery prepareOrder(CustomerArray customerArray, ProductArray productArray) throws InputMismatchException,
            NullParameterException, InvalidValueException, InvalidMemberNumberException, NullOrEmptyStringException {
        Scanner scanner = new Scanner(System.in);
        // check if there are customers and products
        if (customerArray.length == 0) {
            System.out.println("Sorry, no customers available");
            return null;
        }

        if (productArray.length == 0) {
            System.out.println("Sorry, no products available.");
            return null;
        }

        // choose a customer
        System.out.println("Please choose a customer from the list:");
        for (int i = 0; i < customerArray.length; i++) {
            System.out.println(String.valueOf(i + 1) + ". " + customerArray.get(i).getFullName());
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        Customer customer = customerArray.get(choice - 1);
        System.out.println();

        // choose a product
        ProductArray bought = new ProductArray();
        System.out.println("Please choose a product from the list:");
        for (int i = 0; i < productArray.length; i++) {
            System.out.println(String.valueOf(i + 1) + ". " + productArray.get(i).getName());
        }
        choice = scanner.nextInt();
        scanner.nextLine();
        bought.add(productArray.get(choice - 1));

        System.out.println();

        // enter the date of the delivery
        System.out.println("Please enter the delivery date:");
        System.out.print("Enter Day: ");
        int day = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Month: ");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        Date date = new Date(year - 1900, month - 1, day);

        System.out.println();
        System.out.println(productArray.get(choice - 1).getName() + " was successfully added to the order.");

        // ask if to add other products
        char c = choose("Would you like to add another product? (Y/N)", 'Y', 'N', scanner);
        System.out.println();

        // choose another product
        while (c == 'Y') {
            System.out.println("Please choose a product from the list:");
            for (int i = 0; i < productArray.length; i++) {
                System.out.println(String.valueOf(i + 1) + ". " + productArray.get(i).getName());
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            bought.add(productArray.get(choice - 1));
            System.out.println();
            System.out.println(productArray.get(choice - 1).getName() + " was successfully added to the order.");
            System.out.println();

            c = choose("Would you like to add another product? (Y/N)", 'Y', 'N', scanner);
        }

        // ask if it is a platinum package
        c = choose("Is this a Platinum Package? (Y/N)", 'Y', 'N', scanner);
        if (c == 'Y') {
            System.out.print("Please input your member number: ");
            String memberNumber = scanner.nextLine();
            System.out.println();
            // create a platinum package
            Package pkg = new PlatinumPackage(customer, bought.get(0), memberNumber);
            for (int i = 1; i < bought.length; i++) {
                pkg.addProduct(bought.get(i));
            }
            System.out.println("Package for " + customer.getFullName() + " was successfully prepared.");
            return new Delivery(pkg, date);
        } else {
            // create a normal package
            Package pkg = new Package(customer, bought.get(0));
            for (int i = 1; i < bought.length; i++) {
                pkg.addProduct(bought.get(i));
            }
            System.out.println("Package for " + customer.getFullName() + " was successfully prepared.");
            return new Delivery(pkg, date);
        }
    }

    // choose between a and b
    private char choose(String prompt, char a, char b, Scanner scanner) {
        while (true) {
            System.out.println(prompt);
            String s = scanner.nextLine();
            if (s.length() == 1) {
                if (s.toUpperCase().charAt(0) == a) {
                    return a;
                } else if (s.toUpperCase().charAt(0) == b) {
                    return b;
                }
            }
        }
    }

    /**
     * Display all deliveries
     *
     * @param deliveryArray the array of deliveries
     */
    public void displayDeliveries(DeliveryArray deliveryArray) {
        Scanner scanner = new Scanner(System.in);
        if (deliveryArray == null || deliveryArray.length == 0) {
            System.out.println("Sorry, no deliveries available");
            return;
        }

        // choose the sort of ascending or descending
        char c = choose("Enter sort order (A/D)", 'A', 'D', scanner);

        int[] printed = new int[deliveryArray.length];
        int printNum = 0;

        System.out.println("Summary of all packages:");
        String name = "";

        while (printNum < deliveryArray.length) {
            int idx = -1;
            for (int i = 0; i < deliveryArray.length; i++) {
                int id = -1;
                for (int j = 0; j < printNum; j++) {
                    if (printed[j] == i) {
                        id = j;
                        break;
                    }
                }
                if (id == -1) {
                    if (idx == -1) {
                        idx = i;
                        name = deliveryArray.get(i).getPkg().getCustomer().getLastName();
                    } else {
                        if (c == 'A' && deliveryArray.get(i).getPkg().getCustomer().getLastName().compareTo(name) < 0) {
                            idx = i;
                            name = deliveryArray.get(i).getPkg().getCustomer().getLastName();
                        }
                        if (c == 'D' && deliveryArray.get(i).getPkg().getCustomer().getLastName().compareTo(name) > 0) {
                            idx = i;
                            name = deliveryArray.get(i).getPkg().getCustomer().getLastName();
                        }
                    }
                }
            }

            System.out.println("------------------------------------------------");
            System.out.println(deliveryArray.get(idx).getPkg().toString());
            printed[printNum++] = idx;
        }
    }

    /**
     * Search the deliveries of a date
     *
     * @param deliveryArray the delivery array
     * @throws InputMismatchException
     */
    public void deliverySearch(DeliveryArray deliveryArray) throws InputMismatchException {
        // ask for a date
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%-25s", "Enter Day:");
        int day = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("%-25s", "Enter Month:");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("%-25s", "Enter Year:");
        int year = scanner.nextInt();
        scanner.nextLine();

        // find and print the delivery
        Date date = new Date(year - 1900, month - 1, day);
        for (int i = 0; i < deliveryArray.length; i++) {
            if (deliveryArray.get(i).getDate().equals(date)) {
                System.out.println("------------------------------------------------");
                System.out.println(deliveryArray.get(i).toString());
            }
        }
    }

    /**
     * Seed the data
     *
     * @param application the application
     */
    public void seedData(MiBayApplication application) {
        if (!hasSeededData) {
            for (int i = 0; i < 4; i++) {
                application.getCustomerArray().add(seedCustomers.get(i));
                application.getProductArray().add(seedProducts.get(i));
            }
            hasSeededData = true;
        } else {
            System.out.println("The collections has already been seeded");
        }
    }
}
