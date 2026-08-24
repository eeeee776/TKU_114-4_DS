class IntNode {
    int value;
    IntNode left;
    IntNode right;
    IntNode(int value) { this.value = value; }
}

class RangeBst {
    private IntNode root;

    // ... (省略基本的 add method，同前面範例) ...
    boolean add(int value) {
        if (root == null) { root = new IntNode(value); return true; }
        IntNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) { current.left = new IntNode(value); return true; }
                current = current.left;
            } else {
                if (current.right == null) { current.right = new IntNode(value); return true; }
                current = current.right;
            }
        }
    }

    Integer minimum() {
        if (root == null) return null;
        IntNode current = root;
        while (current.left != null) current = current.left;
        return current.value;
    }

    Integer maximum() {
        if (root == null) return null;
        IntNode current = root;
        while (current.right != null) current = current.right;
        return current.value;
    }

    void printRange(int low, int high) {
        // 處理 low > high 的防呆機制
        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }
        System.out.print("範圍 [" + low + ", " + high + "]: ");
        printRange(root, low, high);
        System.out.println();
    }

    private void printRange(IntNode node, int low, int high) {
        if (node == null) return;
        
        // 如果當前值大於 low，左子樹才有可能有落在範圍內的值
        if (node.value > low) {
            printRange(node.left, low, high);
        }
        
        // 如果當前值在範圍內，印出
        if (node.value >= low && node.value <= high) {
            System.out.print(node.value + " ");
        }
        
        // 如果當前值小於 high，右子樹才有可能有落在範圍內的值
        if (node.value < high) {
            printRange(node.right, low, high);
        }
    }
}

public class BstRangeReport {
    public static void main(String[] args) {
        RangeBst tree = new RangeBst();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) tree.add(v);

        System.out.println("Min: " + tree.minimum());
        System.out.println("Max: " + tree.maximum());
        
        tree.printRange(25, 65); // 預期: 30 40 50 60
        tree.printRange(80, 10); // 測試 low > high 互換，預期: 20 30 40 50 60 70 80
    }
}