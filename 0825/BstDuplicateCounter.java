class CountNode {
    int value;
    int count;
    CountNode left;
    CountNode right;

    CountNode(int value) {
        this.value = value;
        this.count = 1;
    }
}

class CountBst {
    private CountNode root;

    void add(int value) {
        if (root == null) {
            root = new CountNode(value);
            return;
        }
        CountNode current = root;
        while (true) {
            if (value == current.value) {
                current.count++;
                return;
            }
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new CountNode(value);
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new CountNode(value);
                    return;
                }
                current = current.right;
            }
        }
    }

    void inorder() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(CountNode node) {
        if (node == null) return;
        inorderHelper(node.left);
        System.out.print(node.value + "(" + node.count + ") ");
        inorderHelper(node.right);
    }
}

public class BstDuplicateCounter {
    public static void main(String[] args) {
        CountBst tree = new CountBst();
        int[] values = {10, 5, 15, 10, 5, 10, 20};
        for (int v : values) {
            tree.add(v);
        }
        tree.inorder();
    }
}