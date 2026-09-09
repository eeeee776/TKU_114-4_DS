class Equipment {
    private String id;
    private String name;
    private int availableCount;

    Equipment(String id, String name, int availableCount) {
        this.id = (id == null || id.isBlank()) ? "Unknown" : id;
        this.name = (name == null || name.isBlank()) ? "Unknown" : name;
      
        this.availableCount = Math.max(0, availableCount);
    }

    boolean borrowOne() {
        if (availableCount > 0) {
            availableCount--;
            return true;
        }
        return false;
    }

    
    void returnItems(int quantity) {
        if (quantity > 0) {
            availableCount += quantity;
        }
    }

    @Override
    public String toString() {
        return "設備編號: " + id + ", 名稱: " + name + ", 可借數量: " + availableCount;
    }
}

public class EquipmentInventory {
    public static void main(String[] args) {
        Equipment eq1 = new Equipment("E01", "Projector", 1);
        Equipment eq2 = new Equipment("", "", -5); 
        System.out.println("--- 測試設備一 ---");
        System.out.println(eq1);
        System.out.println("借用 eq1: " + eq1.borrowOne()); 
        System.out.println("再次借用 eq1: " + eq1.borrowOne());
        eq1.returnItems(2);
        System.out.println("歸還 2 個後: " + eq1);

        System.out.println("\n--- 測試設備二 (防呆測試) ---");
        System.out.println(eq2);
    }
}