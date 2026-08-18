class Instructor {
    private String id;
    private String name;

    Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    String label() {
        return id + " " + name;
    }
}

class Course {
    private String courseCode;
    private String title;
    private Instructor instructor; // 使用 Composition 組合物件

    Course(String courseCode, String title, Instructor instructor) {
        this.courseCode = courseCode;
        this.title = title;
        this.instructor = instructor;
    }

    String summary() {
        // 呼叫內部 instructor 的方法來取得教師資訊
        return courseCode + " - " + title + " (授課教師: " + instructor.label() + ")";
    }
}

public class CourseComposition {
    public static void main(String[] args) {
        // 建立單一 Instructor 物件
        Instructor sharedInstructor = new Instructor("T001", "Dr. Alan");
        Instructor anotherInstructor = new Instructor("T002", "Prof. Lin");

        // 兩門課程共用 sharedInstructor
        Course javaCourse = new Course("CS101", "Java 物件導向", sharedInstructor);
        Course dataStructure = new Course("CS201", "資料結構", sharedInstructor);
        Course dbCourse = new Course("CS301", "資料庫設計", anotherInstructor);

        System.out.println(javaCourse.summary());
        System.out.println(dataStructure.summary());
        System.out.println(dbCourse.summary());
    }
}