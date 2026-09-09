class ExprNode {
    String value;
    ExprNode left;
    ExprNode right;

    ExprNode(String value) {
        this.value = value;
    }
}

public class TraversalSelector {
    static void preorderPrefix(ExprNode node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        preorderPrefix(node.left);
        preorderPrefix(node.right);
    }

    static void inorderInfix(ExprNode node) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            System.out.print(node.value);
            return;
        }
        System.out.print("(");
        inorderInfix(node.left);
        System.out.print(" " + node.value + " ");
        inorderInfix(node.right);
        System.out.print(")");
    }

    static void postorderPostfix(ExprNode node) {
        if (node == null) return;
        postorderPostfix(node.left);
        postorderPostfix(node.right);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args) {
        ExprNode root = new ExprNode("*");
        root.left = new ExprNode("+");
        root.right = new ExprNode("C");
        root.left.left = new ExprNode("A");
        root.left.right = new ExprNode("B");

        System.out.print("Prefix (Preorder): ");
        preorderPrefix(root);
        System.out.println();

        System.out.print("Infix (Inorder): ");
        inorderInfix(root);
        System.out.println();

        System.out.print("Postfix (Postorder): ");
        postorderPostfix(root);
        System.out.println();
    }
}