class SkewedNode {
    int value;
    SkewedNode left;
    SkewedNode right;

    SkewedNode(int value) {
        this.value = value;
    }
}

class SkewedBst {
    private SkewedNode root;

    boolean add(int value) {
        if (root == null) {
            root = new SkewedNode(value);
            return true;
        }
        SkewedNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new SkewedNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new SkewedNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(SkewedNode node) {
        return node == null ? 0 : 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }

    int height() {
        return heightHelper(root);
    }

    private int heightHelper(SkewedNode node) {
        return node == null ? -1 : 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
    }

    int getTotalSearchCount(int[] targets) {
        int total = 0;
        for (int t : targets) {
            total += getSearchCount(t);
        }
        return total;
    }

    private int getSearchCount(int target) {
        int count = 0;
        SkewedNode current = root;
        while (current != null) {
            count++;
            if (target == current.value) return count;
            current = target < current.value ? current.left : current.right;
        }
        return count;
    }
}

public class SkewedBstReport {
    public static void main(String[] args) {
        int[] sortedData = {10, 20, 30, 40, 50, 60, 70};
        int[] balancedData = {40, 20, 60, 10, 30, 50, 70};

        SkewedBst sortedTree = new SkewedBst();
        SkewedBst balancedTree = new SkewedBst();

        for (int v : sortedData) sortedTree.add(v);
        for (int v : balancedData) balancedTree.add(v);

        System.out.println("Sorted Insert Tree:");
        System.out.println("Size: " + sortedTree.size());
        System.out.println("Height: " + sortedTree.height());
        System.out.println("Search Count for all: " + sortedTree.getTotalSearchCount(sortedData));

        System.out.println("\nBalanced Insert Tree:");
        System.out.println("Size: " + balancedTree.size());
        System.out.println("Height: " + balancedTree.height());
        System.out.println("Search Count for all: " + balancedTree.getTotalSearchCount(sortedData));
    }
}