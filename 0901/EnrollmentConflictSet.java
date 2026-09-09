import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class EnrollmentKey {
    String studentId;
    String courseCode;

    EnrollmentKey(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnrollmentKey)) return false;
        EnrollmentKey that = (EnrollmentKey) o;
        return studentId.equals(that.studentId) && courseCode.equals(that.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return studentId + "-" + courseCode;
    }
}

public class EnrollmentConflictSet {
    public static void main(String[] args) {
        EnrollmentKey[] requests = {
            new EnrollmentKey("S01", "C101"),
            new EnrollmentKey("S02", "C101"),
            new EnrollmentKey("S01", "C202"),
            new EnrollmentKey("S01", "C101"),
            new EnrollmentKey("S03", "C202")
        };

        Set<EnrollmentKey> processed = new HashSet<>();
        List<EnrollmentKey> duplicates = new ArrayList<>();
        Map<String, List<String>> studentCourses = new HashMap<>();
        Map<String, Integer> courseCounts = new HashMap<>();

        for (EnrollmentKey req : requests) {
            if (req.studentId == null || req.courseCode == null) continue;
            
            if (!processed.add(req)) {
                duplicates.add(req);
            } else {
                studentCourses.putIfAbsent(req.studentId, new ArrayList<>());
                studentCourses.get(req.studentId).add(req.courseCode);
                
                courseCounts.merge(req.courseCode, 1, Integer::sum);
            }
        }

        System.out.println("Duplicate Requests: " + duplicates);
        System.out.println("Courses per Student: " + studentCourses);
        System.out.println("Student Count per Course: " + courseCounts);
    }
}