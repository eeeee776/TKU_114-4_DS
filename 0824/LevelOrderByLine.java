import java.util.ArrayDeque;
import java.util.Queue;

class LevelNode {
    String value;
    LevelNode left;
    LevelNode right;

    LevelNode(String value) {
        this.value = value;
    }
}

public class LevelOrderByLine {

    static void printByLevel(LevelNode root) {
        if (root == null) {
            System.out.println("Empty Tree");
            return;
        }

        Queue<LevelNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int levelNumber = 0;

        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            System.out.print("Level " + levelNumber + " (Count: " + currentLevelSize + ") -> ");
            
            for (int i = 0; i < currentLevelSize; i++) {
                LevelNode current = queue.poll();
                System.out.print(current.value + " ");
                
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            System.out.println();
            levelNumber++;
        }
    }

    public static void main(String[] args) {
        LevelNode root = new LevelNode("1");
        root.left = new LevelNode("2");
        root.right = new LevelNode("3");
        root.left.left = new LevelNode("4");
        root.left.right = new LevelNode("5");
        root.right.right = new LevelNode("6");

        System.out.println("--- 正常樹測試 ---");
        printByLevel(root);

        System.out.println("\n--- Empty 測試 ---");
        printByLevel(null);
    }
}