class ShapeCompNode {
    int value;
    ShapeCompNode left;
    ShapeCompNode right;
    ShapeCompNode(int value) { this.value = value; }
}

class ShapeCompBst {
    ShapeCompNode root;

    void add(int value) {
        if (root == null) {
            root = new ShapeCompNode(value);
            return;
        }
        ShapeCompNode current = root;
        while (true) {
            if (value == current.value) return;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new ShapeCompNode(value);
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ShapeCompNode(value);
                    return;
                }
                current = current.right;
            }
        }
    }

    int height() {
        return heightHelper(root);
    }

    private int heightHelper(ShapeCompNode node) {
        if (node == null) return -1;
        return 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
    }

    int getSearchComparisons(int target) {
        int count = 0;
        ShapeCompNode current = root;
        while (current != null) {
            count++;
            if (target == current.value) return count;
            current = target < current.value ? current.left : current.right;
        }
        return count;
    }

    int getTotalSearchComparisons(int[] targets) {
        int total = 0;
        for (int t : targets) total += getSearchComparisons(t);
        return total;
    }
}

public class TreeShapeComparison {
    public static void main(String[] args) {
        int[] asc = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] desc = {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] balanced = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};

        ShapeCompBst tAsc = new ShapeCompBst();
        for (int v : asc) tAsc.add(v);

        ShapeCompBst tDesc = new ShapeCompBst();
        for (int v : desc) tDesc.add(v);

        ShapeCompBst tBal = new ShapeCompBst();
        for (int v : balanced) tBal.add(v);

        System.out.println("Ascending  | Height: " + tAsc.height() + " | All Keys Comps: " + tAsc.getTotalSearchComparisons(asc) + " | Missing (99) Comps: " + tAsc.getSearchComparisons(99));
        System.out.println("Descending | Height: " + tDesc.height() + " | All Keys Comps: " + tDesc.getTotalSearchComparisons(desc) + " | Missing (99) Comps: " + tDesc.getSearchComparisons(99));
        System.out.println("Balanced   | Height: " + tBal.height() + " | All Keys Comps: " + tBal.getTotalSearchComparisons(balanced) + " | Missing (99) Comps: " + tBal.getSearchComparisons(99));
    }
}