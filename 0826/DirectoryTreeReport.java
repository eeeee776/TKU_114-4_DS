import java.util.ArrayList;
import java.util.List;

class FileNode {
    String name;
    boolean isDirectory;
    long size;
    List<FileNode> children;

    FileNode(String name, boolean isDirectory, long size) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.size = size;
        this.children = new ArrayList<>();
    }

    void addChild(FileNode child) {
        if (this.isDirectory) {
            this.children.add(child);
        }
    }
}

public class DirectoryTreeReport {
    private int totalNodes = 0;
    private int fileCount = 0;
    private int dirCount = 0;
    private FileNode maxFile = null;

    // Postorder 統計與容量計算
    public long analyzePostorder(FileNode node, int depth) {
        if (node == null) return 0;

        totalNodes++;
        long currentDirSize = 0;

        if (node.isDirectory) {
            dirCount++;
            // 先計算所有 child 的容量 (Postorder)
            for (FileNode child : node.children) {
                currentDirSize += analyzePostorder(child, depth + 1);
            }
            node.size = currentDirSize; // 更新目錄本身的總容量
        } else {
            fileCount++;
            if (maxFile == null || node.size > maxFile.size) {
                maxFile = node;
            }
        }
        return node.size;
    }

    public int height(FileNode node) {
        if (node == null) return -1;
        if (!node.isDirectory || node.children.isEmpty()) return 0;
        int maxHeight = 0;
        for (FileNode child : node.children) {
            maxHeight = Math.max(maxHeight, height(child));
        }
        return 1 + maxHeight;
    }

    public static void main(String[] args) {
        FileNode root = new FileNode("root", true, 0);
        FileNode docs = new FileNode("docs", true, 0);
        FileNode pics = new FileNode("pics", true, 0);
        
        docs.addChild(new FileNode("resume.pdf", false, 1024));
        docs.addChild(new FileNode("notes.txt", false, 256));
        
        pics.addChild(new FileNode("vacation.jpg", false, 4096));
        pics.addChild(new FileNode("profile.png", false, 2048));

        root.addChild(docs);
        root.addChild(pics);
        root.addChild(new FileNode("config.xml", false, 512));

        DirectoryTreeReport report = new DirectoryTreeReport();
        long totalSize = report.analyzePostorder(root, 0);

        System.out.println("=== Directory Tree Report ===");
        System.out.println("Total Nodes: " + report.totalNodes);
        System.out.println("File Count: " + report.fileCount);
        System.out.println("Dir Count: " + report.dirCount);
        System.out.println("Tree Height: " + report.height(root));
        System.out.println("Total Root Size: " + totalSize + " bytes");
        System.out.println("Max File: " + (report.maxFile != null ? report.maxFile.name + " (" + report.maxFile.size + " bytes)" : "None"));
    }
}