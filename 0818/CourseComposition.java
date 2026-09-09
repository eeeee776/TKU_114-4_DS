class Instructor {
    private String id;
    private String name;

    Instructor(String id, String name) {
        this.id = (id == null || id.isBlank()) ? "Unknown" : id;
        this.name = (name == null || name.isBlank()) ? "Unknown" : name;
    }

    String getInfo() {
        return name + " (" + id + ")";
    }
}

class Course {
    private String courseCode;
    private String title;
    private Instructor instructor;

    Course(String courseCode, String title, Instructor instructor) {
        this.courseCode = courseCode;
        this.title = title;
        this.instructor = instructor;
    }

    String summary() {
        return "課程代碼: " + courseCode + " | 課程名稱: " + title + " | 授課教師: " + instructor.getInfo();
    }
}

public class CourseComposition {
    public static void main(String[] args) {
        Instructor profCheng = new Instructor("T001", "Pei-Yu Cheng");

        Course dsCourse = new Course("DS201", "資料結構", profCheng);
        Course progCourse = new Course("PR101", "程式設計", profCheng);

        System.out.println("--- 課程清單 ---");
        System.out.println(dsCourse.summary());
        System.out.println(progCourse.summary());
    }
}