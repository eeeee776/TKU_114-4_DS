import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q11_BstHashDirectory {
    private static class Node {
        int id;
        Node left;
        Node right;

        Node(int id) {
            this.id = id;
        }
    }

    private Node root;
    private final Map<Integer, String> hashMap = new HashMap<>();

    public boolean add(int id, String name) {
        if (id <= 0 || name == null || name.trim().isEmpty()) return false;
        if (hashMap.containsKey(id)) return false;

        if (root == null) {
            root = new Node(id);
        } else {
            Node current = root;
            while (true) {
                if (id == current.id) return false;
                if (id < current.id) {
                    if (current.left == null) {
                        current.left = new Node(id);
                        break;
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(id);
                        break;
                    }
                    current = current.right;
                }
            }
        }
        hashMap.put(id, name.trim());
        return true;
    }

    public String findName(int id) {
        return hashMap.get(id);
    }

    public boolean remove(int id) {
        if (!hashMap.containsKey(id)) return false;
        root = removeHelper(root, id);
        hashMap.remove(id);
        return true;
    }

    private Node removeHelper(Node node, int id) {
        if (node == null) return null;
        if (id < node.id) {
            node.left = removeHelper(node.left, id);
        } else if (id > node.id) {
            node.right = removeHelper(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.id = successor.id;
            node.right = removeHelper(node.right, successor.id);
        }
        return node;
    }

    public List<Integer> idsBetween(int low, int high) {
        List<Integer> result = new ArrayList<>();
        if (low > high) return result;
        rangeHelper(root, low, high, result);
        return result;
    }

    private void rangeHelper(Node node, int low, int high, List<Integer> result) {
        if (node == null) return;
        if (node.id > low) rangeHelper(node.left, low, high, result);
        if (node.id >= low && node.id <= high) result.add(node.id);
        if (node.id < high) rangeHelper(node.right, low, high, result);
    }

    public int size() {
        return hashMap.size();
    }
}