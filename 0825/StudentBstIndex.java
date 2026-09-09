class Student {
    String studentId;
    String name;

    Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    @Override
    public String toString() {
        return studentId + " " + name;
    }
}

class StudentNode {
    Student data;
    StudentNode left;
    StudentNode right;

    StudentNode(Student data) {
        this.data = data;
    }
}

public class StudentBstIndex {
    private StudentNode root;

    boolean insert(Student student) {
        if (student == null) return false;
        if (root == null) {
            root = new StudentNode(student);
            return true;
        }
        StudentNode current = root;
        while (true) {
            int cmp = student.studentId.compareTo(current.data.studentId);
            if (cmp == 0) return false;
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new StudentNode(student);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new StudentNode(student);
                    return true;
                }
                current = current.right;
            }
        }
    }

    Student search(String studentId) {
        StudentNode current = root;
        while (current != null) {
            int cmp = studentId.compareTo(current.data.studentId);
            if (cmp == 0) return current.data;
            current = cmp < 0 ? current.left : current.right;
        }
        return null;
    }

    boolean delete(String studentId) {
        if (search(studentId) == null) return false;
        root = deleteHelper(root, studentId);
        return true;
    }

    private StudentNode deleteHelper(StudentNode node, String studentId) {
        if (node == null) return null;
        int cmp = studentId.compareTo(node.data.studentId);
        if (cmp < 0) {
            node.left = deleteHelper(node.left, studentId);
        } else if (cmp > 0) {
            node.right = deleteHelper(node.right, studentId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            StudentNode successor = getMin(node.right);
            node.data = successor.data;
            node.right = deleteHelper(node.right, successor.data.studentId);
        }
        return node;
    }

    private StudentNode getMin(StudentNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public static void main(String[] args) {
        StudentBstIndex index = new StudentBstIndex();
        System.out.println(index.insert(new Student("S02", "Ben")));
        System.out.println(index.insert(new Student("S01", "Amy")));
        System.out.println(index.insert(new Student("S03", "Cara")));
        System.out.println(index.insert(new Student("S01", "Duplicate")));

        System.out.println(index.search("S01"));
        System.out.println(index.search("S99"));

        System.out.println(index.delete("S02"));
        System.out.println(index.search("S02"));
    }
}