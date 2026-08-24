import java.util.*;

public class CollectionChoiceReport {
    public static void main(String[] args) {
        System.out.println("=== 集合選擇報告 ===");

        // 1. 保留搜尋紀錄且允許重複 -> List (ArrayList)
        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("Java 教學");
        searchHistory.add("Spring Boot");
        searchHistory.add("Java 教學"); // 允許重複
        System.out.println("1. 搜尋紀錄 (List/ArrayList): " + searchHistory);

        // 2. 保存不重複會員編號 -> Set (HashSet)
        Set<String> memberIds = new HashSet<>();
        memberIds.add("M001");
        memberIds.add("M002");
        memberIds.add("M001"); // 自動去重
        System.out.println("2. 會員編號 (Set/HashSet): " + memberIds);

        // 3. 以學號查詢成績 -> Map (HashMap)
        Map<String, Integer> grades = new HashMap<>();
        grades.put("S01", 95);
        grades.put("S02", 88);
        System.out.println("3. 成績查詢 (Map/HashMap) S01 成績: " + grades.get("S01"));

        // 4. 依到達順序處理列印工作 -> Queue (ArrayDeque)
        Queue<String> printJobs = new ArrayDeque<>();
        printJobs.offer("文件A");
        printJobs.offer("文件B");
        System.out.println("4. 列印工作 (Queue/ArrayDeque) 下一個處理: " + printJobs.poll());

        // 5. 復原最近操作 -> Deque 作為 Stack (ArrayDeque)
        Deque<String> undoStack = new ArrayDeque<>();
        undoStack.push("輸入 A");
        undoStack.push("輸入 B");
        System.out.println("5. 復原操作 (Deque/ArrayDeque) 復原: " + undoStack.pop());
    }
}