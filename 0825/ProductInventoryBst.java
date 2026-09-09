class InventoryProduct {
    int id;
    String name;
    int stock;

    InventoryProduct(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = Math.max(0, stock);
    }

    @Override
    public String toString() {
        return id + " " + name + " (Stock: " + stock + ")";
    }
}

class InvNode {
    InventoryProduct data;
    InvNode left;
    InvNode right;

    InvNode(InventoryProduct data) {
        this.data = data;
    }
}

public class ProductInventoryBst {
    private InvNode root;

    boolean add(InventoryProduct product) {
        if (product == null) return false;
        if (root == null) {
            root = new InvNode(product);
            return true;
        }
        InvNode current = root;
        while (true) {
            if (product.id == current.data.id) return false;
            if (product.id < current.data.id) {
                if (current.left == null) {
                    current.left = new InvNode(product);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new InvNode(product);
                    return true;
                }
                current = current.right;
            }
        }
    }

    InventoryProduct find(int id) {
        InvNode current = root;
        while (current != null) {
            if (id == current.data.id) return current.data;
            current = id < current.data.id ? current.left : current.right;
        }
        return null;
    }

    boolean restock(int id, int amount) {
        if (amount <= 0) return false;
        InventoryProduct p = find(id);
        if (p == null) return false;
        p.stock += amount;
        return true;
    }

    boolean deduct(int id, int amount) {
        if (amount <= 0) return false;
        InventoryProduct p = find(id);
        if (p == null || p.stock < amount) return false;
        p.stock -= amount;
        return true;
    }

    boolean delete(int id) {
        if (find(id) == null) return false;
        root = deleteHelper(root, id);
        return true;
    }

    private InvNode deleteHelper(InvNode node, int id) {
        if (node == null) return null;
        if (id < node.data.id) {
            node.left = deleteHelper(node.left, id);
        } else if (id > node.data.id) {
            node.right = deleteHelper(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            InvNode successor = getMin(node.right);
            node.data = successor.data;
            node.right = deleteHelper(node.right, successor.data.id);
        }
        return node;
    }

    private InvNode getMin(InvNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    void inorderReport() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(InvNode node) {
        if (node == null) return;
        inorderHelper(node.left);
        System.out.print(node.data + " | ");
        inorderHelper(node.right);
    }

    public static void main(String[] args) {
        ProductInventoryBst inv = new ProductInventoryBst();
        inv.add(new InventoryProduct(200, "Monitor", 5));
        inv.add(new InventoryProduct(100, "Mouse", 10));
        inv.add(new InventoryProduct(300, "Keyboard", 3));

        inv.restock(100, 5);
        inv.deduct(200, 2);
        inv.deduct(300, 10);
        
        inv.inorderReport();

        inv.delete(200);
        inv.inorderReport();
    }
}