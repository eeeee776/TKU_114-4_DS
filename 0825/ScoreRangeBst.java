class ScoreRecord {
    int score;
    int studentId;

    ScoreRecord(int score, int studentId) {
        this.score = score;
        this.studentId = studentId;
    }
    
    int compareTo(ScoreRecord other) {
        if (this.score != other.score) {
            return Integer.compare(this.score, other.score);
        }
        return Integer.compare(this.studentId, other.studentId);
    }

    @Override
    public String toString() {
        return "分數:" + score + "(學號:" + studentId + ")";
    }
}

class ScoreNode {
    ScoreRecord data;
    ScoreNode left;
    ScoreNode right;
    ScoreNode(ScoreRecord data) { this.data = data; }
}

class ScoreBst {
    private ScoreNode root;

    boolean add(ScoreRecord record) {
        if (root == null) {
            root = new ScoreNode(record);
            return true;
        }
        ScoreNode current = root;
        while (true) {
            int cmp = record.compareTo(current.data);
            if (cmp == 0) return false; 
            
            if (cmp < 0) {
                if (current.left == null) { current.left = new ScoreNode(record); return true; }
                current = current.left;
            } else {
                if (current.right == null) { current.right = new ScoreNode(record); return true; }
                current = current.right;
            }
        }
    }

    void printRange(int minScore, int maxScore) {
        System.out.println("查詢分數範圍 [" + minScore + " ~ " + maxScore + "]:");
        printRange(root, minScore, maxScore);
        System.out.println();
    }

    private void printRange(ScoreNode node, int minScore, int maxScore) {
        if (node == null) return;
        
        if (node.data.score > minScore) {
            printRange(node.left, minScore, maxScore);
        }
        
        if (node.data.score >= minScore && node.data.score <= maxScore) {
            System.out.println(node.data);
        }
        
        if (node.data.score < maxScore) {
            printRange(node.right, minScore, maxScore);
        }
    }
}

public class ScoreRangeBst {
    public static void main(String[] args) {
        ScoreBst tree = new ScoreBst();
        tree.add(new ScoreRecord(85, 101));
        tree.add(new ScoreRecord(92, 102));
        tree.add(new ScoreRecord(85, 103)); 
        tree.add(new ScoreRecord(78, 104));
        tree.add(new ScoreRecord(95, 105));

        tree.printRange(80, 93); 
    }
}