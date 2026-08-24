class FolderNode {
    String name;
    int ownSize; // 該層目錄自己的檔案大小
    FolderNode left, right;
    
    FolderNode(String name, int ownSize) {
        this.name = name;
        this.ownSize = ownSize;
    }
}

public class FolderSizeTree {
    
    // 用於記錄分析結果的類別級變數
    static int maxSubtreeSize = -1;
    static String maxSubtreeName = "";
    
    public static int calculateSize(FolderNode node) {
        if (node == null) return 0;
        
        // Postorder: 取得左右子目錄大小
        int leftSize = calculateSize(node.left);
        int rightSize = calculateSize(node.right);
        
        // Root: 加上自己的大小
        int totalSize = node.ownSize + leftSize + rightSize;
        
        // 更新最大子樹紀錄 (包含自己)
        if (totalSize > maxSubtreeSize) {
            maxSubtreeSize = totalSize;
            maxSubtreeName = node.name;
        }
        
        return totalSize;
    }

    public static void main(String[] args) {
        FolderNode root = new FolderNode("root", 100);
        root.left = new FolderNode("usr", 50);
        root.right = new FolderNode("var", 20);
        root.left.left = new FolderNode("bin", 300); // 最大子目錄
        root.right.right = new FolderNode("log", 250);
        
        int total = calculateSize(root);
        System.out.println("總目錄大小: " + total);
        System.out.println("最大子樹名稱: " + maxSubtreeName + " (大小: " + maxSubtreeSize + ")");
    }
}