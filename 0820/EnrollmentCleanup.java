import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {
    public static void main(String[] args) {
        // 使用 ArrayList 包裝以便修改
        List<String> names = new ArrayList<>();
        names.add("Amy");
        names.add(null);
        names.add("Ben");
        names.add("  ");
        names.add("Amy"); // 重複
        names.add("Cara");

        System.out.println("清理前名單: " + names);

        // 1. 使用 Iterator 清理 null 或空白資料
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name == null || name.isBlank()) {
                iterator.remove(); // 安全移除
            }
        }

        // 2. 找出重複項目並準備去重後的結果
        Set<String> uniqueNames = new HashSet<>();
        Set<String> duplicateReport = new HashSet<>();
        List<String> cleanedList = new ArrayList<>();

        for (String name : names) {
            // Set.add 若已存在會回傳 false
            if (!uniqueNames.add(name)) {
                duplicateReport.add(name);
            } else {
                cleanedList.add(name); // 只保留第一次出現的有效名單
            }
        }

        System.out.println("清理後名單 (含重複): " + names);
        System.out.println("最終去重名單: " + cleanedList);
        System.out.println("重複名單報告: " + duplicateReport);
    }
}