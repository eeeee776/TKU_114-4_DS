class TraversalNode {
    String value;
    TraversalNode left;
    TraversalNode right;

    TraversalNode(String value) {
        this.value = value;
    }
}

public class ThreeTraversalPractice {
    static void preorder(TraversalNode node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(TraversalNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    static void postorder(TraversalNode node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args) {
        TraversalNode root = new TraversalNode("M");
        root.left = new TraversalNode("F");
        root.left.left = new TraversalNode("B");
        root.right = new TraversalNode("T");
        root.right.left = new TraversalNode("R");
        root.right.right = new TraversalNode("Z");

        System.out.print("Preorder: ");
        preorder(root);
        System.out.println();

        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();

        System.out.print("Postorder: ");
        postorder(root);
        System.out.println();
    }
}