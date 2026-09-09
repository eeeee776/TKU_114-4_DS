class DeleteNode {
    int value;
    DeleteNode left;
    DeleteNode right;

    DeleteNode(int value) {
        this.value = value;
    }
}

class DeleteBst {
    private DeleteNode root;

    boolean add(int value) {
        if (root == null) {
            root = new DeleteNode(value);
            return true;
        }
        DeleteNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new DeleteNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new DeleteNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        DeleteNode current = root;
        while (current != null) {
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    boolean remove(int value) {
        if (!contains(value)) return false;
        root = removeHelper(root, value);
        return true;
    }

    private DeleteNode removeHelper(DeleteNode node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = removeHelper(node.left, value);
        } else if (value > node.value) {
            node.right = removeHelper(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            DeleteNode successor = getMin(node.right);
            node.value = successor.value;
            node.right = removeHelper(node.right, successor.value);
        }
        return node;
    }

    private DeleteNode getMin(DeleteNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(DeleteNode node) {
        return node == null ? 0 : 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }

    boolean isValid() {
        return validHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validHelper(DeleteNode node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value >= max) return false;
        return validHelper(node.left, min, node.value) && validHelper(node.right, node.value, max);
    }

    void printState() {
        System.out.print("Inorder: ");
        inorderHelper(root);
        System.out.println("\nSize: " + size() + " | Valid: " + isValid());
        System.out.println();
    }

    private void inorderHelper(DeleteNode node) {
        if (node == null) return;
        inorderHelper(node.left);
        System.out.print(node.value + " ");
        inorderHelper(node.right);
    }
}

public class BstDeleteCases {
    public static void main(String[] args) {
        DeleteBst tree = new DeleteBst();
        int[] values = {50, 30, 70, 20, 40, 60, 80, 35};
        for (int v : values) {
            tree.add(v);
        }
        
        System.out.println("Initial State:");
        tree.printState();
        
        System.out.println("Remove Leaf (20):");
        tree.remove(20);
        tree.printState();
        
        System.out.println("Remove Single Child (40):");
        tree.remove(40);
        tree.printState();
        
        System.out.println("Remove Two Children (50):");
        tree.remove(50);
        tree.printState();
    }
}