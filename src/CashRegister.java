// Homework 1: Sales Register Program
// Course: CIS357
// Due date: 7/5/22
// Name: Jason Swartzendruber
// GitHub: https://github.com/jswartzendruber/CIS357-hw1-swartzendruber
// Instructor: Il-Hyung Cho
// Program description: This program simulates a cash register. There are 10 items that can
//                      be bought, multiple items can be bought at once, and multiple sales
//                      can take place during the day. At the end of the day the total
//                      sale amount for the day is displayed.

/**
 * Driver class for the program, has some setup and both main loops.
 */
public class CashRegister {
    /**
     * Contains array of items that can be sold
     */
    public static final Item[] items = Utilities.loadStoreItems();

    public static void main(String[] args) {
        System.out.println("Welcome to Swartzendruber cash register system!");
        System.out.println();
        double totalSale = 0;

        // Prompt user for sales until they say no
        while (Utilities.promptAnotherSale()) {
            Sale sale = new Sale();
            System.out.println("--------------------");
            ItemSale itemSale = ItemSale.doSale();

            // Prompt user for items until they say -1
            while (itemSale != null) {
                sale.addSale(itemSale);
                itemSale = ItemSale.doSale();
            }

            totalSale += sale.subtotal();

            System.out.println("------------------------------");
            sale.printSale();
            System.out.println("------------------------------");
            System.out.println();
        }

        System.out.println();
        System.out.printf("The total sale for the day is $ %.2f\n", totalSale);
        System.out.println();
        System.out.println("Thanks for using POST system. Goodbye.");
    }
}
