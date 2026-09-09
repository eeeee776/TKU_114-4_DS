class Member {
    String memberId;
    String email;

    Member(String memberId, String email) {
        this.memberId = memberId;
        this.email = email;
    }

    @Override
    public String toString() {
        return memberId + " (" + email + ")";
    }
}

class MemberNode {
    Member data;
    MemberNode left;
    MemberNode right;

    MemberNode(Member data) {
        this.data = data;
    }
}

public class MemberBstIndex {
    private MemberNode root;

    boolean add(Member member) {
        if (member == null || member.memberId == null || member.email == null || member.email.isBlank()) {
            return false;
        }
        if (root == null) {
            root = new MemberNode(member);
            return true;
        }
        MemberNode current = root;
        while (true) {
            int cmp = member.memberId.compareTo(current.data.memberId);
            if (cmp == 0) return false;
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new MemberNode(member);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new MemberNode(member);
                    return true;
                }
                current = current.right;
            }
        }
    }

    Member find(String memberId) {
        if (memberId == null) return null;
        MemberNode current = root;
        while (current != null) {
            int cmp = memberId.compareTo(current.data.memberId);
            if (cmp == 0) return current.data;
            current = cmp < 0 ? current.left : current.right;
        }
        return null;
    }

    boolean updateEmail(String memberId, String newEmail) {
        if (newEmail == null || newEmail.isBlank()) return false;
        Member member = find(memberId);
        if (member == null) return false;
        member.email = newEmail;
        return true;
    }

    boolean remove(String memberId) {
        if (find(memberId) == null) return false;
        root = removeHelper(root, memberId);
        return true;
    }

    private MemberNode removeHelper(MemberNode node, String memberId) {
        if (node == null) return null;
        int cmp = memberId.compareTo(node.data.memberId);
        if (cmp < 0) {
            node.left = removeHelper(node.left, memberId);
        } else if (cmp > 0) {
            node.right = removeHelper(node.right, memberId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            MemberNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = removeHelper(node.right, successor.data.memberId);
        }
        return node;
    }

    void inorderReport() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(MemberNode node) {
        if (node == null) return;
        inorderHelper(node.left);
        System.out.print(node.data + " | ");
        inorderHelper(node.right);
    }

    public static void main(String[] args) {
        MemberBstIndex index = new MemberBstIndex();
        System.out.println(index.add(new Member("M02", "ben@test.com")));
        System.out.println(index.add(new Member("M01", "amy@test.com")));
        System.out.println(index.add(new Member("M03", "cara@test.com")));
        System.out.println(index.add(new Member("M02", "dup@test.com")));
        System.out.println(index.add(new Member("M04", "  "))); 

        index.inorderReport();

        System.out.println(index.updateEmail("M01", "amy_new@test.com"));
        System.out.println(index.updateEmail("M03", "")); 

        System.out.println(index.remove("M02"));
        
        index.inorderReport();
    }
}