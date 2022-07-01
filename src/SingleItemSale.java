public class SingleItemSale {
    private final int itemQuantity;
    private final Item item;

    /**
     * @param item         Item that is being sold
     * @param itemQuantity Quantity of item that is being sold
     */
    public SingleItemSale(Item item, int itemQuantity) {
        this.itemQuantity = itemQuantity;
        this.item = item;
    }

    /**
     * @param retry True = show error message, False = hide error message
     * @return Int value from user
     */
    private static int getAnInt(boolean retry, String prompt) {
        // Display error if user inputted garbage or int outside of range
        if (retry) {
            System.out.println("!!! Invalid " + prompt);
            System.out.println();
        }

        System.out.printf("Enter %-14s", prompt + ":");
        int x = Integer.MIN_VALUE;
        if (CashRegister.scanner.hasNextInt()) {
            x = CashRegister.scanner.nextInt();
        } else {
            CashRegister.scanner.next(); // Skip garbage input
        }

        return x;
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
            productCode = getAnInt(retry, "product code");
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
            quantity = getAnInt(retry, "quantity");
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
    public static SingleItemSale doSale() {
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

        return new SingleItemSale(CashRegister.items[productCode], quantity);
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public Item getItem() {
        return item;
    }
}