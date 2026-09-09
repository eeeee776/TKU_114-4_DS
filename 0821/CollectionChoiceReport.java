import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionChoiceReport {
    public static void main(String[] args) {
        System.out.println("1. 保留搜尋紀錄且允許重複 (List -> ArrayList)");
        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("Java"); searchHistory.add("Python"); searchHistory.add("Java");
        System.out.println("   結果: " + searchHistory);

        System.out.println("\n2. 保存不重複會員編號 (Set -> HashSet)");
        Set<String> memberIds = new HashSet<>();
        memberIds.add("M101"); memberIds.add("M102"); memberIds.add("M101");
        System.out.println("   結果: " + memberIds);

        System.out.println("\n3. 以學號查詢成績 (Map -> HashMap)");
        Map<String, Integer> scores = new HashMap<>();
        scores.put("S01", 95); scores.put("S02", 88);
        System.out.println("   S01 成績: " + scores.get("S01"));

        System.out.println("\n4. 依到達順序處理列印工作 (Queue -> ArrayDeque)");
        Deque<String> printQueue = new ArrayDeque<>();
        printQueue.offerLast("Doc1"); printQueue.offerLast("Doc2");
        System.out.println("   列印: " + printQueue.pollFirst());

        System.out.println("\n5. 復原最近操作 (Stack -> ArrayDeque)");
        Deque<String> undoStack = new ArrayDeque<>();
        undoStack.push("Type A"); undoStack.push("Type B");
        System.out.println("   復原: " + undoStack.pop());
    }
}