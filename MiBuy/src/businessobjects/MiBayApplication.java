package businessobjects;

import java.io.*;
import java.util.Scanner;

/**
 * MiBayApplication
 * This class is the main class to run
 *
 * @LiangyuNie
 * @version1.2
 */
public class MiBayApplication {
    private CustomerArray customerArray;
    private ProductArray productArray;
    private DeliveryArray deliveryArray;
    private Scanner scanner;

    /**
     * Getter of the customerArray
     *
     * @return customerArray
     */
    public CustomerArray getCustomerArray() {
        return customerArray;
    }

    /**
     * Getter of the productArray
     *
     * @return productArray
     */
    public ProductArray getProductArray() {
        return productArray;
    }

    /**
     * Load the data from the file
     *
     * @return true iff the data is loaded
     */
    private boolean loadFromFile() {
        try {
            // try to open the file
            File file = new File("maindata.txt");
            if (!file.exists()) {
                file = new File("backupdata.txt");
                if (!file.exists()) {
                    System.out.println("No data was loaded");
                    return false;
                } else {
                    System.out.println("Data was loaded from backupdata.txt");
                }
            } else {
                System.out.println("Data was loaded from maindata.txt");
            }

            // read the customers and the products from the file
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            int n = Integer.parseInt(reader.readLine());
            String s;
            for (int i = 0; i < n; i++) {
                s = reader.readLine();
                String[] parts = s.split(",");
                customerArray.add(new Customer(parts[0], parts[1], new Address(parts[2], parts[3], parts[4], parts[5])));
                if (parts.length > 6) {
                    customerArray.get(i).addSecondAddress(new Address(parts[6], parts[7], parts[8], parts[9]));
                }
            }

            n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                s = reader.readLine();
                String[] parts = s.split(",");
                productArray.add(new Product(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2])));
            }
            reader.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Save the data to the file
     */
    private void saveToFile() {
        try {
            // open the files to write
            PrintWriter writer = new PrintWriter("data.txt");
            PrintWriter writer2 = new PrintWriter("backup.txt");
            writer.println(customerArray.length);
            // write the customers and the products
            for (int i = 0; i < customerArray.length; i++) {
                writer.println(customerArray.get(i).getDetails());
                writer2.println(customerArray.get(i).getDetails());
            }
            writer.println(productArray.length);
            for (int i = 0; i < productArray.length; i++) {
                writer.println(productArray.get(i).getDetails());
                writer2.println(productArray.get(i).getDetails());
            }
            writer.close();
            writer2.close();

            writer.close();
            writer2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Run the application
     */
    public void start() {
        String command = "";
        Menu menu = new Menu();
        while (true) {
            // print the main menu
            menu.mainMenu();
            // read the command
            command = scanner.nextLine().toUpperCase();
            // call methods according to the command
            switch (command) {
                case "AC": {
                    try {
                        Customer c = menu.addCustomer();
                        if (c != null) {
                            customerArray.add(c);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case "AP": {
                    try {
                        Product p = menu.addProduct();
                        if (p != null) {
                            productArray.add(p);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case "PP": {
                    try {
                        Delivery d = menu.prepareOrder(customerArray, productArray);
                        if (d != null) {
                            deliveryArray.add(d);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case "DA": {
                    menu.displayDeliveries(deliveryArray);
                    break;
                }

                case "DS": {
                    menu.deliverySearch(deliveryArray);
                    break;
                }

                case "SD": {
                    menu.seedData(this);
                    break;
                }

                case "EX": {
                    saveToFile();
                    return;
                }

                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }

    /**
     * Constructor
     */
    public MiBayApplication() {
        if (!loadFromFile()) {
            customerArray = new CustomerArray();
            productArray = new ProductArray();
            deliveryArray = new DeliveryArray();
        }
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        MiBayApplication app = new MiBayApplication();
        app.start();
    }
}
