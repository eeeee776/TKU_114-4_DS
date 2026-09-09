import java.util.ArrayList;
import java.util.List;

class StatNode {
    int value;
    StatNode left;
    StatNode right;

    StatNode(int value) {
        this.value = value;
    }
}

public class BstRangeStatistics {
    private StatNode root;

    boolean add(int value) {
        if (root == null) {
            root = new StatNode(value);
            return true;
        }
        StatNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new StatNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new StatNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    List<Integer> valuesBetween(int low, int high) {
        List<Integer> list = new ArrayList<>();
        if (low <= high) valuesHelper(root, low, high, list);
        return list;
    }

    private void valuesHelper(StatNode node, int low, int high, List<Integer> list) {
        if (node == null) return;
        if (node.value > low) valuesHelper(node.left, low, high, list);
        if (node.value >= low && node.value <= high) list.add(node.value);
        if (node.value < high) valuesHelper(node.right, low, high, list);
    }

    int countBetween(int low, int high) {
        if (low > high) return 0;
        return countHelper(root, low, high);
    }

    private int countHelper(StatNode node, int low, int high) {
        if (node == null) return 0;
        int count = 0;
        if (node.value > low) count += countHelper(node.left, low, high);
        if (node.value >= low && node.value <= high) count++;
        if (node.value < high) count += countHelper(node.right, low, high);
        return count;
    }

    int sumBetween(int low, int high) {
        if (low > high) return 0;
        return sumHelper(root, low, high);
    }

    private int sumHelper(StatNode node, int low, int high) {
        if (node == null) return 0;
        int sum = 0;
        if (node.value > low) sum += sumHelper(node.left, low, high);
        if (node.value >= low && node.value <= high) sum += node.value;
        if (node.value < high) sum += sumHelper(node.right, low, high);
        return sum;
    }

    public static void main(String[] args) {
        BstRangeStatistics tree = new BstRangeStatistics();
        int[] data = {50, 30, 70, 20, 40, 60, 80};
        for (int v : data) tree.add(v);

        System.out.println("Values [35, 70]: " + tree.valuesBetween(35, 70));
        System.out.println("Count [35, 70]: " + tree.countBetween(35, 70));
        System.out.println("Sum [35, 70]: " + tree.sumBetween(35, 70));

        System.out.println("Values [70, 35]: " + tree.valuesBetween(70, 35));
        System.out.println("Count [70, 35]: " + tree.countBetween(70, 35));
        System.out.println("Sum [70, 35]: " + tree.sumBetween(70, 35));
    }
}