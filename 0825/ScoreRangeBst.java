class ScoreRecord {
    int score;
    String studentId;
    String compositeKey;

    ScoreRecord(int score, String studentId) {
        this.score = score;
        this.studentId = studentId;
        this.compositeKey = String.format("%03d_%s", score, studentId);
    }

    @Override
    public String toString() {
        return studentId + "(" + score + ")";
    }
}

class ScoreNode {
    ScoreRecord data;
    ScoreNode left;
    ScoreNode right;

    ScoreNode(ScoreRecord data) {
        this.data = data;
    }
}

public class ScoreRangeBst {
    private ScoreNode root;

    boolean add(int score, String studentId) {
        ScoreRecord record = new ScoreRecord(score, studentId);
        if (root == null) {
            root = new ScoreNode(record);
            return true;
        }
        ScoreNode current = root;
        while (true) {
            int cmp = record.compositeKey.compareTo(current.data.compositeKey);
            if (cmp == 0) return false;
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new ScoreNode(record);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ScoreNode(record);
                    return true;
                }
                current = current.right;
            }
        }
    }

    void printRange(int minScore, int maxScore) {
        rangeHelper(root, minScore, maxScore);
        System.out.println();
    }

    private void rangeHelper(ScoreNode node, int minScore, int maxScore) {
        if (node == null) return;
        if (node.data.score > minScore) {
            rangeHelper(node.left, minScore, maxScore);
        }
        if (node.data.score >= minScore && node.data.score <= maxScore) {
            System.out.print(node.data + " ");
        }
        if (node.data.score < maxScore) {
            rangeHelper(node.right, minScore, maxScore);
        }
    }

    public static void main(String[] args) {
        ScoreRangeBst bst = new ScoreRangeBst();
        bst.add(85, "S01");
        bst.add(90, "S02");
        bst.add(85, "S03");
        bst.add(70, "S04");
        bst.add(95, "S05");

        bst.printRange(80, 90);
    }
}