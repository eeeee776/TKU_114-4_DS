class CheckNode {
    int value;
    CheckNode left;
    CheckNode right;

    CheckNode(int value) {
        this.value = value;
    }
}

public class BstInvariantChecker {
    static boolean isValid(CheckNode node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value >= max) return false;
        return isValid(node.left, min, node.value) && isValid(node.right, node.value, max);
    }

    public static void main(String[] args) {
        CheckNode validRoot = new CheckNode(50);
        validRoot.left = new CheckNode(30);
        validRoot.right = new CheckNode(70);
        System.out.println("Valid Tree: " + isValid(validRoot, Long.MIN_VALUE, Long.MAX_VALUE));

        CheckNode invalid1 = new CheckNode(50);
        invalid1.left = new CheckNode(60);
        System.out.println("Invalid 1 (Left child larger): " + isValid(invalid1, Long.MIN_VALUE, Long.MAX_VALUE));

        CheckNode invalid2 = new CheckNode(50);
        invalid2.right = new CheckNode(40);
        System.out.println("Invalid 2 (Right child smaller): " + isValid(invalid2, Long.MIN_VALUE, Long.MAX_VALUE));

        CheckNode invalid3 = new CheckNode(50);
        invalid3.left = new CheckNode(30);
        invalid3.left.right = new CheckNode(60);
        System.out.println("Invalid 3 (Deep violation): " + isValid(invalid3, Long.MIN_VALUE, Long.MAX_VALUE));
    }
}