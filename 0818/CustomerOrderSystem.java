class Customer {
    private String id;
    private String name;

    Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    String getDetails() { return id + " " + name; }
}

class OrderItem {
    private String productName;
    private int price;
    private int quantity;

    OrderItem(String productName, int price, int quantity) {
        this.productName = productName;
        this.price = Math.max(0, price);
        this.quantity = Math.max(0, quantity);
    }

    int getSubtotal() { return price * quantity; }

    @Override
    public String toString() {
        return productName + " ($" + price + " x " + quantity + ")";
    }
}

class CustomerOrder {
    private String orderId;
    private Customer customer;
    private OrderItem[] items;

    CustomerOrder(String orderId, Customer customer, OrderItem[] items) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = (items == null) ? new OrderItem[0] : items;
    }

    int calculateTotal() {
        int total = 0;
        for (OrderItem item : items) {
            if (item != null) total += item.getSubtotal();
        }
        return total;
    }

    void printReceipt() {
        System.out.println("=== 訂單 " + orderId + " ===");
        System.out.println("顧客: " + customer.getDetails());
        System.out.println("購買品項 (" + items.length + " 項):");
        for (OrderItem item : items) {
            System.out.println(" - " + item + " 小計: $" + item.getSubtotal());
        }
        System.out.println("總金額: $" + calculateTotal());
        System.out.println("====================\n");
    }
}

public class CustomerOrderSystem {
    public static void main(String[] args) {
        Customer c1 = new Customer("C001", "Alice");
        
        OrderItem[] cart = {
            new OrderItem("無線鍵盤", 1200, 1),
            new OrderItem("AAA電池", 50, 4)
        };

        CustomerOrder order = new CustomerOrder("ORD-9988", c1, cart);
        order.printReceipt();
    }
}