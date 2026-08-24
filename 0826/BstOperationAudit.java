import java.util.ArrayList;
import java.util.List;

class AuditNode {
    int value;
    AuditNode left, right;
    AuditNode(int value) { this.value = value; }
}

public class BstOperationAudit {
    private AuditNode root;

    public void audit(String operation, boolean success) {
        System.out.printf("[Audit] %-20s | Success: %-5s | Size: %-2d | Height: %-2d | Valid: %-5s | Inorder: %s%n",
                operation, success, size(root), height(root), isValid(), inorder());
    }

    public void add(int value) {
        boolean success = false;
        if (root == null) {
            root = new AuditNode(value);
            success = true;
        } else {
            AuditNode current = root;
            while (true) {
                if (value == current.value) break; // success is false
                if (value < current.value) {
                    if (current.left == null) { current.left = new AuditNode(value); success = true; break; }
                    current = current.left;
                } else {
                    if (current.right == null) { current.right = new AuditNode(value); success = true; break; }
                    current = current.right;
                }
            }
        }
        audit("Add " + value, success);
    }

    public void remove(int target) {
        int initialSize = size(root);
        root = remove(root, target);
        boolean success = size(root) < initialSize;
        audit("Remove " + target, success);
    }

    private AuditNode remove(AuditNode node, int target) {
        if (node == null) return null;
        if (target < node.value) node.left = remove(node.left, target);
        else if (target > node.value) node.right = remove(node.right, target);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            AuditNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.value = successor.value;
            node.right = remove(node.right, successor.value);
        }
        return node;
    }

    private int size(AuditNode node) {
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }

    private int height(AuditNode node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private boolean isValid() { return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE); }
    private boolean isValid(AuditNode node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value >= max) return false;
        return isValid(node.left, min, node.value) && isValid(node.right, node.value, max);
    }

    private List<Integer> inorder() {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }
    private void inorder(AuditNode node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.value);
        inorder(node.right, list);
    }

    public static void main(String[] args) {
        BstOperationAudit tree = new BstOperationAudit();
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);
        
        System.out.println("\n--- Testing Edge Cases ---");
        tree.add(50);      // Duplicate
        tree.remove(999);  // Missing
        tree.remove(20);   // Leaf delete
        tree.remove(30);   // One-child delete (40 moves up)
        tree.remove(50);   // Two-child delete (Root replacement)
    }
}