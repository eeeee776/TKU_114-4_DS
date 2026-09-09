import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {
    static void testList(List<Integer> list) {
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(1, 15);
        
        System.out.println("Contains 20: " + list.contains(20));
        list.remove(Integer.valueOf(30));
        
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        
        System.out.println("Result: " + list + ", Sum: " + sum);
    }

    public static void main(String[] args) {
        System.out.println("--- ArrayList 測試 ---");
        testList(new ArrayList<>());
        
        System.out.println("\n--- LinkedList 測試 ---");
        testList(new LinkedList<>());
        
        System.out.println("\n--- 內部成本差異說明 ---");
        System.out.println("ArrayList: 依 index 讀取 (get) 極快，但在中間插入/刪除時，需搬移後方全部元素，成本較高。");
        System.out.println("LinkedList: 插入/刪除只需改變節點連結，但不支援快速 index 讀取，需從頭走訪，找資料成本較高。");
    }
}