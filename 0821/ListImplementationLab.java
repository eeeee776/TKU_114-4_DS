import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    static void testListOperations(String listType, List<Integer> list) {
        System.out.println("=== 測試 " + listType + " ===");
        
        // 1. 尾端新增
        list.add(10);
        list.add(20);
        list.add(40);
        System.out.println("新增後: " + list);
        
        // 2. 指定位置插入
        list.add(2, 30);
        System.out.println("index 2 插入 30: " + list);
        
        // 3. 搜尋
        System.out.println("尋找數值 20 的 index: " + list.indexOf(20));
        
        // 4. 刪除 (依 index)
        list.remove(1); // 刪除 index 1 的元素 (20)
        System.out.println("刪除 index 1 後: " + list);
        
        // 5. 總和
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        System.out.println("總和: " + sum + "\n");
    }

    public static void main(String[] args) {
        testListOperations("ArrayList", new ArrayList<>());
        testListOperations("LinkedList", new LinkedList<>());
        
        /*
         * 【內部成本差異說明】
         * 1. 依 index 讀取 (get/set)：
         *    - ArrayList 透過底層陣列直接定位，時間複雜度為 O(1)。
         *    - LinkedList 必須從頭或尾逐一走訪節點，時間複雜度為 O(n)。
         * 
         * 2. 在中間插入或刪除 (add/remove at index)：
         *    - ArrayList 需要將該 index 後方的所有元素搬移，成本較高 (O(n))。
         *    - LinkedList 若已知位置，只需修改節點間的參考 (O(1))，但「尋找該位置」仍需 O(n)。
         * 
         * 3. 記憶體空間：
         *    - ArrayList 的連續記憶體區域性較佳，但擴容時會有短暫的陣列複製成本及未使用的預留空間。
         *    - LinkedList 每個節點都需要額外空間儲存前後的參考(next/prev reference)，記憶體負擔較大。
         */
    }
}