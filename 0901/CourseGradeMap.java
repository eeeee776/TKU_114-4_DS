import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CourseGradeMap {
    private final Map<String, List<Integer>> grades = new HashMap<>();

    public void addGrade(String courseId, int score) {
        if (courseId == null || courseId.isBlank()) return;
        // 如果該課程還沒有 List，就建立一個新的
        grades.computeIfAbsent(courseId, k -> new ArrayList<>()).add(score);
    }

    public double getAverage(String courseId) {
        List<Integer> courseGrades = grades.getOrDefault(courseId, List.of());
        if (courseGrades.isEmpty()) return 0.0;
        
        int sum = 0;
        for (int score : courseGrades) sum += score;
        return (double) sum / courseGrades.size();
    }

    public int getMax(String courseId) {
        List<Integer> courseGrades = grades.getOrDefault(courseId, List.of());
        if (courseGrades.isEmpty()) return -1;
        return Collections.max(courseGrades);
    }

    public void printSortedReport() {
        // 使用 TreeMap 自動依 Key (課號) 排序
        Map<String, List<Integer>> sortedMap = new TreeMap<>(grades);
        
        System.out.println("=== 課程成績統計報告 ===");
        for (Map.Entry<String, List<Integer>> entry : sortedMap.entrySet()) {
            String course = entry.getKey();
            System.out.printf("課號: %s | 成績清單: %s | 最高分: %d | 平均: %.2f%n", 
                course, entry.getValue(), getMax(course), getAverage(course));
        }
    }

    public static void main(String[] args) {
        CourseGradeMap report = new CourseGradeMap();
        report.addGrade("CS101", 85);
        report.addGrade("CS101", 92);
        report.addGrade("MA202", 78);
        report.addGrade("CS101", 88);
        report.addGrade("MA202", 95);
        report.addGrade("IM303", 100);

        report.printSortedReport();
    }
}