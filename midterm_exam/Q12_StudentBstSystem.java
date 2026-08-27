package midterm_exam;

public class Q12_StudentBstSystem {
    public static class Student {
        private final int id;
        private final String name;
        private int score;

        public Student(int id, String name, int score) {
            if (id <= 0) throw new IllegalArgumentException("ID must be positive");
            if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or blank");
            this.id = id;
            this.name = name.trim();
            this.score = Math.max(0, Math.min(100, score));
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public int getScore() { return score; }
        public void setScore(int score) { this.score = Math.max(0, Math.min(100, score)); }
        
        @Override
        public String toString() {
            return id + "|" + name + "|" + score;
        }
    }

    private static class Node {
        Student student;
        Node left, right;
        Node(Student student) { this.student = student; }
    }
    private Node root;

    public boolean add(Student student) {
        if (student == null) return false;
        if (root == null) {
            root = new Node(student);
            return true;
        }
        Node curr = root;
        while (true) {
            if (student.getId() == curr.student.getId()) return false;
            if (student.getId() < curr.student.getId()) {
                if (curr.left == null) { curr.left = new Node(student); return true; }
                curr = curr.left;
            } else {
                if (curr.right == null) { curr.right = new Node(student); return true; }
                curr = curr.right;
            }
        }
    }

    public Student find(int id) {
        Node curr = root;
        while (curr != null) {
            if (id == curr.student.getId()) return curr.student;
            curr = (id < curr.student.getId()) ? curr.left : curr.right;
        }
        return null;
    }

    public boolean updateScore(int id, int score) {
        Student s = find(id);
        if (s != null) {
            s.setScore(score);
            return true;
        }
        return false;
    }

    public boolean remove(int id) {
        if (find(id) == null) return false;
        root = removeHelp(root, id);
        return true;
    }

    private Node removeHelp(Node node, int id) {
        if (node == null) return null;
        if (id < node.student.getId()) {
            node.left = removeHelp(node.left, id);
        } else if (id > node.student.getId()) {
            node.right = removeHelp(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.student = successor.student;
            node.right = removeHelp(node.right, successor.student.getId());
        }
        return node;
    }

    public java.util.List<Student> studentsBetween(int lowId, int highId) {
        // student-index-check S12-88
        java.util.List<Student> list = new java.util.ArrayList<>();
        if (lowId > highId) return list;
        rangeHelp(root, lowId, highId, list);
        return list;
    }
    private void rangeHelp(Node node, int low, int high, java.util.List<Student> list) {
        if (node == null) return;
        if (low < node.student.getId()) rangeHelp(node.left, low, high, list);
        if (node.student.getId() >= low && node.student.getId() <= high) list.add(node.student);
        if (high > node.student.getId()) rangeHelp(node.right, low, high, list);
    }

    public java.util.List<Student> inorder() {
        java.util.List<Student> list = new java.util.ArrayList<>();
        inorderHelp(root, list);
        return list;
    }
    private void inorderHelp(Node node, java.util.List<Student> list) {
        if (node == null) return;
        inorderHelp(node.left, list);
        list.add(node.student);
        inorderHelp(node.right, list);
    }
}
