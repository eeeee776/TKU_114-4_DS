import java.util.ArrayList;
import java.util.List;

class Member {
    final int memberId;
    String name;
    String email;

    Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("[ID: %d] %s (%s)", memberId, name, email);
    }
}

class MemberNode {
    Member data;
    MemberNode left, right;
    MemberNode(Member data) { this.data = data; }
}

public class MemberBstIndex {
    private MemberNode root;

    public boolean add(Member member) {
        if (member == null || isBlank(member.email)) return false;
        if (root == null) {
            root = new MemberNode(member);
            return true;
        }
        MemberNode current = root;
        while (true) {
            if (member.memberId == current.data.memberId) return false; // ID不可重複
            if (member.memberId < current.data.memberId) {
                if (current.left == null) { current.left = new MemberNode(member); return true; }
                current = current.left;
            } else {
                if (current.right == null) { current.right = new MemberNode(member); return true; }
                current = current.right;
            }
        }
    }

    public Member find(int id) {
        MemberNode current = root;
        while (current != null) {
            if (id == current.data.memberId) return current.data;
            current = id < current.data.memberId ? current.left : current.right;
        }
        return null;
    }

    public boolean updateEmail(int id, String newEmail) {
        if (isBlank(newEmail)) return false;
        Member target = find(id);
        if (target == null) return false;
        target.email = newEmail;
        return true;
    }

    public boolean remove(int id) {
        if (find(id) == null) return false;
        root = remove(root, id);
        return true;
    }

    private MemberNode remove(MemberNode node, int id) {
        if (id < node.data.memberId) node.left = remove(node.left, id);
        else if (id > node.data.memberId) node.right = remove(node.right, id);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            MemberNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = remove(node.right, successor.data.memberId);
        }
        return node;
    }

    public void inorderReport() {
        System.out.println("--- Member Report (Sorted by ID) ---");
        inorder(root);
        System.out.println("------------------------------------");
    }

    private void inorder(MemberNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.println(node.data);
        inorder(node.right);
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static void main(String[] args) {
        MemberBstIndex index = new MemberBstIndex();
        
        index.add(new Member(102, "Alice", "alice@test.com"));
        index.add(new Member(101, "Bob", "bob@test.com"));
        index.add(new Member(105, "Charlie", "charlie@test.com"));
        
        System.out.println("Add Empty Email: " + index.add(new Member(106, "Dave", ""))); // False
        System.out.println("Add Duplicate ID: " + index.add(new Member(101, "CopyCat", "cc@t.com"))); // False
        
        index.updateEmail(101, "new.bob@test.com");
        index.remove(102);

        index.inorderReport(); 
    }
}