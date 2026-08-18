class Equipment {
    private String id;
    private String name;
    private int availableCount;

    Equipment(String id, String name, int availableCount) {
        // 驗證 id，若為 null 或全白字元則設為 "Unknown"
        this.id = (id == null || id.isBlank()) ? "Unknown" : id;
        
        // 驗證 name，若為 null 或全白字元則設為 "Unknown"
        this.name = (name == null || name.isBlank()) ? "Unknown" : name;
        
        // 庫存不得為負數
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
        return "設備[" + id + "] " + name + " | 可借數量: " + availableCount;
    }
}

public class EquipmentInventory {
    public static void main(String[] args) {
        // 測試一：正常建立物件與借用
        Equipment projector = new Equipment("E001", "投影機", 1);
        System.out.println("初始狀態：" + projector);
        System.out.println("借用一次：" + projector.borrowOne());
        System.out.println("借用兩次：" + projector.borrowOne()); // 預期失敗
        
        projector.returnItems(2);
        System.out.println("歸還 2 台後：" + projector);
        
        System.out.println("---");
        
        // 測試二：測試邊界條件 (空白 id、負數庫存)
        Equipment brokenItem = new Equipment("   ", "", -5);
        System.out.println("錯誤輸入修正：" + brokenItem);
        System.out.println("借用缺貨品：" + brokenItem.borrowOne()); // 預期失敗
    }
}