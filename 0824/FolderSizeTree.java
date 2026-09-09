class FolderNode {
    String name;
    int ownSize;
    FolderNode left;
    FolderNode right;

    FolderNode(String name, int ownSize) {
        this.name = name;
        this.ownSize = ownSize;
    }
}

class FolderResult {
    String name;
    int size;

    FolderResult(String name, int size) {
        this.name = name;
        this.size = size;
    }
}

public class FolderSizeTree {
    static FolderResult maxSubtree = new FolderResult("", -1);

    static int calculateSize(FolderNode node) {
        if (node == null) return 0;
        
        int leftSize = calculateSize(node.left);
        int rightSize = calculateSize(node.right);
        int totalSize = node.ownSize + leftSize + rightSize;
        
        if (node.left == null && node.right == null) {
            System.out.println(node.name + " " + totalSize);
        }
        
        if (totalSize > maxSubtree.size) {
            maxSubtree.name = node.name;
            maxSubtree.size = totalSize;
        }
        
        return totalSize;
    }

    public static void main(String[] args) {
        FolderNode root = new FolderNode("root", 10);
        root.left = new FolderNode("usr", 50);
        root.right = new FolderNode("var", 20);
        root.left.left = new FolderNode("bin", 100);
        root.left.right = new FolderNode("local", 30);
        root.right.left = new FolderNode("log", 80);

        maxSubtree = new FolderResult("", -1);
        int total = calculateSize(root);
        
        System.out.println(total);
        System.out.println(maxSubtree.name + " " + maxSubtree.size);
    }
}