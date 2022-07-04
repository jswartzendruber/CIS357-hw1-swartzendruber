/**
 * Extension of Item which additionally contains the quantity of the item sold.
 */
public class ItemSale extends Item {
    private final int itemQuantity;

    /**
     * @param item         Item that is being sold
     * @param itemQuantity Quantity of item that is being sold
     */
    public ItemSale(Item item, int itemQuantity) {
        super(item.getItemCode(), item.getItemName(), item.getUnitPrice());
        this.itemQuantity = itemQuantity;
    }

    /**
     * Re-prompts user until user provides a valid product code between 1 and 10 (inclusive)
     * or a product code of -1, which ends the current sale
     *
     * @return The product code of the item the user wants
     */
    private static int retrieveProductCode() {
        boolean retry = false;
        int productCode;

        do {
            productCode = Utilities.getAnInt(retry, "product code");
            retry = true;
        } while (!(productCode == -1 || (productCode >= 1 && productCode <= 10)));

        return productCode;
    }

    /**
     * Re-prompts user until a valid quantity of at least 1 is provided
     *
     * @return The quantity of the item the user wants
     */
    private static int retrieveQuantity() {
        boolean retry = false;
        int quantity;

        do {
            quantity = Utilities.getAnInt(retry, "quantity");
            retry = true;
        } while (quantity < 1);

        return quantity;
    }

    /**
     * Prompts user for item code and quantity.
     *
     * @return The item user chose with quantity. Returns null if user submitted
     * code -1 to stop adding items.
     */
    public static ItemSale doSale() {
        int productCode = retrieveProductCode();
        if (productCode == -1) {
            return null;   // Finish this sale
        } else {
            productCode--; // Normalize item code to array
        }

        System.out.printf("%18s: %s\n", "item name", CashRegister.items[productCode].getItemName());

        int quantity = retrieveQuantity();
        System.out.printf("%18s: $ %6.2f\n", "item total", CashRegister.items[productCode].getUnitPrice() * quantity);
        System.out.println();

        return new ItemSale(CashRegister.items[productCode], quantity);
    }

    public int getItemQuantity() {
        return itemQuantity;
    }
}