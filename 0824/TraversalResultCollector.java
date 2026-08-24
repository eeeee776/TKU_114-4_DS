import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

class ColNode {
    String value;
    ColNode left, right;
    ColNode(String value) { this.value = value; }
}

public class TraversalResultCollector {

    public static List<String> preorder(ColNode node) {
        List<String> result = new ArrayList<>();
        preorderHelper(node, result);
        return result;
    }
    private static void preorderHelper(ColNode node, List<String> res) {
        if (node == null) return;
        res.add(node.value);
        preorderHelper(node.left, res);
        preorderHelper(node.right, res);
    }

    // Inorder 與 Postorder 概念相同，只改變 res.add 的位置
    public static List<String> inorder(ColNode node) {
        List<String> result = new ArrayList<>();
        inorderHelper(node, result);
        return result;
    }
    private static void inorderHelper(ColNode node, List<String> res) {
        if (node == null) return;
        inorderHelper(node.left, res);
        res.add(node.value);
        inorderHelper(node.right, res);
    }

    public static List<String> levelOrder(ColNode node) {
        List<String> result = new ArrayList<>();
        if (node == null) return result;

        Queue<ColNode> queue = new ArrayDeque<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            ColNode current = queue.poll();
            result.add(current.value);
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
        return result;
    }

    public static void main(String[] args) {
        ColNode root = new ColNode("A");
        root.left = new ColNode("B");
        root.right = new ColNode("C");
        root.left.left = new ColNode("D");

        System.out.println("Preorder: " + preorder(root));
        System.out.println("Inorder: " + inorder(root));
        System.out.println("Level-order: " + levelOrder(root));
    }
}