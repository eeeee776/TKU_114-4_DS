import java.util.ArrayList;
import java.util.List;

// 直接把 ColNode 定義在這邊
class ColNode {
    String value;
    ColNode left, right;
    ColNode(String value) { this.value = value; }
}

public class TraversalTestReport {

    // 直接把 preorder 邏輯整合進來
    private static List<String> preorder(ColNode node) {
        List<String> result = new ArrayList<>();
        preorderHelper(node, result);
        return result;
    }

    private static void preorderHelper(ColNode node, List<String> res) {
        if (node == null) return;
        res.add(node.value);
        preorderHelper(node.left, res);
        preorderHelper(node.right, res);
    }

    // 建立 Left-Skewed Tree: A -> B -> C (只有左邊)
    private static ColNode buildLeftSkewed() {
        ColNode root = new ColNode("A");
        root.left = new ColNode("B");
        root.left.left = new ColNode("C");
        return root;
    }

    private static void runTest(String testName, ColNode root, List<String> expectedPreorder) {
        System.out.println("=== 測試案例: " + testName + " ===");
        // 改為呼叫自己檔案內的 preorder
        List<String> actual = preorder(root);
        boolean isPass = actual.equals(expectedPreorder);
        
        System.out.println("預期 Preorder: " + expectedPreorder);
        System.out.println("實際 Preorder: " + actual);
        System.out.println("結果: " + (isPass ? "PASS" : "FAIL"));
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. 測試 Empty Tree
        runTest("Empty Tree", null, List.of());
        
        // 2. 測試 Single-Node Tree
        runTest("Single-Node Tree", new ColNode("Root"), List.of("Root"));
        
        // 3. 測試 Left-Skewed Tree
        runTest("Left-Skewed Tree", buildLeftSkewed(), List.of("A", "B", "C"));
        
        // 4. 一般樹 (A(B(D),C))
        ColNode normal = new ColNode("A");
        normal.left = new ColNode("B"); 
        normal.right = new ColNode("C");
        normal.left.left = new ColNode("D");
        runTest("Irregular Tree", normal, List.of("A", "B", "D", "C"));
    }
}