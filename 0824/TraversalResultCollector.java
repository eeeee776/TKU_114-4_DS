import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

class CollectorNode {
    String value;
    CollectorNode left;
    CollectorNode right;

    CollectorNode(String value) {
        this.value = value;
    }
}

public class TraversalResultCollector {
    static List<String> preorder(CollectorNode node) {
        List<String> list = new ArrayList<>();
        preorderHelper(node, list);
        return list;
    }

    private static void preorderHelper(CollectorNode node, List<String> list) {
        if (node == null) return;
        list.add(node.value);
        preorderHelper(node.left, list);
        preorderHelper(node.right, list);
    }

    static List<String> inorder(CollectorNode node) {
        List<String> list = new ArrayList<>();
        inorderHelper(node, list);
        return list;
    }

    private static void inorderHelper(CollectorNode node, List<String> list) {
        if (node == null) return;
        inorderHelper(node.left, list);
        list.add(node.value);
        inorderHelper(node.right, list);
    }

    static List<String> postorder(CollectorNode node) {
        List<String> list = new ArrayList<>();
        postorderHelper(node, list);
        return list;
    }

    private static void postorderHelper(CollectorNode node, List<String> list) {
        if (node == null) return;
        postorderHelper(node.left, list);
        postorderHelper(node.right, list);
        list.add(node.value);
    }

    static List<String> levelOrder(CollectorNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) return list;
        Queue<CollectorNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            CollectorNode current = queue.poll();
            list.add(current.value);
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(preorder(null));

        CollectorNode single = new CollectorNode("A");
        System.out.println(levelOrder(single));

        CollectorNode leftSkewed = new CollectorNode("1");
        leftSkewed.left = new CollectorNode("2");
        leftSkewed.left.left = new CollectorNode("3");
        System.out.println(inorder(leftSkewed));

        CollectorNode complete = new CollectorNode("A");
        complete.left = new CollectorNode("B");
        complete.right = new CollectorNode("C");
        complete.left.left = new CollectorNode("D");
        complete.left.right = new CollectorNode("E");
        complete.right.left = new CollectorNode("F");
        complete.right.right = new CollectorNode("G");
        System.out.println(postorder(complete));
    }
}