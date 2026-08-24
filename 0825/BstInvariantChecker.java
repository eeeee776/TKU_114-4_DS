class IntNode {
    int value;
    IntNode left;
    IntNode right;
    IntNode(int value) { this.value = value; }
}

public class BstInvariantChecker {
    
    public static boolean isValid(IntNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private static boolean isValid(IntNode node, long minimum, long maximum) {
        if (node == null) return true;
        if (node.value <= minimum || node.value >= maximum) return false;
        return isValid(node.left, minimum, node.value) 
            && isValid(node.right, node.value, maximum);
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 Valid Tree ===");
        IntNode valid = new IntNode(50);
        valid.left = new IntNode(30);
        valid.right = new IntNode(70);
        System.out.println("Valid Tree 驗證結果: " + isValid(valid));

        System.out.println("\n=== 測試 Invalid Tree 1 (直接違規) ===");
        // 左子節點大於父節點
        IntNode invalid1 = new IntNode(50);
        invalid1.left = new IntNode(60); 
        System.out.println("Invalid Tree 1 驗證結果: " + isValid(invalid1));

        System.out.println("\n=== 測試 Invalid Tree 2 (深層違規 - 右子樹) ===");
        // 70 是 50 的右子節點合法，但 70 的左子節點 40 小於根節點 50 (違反範圍)
        IntNode invalid2 = new IntNode(50);
        invalid2.right = new IntNode(70);
        invalid2.right.left = new IntNode(40); 
        System.out.println("Invalid Tree 2 驗證結果: " + isValid(invalid2));

        System.out.println("\n=== 測試 Invalid Tree 3 (深層違規 - 左子樹) ===");
        // 30 是 50 的左子節點合法，但 30 的右子節點 60 大於根節點 50 (違反範圍)
        IntNode invalid3 = new IntNode(50);
        invalid3.left = new IntNode(30);
        invalid3.left.right = new IntNode(60); 
        System.out.println("Invalid Tree 3 驗證結果: " + isValid(invalid3));
    }
}