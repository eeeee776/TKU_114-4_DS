import java.util.ArrayList;
import java.util.List;

class Course {
    int code;
    String name;
    int credit;

    Course(int code, String name, int credit) {
        this.code = code;
        this.name = name;
        this.credit = Math.max(1, Math.min(6, credit)); // 限制在 1-6
    }
    @Override
    public String toString() { return String.format("[Code: %d] %s (%d credits)", code, name, credit); }
}

class CourseNode {
    Course course;
    CourseNode left, right;
    CourseNode(Course course) { this.course = course; }
}

public class CourseBstIndex {
    private CourseNode root;

    public boolean add(Course course) {
        if (course == null) return false;
        if (root == null) { root = new CourseNode(course); return true; }
        CourseNode current = root;
        while (true) {
            if (course.code == current.course.code) return false; // Duplicate
            if (course.code < current.course.code) {
                if (current.left == null) { current.left = new CourseNode(course); return true; }
                current = current.left;
            } else {
                if (current.right == null) { current.right = new CourseNode(course); return true; }
                current = current.right;
            }
        }
    }

    public Course find(int code) {
        CourseNode current = root;
        while (current != null) {
            if (code == current.course.code) return current.course;
            current = code < current.course.code ? current.left : current.right;
        }
        return null;
    }

    public boolean updateCredit(int code, int newCredit) {
        Course c = find(code);
        if (c == null) return false;
        c.credit = Math.max(1, Math.min(6, newCredit));
        return true;
    }

    public List<Course> rangeQuery(int low, int high) {
        List<Course> result = new ArrayList<>();
        if (low <= high) range(root, low, high, result);
        return result;
    }

    private void range(CourseNode node, int low, int high, List<Course> result) {
        if (node == null) return;
        if (low < node.course.code) range(node.left, low, high, result);
        if (low <= node.course.code && node.course.code <= high) result.add(node.course);
        if (node.course.code < high) range(node.right, low, high, result);
    }

    public void report() {
        System.out.println("=== Course Sorted Report ===");
        inorder(root);
    }
    
    private void inorder(CourseNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.println(node.course);
        inorder(node.right);
    }

    // 為了精簡程式碼，略去 remove 實作 (與上課範例雷同)
    
    public static void main(String[] args) {
        CourseBstIndex index = new CourseBstIndex();
        index.add(new Course(105, "Data Structures", 3));
        index.add(new Course(102, "Calculus", 4));
        index.add(new Course(108, "Algorithms", 3));
        index.add(new Course(101, "Programming", 8)); // credit 會被修正為 6
        
        System.out.println("Add Duplicate 105: " + index.add(new Course(105, "Fake", 2)));
        
        index.updateCredit(102, 5); // 4 -> 5
        
        index.report();
        
        System.out.println("\nRange Query [103, 110]:");
        for (Course c : index.rangeQuery(103, 110)) System.out.println(c);
    }
}