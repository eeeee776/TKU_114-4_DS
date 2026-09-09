class OrderRecord {
    int orderId;
    int amount;

    OrderRecord(int orderId, int amount) {
        this.orderId = orderId;
        this.amount = Math.max(0, amount);
    }

    @Override
    public String toString() {
        return "Order[" + orderId + "]: " + amount;
    }
}

class OrderNode {
    OrderRecord data;
    OrderNode left;
    OrderNode right;

    OrderNode(OrderRecord data) {
        this.data = data;
    }
}

public class OrderBstSystem {
    private OrderNode root;

    boolean add(int orderId, int amount) {
        OrderRecord record = new OrderRecord(orderId, amount);
        if (root == null) {
            root = new OrderNode(record);
            return true;
        }
        OrderNode current = root;
        while (true) {
            if (orderId == current.data.orderId) return false;
            if (orderId < current.data.orderId) {
                if (current.left == null) {
                    current.left = new OrderNode(record);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new OrderNode(record);
                    return true;
                }
                current = current.right;
            }
        }
    }

    OrderRecord find(int orderId) {
        OrderNode current = root;
        while (current != null) {
            if (orderId == current.data.orderId) return current.data;
            current = orderId < current.data.orderId ? current.left : current.right;
        }
        return null;
    }

    boolean updateAmount(int orderId, int newAmount) {
        OrderRecord record = find(orderId);
        if (record == null) return false;
        record.amount = Math.max(0, newAmount);
        return true;
    }

    boolean cancel(int orderId) {
        if (find(orderId) == null) return false;
        root = cancelHelper(root, orderId);
        return true;
    }

    private OrderNode cancelHelper(OrderNode node, int orderId) {
        if (node == null) return null;
        if (orderId < node.data.orderId) {
            node.left = cancelHelper(node.left, orderId);
        } else if (orderId > node.data.orderId) {
            node.right = cancelHelper(node.right, orderId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            OrderNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = cancelHelper(node.right, successor.data.orderId);
        }
        return node;
    }

    void rangeReport(int minId, int maxId) {
        rangeHelper(root, minId, maxId);
        System.out.println();
    }

    private void rangeHelper(OrderNode node, int minId, int maxId) {
        if (node == null) return;
        if (node.data.orderId > minId) {
            rangeHelper(node.left, minId, maxId);
        }
        if (node.data.orderId >= minId && node.data.orderId <= maxId) {
            System.out.print(node.data + " | ");
        }
        if (node.data.orderId < maxId) {
            rangeHelper(node.right, minId, maxId);
        }
    }

    void summary() {
        int[] result = new int[2];
        summaryHelper(root, result);
        System.out.println("Total Orders: " + result[0] + ", Total Amount: " + result[1]);
    }

    private void summaryHelper(OrderNode node, int[] result) {
        if (node == null) return;
        result[0]++;
        result[1] += node.data.amount;
        summaryHelper(node.left, result);
        summaryHelper(node.right, result);
    }

    public static void main(String[] args) {
        OrderBstSystem sys = new OrderBstSystem();
        sys.add(1005, 500);
        sys.add(1001, 300);
        sys.add(1008, 1200);
        sys.add(1003, 400);

        sys.updateAmount(1001, 350);
        sys.cancel(1008);

        sys.rangeReport(1000, 1005);
        sys.summary();
    }
}