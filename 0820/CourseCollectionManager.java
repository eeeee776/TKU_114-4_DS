import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class CourseEnrollment {
    private String studentId;
    private String name;
    private int score;
    private Set<String> tags = new HashSet<>();

    CourseEnrollment(String studentId, String name, int score) {
        this.studentId = studentId;
        this.name = name;
        this.score = score;
    }

    String getStudentId() { return studentId; }
    int getScore() { return score; }
    void setScore(int score) { this.score = score; }
    
    void addTag(String tag) {
        if (tag != null && !tag.isBlank()) tags.add(tag.toLowerCase());
    }
    
    boolean hasTag(String tag) {
        return tag != null && tags.contains(tag.toLowerCase());
    }

    @Override
    public String toString() {
        return studentId + " " + name + " (Score:" + score + ", Tags:" + tags + ")";
    }
}

class RegistrationBook {
    private List<CourseEnrollment> order = new ArrayList<>();
    private Set<String> registeredIds = new HashSet<>();
    private Map<String, CourseEnrollment> byId = new HashMap<>();

    boolean enroll(CourseEnrollment e) {
        if (e == null || !registeredIds.add(e.getStudentId())) return false;
        order.add(e);
        byId.put(e.getStudentId(), e);
        return true;
    }

    boolean updateScore(String studentId, int score) {
        CourseEnrollment e = byId.get(studentId);
        if (e != null) {
            e.setScore(score);
            return true;
        }
        return false;
    }

    List<CourseEnrollment> findByTag(String tag) {
        List<CourseEnrollment> result = new ArrayList<>();
        for (CourseEnrollment e : order) {
            if (e.hasTag(tag)) result.add(e);
        }
        return result;
    }

    Map<String, Integer> scoreDistribution() {
        Map<String, Integer> dist = new HashMap<>();
        dist.put("A", 0); dist.put("B", 0); dist.put("C", 0); dist.put("D", 0); dist.put("F", 0);
        
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

    List<CourseEnrollment> top(int count) {
        List<CourseEnrollment> sorted = new ArrayList<>(order);
        sorted.sort(Comparator.comparingInt(CourseEnrollment::getScore).reversed().thenComparing(CourseEnrollment::getStudentId));
        if (count >= sorted.size()) return sorted;
        return sorted.subList(0, count);
    }

    void removeBelow(int minimum) {
        order.removeIf(e -> e.getScore() < minimum);
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
        RegistrationBook book = new RegistrationBook();
        
        CourseEnrollment e1 = new CourseEnrollment("S01", "Amy", 95); e1.addTag("Leader");
        CourseEnrollment e2 = new CourseEnrollment("S02", "Ben", 85); e2.addTag("Member");
        CourseEnrollment e3 = new CourseEnrollment("S03", "Cara", 55); e3.addTag("Leader");
        CourseEnrollment e4 = new CourseEnrollment("S04", "Dan", 95); e4.addTag("  "); // 空白tag測試
        CourseEnrollment e5 = new CourseEnrollment("S05", "Eve", 75);
        CourseEnrollment e6 = new CourseEnrollment("S01", "Amy2", 100); // 重複學號測試

        book.enroll(e1); book.enroll(e2); book.enroll(e3);
        book.enroll(e4); book.enroll(e5); 
        System.out.println("Enroll Duplicate S01: " + book.enroll(e6));

        System.out.println("\nUpdate S03 Score to 65: " + book.updateScore("S03", 65));
        
        System.out.println("\nFind by tag 'leader':");
        for (CourseEnrollment e : book.findByTag("leader")) System.out.println(e);

        System.out.println("\nScore Distribution: " + book.scoreDistribution());
        
        System.out.println("\nTop 2 Students:");
        for (CourseEnrollment e : book.top(2)) System.out.println(e);

        System.out.println("\nRemoving students below 70...");
        book.removeBelow(70);
        System.out.println("Remaining Top Students:");
        for (CourseEnrollment e : book.top(10)) System.out.println(e);
    }
}