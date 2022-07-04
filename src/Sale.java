import java.util.Arrays;
import java.util.Comparator;

/**
 * This object exists to hold an array of item sales, and to display totals for the sale as well as a
 * sorted breakdown of each item.
 */
public class Sale {
    private final ItemSale[] itemSales = new ItemSale[10];

    /**
     * Sums item sales and returns the total
     *
     * @return The sale total (without tax)
     */
    public double subtotal() {
        double subtotal = 0;

        for (ItemSale itemSale : itemSales) {
            if (itemSale != null) {
                subtotal += itemSale.getItemQuantity() * itemSale.getUnitPrice();
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
     * @param itemSale The individual item sale to be added to the current sale
     */
    public void addSale(ItemSale itemSale) {
        int itemCode = itemSale.getItemCode() - 1; // Normalize item code to array
        if (itemSales[itemCode] == null) {
            // This item has not been sold yet
            itemSales[itemCode] = itemSale;
        } else {
            // This item has been sold, need to combine sales
            int alreadySoldAmount = itemSales[itemCode].getItemQuantity();
            ItemSale newSale = new ItemSale(itemSale, alreadySoldAmount + itemSale.getItemQuantity());
            itemSales[itemCode] = newSale;
        }
    }

    /**
     * Sorts each item sale by item name alphabetically
     *
     * @return A new array of exact size with the ItemSales in sorted order, by name alphabetically.
     */
    public ItemSale[] sorted() {
        // Count item sales
        int count = 0;
        for (ItemSale itemSale : itemSales) {
            if (itemSale != null) {
                count++;
            }
        }

        // Create new array of item sales that will be sorted
        ItemSale[] sortedItemSales = new ItemSale[count];
        int i = 0;
        for (ItemSale itemSale : itemSales) {
            if (itemSale != null) {
                sortedItemSales[i] = itemSale;
                i++;
            }
        }

        // Sort by item name alphabetically
        Arrays.sort(sortedItemSales, Comparator.comparing(Item::getItemName));
        return sortedItemSales;
    }

    /**
     * Displays a breakdown of the sale
     */
    public void printSale() {
        System.out.println("Items list:");
        ItemSale[] sortedItemSale = sorted();
        for (ItemSale itemSale : sortedItemSale) {
            System.out.printf("%5d %-13s $ %6.2f\n", itemSale.getItemQuantity(), itemSale.getItemName(), itemSale.getUnitPrice() * itemSale.getItemQuantity());
        }
        System.out.printf("%-20s$ %6.2f\n", "Subtotal", subtotal());
        double totalWithTax = totalWithTax();
        System.out.printf("Total with tax (6%%) $ %6.2f\n", totalWithTax);

        // magic padding
        int digitsBeforeDecimal = 3 - String.format("%.0f", totalWithTax).length();
        String spacePadding = " ".repeat(digitsBeforeDecimal);
        double tendered = Utilities.getDoubleFromUser("Tendered Amount     $ " + spacePadding, totalWithTax);
        System.out.printf("%-20s$ %6.2f\n", "Change", tendered - totalWithTax);
    }
}