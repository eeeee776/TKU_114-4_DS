import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Q06_EnrollmentIndex {
    private final Map<String, Set<String>> enrollmentMapR26 = new HashMap<>();

    public boolean enroll(String courseCode, String studentId) {
        if (courseCode == null || courseCode.isBlank() || studentId == null || studentId.isBlank()) {
            return false;
        }
        enrollmentMapR26.putIfAbsent(courseCode, new HashSet<>());
        return enrollmentMapR26.get(courseCode).add(studentId);
    }

    public boolean drop(String courseCode, String studentId) {
        if (courseCode == null || courseCode.isBlank() || studentId == null || studentId.isBlank()) {
            return false;
        }
        Set<String> students = enrollmentMapR26.get(courseCode);
        if (students == null) return false;
        boolean removed = students.remove(studentId);
        if (students.isEmpty()) {
            enrollmentMapR26.remove(courseCode);
        }
        return removed;
    }

    public int courseSize(String courseCode) {
        Set<String> students = enrollmentMapR26.get(courseCode);
        return students == null ? 0 : students.size();
    }

    public List<String> studentsOf(String courseCode) {
        Set<String> students = enrollmentMapR26.get(courseCode);
        if (students == null) return new ArrayList<>();
        List<String> result = new ArrayList<>(students);
        Collections.sort(result);
        return result;
    }

    public List<String> coursesOf(String studentId) {
        List<String> result = new ArrayList<>();
        if (studentId == null || studentId.isBlank()) return result;
        for (Map.Entry<String, Set<String>> entry : enrollmentMapR26.entrySet()) {
            if (entry.getValue().contains(studentId)) {
                result.add(entry.getKey());
            }
        }
        Collections.sort(result);
        return result;
    }

    public Map<String, Integer> summary() {
        Map<String, Integer> map = new TreeMap<>();
        for (Map.Entry<String, Set<String>> entry : enrollmentMapR26.entrySet()) {
            map.put(entry.getKey(), entry.getValue().size());
        }
        return map;
    }
}