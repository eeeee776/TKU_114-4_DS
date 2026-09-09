class StatNode {
    int value;
    StatNode left;
    StatNode right;

    StatNode(int value) {
        this.value = value;
    }
}

public class BinaryTreeStatistics {
    static int size(StatNode node) {
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }

    static int sum(StatNode node) {
        if (node == null) return 0;
        return node.value + sum(node.left) + sum(node.right);
    }

    static Integer maximum(StatNode node) {
        if (node == null) return null;
        Integer maxLeft = maximum(node.left);
        Integer maxRight = maximum(node.right);
        int currentMax = node.value;
        if (maxLeft != null && maxLeft > currentMax) currentMax = maxLeft;
        if (maxRight != null && maxRight > currentMax) currentMax = maxRight;
        return currentMax;
    }

    static int leafCount(StatNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return leafCount(node.left) + leafCount(node.right);
    }

    static int height(StatNode node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    static boolean contains(StatNode node, int target) {
        if (node == null) return false;
        if (node.value == target) return true;
        return contains(node.left, target) || contains(node.right, target);
    }

    public static void main(String[] args) {
        StatNode root = new StatNode(10);
        root.left = new StatNode(5);
        root.right = new StatNode(20);
        root.left.left = new StatNode(3);

        System.out.println(size(root));
        System.out.println(sum(root));
        System.out.println(maximum(root));
        System.out.println(maximum(null));
        System.out.println(leafCount(root));
        System.out.println(height(root));
        System.out.println(contains(root, 20));
        System.out.println(contains(root, 99));
    }
}