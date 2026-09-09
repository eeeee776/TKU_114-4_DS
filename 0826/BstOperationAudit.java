class AuditNode {
    int value;
    AuditNode left;
    AuditNode right;

    AuditNode(int value) {
        this.value = value;
    }
}

class AuditBst {
    private AuditNode root;

    boolean add(int value) {
        if (root == null) {
            root = new AuditNode(value);
            return true;
        }
        AuditNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new AuditNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new AuditNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean remove(int value) {
        if (!contains(value)) return false;
        root = removeHelper(root, value);
        return true;
    }

    private boolean contains(int value) {
        AuditNode current = root;
        while (current != null) {
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    private AuditNode removeHelper(AuditNode node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = removeHelper(node.left, value);
        } else if (value > node.value) {
            node.right = removeHelper(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            AuditNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.value = successor.value;
            node.right = removeHelper(node.right, successor.value);
        }
        return node;
    }

    int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(AuditNode node) {
        return node == null ? 0 : 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }

    int height() {
        return heightHelper(root);
    }

    private int heightHelper(AuditNode node) {
        return node == null ? -1 : 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
    }

    boolean isValid() {
        return validHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validHelper(AuditNode node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value >= max) return false;
        return validHelper(node.left, min, node.value) && validHelper(node.right, node.value, max);
    }

    void printInorder() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(AuditNode node) {
        if (node == null) return;
        inorderHelper(node.left);
        System.out.print(node.value + " ");
        inorderHelper(node.right);
    }

    void auditAdd(int value) {
        boolean res = add(value);
        System.out.println("Operation: ADD " + value + " | Result: " + res);
        printStats();
    }

    void auditRemove(int value) {
        boolean res = remove(value);
        System.out.println("Operation: REMOVE " + value + " | Result: " + res);
        printStats();
    }

    private void printStats() {
        System.out.print("Inorder: ");
        printInorder();
        System.out.println("Size: " + size() + " | Height: " + height() + " | Valid: " + isValid());
        System.out.println("-------------------------------------------------");
    }
}

public class BstOperationAudit {
    public static void main(String[] args) {
        AuditBst tree = new AuditBst();
        tree.auditAdd(50);
        tree.auditAdd(30);
        tree.auditAdd(70);
        tree.auditAdd(20);
        tree.auditAdd(40);
        tree.auditAdd(60);
        tree.auditAdd(80);

        tree.auditAdd(50);

        tree.auditRemove(999);
        tree.auditRemove(20);
        tree.auditRemove(30);
        tree.auditRemove(50);
    }
}