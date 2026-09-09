import java.util.ArrayList;
import java.util.List;

class ManageOrder {
    int orderId;
    String customer;
    int amount;
    String status;

    ManageOrder(int orderId, String customer, int amount) {
        this.orderId = orderId;
        this.customer = customer;
        this.amount = Math.max(0, amount);
        this.status = "NEW";
    }

    @Override
    public String toString() {
        return orderId + " " + customer + " $" + amount + " [" + status + "]";
    }
}

class ManageOrderNode {
    ManageOrder data;
    ManageOrderNode left;
    ManageOrderNode right;

    ManageOrderNode(ManageOrder data) {
        this.data = data;
    }
}

public class OrderManagementBst {
    private ManageOrderNode root;

    boolean add(int orderId, String customer, int amount) {
        ManageOrder order = new ManageOrder(orderId, customer, amount);
        if (root == null) {
            root = new ManageOrderNode(order);
            return true;
        }
        ManageOrderNode current = root;
        while (true) {
            if (orderId == current.data.orderId) return false;
            if (orderId < current.data.orderId) {
                if (current.left == null) {
                    current.left = new ManageOrderNode(order);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ManageOrderNode(order);
                    return true;
                }
                current = current.right;
            }
        }
    }

    ManageOrder find(int orderId) {
        ManageOrderNode current = root;
        while (current != null) {
            if (orderId == current.data.orderId) return current.data;
            current = orderId < current.data.orderId ? current.left : current.right;
        }
        return null;
    }

    boolean updateStatus(int orderId, String status) {
        ManageOrder o = find(orderId);
        if (o == null || status == null || status.isBlank()) return false;
        o.status = status;
        return true;
    }

    boolean cancel(int orderId) {
        return updateStatus(orderId, "CANCELLED");
    }

    boolean remove(int orderId) {
        ManageOrder o = find(orderId);
        if (o == null || !o.status.equals("CANCELLED")) return false;
        root = removeHelper(root, orderId);
        return true;
    }

    private ManageOrderNode removeHelper(ManageOrderNode node, int orderId) {
        if (node == null) return null;
        if (orderId < node.data.orderId) {
            node.left = removeHelper(node.left, orderId);
        } else if (orderId > node.data.orderId) {
            node.right = removeHelper(node.right, orderId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            ManageOrderNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = removeHelper(node.right, successor.data.orderId);
        }
        return node;
    }

    List<ManageOrder> idRangeReport(int startId, int endId) {
        List<ManageOrder> res = new ArrayList<>();
        if (startId <= endId) rangeHelper(root, startId, endId, res);
        return res;
    }

    private void rangeHelper(ManageOrderNode node, int startId, int endId, List<ManageOrder> res) {
        if (node == null) return;
        if (node.data.orderId > startId) rangeHelper(node.left, startId, endId, res);
        if (node.data.orderId >= startId && node.data.orderId <= endId) res.add(node.data);
        if (node.data.orderId < endId) rangeHelper(node.right, startId, endId, res);
    }

    int totalAmount() {
        return totalAmountHelper(root);
    }

    private int totalAmountHelper(ManageOrderNode node) {
        if (node == null) return 0;
        int currentAmount = node.data.status.equals("CANCELLED") ? 0 : node.data.amount;
        return currentAmount + totalAmountHelper(node.left) + totalAmountHelper(node.right);
    }

    public static void main(String[] args) {
        OrderManagementBst sys = new OrderManagementBst();
        sys.add(200, "Amy", 500);
        sys.add(100, "Ben", 300);
        sys.add(300, "Cara", -100);

        sys.cancel(100);
        System.out.println("Remove new: " + sys.remove(200));
        System.out.println("Remove cancelled: " + sys.remove(100));
        
        System.out.println("Total Amount: " + sys.totalAmount());
        System.out.println("Range [100, 300]: " + sys.idRangeReport(100, 300));
    }
}