import java.util.ArrayList;
import java.util.List;

public class Q12_StudentBstSystem {
    public static class Student {
        private final int id;
        private final String name;
        private int score;

        public Student(int id, String name, int score) {
            if (id <= 0 || name == null || name.isBlank()) {
                throw new IllegalArgumentException();
            }
            this.id = id;
            this.name = name;
            this.score = Math.max(0, Math.min(100, score));
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return id + "|" + name + "|" + score;
        }
    }

    private static class Node {
        Student data;
        Node left;
        Node right;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node root;

    public boolean add(Student student) {
        if (student == null) return false;
        if (root == null) {
            root = new Node(student);
            return true;
        }
        Node current = root;
        while (true) {
            if (student.getId() == current.data.getId()) return false;
            if (student.getId() < current.data.getId()) {
                if (current.left == null) {
                    current.left = new Node(student);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(student);
                    return true;
                }
                current = current.right;
            }
        }
    }

    public Student find(int id) {
        Node current = root;
        while (current != null) {
            if (id == current.data.getId()) return current.data;
            current = id < current.data.getId() ? current.left : current.right;
        }
        return null;
    }

    public boolean updateScore(int id, int score) {
        Student student = find(id);
        if (student == null) return false;
        student.score = Math.max(0, Math.min(100, score));
        return true;
    }

    public boolean remove(int id) {
        if (find(id) == null) return false;
        root = removeHelper(root, id);
        return true;
    }

    private Node removeHelper(Node node, int id) {
        if (node == null) return null;
        if (id < node.data.getId()) {
            node.left = removeHelper(node.left, id);
        } else if (id > node.data.getId()) {
            node.right = removeHelper(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = removeHelper(node.right, successor.data.getId());
        }
        return node;
    }

    public List<Student> studentsBetween(int lowId, int highId) {
        List<Student> result = new ArrayList<>();
        if (lowId <= highId) {
            rangeHelper(root, lowId, highId, result);
        }
        return result;
    }

    private void rangeHelper(Node node, int lowId, int highId, List<Student> result) {
        if (node == null) return;
        if (node.data.getId() > lowId) {
            rangeHelper(node.left, lowId, highId, result);
        }
        if (node.data.getId() >= lowId && node.data.getId() <= highId) {
            result.add(node.data);
        }
        if (node.data.getId() < highId) {
            rangeHelper(node.right, lowId, highId, result);
        }
    }

    public List<Student> inorder() {
        List<Student> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(Node node, List<Student> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(node.data);
        inorderHelper(node.right, result);
    }
}