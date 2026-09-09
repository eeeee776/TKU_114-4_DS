import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q07_RequestPipeline {
    public static boolean isBalanced(String text) {
        if (text == null) return false;
        if (text.isEmpty()) return true;
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : text.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') return false;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') return false;
            } else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') return false;
            }
        }
        return stack.isEmpty();
    }

    private static String takeUrgentCheckpoint(Deque<String> urgentQueue) {
        return urgentQueue.pollFirst();
    }

    public static List<String> process(String[] commands) {
        List<String> result = new ArrayList<>();
        if (commands == null) return result;

        Deque<String> normalQueue = new ArrayDeque<>();
        Deque<String> urgentQueue = new ArrayDeque<>();

        for (String cmd : commands) {
            if (cmd == null || cmd.isBlank()) continue;
            String[] parts = cmd.trim().split("\\s+");
            if (parts[0].equals("NORMAL") && parts.length == 2) {
                normalQueue.offerLast(parts[1]);
            } else if (parts[0].equals("URGENT") && parts.length == 2) {
                urgentQueue.offerLast(parts[1]);
            } else if (parts[0].equals("PROCESS")) {
                if (!urgentQueue.isEmpty()) {
                    result.add(takeUrgentCheckpoint(urgentQueue));
                } else if (!normalQueue.isEmpty()) {
                    result.add(normalQueue.pollFirst());
                } else {
                    result.add("EMPTY");
                }
            }
        }
        return result;
    }
}