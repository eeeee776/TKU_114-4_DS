class IntNode {
    int value;
    IntNode left;
    IntNode right;

    IntNode(int value) { 
        this.value = value; 
    }
}
class SkewedBst {
    private IntNode root;
    // ... (省略 add, size, height 方法) ...
    boolean add(int value) { /* 同上 */ return true; }
    int size() { return size(root); }
    private int size(IntNode node) { return node == null ? 0 : 1 + size(node.left) + size(node.right); }
    int height() { return height(root); }
    private int height(IntNode node) {
        return node == null ? -1 : 1 + Math.max(height(node.left), height(node.right));
    }

    // 計算尋找特定值的比較次數
    int getSearchComparisons(int value) {
        IntNode current = root;
        int count = 0;
        while (current != null) {
            count++;
            if (value == current.value) return count;
            current = value < current.value ? current.left : current.right;
        }
        return count;
    }
}

public class SkewedBstReport {
    public static void main(String[] args) {
        int[] sortedData = {10, 20, 30, 40, 50, 60, 70};
        int[] balancedData = {40, 20, 60, 10, 30, 50, 70};

        SkewedBst skewedTree = new SkewedBst();
        SkewedBst balancedTree = new SkewedBst();

        for (int v : sortedData) skewedTree.add(v);
        for (int v : balancedData) balancedTree.add(v);

        report("Skewed Tree (Sorted Insert)", skewedTree, sortedData);
        report("Balanced Tree (Random Insert)", balancedTree, balancedData);
    }

    private static void report(String name, SkewedBst tree, int[] data) {
        System.out.println("--- " + name + " ---");
        System.out.println("Size: " + tree.size() + ", Height: " + tree.height());
        
        int totalComparisons = 0;
        for (int v : data) {
            totalComparisons += tree.getSearchComparisons(v);
        }
        System.out.println("尋找所有節點的總比較次數: " + totalComparisons);
        System.out.println();
    }
}