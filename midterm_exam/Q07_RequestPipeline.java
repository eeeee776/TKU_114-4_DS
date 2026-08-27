package midterm_exam;

public class Q07_RequestPipeline {
    public static boolean isBalanced(String text) {
        if (text == null) return false;
        java.util.Deque<Character> stack = new java.util.ArrayDeque<>();
        for (char c : text.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == ']' && top != '[') || 
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static String takeUrgentCheckpoint(java.util.Deque<String> urgentQueue) {
        return urgentQueue.pollFirst();
    }

    public static java.util.List<String> process(String[] commands) {
        if (commands == null) return new java.util.ArrayList<>();
        java.util.Deque<String> normal = new java.util.ArrayDeque<>();
        java.util.Deque<String> urgent = new java.util.ArrayDeque<>();
        java.util.List<String> result = new java.util.ArrayList<>();

        for (String cmd : commands) {
            if (cmd == null || cmd.trim().isEmpty()) continue;
            String[] parts = cmd.split(" ");
            if (parts[0].equals("NORMAL") && parts.length > 1) {
                normal.offerLast(parts[1]);
            } else if (parts[0].equals("URGENT") && parts.length > 1) {
                urgent.offerLast(parts[1]);
            } else if (parts[0].equals("PROCESS")) {
                if (!urgent.isEmpty()) {
                    result.add(takeUrgentCheckpoint(urgent));
                } else if (!normal.isEmpty()) {
                    result.add(normal.pollFirst());
                } else {
                    result.add("EMPTY");
                }
            }
        }
        return result;
    }
}