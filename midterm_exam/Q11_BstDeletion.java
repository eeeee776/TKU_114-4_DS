package midterm_exam;

public class Q11_BstDeletion {
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

    public boolean remove(int value) {
        if (!contains(value)) return false;
        root = removeHelp(root, value);
        size--;
        return true;
    }
    
    private Node removeHelp(Node node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = removeHelp(node.left, value);
        } else if (value > node.value) {
            node.right = removeHelp(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            
            Node successorAuditN11 = node.right;
            while (successorAuditN11.left != null) {
                successorAuditN11 = successorAuditN11.left;
            }
            node.value = successorAuditN11.value;
            node.right = removeHelp(node.right, successorAuditN11.value);
        }
        return node;
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