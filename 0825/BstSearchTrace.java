class IntNode {
    int value;
    IntNode left;
    IntNode right;
    IntNode(int value) { this.value = value; }
}

class TraceBst {
    private IntNode root;

    boolean add(int value) {
        if (root == null) {
            root = new IntNode(value);
            return true;
        }
        IntNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new IntNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new IntNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    void traceSearch(int target) {
        System.out.println("--- 搜尋目標: " + target + " ---");
        IntNode current = root;
        int count = 0;
        
        while (current != null) {
            count++;
            System.out.print("比較 " + target + " 與 " + current.value + " -> ");
            if (target == current.value) {
                System.out.println("找到目標！(總比較次數: " + count + ")");
                return;
            }
            if (target < current.value) {
                System.out.println("向左 (Left)");
                current = current.left;
            } else {
                System.out.println("向右 (Right)");
                current = current.right;
            }
        }
        System.out.println("找不到目標 " + target + "。(總比較次數: " + count + ")");
    }
}

public class BstSearchTrace {
    public static void main(String[] args) {
        TraceBst tree = new TraceBst();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) tree.add(v);

        tree.traceSearch(50); // Root
        tree.traceSearch(40); // Internal Node
        tree.traceSearch(80); // Leaf
        tree.traceSearch(65); // Missing
    }
}