class IntNode {
    int value;
    IntNode left;
    IntNode right;

    IntNode(int value) { 
        this.value = value; 
    }
}
class DeleteBst {
    private IntNode root;

    // ... (省略 add, size, isValid, inorder 等基本方法，參考原始教材) ...
    boolean add(int value) { /* 同上 */ return true; }
    int size() { return size(root); }
    private int size(IntNode node) { return node == null ? 0 : 1 + size(node.left) + size(node.right); }
    void inorder() { inorder(root); System.out.println(); }
    private void inorder(IntNode node) {
        if (node == null) return;
        inorder(node.left); System.out.print(node.value + " "); inorder(node.right);
    }
    boolean isValid() { return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE); }
    private boolean isValid(IntNode node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value >= max) return false;
        return isValid(node.left, min, node.value) && isValid(node.right, node.value, max);
    }

    boolean remove(int value) {
        int initialSize = size();
        root = remove(root, value);
        return size() < initialSize;
    }
    
    private IntNode remove(IntNode node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = remove(node.left, value);
        } else if (value > node.value) {
            node.right = remove(node.right, value);
        } else {
            // Case 1 & Case 2
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            
            // Case 3: Two children
            IntNode successor = minimumNode(node.right);
            node.value = successor.value;
            node.right = remove(node.right, successor.value);
        }
        return node;
    }
    
    private IntNode minimumNode(IntNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
}

public class BstDeleteCases {
    public static void main(String[] args) {
        DeleteBst tree = new DeleteBst();
        // 建構特定的樹：50, 30, 70, 20, 40, 80 (70 沒有左子節點，為 single child)
        for (int v : new int[]{50, 30, 70, 20, 40, 80}) tree.add(v);

        System.out.println("=== 原始樹 ===");
        printStatus(tree);

        System.out.println("=== 刪除 Leaf (20) ===");
        tree.remove(20);
        printStatus(tree);

        System.out.println("=== 刪除 Single-Child (70) ===");
        tree.remove(70);
        printStatus(tree);

        System.out.println("=== 刪除 Two-Child (30) ===");
        tree.remove(30);
        printStatus(tree);
    }

    private static void printStatus(DeleteBst tree) {
        System.out.print("Inorder: ");
        tree.inorder();
        System.out.println("Size: " + tree.size() + ", IsValid: " + tree.isValid());
        System.out.println();
    }
}