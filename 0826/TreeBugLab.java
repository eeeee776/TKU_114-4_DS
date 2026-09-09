class BugNode {
    int value;
    BugNode left;
    BugNode right;

    BugNode(int value) {
        this.value = value;
    }
}

public class TreeBugLab {

    static boolean searchBuggy(BugNode root, int target) {
        BugNode current = root;
        while (current != null) {
            if (target == current.value) return true;
            current = target < current.value ? current.right : current.left; 
        }
        return false;
    }

    static boolean searchFixed(BugNode root, int target) {
        BugNode current = root;
        while (current != null) {
            if (target == current.value) return true;
            current = target < current.value ? current.left : current.right;
        }
        return false;
    }

    static void inorderBuggy(BugNode node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        inorderBuggy(node.left);
        inorderBuggy(node.right);
    }

    static void inorderFixed(BugNode node) {
        if (node == null) return;
        inorderFixed(node.left);
        System.out.print(node.value + " ");
        inorderFixed(node.right);
    }

    static BugNode removeBuggy(BugNode node, int target) {
        if (node == null) return null;
        if (target < node.value) {
            node.left = removeBuggy(node.left, target);
        } else if (target > node.value) {
            node.right = removeBuggy(node.right, target);
        } else {
            if (node.left == null) return null; 
            if (node.right == null) return null; 
            BugNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.value = successor.value;
            node.right = removeBuggy(node.right, successor.value);
        }
        return node;
    }

    static BugNode removeFixed(BugNode node, int target) {
        if (node == null) return null;
        if (target < node.value) {
            node.left = removeFixed(node.left, target);
        } else if (target > node.value) {
            node.right = removeFixed(node.right, target);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            BugNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.value = successor.value;
            node.right = removeFixed(node.right, successor.value);
        }
        return node;
    }

    static boolean validateBuggy(BugNode node) {
        if (node == null) return true;
        if (node.left != null && node.left.value >= node.value) return false;
        if (node.right != null && node.right.value <= node.value) return false;
        return validateBuggy(node.left) && validateBuggy(node.right);
    }

    static boolean validateFixed(BugNode node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value >= max) return false;
        return validateFixed(node.left, min, node.value) && validateFixed(node.right, node.value, max);
    }

    public static void main(String[] args) {
        BugNode root = new BugNode(50);
        root.left = new BugNode(30);
        root.right = new BugNode(70);

        System.out.println("Search Buggy (30): " + searchBuggy(root, 30));
        System.out.println("Search Fixed (30): " + searchFixed(root, 30));

        System.out.print("Inorder Buggy: ");
        inorderBuggy(root);
        System.out.println();
        System.out.print("Inorder Fixed: ");
        inorderFixed(root);
        System.out.println();

        BugNode removeTreeBuggy = new BugNode(50);
        removeTreeBuggy.left = new BugNode(30);
        removeTreeBuggy.left.left = new BugNode(20);
        removeTreeBuggy = removeBuggy(removeTreeBuggy, 30);
        System.out.print("Remove Buggy Result: ");
        inorderFixed(removeTreeBuggy);
        System.out.println();

        BugNode removeTreeFixed = new BugNode(50);
        removeTreeFixed.left = new BugNode(30);
        removeTreeFixed.left.left = new BugNode(20);
        removeTreeFixed = removeFixed(removeTreeFixed, 30);
        System.out.print("Remove Fixed Result: ");
        inorderFixed(removeTreeFixed);
        System.out.println();

        BugNode validTree = new BugNode(50);
        validTree.left = new BugNode(30);
        validTree.left.right = new BugNode(60); 
        System.out.println("Validate Buggy: " + validateBuggy(validTree));
        System.out.println("Validate Fixed: " + validateFixed(validTree, Long.MIN_VALUE, Long.MAX_VALUE));
    }
}