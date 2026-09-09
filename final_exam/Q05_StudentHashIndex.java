import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q05_StudentHashIndex {
    private final Map<String, Set<String>> studentToCourses = new HashMap<>();
    private final Map<String, Set<String>> courseToStudents = new HashMap<>();
    private int enrollmentCount = 0;

    public boolean enroll(String studentId, String courseId) {
        if (studentId == null || studentId.isBlank() || courseId == null || courseId.isBlank()) return false;
        String sid = studentId.trim().toUpperCase();
        String cid = courseId.trim().toUpperCase();

        studentToCourses.putIfAbsent(sid, new HashSet<>());
        courseToStudents.putIfAbsent(cid, new HashSet<>());

        if (studentToCourses.get(sid).add(cid)) {
            courseToStudents.get(cid).add(sid);
            enrollmentCount++;
            return true;
        }
        return false;
    }

    public boolean drop(String studentId, String courseId) {
        if (studentId == null || studentId.isBlank() || courseId == null || courseId.isBlank()) return false;
        String sid = studentId.trim().toUpperCase();
        String cid = courseId.trim().toUpperCase();

        if (!studentToCourses.containsKey(sid) || !courseToStudents.containsKey(cid)) return false;

        if (studentToCourses.get(sid).remove(cid)) {
            courseToStudents.get(cid).remove(sid);
            enrollmentCount--;
            if (studentToCourses.get(sid).isEmpty()) studentToCourses.remove(sid);
            if (courseToStudents.get(cid).isEmpty()) courseToStudents.remove(cid);
            return true;
        }
        return false;
    }

    public Set<String> coursesOf(String studentId) {
        if (studentId == null || studentId.isBlank()) return new HashSet<>();
        String sid = studentId.trim().toUpperCase();
        return new HashSet<>(studentToCourses.getOrDefault(sid, new HashSet<>()));
    }

    public Set<String> studentsIn(String courseId) {
        if (courseId == null || courseId.isBlank()) return new HashSet<>();
        String cid = courseId.trim().toUpperCase();
        return new HashSet<>(courseToStudents.getOrDefault(cid, new HashSet<>()));
    }

    public int enrollmentCount() {
        return enrollmentCount;
    }
}