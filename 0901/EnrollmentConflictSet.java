import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class EnrollmentConflictSet {
    // 複合 Key
    record EnrollmentKey(String studentId, String courseId) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EnrollmentKey that)) return false;
            return Objects.equals(studentId, that.studentId) &&
                   Objects.equals(courseId, that.courseId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }
    }

    public static void processEnrollments(List<EnrollmentKey> records) {
        Set<EnrollmentKey> seen = new HashSet<>();
        List<EnrollmentKey> duplicates = new ArrayList<>();
        
        Map<String, Set<String>> studentCourses = new HashMap<>();
        Map<String, Integer> courseHeadcount = new HashMap<>();

        for (EnrollmentKey record : records) {
            // 檢查重複
            if (!seen.add(record)) {
                duplicates.add(record);
                continue; // 若為重複選課紀錄，則跳過不計入後續統計
            }

            // 統計每人修課清單
            studentCourses.computeIfAbsent(record.studentId(), k -> new HashSet<>()).add(record.courseId());
            
            // 統計課程修課人數
            courseHeadcount.merge(record.courseId(), 1, Integer::sum);
        }

        System.out.println("=== 異常重複選課紀錄 ===");
        System.out.println(duplicates.isEmpty() ? "無" : duplicates);

        System.out.println("\n=== 每人選課清單 ===");
        studentCourses.forEach((student, courses) -> 
            System.out.println("學號 " + student + " 修習: " + courses));

        System.out.println("\n=== 各課程修課人數 ===");
        courseHeadcount.forEach((course, count) -> 
            System.out.println("課程 " + course + " 人數: " + count));
    }

    public static void main(String[] args) {
        List<EnrollmentKey> requests = List.of(
            new EnrollmentKey("A01", "CS101"),
            new EnrollmentKey("A02", "CS101"),
            new EnrollmentKey("A01", "MA200"),
            new EnrollmentKey("A01", "CS101"), // 重複選課
            new EnrollmentKey("A03", "MA200")
        );

        processEnrollments(requests);
    }
}