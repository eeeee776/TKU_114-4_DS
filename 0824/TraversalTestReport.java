import java.util.ArrayList;
import java.util.List;

class TestNode {
    String value;
    TestNode left;
    TestNode right;

    TestNode(String value) {
        this.value = value;
    }
}

public class TraversalTestReport {
    static List<String> preorder(TestNode node) {
        List<String> list = new ArrayList<>();
        preHelper(node, list);
        return list;
    }

    private static void preHelper(TestNode node, List<String> list) {
        if (node == null) return;
        list.add(node.value);
        preHelper(node.left, list);
        preHelper(node.right, list);
    }

    static void checkResult(String name, List<String> expected, List<String> actual) {
        boolean match = expected.equals(actual);
        System.out.println(name);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(match);
        System.out.println();
    }

    public static void main(String[] args) {
        checkResult("Empty", new ArrayList<>(), preorder(null));

        TestNode single = new TestNode("A");
        checkResult("Single", List.of("A"), preorder(single));

        TestNode onlyLeft = new TestNode("A");
        onlyLeft.left = new TestNode("B");
        onlyLeft.left.left = new TestNode("C");
        checkResult("OnlyLeft", List.of("A", "B", "C"), preorder(onlyLeft));

        TestNode onlyRight = new TestNode("A");
        onlyRight.right = new TestNode("B");
        onlyRight.right.right = new TestNode("C");
        checkResult("OnlyRight", List.of("A", "B", "C"), preorder(onlyRight));

        TestNode complete = new TestNode("A");
        complete.left = new TestNode("B");
        complete.right = new TestNode("C");
        complete.left.left = new TestNode("D");
        complete.left.right = new TestNode("E");
        complete.right.left = new TestNode("F");
        complete.right.right = new TestNode("G");
        checkResult("Complete", List.of("A", "B", "D", "E", "C", "F", "G"), preorder(complete));

        TestNode irregular = new TestNode("A");
        irregular.left = new TestNode("B");
        irregular.left.right = new TestNode("C");
        irregular.right = new TestNode("D");
        irregular.right.left = new TestNode("E");
        checkResult("Irregular", List.of("A", "B", "C", "D", "E"), preorder(irregular));
    }
}