class RangeNode {
    int value;
    RangeNode left;
    RangeNode right;

    RangeNode(int value) {
        this.value = value;
    }
}

class RangeBst {
    private RangeNode root;

    boolean add(int value) {
        if (root == null) {
            root = new RangeNode(value);
            return true;
        }
        RangeNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new RangeNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new RangeNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    Integer minimum() {
        if (root == null) return null;
        RangeNode current = root;
        while (current.left != null) current = current.left;
        return current.value;
    }

    Integer maximum() {
        if (root == null) return null;
        RangeNode current = root;
        while (current.right != null) current = current.right;
        return current.value;
    }

    void printRange(int low, int high) {
        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }
        System.out.print("Range [" + low + ", " + high + "]: ");
        rangeHelper(root, low, high);
        System.out.println();
    }

    private void rangeHelper(RangeNode node, int low, int high) {
        if (node == null) return;
        if (node.value > low) {
            rangeHelper(node.left, low, high);
        }
        if (node.value >= low && node.value <= high) {
            System.out.print(node.value + " ");
        }
        if (node.value < high) {
            rangeHelper(node.right, low, high);
        }
    }
}

public class BstRangeReport {
    public static void main(String[] args) {
        RangeBst tree = new RangeBst();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) {
            tree.add(v);
        }
        System.out.println("Min: " + tree.minimum());
        System.out.println("Max: " + tree.maximum());
        tree.printRange(30, 60);
        tree.printRange(60, 30);
    }
}