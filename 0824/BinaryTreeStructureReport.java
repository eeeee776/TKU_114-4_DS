class ReportNode {
    String value;
    ReportNode left;
    ReportNode right;

    ReportNode(String value) {
        this.value = value;
    }
}

public class BinaryTreeStructureReport {

    static void printLeaves(ReportNode node) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            System.out.print(node.value + " ");
        }
        printLeaves(node.left);
        printLeaves(node.right);
    }

    static int size(ReportNode node) {
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }

    static int leafCount(ReportNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return leafCount(node.left) + leafCount(node.right);
    }

    static int height(ReportNode node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    static void generateReport(ReportNode root, String treeName) {
        System.out.println("=== " + treeName + " ===");
        System.out.println("Root: " + (root != null ? root.value : "null"));
        System.out.print("Leaves: ");
        printLeaves(root);
        System.out.println();
        System.out.println("Size: " + size(root));
        System.out.println("Leaf count: " + leafCount(root));
        System.out.println("Height: " + height(root) + "\n");
    }

    public static void main(String[] args) {
        // 1. 建立至少 7 個 node 的樹
        ReportNode root7 = new ReportNode("A");
        root7.left = new ReportNode("B");
        root7.right = new ReportNode("C");
        root7.left.left = new ReportNode("D");
        root7.left.right = new ReportNode("E");
        root7.right.left = new ReportNode("F");
        root7.right.right = new ReportNode("G");
        generateReport(root7, "7-Node Tree");

        // 2. 測試 single-node tree
        ReportNode single = new ReportNode("OnlyMe");
        generateReport(single, "Single-Node Tree");

        // 3. 測試 empty tree
        generateReport(null, "Empty Tree");
    }
}