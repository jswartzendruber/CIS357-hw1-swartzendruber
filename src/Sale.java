import java.util.Arrays;
import java.util.Comparator;

public class Sale {
    private final SingleItemSale[] singleItemSales = new SingleItemSale[10];

    /**
     * Sums item sales and returns the total
     *
     * @return The sale total (without tax)
     */
    public double subtotal() {
        double subtotal = 0;

        for (SingleItemSale singleItemSale : singleItemSales) {
            if (singleItemSale != null) {
                subtotal += singleItemSale.getItemQuantity() * singleItemSale.getItem().getUnitPrice();
            }
        }

        return subtotal;
    }

    /**
     * Adds 6% tax on the subtotal
     *
     * @return The sale total with tax (6%)
     */
    public double totalWithTax() {
        return subtotal() * 1.06;
    }

    /**
     * Adds an individual item sale to the overall sale
     *
     * @param singleItemSale The individual item sale to be added to the current sale
     */
    public void addSale(SingleItemSale singleItemSale) {
        int itemCode = singleItemSale.getItem().getItemCode() - 1; // Normalize item code to array
        if (singleItemSales[itemCode] == null) {
            // This item has not been sold yet
            singleItemSales[itemCode] = singleItemSale;
        } else {
            // This item has been sold, need to combine sales
            int alreadySoldAmount = singleItemSales[itemCode].getItemQuantity();
            SingleItemSale newSale = new SingleItemSale(singleItemSale.getItem(), alreadySoldAmount + singleItemSale.getItemQuantity());
            singleItemSales[itemCode] = newSale;
        }
    }

    /**
     * Sorts each item sale by item name alphabetically
     *
     * @return A new array of exact size with the ItemSales in sorted order, by name alphabetically.
     */
    public SingleItemSale[] sorted() {
        // Count item sales
        int count = 0;
        for (SingleItemSale singleItemSale : singleItemSales) {
            if (singleItemSale != null) {
                count++;
            }
        }

        // Create new array of item sales that will be sorted
        SingleItemSale[] sortedSingleItemSales = new SingleItemSale[count];
        int i = 0;
        for (SingleItemSale singleItemSale : singleItemSales) {
            if (singleItemSale != null) {
                sortedSingleItemSales[i] = singleItemSale;
                i++;
            }
        }

        // Sort by item name alphabetically
        Arrays.sort(sortedSingleItemSales, Comparator.comparing(singleItemSale -> singleItemSale.getItem().getItemName()));
        return sortedSingleItemSales;
    }

    /**
     * Displays a breakdown of the sale
     */
    public void printSale() {
        System.out.println("Items list:");
        SingleItemSale[] sortedSingleItemSale = sorted();
        for (SingleItemSale singleItemSale : sortedSingleItemSale) {
            System.out.printf("%5d %-13s $ %6.2f\n", singleItemSale.getItemQuantity(), singleItemSale.getItem().getItemName(), singleItemSale.getItem().getUnitPrice() * singleItemSale.getItemQuantity());
        }
        System.out.printf("%-20s$ %6.2f\n", "Subtotal", subtotal());
        double totalWithTax = totalWithTax();
        System.out.printf("Total with tax (6%%) $ %6.2f\n", totalWithTax);

        int digitsBeforeDecimal = 3 - String.format("%.0f", totalWithTax).length();
        String spacePadding = " ".repeat(digitsBeforeDecimal);
        double tendered = Utilities.getDoubleFromUser("Tendered Amount     $ " + spacePadding, totalWithTax);
        System.out.printf("%-20s$ %6.2f\n", "Change", tendered - totalWithTax);
    }
}