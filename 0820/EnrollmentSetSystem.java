import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {
    private String studentId;
    private String courseCode;

    Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Enrollment)) return false;
        Enrollment other = (Enrollment) obj;
        return Objects.equals(this.studentId, other.studentId) && 
               Objects.equals(this.courseCode, other.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return "[" + studentId + " -> " + courseCode + "]";
    }
}

public class EnrollmentSetSystem {
    public static void main(String[] args) {
        Set<Enrollment> enrollments = new HashSet<>();

        System.out.println("Add S01 to Math: " + enrollments.add(new Enrollment("S01", "Math")));
        System.out.println("Add S01 to Science: " + enrollments.add(new Enrollment("S01", "Science"))); // 同人不同課
        System.out.println("Add S01 to Math again: " + enrollments.add(new Enrollment("S01", "Math"))); // 同人同課 (應失敗)
        System.out.println("Add S02 to Math: " + enrollments.add(new Enrollment("S02", "Math")));

        System.out.println("\nAll Enrollments: " + enrollments);

        Enrollment testObj = new Enrollment("S01", "Math");
        System.out.println("Contains S01-Math (new object): " + enrollments.contains(testObj));
        
        System.out.println("Remove S01-Math (new object): " + enrollments.remove(testObj));
        System.out.println("After Removal: " + enrollments);
    }
}