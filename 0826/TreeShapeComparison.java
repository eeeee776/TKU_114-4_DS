class ShapeBst {
    class Node {
        int value; Node left, right;
        Node(int value) { this.value = value; }
    }
    Node root;

    void add(int value) {
        if (root == null) { root = new Node(value); return; }
        Node cur = root;
        while (true) {
            if (value == cur.value) return;
            if (value < cur.value) {
                if (cur.left == null) { cur.left = new Node(value); return; }
                cur = cur.left;
            } else {
                if (cur.right == null) { cur.right = new Node(value); return; }
                cur = cur.right;
            }
        }
    }

    // 計算特定 key 的搜尋比較次數
    int countSearchComparisons(int target) {
        int comparisons = 0;
        Node cur = root;
        while (cur != null) {
            comparisons++;
            if (target == cur.value) return comparisons;
            cur = target < cur.value ? cur.left : cur.right;
        }
        return comparisons;
    }

    int height() { return height(root); }
    private int height(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }
}

public class TreeShapeComparison {
    public static void runTest(String label, int[] data) {
        ShapeBst tree = new ShapeBst();
        for (int d : data) tree.add(d);

        int totalSuccessComps = 0;
        for (int i = 1; i <= 15; i++) {
            totalSuccessComps += tree.countSearchComparisons(i);
        }
        
        int missingComps = tree.countSearchComparisons(99);

        System.out.printf("%-12s | Height: %-2d | Total Success Comps: %-3d | Missing Key(99) Comps: %-2d%n",
                label, tree.height(), totalSuccessComps, missingComps);
    }

    public static void main(String[] args) {
        int[] asc = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        int[] desc = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        int[] balanced = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};

        System.out.println("=== Tree Shape Performance Comparison ===");
        runTest("Ascending", asc);
        runTest("Descending", desc);
        runTest("Balanced", balanced);
    }
}