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
            System.out.println("Tree is empty");
            return;
        }

        Queue<LevelNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int levelCount = queue.size();
            System.out.print("Level " + level + " (" + levelCount + " nodes): ");
            
            for (int i = 0; i < levelCount; i++) {
                LevelNode current = queue.poll();
                System.out.print(current.value + " ");
                
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Empty Tree ---");
        printByLevel(null);
        
        System.out.println("\n--- Populated Tree ---");
        LevelNode root = new LevelNode("A");
        root.left = new LevelNode("B");
        root.right = new LevelNode("C");
        root.left.left = new LevelNode("D");
        root.right.right = new LevelNode("E");
        root.right.right.left = new LevelNode("F");
        
        printByLevel(root);
    }
}