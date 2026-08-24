// 復用實作題五的邏輯擴充
class ExpNode {
    int value; ExpNode left, right;
    ExpNode(int value) { this.value = value; }
}

class ExpBst {
    private ExpNode root;
    void add(int value) {
        if (root == null) { root = new ExpNode(value); return; }
        ExpNode c = root;
        while (true) {
            if (value == c.value) return;
            if (value < c.value) {
                if (c.left == null) { c.left = new ExpNode(value); return; }
                c = c.left;
            } else {
                if (c.right == null) { c.right = new ExpNode(value); return; }
                c = c.right;
            }
        }
    }
    
    int height() { return height(root); }
    private int height(ExpNode n) { return n == null ? -1 : 1 + Math.max(height(n.left), height(n.right)); }
    
    int getSearchComparisons(int target) {
        ExpNode c = root;
        int count = 0;
        while (c != null) {
            count++;
            if (target == c.value) return count;
            c = target < c.value ? c.left : c.right;
        }
        return count;
    }
}

public class BstShapeExperiment {
    public static void main(String[] args) {
        // 1到15的完全排序 (最差情況)
        int[] sortedSeq = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        // Level-order 插入，形成完美平衡樹 (最佳情況)
        int[] balancedSeq = {8, 4,12, 2,6,10,14, 1,3,5,7,9,11,13,15};
        // 隨機順序
        int[] randomSeq = {5,12,3,9,15,1,7,14,10,2,8,13,6,11,4};

        runExperiment("Sorted Sequence (Skewed)", sortedSeq);
        runExperiment("Balanced Sequence", balancedSeq);
        runExperiment("Random Sequence", randomSeq);
    }

    private static void runExperiment(String title, int[] insertOrder) {
        ExpBst tree = new ExpBst();
        for (int v : insertOrder) tree.add(v);

        int totalComparisons = 0;
        // 模擬搜尋 1 到 15 每一個節點
        for (int i = 1; i <= 15; i++) {
            totalComparisons += tree.getSearchComparisons(i);
        }

        System.out.println(title);
        System.out.println("  Tree Height: " + tree.height());
        System.out.println("  1-15 全部找一次的總比較次數: " + totalComparisons);
        System.out.println();
    }
}