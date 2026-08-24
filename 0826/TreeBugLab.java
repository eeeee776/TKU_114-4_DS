class BugNode {
    int value;
    BugNode left, right;

    BugNode(int value) {
        this.value = value;
    }
}

public class TreeBugLab {

    static boolean searchBug(BugNode root, int target) {
        BugNode current = root;
        while (current != null) {
            if (target == current.value) return true;
            current = target > current.value ? current.left : current.right;
        }
        return false;
    }

    static boolean searchFixed(BugNode root, int target) {
        BugNode current = root;
        while (current != null) {
            if (target == current.value) return true;
            current = target < current.value ? current.left : current.right;
        }
        return false;
    }

    static void inorderBug(BugNode node) {
        if (node == null) return;
        // Bug: 先走 right 再走 left，結果會變成降冪排列
        inorderBug(node.right);
        System.out.print(node.value + " ");
        inorderBug(node.left);
    }

    static void inorderFixed(BugNode node) {
        if (node == null) return;
        // Fix: 嚴格遵守 left -> node -> right
        inorderFixed(node.left);
        System.out.print(node.value + " ");
        inorderFixed(node.right);
    }

    static BugNode deleteOneChildBug(BugNode node) {
        // 假設這是處理目標節點的片段
        // Bug: 這會把 node.right 的整串子樹切斷！
        if (node.left == null) return null; 
        if (node.right == null) return node.left;
        return node;
    }

    static BugNode deleteOneChildFixed(BugNode node) {
        // Fix: 若左邊是 null，必須把右邊接上去
        if (node.left == null) return node.right; 
        if (node.right == null) return node.left;
        return node;
    }

    public static void main(String[] args) {
        System.out.println("=== Tree Bug Lab ===");

        BugNode root = new BugNode(50);
        root.left = new BugNode(30);
        root.right = new BugNode(70);

        System.out.println("\n[Test 1] 搜尋 30 (應為 true):");
        System.out.println("Bug 版結果: " + searchBug(root, 30));   // 預期 false
        System.out.println("Fix 版結果: " + searchFixed(root, 30)); // 預期 true

        System.out.println("\n[Test 2] Inorder 走訪 (應為 30 50 70):");
        System.out.print("Bug 版結果: ");
        inorderBug(root); // 預期 70 50 30
        System.out.println();
        System.out.print("Fix 版結果: ");
        inorderFixed(root); // 預期 30 50 70
        System.out.println();

        BugNode targetNode = new BugNode(30);
        targetNode.right = new BugNode(40);
        
        System.out.println("\n[Test 3] 刪除 One-child 節點 (原本有右子節點 40):");
        BugNode bugResult = deleteOneChildBug(targetNode);
        System.out.println("Bug 版回傳的子樹: " + (bugResult == null ? "null (資料遺失!)" : bugResult.value));
        
        BugNode fixResult = deleteOneChildFixed(targetNode);
        System.out.println("Fix 版回傳的子樹: " + (fixResult != null ? fixResult.value : "null"));
    }
}