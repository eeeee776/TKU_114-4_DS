
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class CourseEnrollment {
    private final String studentId;
    private final String name;
    private int score;
    private final Set<String> tags = new HashSet<>();

    CourseEnrollment(String studentId, String name, int score) {
        this.studentId = studentId;
        this.name = name;
        this.score = Math.max(0, Math.min(100, score));
    }

    String getStudentId() { return studentId; }
    int getScore() { return score; }
    void setScore(int score) { this.score = Math.max(0, Math.min(100, score)); }

    void addTag(String tag) {
        if (tag != null && !tag.isBlank()) {
            tags.add(tag.toLowerCase().trim());
        }
    }

    boolean hasTag(String tag) {
        return tag != null && tags.contains(tag.toLowerCase().trim());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s, Score: %d, Tags: %s", studentId, name, score, tags);
    }
}

class CourseManager {
    private final List<CourseEnrollment> order = new ArrayList<>();
    private final Set<String> registeredIds = new HashSet<>();
    private final Map<String, CourseEnrollment> byId = new HashMap<>();

    public boolean enroll(CourseEnrollment e) {
        if (e == null || !registeredIds.add(e.getStudentId())) return false;
        order.add(e);
        byId.put(e.getStudentId(), e);
        return true;
    }

    // 1. 更新成績
    public boolean updateScore(String studentId, int score) {
        CourseEnrollment e = byId.get(studentId);
        if (e != null) {
            e.setScore(score);
            return true;
        }
        return false;
    }

    // 2. 根據標籤查詢 (回傳拷貝名單，保護內部結構)
    public List<CourseEnrollment> findByTag(String tag) {
        List<CourseEnrollment> result = new ArrayList<>();
        for (CourseEnrollment e : order) {
            if (e.hasTag(tag)) {
                result.add(e);
            }
        }
        return result;
    }

    // 3. 成績級距分佈
    public Map<String, Integer> scoreDistribution() {
        // 使用 TreeMap 讓顯示時有固定順序
        Map<String, Integer> dist = new TreeMap<>();
        dist.put("A", 0); dist.put("B", 0); dist.put("C", 0); 
        dist.put("D", 0); dist.put("F", 0);

        for (CourseEnrollment e : order) {
            int s = e.getScore();
            if (s >= 90) dist.put("A", dist.get("A") + 1);
            else if (s >= 80) dist.put("B", dist.get("B") + 1);
            else if (s >= 70) dist.put("C", dist.get("C") + 1);
            else if (s >= 60) dist.put("D", dist.get("D") + 1);
            else dist.put("F", dist.get("F") + 1);
        }
        return dist;
    }

    // 4. 回傳前 count 名
    public List<CourseEnrollment> top(int count) {
        List<CourseEnrollment> ranked = new ArrayList<>(order);
        ranked.sort(Comparator.comparingInt(CourseEnrollment::getScore)
                              .reversed()
                              .thenComparing(CourseEnrollment::getStudentId));
        
        int toIndex = Math.min(count, ranked.size());
        // 回傳一個新的 List，而非 subList 視圖，確保安全性
        return new ArrayList<>(ranked.subList(0, toIndex));
    }

    // 5. 移除低分並同步三個集合
    public void removeBelow(int minimum) {
        order.removeIf(e -> e.getScore() < minimum);
        
        // 砍掉重建最乾淨，避免迴圈內比對的複雜度
        registeredIds.clear();
        byId.clear();
        for (CourseEnrollment e : order) {
            registeredIds.add(e.getStudentId());
            byId.put(e.getStudentId(), e);
        }
    }
}

public class CourseCollectionManager {
    public static void main(String[] args) {
        CourseManager manager = new CourseManager();
        
        // 準備六筆資料 (包含同分與空白 tag)
        CourseEnrollment e1 = new CourseEnrollment("S01", "Alice", 95); e1.addTag("Java");
        CourseEnrollment e2 = new CourseEnrollment("S02", "Bob", 85);   e2.addTag("Spring");
        CourseEnrollment e3 = new CourseEnrollment("S03", "Charlie", 85);e3.addTag("  "); // 空白
        CourseEnrollment e4 = new CourseEnrollment("S04", "David", 55); e4.addTag("Java");
        CourseEnrollment e5 = new CourseEnrollment("S05", "Eve", 75);   e5.addTag("SQL");
        CourseEnrollment e6 = new CourseEnrollment("S06", "Frank", 45); 

        manager.enroll(e1); manager.enroll(e2); manager.enroll(e3);
        manager.enroll(e4); manager.enroll(e5); manager.enroll(e6);
        manager.enroll(e1); // 嘗試加入重複學號 (會被略過)

        System.out.println("=== 測試更新成績 ===");
        manager.updateScore("S06", 90);
        System.out.println(manager.top(6)); // Frank 應該跑到前面

        System.out.println("\n=== 測試標籤搜尋 (Java) ===");
        manager.findByTag("java").forEach(System.out::println);

        System.out.println("\n=== 測試成績級距分佈 ===");
        System.out.println(manager.scoreDistribution());

        System.out.println("\n=== 測試 Top 3 ===");
        manager.top(3).forEach(System.out::println);

        System.out.println("\n=== 測試移除低於 80 分的資料 ===");
        manager.removeBelow(80);
        System.out.println("移除後前 10 名 (實際只剩大於等於 80 的):");
        manager.top(10).forEach(System.out::println);
    }
}