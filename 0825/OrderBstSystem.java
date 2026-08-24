class Order {
    int orderId;
    String customerName;
    int amount;
    boolean isCancelled;

    Order(int orderId, String customerName, int amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.isCancelled = false;
    }
    
    @Override
    public String toString() {
        return "[" + orderId + "] " + customerName + " - $" + amount + (isCancelled ? " (已取消)" : "");
    }
}

class OrderNode {
    Order data; OrderNode left, right;
    OrderNode(Order data) { this.data = data; }
}


class OrderTree {
    private OrderNode root;

    boolean add(Order order) {
        if (order == null) return false;
        if (root == null) { root = new OrderNode(order); return true; }
        OrderNode current = root;
        while (true) {
            if (order.orderId == current.data.orderId) return false;
            if (order.orderId < current.data.orderId) {
                if (current.left == null) { current.left = new OrderNode(order); return true; }
                current = current.left;
            } else {
                if (current.right == null) { current.right = new OrderNode(order); return true; }
                current = current.right;
            }
        }
    }

    Order find(int orderId) {
        OrderNode current = root;
        while (current != null) {
            if (orderId == current.data.orderId) return current.data;
            current = orderId < current.data.orderId ? current.left : current.right;
        }
        return null;
    }

    boolean cancelOrder(int orderId) {
        Order order = find(orderId);
        if (order != null && !order.isCancelled) {
            order.isCancelled = true;
            return true;
        }
        return false;
    }

    boolean updateAmount(int orderId, int newAmount) {
        Order order = find(orderId);
        if (order != null && !order.isCancelled && newAmount >= 0) {
            order.amount = newAmount;
            return true;
        }
        return false;
    }

    void rangeReport(int minId, int maxId) {
        System.out.println("--- 訂單範圍 " + minId + " 到 " + maxId + " ---");
        rangeReport(root, minId, maxId);
        System.out.println("-------------------------");
    }

    private void rangeReport(OrderNode node, int minId, int maxId) {
        if (node == null) return;
        if (node.data.orderId > minId) rangeReport(node.left, minId, maxId);
        if (node.data.orderId >= minId && node.data.orderId <= maxId) {
            System.out.println(node.data);
        }
        if (node.data.orderId < maxId) rangeReport(node.right, minId, maxId);
    }

    void printSummary() {
        int[] stats = new int[3]; 
        calculateSummary(root, stats);
        System.out.println("營運摘要: 有效訂單 " + stats[0] + " 筆，已取消 " + stats[1] + " 筆，總營收 $" + stats[2]);
    }

    private void calculateSummary(OrderNode node, int[] stats) {
        if (node == null) return;
        calculateSummary(node.left, stats);
        
        if (node.data.isCancelled) {
            stats[1]++;
        } else {
            stats[0]++;
            stats[2] += node.data.amount;
        }
        
        calculateSummary(node.right, stats);
    }
}

public class OrderBstSystem {
    public static void main(String[] args) {
        OrderTree sys = new OrderTree();
        sys.add(new Order(1005, "Alice", 1200));
        sys.add(new Order(1002, "Bob", 800));
        sys.add(new Order(1008, "Charlie", 3500));
        sys.add(new Order(1004, "David", 450));

        sys.updateAmount(1002, 950); 
        sys.cancelOrder(1004);       

        sys.rangeReport(1000, 1005);
        
        sys.printSummary(); 
    }
}