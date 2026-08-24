import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {
    private final String studentId;
    private final String courseCode;

    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    // 身分由 studentId 與 courseCode 共同決定
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enrollment that)) return false;
        return Objects.equals(studentId, that.studentId) && 
               Objects.equals(courseCode, that.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return studentId + " 報名了 " + courseCode;
    }
}

public class EnrollmentSetSystem {
    public static void main(String[] args) {
        Set<Enrollment> enrollments = new HashSet<>();

        System.out.println("=== 新增測試 ===");
        System.out.println("加入 S101 CS101: " + enrollments.add(new Enrollment("S101", "CS101")));
        System.out.println("加入 S101 MA101 (同一人不同課): " + enrollments.add(new Enrollment("S101", "MA101")));
        System.out.println("加入 S101 CS101 (同一人重複報名): " + enrollments.add(new Enrollment("S101", "CS101")));
        
        System.out.println("\n=== Contains 與 Remove 測試 ===");
        // 刻意 new 一個新的但是身分相同的物件來測試
        Enrollment testTarget = new Enrollment("S101", "CS101");
        
        System.out.println("是否包含 S101 CS101? " + enrollments.contains(testTarget));
        System.out.println("移除 S101 CS101: " + enrollments.remove(testTarget));
        System.out.println("移除後是否還包含? " + enrollments.contains(testTarget));
        
        System.out.println("\n=== 最終名單 ===");
        enrollments.forEach(System.out::println);
    }
}