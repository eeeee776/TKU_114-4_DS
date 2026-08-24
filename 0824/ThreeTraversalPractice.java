class TravNode {
    String value;
    TravNode left;
    TravNode right;

    TravNode(String value) {
        this.value = value;
    }
}

public class ThreeTraversalPractice {

    static void preorder(TravNode node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(TravNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    static void postorder(TravNode node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args) {
        // 建構 M(F(B,null),T(R,Z))
        TravNode root = new TravNode("M");
        root.left = new TravNode("F");
        root.right = new TravNode("T");
        root.left.left = new TravNode("B");
        // root.left.right = null;
        root.right.left = new TravNode("R");
        root.right.right = new TravNode("Z");

        System.out.print("Preorder  (Root-L-R): ");
        preorder(root);
        System.out.println();

        System.out.print("Inorder   (L-Root-R): ");
        inorder(root);
        System.out.println();

        System.out.print("Postorder (L-R-Root): ");
        postorder(root);
        System.out.println();
    }
}