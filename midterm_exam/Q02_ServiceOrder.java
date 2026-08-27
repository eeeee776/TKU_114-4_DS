package midterm_exam;
public class Q02_ServiceOrder {
    public static class LineItem {
        private final String name;
        private final int unitPrice;
        private final int quantity;

        public LineItem(String name, int unitPrice, int quantity) {
            this.name = name;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

        public String getName() { return name; }
        public int getUnitPrice() { return unitPrice; }
        public int getQuantity() { return quantity; }
        public int subtotal() { return unitPrice * quantity; }
    }

    private final String orderId;
    private final java.util.List<LineItem> items;

    public Q02_ServiceOrder(String orderId) {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be null or blank");
        }
        this.orderId = orderId;
        this.items = new java.util.ArrayList<>();
    }

    public boolean addItem(String name, int unitPrice, int quantity) {
        if (name == null || name.trim().isEmpty() || unitPrice < 0 || quantity <= 0) {
            return false;
        }
        items.add(new LineItem(name, unitPrice, quantity));
        return true;
    }

    public int itemCount() {
        return items.size();
    }

    public int totalAmount() {
        int total = 0;
        for (LineItem item : items) {
            total += item.subtotal();
        }
        return total;
    }

    public String largestItemName() {
        if (items.isEmpty()) return "";
        LineItem largest = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            if (items.get(i).subtotal() > largest.subtotal()) {
                largest = items.get(i);
            }
        }
        return largest.getName();
    }

    public java.util.List<String> itemSummaries() {
        // composition-check 8C21-R
        java.util.List<String> summaries = new java.util.ArrayList<>();
        for (LineItem item : items) {
            summaries.add(item.getName() + ":" + item.subtotal());
        }
        return java.util.Collections.unmodifiableList(summaries);
    }
    // 將這段加在 Q02_ServiceOrder 類別的最下方
    public static void main(String[] args) {
        Q02_ServiceOrder order = new Q02_ServiceOrder("R-01");
        order.addItem("Inspection", 300, 1);
        order.addItem("Cable", 80, 4);
        order.addItem("Cleaning", 200, 1);
        System.out.println(order.itemCount());        // 預期: 3
        System.out.println(order.totalAmount());      // 預期: 820
        System.out.println(order.largestItemName());  // 預期: Cable
        System.out.println(order.itemSummaries());    // 預期: [Inspection:300, Cable:320, Cleaning:200]
    }
}
