import java.util.ArrayList;
import java.util.List;

class RangeNode {
    int value;
    RangeNode left, right;
    RangeNode(int value) { this.value = value; }
}

public class BstRangeStatistics {
    private RangeNode root;

    public void add(int value) {
        if (root == null) { root = new RangeNode(value); return; }
        RangeNode current = root;
        while (true) {
            if (value == current.value) return;
            if (value < current.value) {
                if (current.left == null) { current.left = new RangeNode(value); return; }
                current = current.left;
            } else {
                if (current.right == null) { current.right = new RangeNode(value); return; }
                current = current.right;
            }
        }
    }

    // 1. valuesBetween
    public List<Integer> valuesBetween(int low, int high) {
        List<Integer> result = new ArrayList<>();
        if (low <= high) valuesBetween(root, low, high, result);
        return result;
    }
    private void valuesBetween(RangeNode node, int low, int high, List<Integer> result) {
        if (node == null) return;
        if (low < node.value) valuesBetween(node.left, low, high, result); // 左剪枝
        if (low <= node.value && node.value <= high) result.add(node.value);
        if (node.value < high) valuesBetween(node.right, low, high, result); // 右剪枝
    }

    // 2. countBetween
    public int countBetween(int low, int high) {
        if (low > high) return 0;
        return countBetween(root, low, high);
    }
    private int countBetween(RangeNode node, int low, int high) {
        if (node == null) return 0;
        int count = 0;
        if (low < node.value) count += countBetween(node.left, low, high);
        if (low <= node.value && node.value <= high) count++;
        if (node.value < high) count += countBetween(node.right, low, high);
        return count;
    }

    // 3. sumBetween
    public int sumBetween(int low, int high) {
        if (low > high) return 0;
        return sumBetween(root, low, high);
    }
    private int sumBetween(RangeNode node, int low, int high) {
        if (node == null) return 0;
        int sum = 0;
        if (low < node.value) sum += sumBetween(node.left, low, high);
        if (low <= node.value && node.value <= high) sum += node.value;
        if (node.value < high) sum += sumBetween(node.right, low, high);
        return sum;
    }

    public static void main(String[] args) {
        BstRangeStatistics tree = new BstRangeStatistics();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) tree.add(v);

        System.out.println("Values [35, 75]: " + tree.valuesBetween(35, 75)); // [40, 50, 60, 70]
        System.out.println("Count  [35, 75]: " + tree.countBetween(35, 75));  // 4
        System.out.println("Sum    [35, 75]: " + tree.sumBetween(35, 75));    // 40+50+60+70 = 220
        
        System.out.println("\nEdge Case: low > high [80, 20]");
        System.out.println("Values: " + tree.valuesBetween(80, 20)); // []
        System.out.println("Count:  " + tree.countBetween(80, 20));  // 0
    }
}