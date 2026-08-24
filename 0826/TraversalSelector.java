class ExpNode {
    String value;
    ExpNode left, right;

    ExpNode(String value) {
        this.value = value;
    }
    
    // 判斷是否為葉節點 (運算元)
    boolean isLeaf() {
        return left == null && right == null;
    }
}

public class TraversalSelector {

    // Preorder -> Prefix (前置式)
    public static String prefix(ExpNode node) {
        if (node == null) return "";
        return node.value + " " + prefix(node.left) + prefix(node.right);
    }

    // Inorder -> Infix (中置式)，遇到子樹加上括號
    public static String infix(ExpNode node) {
        if (node == null) return "";
        if (node.isLeaf()) return node.value;
        return "(" + infix(node.left) + " " + node.value + " " + infix(node.right) + ")";
    }

    // Postorder -> Postfix (後置式)
    public static String postfix(ExpNode node) {
        if (node == null) return "";
        return postfix(node.left) + postfix(node.right) + node.value + " ";
    }

    public static void main(String[] args) {
        // 建立樹: (A + B) * (C - D)
        ExpNode root = new ExpNode("*");
        root.left = new ExpNode("+");
        root.right = new ExpNode("-");
        root.left.left = new ExpNode("A");
        root.left.right = new ExpNode("B");
        root.right.left = new ExpNode("C");
        root.right.right = new ExpNode("D");

        System.out.println("Prefix (Preorder):  " + prefix(root).trim());
        System.out.println("Infix (Inorder):    " + infix(root).trim());
        System.out.println("Postfix (Postorder):" + postfix(root).trim());
    }
}