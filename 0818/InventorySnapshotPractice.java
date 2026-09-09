import java.util.Arrays;

final class InventorySnapshot {
    private final String warehouseId;
    private final int[] quantities;

    InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = warehouseId;
        if (quantities == null) {
            this.quantities = new int[0];
        } else {
            this.quantities = Arrays.copyOf(quantities, quantities.length);
        }
    }

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
}

public class InventorySnapshotPractice {
    public static void main(String[] args) {
        int[] testData = {5, 0, 3, 0};
        InventorySnapshot snapshot = new InventorySnapshot("W01", testData);

        System.out.println("--- 庫存快照統計 ---");
        System.out.println("\n--- null 邊界條件測試 ---");
        InventorySnapshot nullSnapshot = new InventorySnapshot("W02", null);
        System.out.println("null 陣列總數量 (預期為 0): " + nullSnapshot.totalQuantity());
    }
}
