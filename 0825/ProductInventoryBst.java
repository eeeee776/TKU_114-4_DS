class Product {
    int id;
    String name;
    int stock;

    Product(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = Math.max(0, stock);
    }
    @Override
    public String toString() {
        return id + ":" + name + "(剩餘" + stock + ")";
    }
}

class ProductNode {
    Product data;
    ProductNode left;
    ProductNode right;
    ProductNode(Product data) { this.data = data; }
}

class ProductBst {
    private ProductNode root;

    boolean add(Product p) {
        if (p == null) return false;
        if (root == null) { root = new ProductNode(p); return true; }
        ProductNode current = root;
        while (true) {
            if (p.id == current.data.id) return false;
            if (p.id < current.data.id) {
                if (current.left == null) { current.left = new ProductNode(p); return true; }
                current = current.left;
            } else {
                if (current.right == null) { current.right = new ProductNode(p); return true; }
                current = current.right;
            }
        }
    }

    Product find(int id) {
        ProductNode current = root;
        while (current != null) {
            if (id == current.data.id) return current.data;
            current = id < current.data.id ? current.left : current.right;
        }
        return null;
    }

    boolean restock(int id, int amount) {
        Product p = find(id);
        if (p == null || amount <= 0) return false;
        p.stock += amount;
        return true;
    }

    boolean deduct(int id, int amount) {
        Product p = find(id);
        if (p == null || amount <= 0 || p.stock < amount) return false;
        p.stock -= amount;
        return true;
    }

    boolean remove(int id) {
        if (find(id) == null) return false;
        root = remove(root, id);
        return true;
    }

    private ProductNode remove(ProductNode node, int id) {
        if (node == null) return null;
        if (id < node.data.id) node.left = remove(node.left, id);
        else if (id > node.data.id) node.right = remove(node.right, id);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            ProductNode successor = getMin(node.right);
            node.data = successor.data;
            node.right = remove(node.right, successor.data.id);
        }
        return node;
    }

    private ProductNode getMin(ProductNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    void inorderReport() {
        System.out.println("--- 庫存報表 ---");
        inorder(root);
        System.out.println("----------------");
    }

    private void inorder(ProductNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.println(node.data);
        inorder(node.right);
    }
}

public class ProductInventoryBst {
    public static void main(String[] args) {
        // 修正：宣告與實例化改用 ProductBst
        ProductBst inv = new ProductBst();
        inv.add(new Product(201, "滑鼠", 10));
        inv.add(new Product(105, "鍵盤", 5));
        inv.add(new Product(309, "螢幕", 2));

        inv.restock(105, 5);      // 鍵盤變 10
        inv.deduct(309, 1);       // 螢幕變 1
        System.out.println("扣除過多庫存: " + inv.deduct(201, 15)); // 預期 false

        inv.inorderReport();

        inv.remove(201); // 下架滑鼠
        System.out.println("下架滑鼠後:");
        inv.inorderReport();
    }
}