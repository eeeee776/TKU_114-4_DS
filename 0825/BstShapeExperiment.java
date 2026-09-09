class ShapeNode {
    int value;
    ShapeNode left;
    ShapeNode right;

    ShapeNode(int value) {
        this.value = value;
    }
}

class ShapeBst {
    private ShapeNode root;
    private int totalComparisons;

    void add(int value) {
        if (root == null) {
            root = new ShapeNode(value);
            return;
        }
        ShapeNode current = root;
        while (true) {
            if (value == current.value) return;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new ShapeNode(value);
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ShapeNode(value);
                    return;
                }
                current = current.right;
            }
        }
    }

    int height() {
        return heightHelper(root);
    }

    private int heightHelper(ShapeNode node) {
        return node == null ? -1 : 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
    }

    int getTotalSearchCount(int[] targets) {
        totalComparisons = 0;
        for (int t : targets) {
            searchCount(t);
        }
        return totalComparisons;
    }

    private void searchCount(int target) {
        ShapeNode current = root;
        while (current != null) {
            totalComparisons++;
            if (target == current.value) return;
            current = target < current.value ? current.left : current.right;
        }
    }
}

public class BstShapeExperiment {
    public static void main(String[] args) {
        int[] sortedData = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] reverseData = {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] balancedData = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};

        ShapeBst sortedTree = new ShapeBst();
        for (int v : sortedData) sortedTree.add(v);

        ShapeBst reverseTree = new ShapeBst();
        for (int v : reverseData) reverseTree.add(v);

        ShapeBst balancedTree = new ShapeBst();
        for (int v : balancedData) balancedTree.add(v);

        System.out.println("Sorted: Height=" + sortedTree.height() + ", Comps=" + sortedTree.getTotalSearchCount(sortedData));
        System.out.println("Reverse: Height=" + reverseTree.height() + ", Comps=" + reverseTree.getTotalSearchCount(reverseData));
        System.out.println("Balanced: Height=" + balancedTree.height() + ", Comps=" + balancedTree.getTotalSearchCount(balancedData));
    }
}