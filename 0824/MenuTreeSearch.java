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
        
        // 找左邊
        int leftDepth = findDepth(node.left, target, currentDepth + 1);
        if (leftDepth != -1) return leftDepth;
        
        // 如果左邊沒有，再找右邊
        return findDepth(node.right, target, currentDepth + 1);
    }

    static int countLeaves(MenuNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

    static void preorder(MenuNode node) {
        if (node == null) return;
        System.out.print(node.name + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public static void main(String[] args) {
        MenuNode root = new MenuNode("Settings");
        root.left = new MenuNode("Display");
        root.right = new MenuNode("Network");
        root.left.left = new MenuNode("Brightness");
        root.left.right = new MenuNode("Resolution");
        root.right.left = new MenuNode("WiFi");

        System.out.print("Preorder Display: ");
        preorder(root);
        System.out.println("\n");

        System.out.println("Contains 'WiFi': " + contains(root, "WiFi"));
        System.out.println("Contains 'Bluetooth': " + contains(root, "Bluetooth"));
        System.out.println();
        
        System.out.println("Depth of 'Settings' (Root): " + findDepth(root, "Settings", 0));
        System.out.println("Depth of 'Resolution': " + findDepth(root, "Resolution", 0));
        System.out.println("Depth of 'Bluetooth' (Not exist): " + findDepth(root, "Bluetooth", 0));
        System.out.println();
        
        System.out.println("Total Leaves: " + countLeaves(root));
    }
}