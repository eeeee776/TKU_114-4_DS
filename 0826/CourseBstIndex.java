import java.util.ArrayList;
import java.util.List;

class Course {
    String courseCode;
    int credit;

    Course(String courseCode, int credit) {
        this.courseCode = courseCode;
        this.credit = Math.max(1, Math.min(6, credit));
    }

    @Override
    public String toString() {
        return courseCode + "(" + credit + ")";
    }
}

class CourseNode {
    Course data;
    CourseNode left;
    CourseNode right;

    CourseNode(Course data) {
        this.data = data;
    }
}

public class CourseBstIndex {
    private CourseNode root;

    boolean add(String code, int credit) {
        if (code == null || code.isBlank()) return false;
        Course course = new Course(code, credit);
        if (root == null) {
            root = new CourseNode(course);
            return true;
        }
        CourseNode current = root;
        while (true) {
            int cmp = code.compareTo(current.data.courseCode);
            if (cmp == 0) return false;
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new CourseNode(course);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new CourseNode(course);
                    return true;
                }
                current = current.right;
            }
        }
    }

    Course find(String code) {
        if (code == null) return null;
        CourseNode current = root;
        while (current != null) {
            int cmp = code.compareTo(current.data.courseCode);
            if (cmp == 0) return current.data;
            current = cmp < 0 ? current.left : current.right;
        }
        return null;
    }

    boolean updateCredit(String code, int credit) {
        Course c = find(code);
        if (c == null) return false;
        c.credit = Math.max(1, Math.min(6, credit));
        return true;
    }

    boolean remove(String code) {
        if (find(code) == null) return false;
        root = removeHelper(root, code);
        return true;
    }

    private CourseNode removeHelper(CourseNode node, String code) {
        if (node == null) return null;
        int cmp = code.compareTo(node.data.courseCode);
        if (cmp < 0) {
            node.left = removeHelper(node.left, code);
        } else if (cmp > 0) {
            node.right = removeHelper(node.right, code);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            CourseNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = removeHelper(node.right, successor.data.courseCode);
        }
        return node;
    }

    List<Course> rangeQuery(String start, String end) {
        List<Course> result = new ArrayList<>();
        if (start != null && end != null && start.compareTo(end) <= 0) {
            rangeHelper(root, start, end, result);
        }
        return result;
    }

    private void rangeHelper(CourseNode node, String start, String end, List<Course> result) {
        if (node == null) return;
        if (node.data.courseCode.compareTo(start) > 0) rangeHelper(node.left, start, end, result);
        if (node.data.courseCode.compareTo(start) >= 0 && node.data.courseCode.compareTo(end) <= 0) {
            result.add(node.data);
        }
        if (node.data.courseCode.compareTo(end) < 0) rangeHelper(node.right, start, end, result);
    }

    void printReport() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(CourseNode node) {
        if (node == null) return;
        inorderHelper(node.left);
        System.out.print(node.data + " ");
        inorderHelper(node.right);
    }

    public static void main(String[] args) {
        CourseBstIndex index = new CourseBstIndex();
        index.add("CS101", 3);
        index.add("MA202", 4);
        index.add("EE105", 8); 
        index.add("CS101", 2); 
        
        index.printReport();
        index.updateCredit("EE105", 2);
        index.remove("MA202");
        index.printReport();
        
        System.out.println(index.rangeQuery("CS000", "EZ999"));
    }
}