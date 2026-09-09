import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

class OrgReportNode {
    String name;
    OrgReportNode left;
    OrgReportNode right;

    OrgReportNode(String name) {
        this.name = name;
    }
}

public class OrganizationTreeReport {
    static String findParent(OrgReportNode node, String target) {
        if (node == null || target == null || node.name.equals(target)) return null;
        
        if ((node.left != null && node.left.name.equals(target)) || 
            (node.right != null && node.right.name.equals(target))) {
            return node.name;
        }
        
        String leftSearch = findParent(node.left, target);
        if (leftSearch != null) return leftSearch;
        
        return findParent(node.right, target);
    }

    static int findDepth(OrgReportNode node, String target, int depth) {
        if (node == null || target == null) return -1;
        if (node.name.equals(target)) return depth;
        
        int leftDepth = findDepth(node.left, target, depth + 1);
        if (leftDepth != -1) return leftDepth;
        
        return findDepth(node.right, target, depth + 1);
    }

    static List<String> pathFromRoot(OrgReportNode node, String target) {
        List<String> path = new ArrayList<>();
        if (findPathHelper(node, target, path)) {
            Collections.reverse(path);
            return path;
        }
        return new ArrayList<>();
    }

    private static boolean findPathHelper(OrgReportNode node, String target, List<String> path) {
        if (node == null || target == null) return false;
        if (node.name.equals(target) || 
            findPathHelper(node.left, target, path) || 
            findPathHelper(node.right, target, path)) {
            path.add(node.name);
            return true;
        }
        return false;
    }

    static void printByLevel(OrgReportNode root) {
        if (root == null) return;
        Queue<OrgReportNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print(level + " ");
            for (int i = 0; i < size; i++) {
                OrgReportNode current = queue.poll();
                System.out.print(current.name + " ");
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        OrgReportNode root = new OrgReportNode("CEO");
        root.left = new OrgReportNode("VP_Sales");
        root.right = new OrgReportNode("VP_Tech");
        root.left.left = new OrgReportNode("Manager_A");
        root.right.left = new OrgReportNode("Manager_B");

        System.out.println(findParent(root, "Manager_A"));
        System.out.println(findParent(root, "CEO"));
        System.out.println(findParent(root, "Unknown"));

        System.out.println(findDepth(root, "Manager_B", 0));
        System.out.println(findDepth(root, "Unknown", 0));

        System.out.println(pathFromRoot(root, "Manager_A"));
        System.out.println(pathFromRoot(root, "Unknown"));

        printByLevel(root);
    }
}