import java.util.ArrayList;
import java.util.List;

public class Q11_BstDeletion {
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            return true;
        }
        Node current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new Node(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    public boolean contains(int value) {
        Node current = root;
        while (current != null) {
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    public boolean remove(int value) {
        if (!contains(value)) return false;
        root = removeHelper(root, value);
        return true;
    }

    private Node removeHelper(Node node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = removeHelper(node.left, value);
        } else if (value > node.value) {
            node.right = removeHelper(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.value = successor.value;
            node.right = removeHelper(node.right, successor.value);
        }
        return node;
    }

    public int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(Node node) {
        if (node == null) return 0;
        return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }

    public List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(Node node, List<Integer> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(node.value);
        inorderHelper(node.right, result);
    }

    public boolean isValid() {
        return validHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validHelper(Node node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value >= max) return false;
        return validHelper(node.left, min, node.value) && validHelper(node.right, node.value, max);
    }
}