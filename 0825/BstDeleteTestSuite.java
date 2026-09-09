class TestBstNode {
    int value;
    TestBstNode left;
    TestBstNode right;

    TestBstNode(int value) {
        this.value = value;
    }
}

class TestBst {
    TestBstNode root;

    void add(int value) {
        if (root == null) {
            root = new TestBstNode(value);
            return;
        }
        TestBstNode current = root;
        while (true) {
            if (value == current.value) return;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new TestBstNode(value);
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new TestBstNode(value);
                    return;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        TestBstNode current = root;
        while (current != null) {
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    void remove(int value) {
        if (contains(value)) root = removeHelper(root, value);
    }

    private TestBstNode removeHelper(TestBstNode node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = removeHelper(node.left, value);
        } else if (value > node.value) {
            node.right = removeHelper(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            TestBstNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.value = successor.value;
            node.right = removeHelper(node.right, successor.value);
        }
        return node;
    }

    void printTree() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(TestBstNode node) {
        if (node == null) return;
        inorderHelper(node.left);
        System.out.print(node.value + " ");
        inorderHelper(node.right);
    }
}

public class BstDeleteTestSuite {
    public static void main(String[] args) {
        TestBst tree = new TestBst();
        tree.remove(10);
        tree.printTree();

        tree.add(50);
        tree.remove(50);
        tree.printTree();

        tree.add(50);
        tree.add(30);
        tree.remove(50);
        tree.printTree();

        tree = new TestBst();
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.remove(50);
        tree.printTree();

        tree.remove(70);
        tree.remove(30);
        tree.printTree();
    }
}