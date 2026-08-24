class Order {
    int orderId;
    String customer;
    int amount; // 金額 >= 0
    String status; // "PENDING", "SHIPPED", "CANCELLED"

    Order(int orderId, String customer, int amount) {
        this.orderId = orderId;
        this.customer = customer;
        this.amount = Math.max(0, amount);
        this.status = "PENDING";
    }
    @Override
    public String toString() { return String.format("Order#%d | %s | $%d | %s", orderId, customer, amount, status); }
}

class OrderNode {
    Order data; OrderNode left, right;
    OrderNode(Order data) { this.data = data; }
}

public class OrderManagementBst {
    private OrderNode root;

    public void add(Order order) {
        if (root == null) { root = new OrderNode(order); return; }
        OrderNode cur = root;
        while (true) {
            if (order.orderId == cur.data.orderId) return;
            if (order.orderId < cur.data.orderId) {
                if (cur.left == null) { cur.left = new OrderNode(order); return; }
                cur = cur.left;
            } else {
                if (cur.right == null) { cur.right = new OrderNode(order); return; }
                cur = cur.right;
            }
        }
    }

    public Order find(int orderId) {
        OrderNode cur = root;
        while (cur != null) {
            if (orderId == cur.data.orderId) return cur.data;
            cur = orderId < cur.data.orderId ? cur.left : cur.right;
        }
        return null;
    }

    public boolean updateStatus(int orderId, String newStatus) {
        Order order = find(orderId);
        if (order != null) { order.status = newStatus; return true; }
        return false;
    }

    public boolean remove(int orderId) {
        Order order = find(orderId);
        if (order == null) return false;
        if (!order.status.equals("CANCELLED")) {
            System.out.println("Remove failed: Order#" + orderId + " is not CANCELLED.");
            return false;
        }
        root = removeNode(root, orderId);
        return true;
    }

    private OrderNode removeNode(OrderNode node, int id) {
        if (id < node.data.orderId) node.left = removeNode(node.left, id);
        else if (id > node.data.orderId) node.right = removeNode(node.right, id);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            OrderNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = removeNode(node.right, successor.data.orderId);
        }
        return node;
    }

    // Traversal 計算總有效金額 (不包含已取消的)
    public long totalActiveAmount() {
        return calculateAmount(root);
    }
    private long calculateAmount(OrderNode node) {
        if (node == null) return 0;
        long sum = 0;
        if (!node.data.status.equals("CANCELLED")) {
            sum += node.data.amount;
        }
        return sum + calculateAmount(node.left) + calculateAmount(node.right);
    }

    public static void main(String[] args) {
        OrderManagementBst sys = new OrderManagementBst();
        sys.add(new Order(5001, "Alice", 1200));
        sys.add(new Order(5002, "Bob", 300));
        sys.add(new Order(5003, "Charlie", 550));

        sys.updateStatus(5002, "CANCELLED");
        sys.updateStatus(5001, "SHIPPED");

        System.out.println("Total Active Amount: $" + sys.totalActiveAmount()); // 1200 + 550 = 1750

        System.out.println("Try remove SHIPPED order:");
        sys.remove(5001); // 失敗

        System.out.println("Remove CANCELLED order:");
        System.out.println("Result: " + sys.remove(5002)); // 成功
    }
}