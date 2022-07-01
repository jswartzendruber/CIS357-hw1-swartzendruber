public class Item {
    private final int itemCode;
    private final String itemName;
    private final double unitPrice;

    /**
     * @param itemCode  Number code that corresponds to a shop item
     * @param itemName  Name of the shop item
     * @param unitPrice Price of the shop item
     */
    public Item(int itemCode, String itemName, double unitPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
    }

    public int getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
