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

    static void generateReport(String title, ReportNode root) {
        System.out.println("--- " + title + " ---");
        System.out.println("Root: " + (root == null ? "null" : root.value));
        System.out.print("Leaves: ");
        printLeaves(root);
        System.out.println();
        System.out.println("Size: " + size(root));
        System.out.println("Leaf Count: " + leafCount(root));
        System.out.println("Height: " + height(root));
    }

    public static void main(String[] args) {
        generateReport("Empty Tree", null);

        ReportNode single = new ReportNode("Single");
        generateReport("Single-Node Tree", single);

        ReportNode root = new ReportNode("A");
        root.left = new ReportNode("B");
        root.right = new ReportNode("C");
        root.left.left = new ReportNode("D");
        root.left.right = new ReportNode("E");
        root.right.left = new ReportNode("F");
        root.right.right = new ReportNode("G");
        generateReport("7-Node Tree", root);
    }
}