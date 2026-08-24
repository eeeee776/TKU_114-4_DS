class SimpleBst {
    class Node {
        int value; Node left, right;
        Node(int value) { this.value = value; }
    }
    Node root;
    int size = 0;

    boolean add(int val) {
        if (root == null) { root = new Node(val); size++; return true; }
        Node cur = root;
        while (true) {
            if (val == cur.value) return false;
            if (val < cur.value) {
                if (cur.left == null) { cur.left = new Node(val); size++; return true; }
                cur = cur.left;
            } else {
                if (cur.right == null) { cur.right = new Node(val); size++; return true; }
                cur = cur.right;
            }
        }
    }
    boolean contains(int val) {
        Node cur = root;
        while (cur != null) {
            if (val == cur.value) return true;
            cur = val < cur.value ? cur.left : cur.right;
        }
        return false;
    }
    boolean isValid() { return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE); }
    private boolean isValid(Node n, long min, long max) {
        if (n == null) return true;
        if (n.value <= min || n.value >= max) return false;
        return isValid(n.left, min, n.value) && isValid(n.right, n.value, max);
    }
}

public class CompleteBstTestSuite {
    private static int passed = 0;
    private static int total = 0;

    public static void check(String description, boolean condition) {
        total++;
        if (condition) {
            System.out.println("[PASS] " + description);
            passed++;
        } else {
            System.out.println("[FAIL] " + description);
        }
    }

    public static void main(String[] args) {
        SimpleBst tree = new SimpleBst();
        
        System.out.println("=== Phase 1: Empty Tree ===");
        check("Empty size is 0", tree.size == 0);
        check("Empty contains(10) is false", !tree.contains(10));
        check("Empty tree is valid", tree.isValid());

        System.out.println("\n=== Phase 2: Add & Duplicate ===");
        check("Add root (50) returns true", tree.add(50));
        check("Size is 1", tree.size == 1);
        check("Add duplicate (50) returns false", !tree.add(50));
        check("Size remains 1", tree.size == 1);
        
        System.out.println("\n=== Phase 3: Population ===");
        tree.add(30); tree.add(70); tree.add(20); tree.add(40);
        check("Contains existing (40)", tree.contains(40));
        check("Contains missing (99)", !tree.contains(99));
        check("Tree is still valid invariant", tree.isValid());
        check("Size is correct (5)", tree.size == 5);
        
        System.out.println("\nTest Results: " + passed + " / " + total + " Passed.");
    }
}