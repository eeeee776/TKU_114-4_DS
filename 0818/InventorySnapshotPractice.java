import java.util.Arrays;

// 使用 final class 避免被繼承並改變行為
final class InventorySnapshot {
    // 欄位標示為 private final
    private final String warehouseId;
    private final int[] quantities;

    InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = warehouseId == null ? "Unknown" : warehouseId;
        
        // 【防禦性複製 1：Constructor】
        // 邊界條件：如果收到 null，建立空陣列避免後續 NullPointerException
        // 否則複製一份全新的陣列，而不是直接接收傳進來的 reference
        this.quantities = quantities == null 
                ? new int[0] 
                : Arrays.copyOf(quantities, quantities.length);
    }

    // 【防禦性複製 2：Getter】
    // 必須回傳陣列的拷貝，否則外部可以透過取得的 Reference 修改內部陣列
    int[] getQuantities() {
        return Arrays.copyOf(quantities, quantities.length);
    }

    int totalQuantity() {
        int total = 0;
        for (int q : quantities) {
            total += q;
        }
        return total;
    }

    int outOfStockCount() {
        int count = 0;
        for (int q : quantities) {
            if (q == 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "倉庫 [" + warehouseId + "] 庫存快照: " + Arrays.toString(quantities);
    }
}

public class InventorySnapshotPractice {
    public static void main(String[] args) {
        // --- 1. 基本測試 ---
        int[] originalArray = {5, 0, 3, 0};
        InventorySnapshot snapshot = new InventorySnapshot("WH-99", originalArray);
        
        System.out.println(snapshot);
        System.out.println("總數量: " + snapshot.totalQuantity() + " (預期為 8)");
        System.out.println("缺貨品項數: " + snapshot.outOfStockCount() + " (預期為 2)");

        System.out.println("\n--- 2. 測試防禦性複製 (外部陣列修改) ---");
        // 嘗試在外部修改原本的陣列
        originalArray[0] = 999;
        System.out.println("外部陣列已被改為: " + Arrays.toString(originalArray));
        // 驗證快照的資料沒有被污染
        System.out.println("修改外部陣列後，快照總數量仍為: " + snapshot.totalQuantity() + " (預期為 8)");

        System.out.println("\n--- 3. 測試防禦性複製 (Getter 取得修改) ---");
        // 嘗試透過 Getter 取出陣列並修改
        int[] receivedArray = snapshot.getQuantities();
        receivedArray[1] = -50;
        // 驗證快照的資料依舊沒有被污染
        System.out.println("取出陣列惡意修改後，快照缺貨數仍為: " + snapshot.outOfStockCount() + " (預期為 2)");

        System.out.println("\n--- 4. 測試邊界條件 (傳入 Null) ---");
        InventorySnapshot nullSnapshot = new InventorySnapshot("WH-NULL", null);
        System.out.println("傳入 Null 建立的快照: " + nullSnapshot);
        System.out.println("Null 快照總數量: " + nullSnapshot.totalQuantity() + " (預期為 0)");
    }
}