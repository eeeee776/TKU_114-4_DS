// 復用課程的基礎 IntNode 與 IntBst 核心方法來進行測試
class SimpleIntNode {
    int value; SimpleIntNode left, right;
    SimpleIntNode(int value) { this.value = value; }
}

class TestBst {
    SimpleIntNode root; // 為了測試方便，直接操作 root
    
    // ... 簡化版 add 與 remove (邏輯同教材) ...
    void add(int v) { root = add(root, v); }
    private SimpleIntNode add(SimpleIntNode node, int v) {
        if (node == null) return new SimpleIntNode(v);
        if (v < node.value) node.left = add(node.left, v);
        else if (v > node.value) node.right = add(node.right, v);
        return node;
    }
    
    boolean remove(int v) {
        if (!contains(v)) return false;
        root = remove(root, v);
        return true;
    }
    private SimpleIntNode remove(SimpleIntNode node, int v) {
        if (node == null) return null;
        if (v < node.value) node.left = remove(node.left, v);
        else if (v > node.value) node.right = remove(node.right, v);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            SimpleIntNode s = node.right;
            while (s.left != null) s = s.left;
            node.value = s.value;
            node.right = remove(node.right, s.value);
        }
        return node;
    }
    
    boolean contains(int v) {
        SimpleIntNode c = root;
        while (c != null) {
            if (v == c.value) return true;
            c = v < c.value ? c.left : c.right;
        }
        return false;
    }
    
    void inorder() { inorder(root); System.out.println(); }
    private void inorder(SimpleIntNode n) {
        if (n == null) return;
        inorder(n.left); System.out.print(n.value + " "); inorder(n.right);
    }
}

public class BstDeleteTestSuite {
    public static void main(String[] args) {
        TestBst tree = new TestBst();
        System.out.println("1. 測試 Empty Tree 刪除: " + tree.remove(10) + " (預期 false)");
        
        tree.add(50);
        System.out.println("2. 測試 Missing 值刪除: " + tree.remove(99) + " (預期 false)");
        
        System.out.println("3. 測試 Single Root 刪除 (50): " + tree.remove(50));
        System.out.print("   樹的狀態 (應為空): "); tree.inorder();
        
        tree.add(50); tree.add(30);
        System.out.println("4. 測試 Root with One Child (刪除50): " + tree.remove(50));
        System.out.print("   樹的狀態 (應剩30): "); tree.inorder();
        
        tree = new TestBst();
        tree.add(50); tree.add(30); tree.add(70); tree.add(20); tree.add(40); tree.add(60); tree.add(80);
        System.out.println("5. 測試 Root with Two Children (刪除50): " + tree.remove(50));
        System.out.print("   樹的狀態: "); tree.inorder();
        
        System.out.println("6. 連續刪除到 Empty:");
        int[] toDelete = {60, 30, 70, 20, 40, 80};
        for (int v : toDelete) tree.remove(v);
        System.out.print("   最終狀態 (應為空): "); tree.inorder();
        System.out.println("   Root 是否為 null: " + (tree.root == null));
    }
}