class MenuNode {
    String name;
    MenuNode left;
    MenuNode right;

    MenuNode(String name) {
        this.name = name;
    }
}

public class MenuTreeSearch {
    static boolean contains(MenuNode node, String target) {
        if (node == null || target == null) return false;
        if (node.name.equals(target)) return true;
        return contains(node.left, target) || contains(node.right, target);
    }

    static int findDepth(MenuNode node, String target, int currentDepth) {
        if (node == null || target == null) return -1;
        if (node.name.equals(target)) return currentDepth;
        
        int leftDepth = findDepth(node.left, target, currentDepth + 1);
        if (leftDepth != -1) return leftDepth;
        
        return findDepth(node.right, target, currentDepth + 1);
    }

    static int countLeaves(MenuNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

    static void preorderDisplay(MenuNode node) {
        if (node == null) return;
        System.out.print(node.name + " ");
        preorderDisplay(node.left);
        preorderDisplay(node.right);
    }

    public static void main(String[] args) {
        MenuNode root = new MenuNode("Home");
        root.left = new MenuNode("Products");
        root.right = new MenuNode("About");
        root.left.left = new MenuNode("Laptops");
        root.left.right = new MenuNode("Phones");
        root.right.right = new MenuNode("Contact");

        System.out.print("Preorder Display: ");
        preorderDisplay(root);
        System.out.println();

        System.out.println("Contains 'Phones': " + contains(root, "Phones"));
        System.out.println("Contains 'Careers': " + contains(root, "Careers"));

        System.out.println("Depth of 'Home': " + findDepth(root, "Home", 0));
        System.out.println("Depth of 'Contact': " + findDepth(root, "Contact", 0));
        System.out.println("Depth of 'Careers': " + findDepth(root, "Careers", 0));

        System.out.println("Total Leaves: " + countLeaves(root));
    }
}