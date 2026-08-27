package midterm_exam;
public class Q09_TreeTraversal {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static java.util.List<Integer> preorder(Node root) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        preHelper(root, list);
        return list;
    }
    private static void preHelper(Node node, java.util.List<Integer> list) {
        if (node == null) return;
        list.add(node.value);
        preHelper(node.left, list);
        preHelper(node.right, list);
    }

    public static java.util.List<Integer> inorder(Node root) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        inHelper(root, list);
        return list;
    }
    private static void inHelper(Node node, java.util.List<Integer> list) {
        if (node == null) return;
        inHelper(node.left, list);
        list.add(node.value);
        inHelper(node.right, list);
    }

    public static java.util.List<Integer> postorder(Node root) {
        java.util.List<Integer> walkRecordP09 = new java.util.ArrayList<>();
        postHelper(root, walkRecordP09);
        return walkRecordP09;
    }
    private static void postHelper(Node node, java.util.List<Integer> list) {
        if (node == null) return;
        postHelper(node.left, list);
        postHelper(node.right, list);
        list.add(node.value);
    }

    public static java.util.List<Integer> levelOrder(Node root) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        if (root == null) return list;
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            list.add(curr.value);
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        return list;
    }
}