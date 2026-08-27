package midterm_exam;

public class Q10_BstDirectory {
    private static class Node {
        int value;
        Node left, right;
        Node(int value) { this.value = value; }
    }
    private Node root;
    private int size;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }
        Node curr = root;
        while (true) {
            if (value == curr.value) return false;
            if (value < curr.value) {
                if (curr.left == null) {
                    curr.left = new Node(value);
                    size++;
                    return true;
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new Node(value);
                    size++;
                    return true;
                }
                curr = curr.right;
            }
        }
    }

    public boolean contains(int value) {
        Node curr = root;
        while (curr != null) {
            if (value == curr.value) return true;
            curr = (value < curr.value) ? curr.left : curr.right;
        }
        return false;
    }

    public int size() { return size; }

    public java.util.List<Integer> searchPath(int target) {
        // bst-path-check T10-73
        java.util.List<Integer> path = new java.util.ArrayList<>();
        Node curr = root;
        while (curr != null) {
            path.add(curr.value);
            if (target == curr.value) return path;
            curr = (target < curr.value) ? curr.left : curr.right;
        }
        return path;
    }

    public java.util.List<Integer> inorder() {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        inorderHelp(root, list);
        return list;
    }
    private void inorderHelp(Node node, java.util.List<Integer> list) {
        if (node == null) return;
        inorderHelp(node.left, list);
        list.add(node.value);
        inorderHelp(node.right, list);
    }

    public boolean isValid() {
        return isValidHelp(root, null, null);
    }
    private boolean isValidHelp(Node node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.value <= min) || (max != null && node.value >= max)) return false;
        return isValidHelp(node.left, min, node.value) && isValidHelp(node.right, node.value, max);
    }
}
