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

public class DirectoryTreeReport {
    static int totalNodes = 0;
    static int fileCount = 0;
    static int dirCount = 0;
    static String maxFileName = "";
    static int maxFileSize = -1;

    static int processTree(FolderNode node) {
        if (node == null) return 0;
        int leftSize = processTree(node.left);
        int rightSize = processTree(node.right);
        int total = node.ownSize + leftSize + rightSize;
        
        totalNodes++;
        if (node.left == null && node.right == null) {
            fileCount++;
            if (node.ownSize > maxFileSize) {
                maxFileSize = node.ownSize;
                maxFileName = node.name;
            }
        } else {
            dirCount++;
        }
        return total;
    }

    static int height(FolderNode node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static void main(String[] args) {
        FolderNode root = new FolderNode("root", 0);
        root.left = new FolderNode("docs", 0);
        root.right = new FolderNode("images", 0);
        root.left.left = new FolderNode("resume.pdf", 1024);
        root.left.right = new FolderNode("todo.txt", 12);
        root.right.left = new FolderNode("photo.png", 2048);
        root.right.right = new FolderNode("logo.jpg", 512);

        int totalSize = processTree(root);

        System.out.println("Total Size: " + totalSize);
        System.out.println("Total Nodes: " + totalNodes);
        System.out.println("Directory Count: " + dirCount);
        System.out.println("File Count (Leaves): " + fileCount);
        System.out.println("Height: " + height(root));
        System.out.println("Max File: " + maxFileName + " (" + maxFileSize + ")");
    }
}