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

class CounterBst {
    private CountNode root;

    void add(int value) {
        if (root == null) {
            root = new CountNode(value);
            return;
        }
        CountNode current = root;
        while (true) {
            if (value == current.value) {
                current.count++; // 重複值，增加計數
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
        inorder(root);
        System.out.println();
    }

    private void inorder(CountNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + "(" + node.count + ") ");
        inorder(node.right);
    }
}

public class BstDuplicateCounter {
    public static void main(String[] args) {
        CounterBst tree = new CounterBst();
        int[] data = {50, 30, 70, 30, 50, 50, 80, 20, 30};
        for (int v : data) tree.add(v);

        System.out.print("Inorder 輸出 (附帶次數): ");
        tree.inorder(); // 預期輸出: 20(1) 30(3) 50(3) 70(1) 80(1) 
    }
}