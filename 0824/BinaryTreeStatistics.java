class StatNode {
    int value;
    StatNode left, right;
    StatNode(int value) { this.value = value; }
}

public class BinaryTreeStatistics {
    
    public static int size(StatNode node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    public static int sum(StatNode node) {
        return node == null ? 0 : node.value + sum(node.left) + sum(node.right);
    }

    public static int maximum(StatNode node) {
        if (node == null) {
            throw new IllegalArgumentException("Cannot find maximum in an empty tree.");
        }
        int max = node.value;
        if (node.left != null) max = Math.max(max, maximum(node.left));
        if (node.right != null) max = Math.max(max, maximum(node.right));
        return max;
    }

    public static int leafCount(StatNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return leafCount(node.left) + leafCount(node.right);
    }

    public static int height(StatNode node) {
        return node == null ? -1 : 1 + Math.max(height(node.left), height(node.right));
    }

    public static boolean contains(StatNode node, int target) {
        if (node == null) return false;
        if (node.value == target) return true;
        return contains(node.left, target) || contains(node.right, target);
    }

    public static void main(String[] args) {
        StatNode root = new StatNode(10);
        root.left = new StatNode(5);
        root.right = new StatNode(20);
        root.left.left = new StatNode(-3);
        root.right.right = new StatNode(42);

        System.out.println("Size: " + size(root));
        System.out.println("Sum: " + sum(root));
        System.out.println("Maximum: " + maximum(root));
        System.out.println("Leaf count: " + leafCount(root));
        System.out.println("Height: " + height(root));
        System.out.println("Contains 20: " + contains(root, 20));
        
        try {
            maximum(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Empty max tree caught: " + e.getMessage());
        }
    }
}