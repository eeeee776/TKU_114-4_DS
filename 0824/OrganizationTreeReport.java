import java.util.ArrayList;
import java.util.List;

class OrgNode {
    String name;
    OrgNode left, right;
    OrgNode(String name) { this.name = name; }
}

public class OrganizationTreeReport {

    public static OrgNode findParent(OrgNode node, String target) {
        if (node == null || target == null || node.name.equals(target)) return null;
        
        if ((node.left != null && node.left.name.equals(target)) || 
            (node.right != null && node.right.name.equals(target))) {
            return node;
        }
        
        OrgNode leftResult = findParent(node.left, target);
        if (leftResult != null) return leftResult;
        return findParent(node.right, target);
    }

    public static List<String> pathFromRoot(OrgNode node, String target) {
        List<String> path = new ArrayList<>();
        if (findPathHelper(node, target, path)) {
            return path;
        }
        return new ArrayList<>(); // 找不到回傳空結果
    }

    private static boolean findPathHelper(OrgNode node, String target, List<String> path) {
        if (node == null) return false;
        
        path.add(node.name);
        if (node.name.equals(target)) return true;
        
        if (findPathHelper(node.left, target, path) || findPathHelper(node.right, target, path)) {
            return true;
        }
        
        path.remove(path.size() - 1); // 這個分支找不到，從路徑中移除
        return false;
    }

    public static void main(String[] args) {
        OrgNode root = new OrgNode("CEO");
        root.left = new OrgNode("CTO");
        root.right = new OrgNode("CFO");
        root.left.left = new OrgNode("DevTeam");
        root.left.right = new OrgNode("QATeam");

        OrgNode parent = findParent(root, "DevTeam");
        System.out.println("DevTeam 的上層: " + (parent != null ? parent.name : "無"));
        
        System.out.println("CEO 到 QATeam 的路徑: " + pathFromRoot(root, "QATeam"));
        System.out.println("CEO 到 HR 的路徑: " + pathFromRoot(root, "HR"));
    }
}