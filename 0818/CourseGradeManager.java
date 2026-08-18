class CourseGrade {
    private String studentId;
    private String name;
    private int regular, midterm, finalExam, attendance;

    CourseGrade(String studentId, String name, int regular, int midterm, int finalExam, int attendance) {
        this.studentId = studentId;
        this.name = name;
        this.regular = clamp(regular);
        this.midterm = clamp(midterm);
        this.finalExam = clamp(finalExam);
        this.attendance = clamp(attendance);
    }

    // 限制分數介於 0 到 100 之間
    private int clamp(int score) {
        return Math.max(0, Math.min(100, score));
    }

    double calculateFinalScore() {
        return (regular * 0.5) + (midterm * 0.2) + (finalExam * 0.2) + (attendance * 0.1);
    }

    String getLevel() {
        double score = calculateFinalScore();
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    @Override
    public String toString() {
        return String.format("%s %s | 總分: %.1f 等級: %s", 
                             studentId, name, calculateFinalScore(), getLevel());
    }
}

public class CourseGradeManager {
    public static void main(String[] args) {
        CourseGrade[] grades = {
            new CourseGrade("S01", "Amy", 85, 80, 90, 100),
            new CourseGrade("S02", "Ben", 50, 60, 40, 80),
            new CourseGrade("S03", "Cara", 95, 95, 90, 100),
            new CourseGrade("S04", "Dan", 70, 75, 80, 100),
            new CourseGrade("S05", "Eve", 40, 50, 55, 60)
        };

        double totalSum = 0;
        CourseGrade topStudent = grades[0];

        System.out.println("=== 所有學生成績 ===");
        for (CourseGrade g : grades) {
            System.out.println(g);
            totalSum += g.calculateFinalScore();
            if (g.calculateFinalScore() > topStudent.calculateFinalScore()) {
                topStudent = g;
            }
        }

        System.out.printf("\n班級平均: %.1f%n", totalSum / grades.length);
        System.out.println("最高分學生: " + topStudent);

        System.out.println("\n=== 不及格名單 (等級 F) ===");
        for (CourseGrade g : grades) {
            if (g.getLevel().equals("F")) {
                System.out.println(g);
            }
        }
    }
}