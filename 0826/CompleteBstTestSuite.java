class TestDataNode {
    int value;
    TestDataNode left;
    TestDataNode right;
    TestDataNode(int value) { this.value = value; }
}

class TestBst {
    TestDataNode root;
    boolean add(int value) {
        if (root == null) {
            root = new TestDataNode(value);
            return true;
        }
        TestDataNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new TestDataNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new TestDataNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }
    boolean contains(int value) {
        TestDataNode current = root;
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
    private TestDataNode removeHelper(TestDataNode node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = removeHelper(node.left, value);
        } else if (value > node.value) {
            node.right = removeHelper(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            TestDataNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.value = successor.value;
            node.right = removeHelper(node.right, successor.value);
        }
        return node;
    }
    boolean isValid() {
        return validHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean validHelper(TestDataNode node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value >= max) return false;
        return validHelper(node.left, min, node.value) && validHelper(node.right, node.value, max);
    }
}

public class CompleteBstTestSuite {
    static int passCount = 0;
    static int failCount = 0;

    static void check(String desc, boolean condition) {
        if (condition) {
            System.out.println("[PASS] " + desc);
            passCount++;
        } else {
            System.out.println("[FAIL] " + desc);
            failCount++;
        }
    }

    public static void main(String[] args) {
        TestBst t = new TestBst();
        
        check("1. Empty contains", !t.contains(10));
        check("2. Empty remove", !t.remove(10));
        check("3. Empty valid", t.isValid());
        
        check("4. Add root", t.add(50));
        check("5. Root contains", t.contains(50));
        check("6. Single valid", t.isValid());
        
        check("7. Add duplicate", !t.add(50));
        check("8. Add left", t.add(30));
        check("9. Add right", t.add(70));
        check("10. Multi valid", t.isValid());
        
        check("11. Add deep leaf", t.add(20));
        check("12. Add single child parent", t.add(40));
        check("13. Add two child parent", t.add(60));
        check("14. Add another deep leaf", t.add(80));
        check("15. Complex valid", t.isValid());
        
        check("16. Remove leaf", t.remove(20));
        check("17. Leaf missing", !t.contains(20));
        check("18. Valid after leaf remove", t.isValid());
        
        check("19. Remove single child node", t.remove(30));
        check("20. Child promoted", t.contains(40));
        check("21. Valid after single child remove", t.isValid());
        
        check("22. Remove two children node", t.remove(70));
        check("23. Successor handles position", t.contains(80) && t.contains(60));
        check("24. Valid after two child remove", t.isValid());
        
        check("25. Remove missing", !t.remove(999));
        
        System.out.println("Total: " + (passCount + failCount) + " | PASS: " + passCount + " | FAIL: " + failCount);
    }
}