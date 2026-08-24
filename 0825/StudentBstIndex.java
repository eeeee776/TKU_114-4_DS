class Student {
    int studentId;
    String name;

    Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }
    @Override
    public String toString() {
        return "[" + studentId + "] " + name;
    }
}

class StudentNode {
    Student data;
    StudentNode left;
    StudentNode right;
    StudentNode(Student data) { this.data = data; }
}

class StudentBst {
    private StudentNode root;

    boolean add(Student student) {
        if (student == null) return false;
        if (root == null) {
            root = new StudentNode(student);
            return true;
        }
        StudentNode current = root;
        while (true) {
            if (student.studentId == current.data.studentId) return false; // 拒絕重複學號
            if (student.studentId < current.data.studentId) {
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

    Student find(int studentId) {
        StudentNode current = root;
        while (current != null) {
            if (studentId == current.data.studentId) return current.data;
            current = studentId < current.data.studentId ? current.left : current.right;
        }
        return null;
    }

    boolean remove(int studentId) {
        if (find(studentId) == null) return false;
        root = remove(root, studentId);
        return true;
    }

    private StudentNode remove(StudentNode node, int studentId) {
        if (node == null) return null;
        if (studentId < node.data.studentId) {
            node.left = remove(node.left, studentId);
        } else if (studentId > node.data.studentId) {
            node.right = remove(node.right, studentId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            StudentNode successor = minimumNode(node.right);
            node.data = successor.data; // 直接替換物件 reference
            node.right = remove(node.right, successor.data.studentId);
        }
        return node;
    }

    private StudentNode minimumNode(StudentNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(StudentNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " | ");
        inorder(node.right);
    }
}

public class StudentBstIndex {
    public static void main(String[] args) {
        StudentBst index = new StudentBst();
        index.add(new Student(105, "Alice"));
        index.add(new Student(102, "Bob"));
        index.add(new Student(108, "Charlie"));
        
        System.out.println("重複加入 102: " + index.add(new Student(102, "David"))); // 預期 false
        
        System.out.println("查詢 108: " + index.find(108));
        
        System.out.print("刪除前: ");
        index.inorder();
        
        index.remove(105);
        System.out.print("刪除 Root (105) 後: ");
        index.inorder();
    }
}