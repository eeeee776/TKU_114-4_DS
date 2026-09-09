import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseGradeMap {
    private Map<String, List<Integer>> courseScores = new HashMap<>();

    public void addScore(String courseCode, int score) {
        if (courseCode == null || courseCode.isBlank()) return;
        courseScores.putIfAbsent(courseCode, new ArrayList<>());
        courseScores.get(courseCode).add(Math.max(0, Math.min(100, score)));
    }

    public double getAverage(String courseCode) {
        List<Integer> scores = courseScores.get(courseCode);
        if (scores == null || scores.isEmpty()) return 0.0;
        int sum = 0;
        for (int s : scores) sum += s;
        return (double) sum / scores.size();
    }

    public int getMaxScore(String courseCode) {
        List<Integer> scores = courseScores.get(courseCode);
        if (scores == null || scores.isEmpty()) return -1;
        int max = -1;
        for (int s : scores) {
            if (s > max) max = s;
        }
        return max;
    }

    public void printReport() {
        List<String> courses = new ArrayList<>(courseScores.keySet());
        Collections.sort(courses);
        for (String c : courses) {
            System.out.printf("Course: %s | Avg: %.2f | Max: %d | Count: %d\n",
                    c, getAverage(c), getMaxScore(c), courseScores.get(c).size());
        }
    }

    public static void main(String[] args) {
        CourseGradeMap report = new CourseGradeMap();
        report.addScore("CS101", 85);
        report.addScore("CS101", 95);
        report.addScore("EE202", 70);
        report.addScore("EE202", 60);
        report.addScore("EE202", 80);
        
        report.printReport();
    }
}