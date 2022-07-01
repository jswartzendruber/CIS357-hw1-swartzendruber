import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilities {

    /**
     * Loads store items into memory from the shop.txt file
     *
     * @return The array of items loaded from the input file
     */
    public static Item[] loadStoreItems() {
        Item[] items = new Item[10];
        try {
            Scanner shopItems = new Scanner(new File("shop.txt"));
            shopItems.nextLine(); // Skip header line

            // Loop through each line in file, and construct item objects
            for (int i = 0; i < 10; i++) {
                String fileLine = shopItems.nextLine();
                String[] lineChunks = fileLine.split("\t");

                int itemCode = Integer.parseInt(lineChunks[0]);
                String itemName = lineChunks[1];
                double itemPrice = Double.parseDouble(lineChunks[2]);

                items[i] = new Item(itemCode, itemName, itemPrice);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Shop items file not found.");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("Shop items file is invalid.");
            System.exit(1);
        }

        return items;
    }

    /**
     * Re-prompts the user until they return a "Y" or "N".
     *
     * @return True = user wants another sale
     * False = user does not want another sale
     */
    public static boolean promptAnotherSale() {
        while (true) {
            System.out.print("Beginning a new sale (Y/N) ");
            String input = CashRegister.scanner.next();

            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            }
        }
    }

    /**
     * @param prompt   Message displayed to the user
     * @param minValue Minimum value accepted from the user
     * @return The double value from the user
     */
    public static double getDoubleFromUser(String prompt, double minValue) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = CashRegister.scanner.nextDouble();

                // Re-prompt if value is below threshold.
                if (value >= minValue) {
                    return value;
                }
            } catch (InputMismatchException e) {
                CashRegister.scanner.nextLine(); // Clear bad input
            }
        }
    }
}
