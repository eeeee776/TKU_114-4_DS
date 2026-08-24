import java.util.*;

public class CourseTagReport {
    public static void main(String[] args) {
        String[] inputTags = {"Java", "Spring", "Java", "SQL", "Spring", "AWS"};

        List<String> tagList = new ArrayList<>();
        Set<String> tagSet = new LinkedHashSet<>(); // 使用 LinkedHashSet 保留第一次加入順序較易讀
        Map<String, Integer> tagCount = new HashMap<>();

        for (String tag : inputTags) {
            tagList.add(tag);
            tagSet.add(tag);
            tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
        }

        System.out.println("=== 標籤統計報告 ===");
        System.out.println("1. List 用途 (保留原始輸入與順序)：");
        System.out.println(tagList);
        
        System.out.println("\n2. Set 用途 (去除重複，確認涵蓋了哪些不同的標籤)：");
        System.out.println(tagSet);
        
        System.out.println("\n3. Map 用途 (以標籤為 Key，統計出現次數)：");
        for (Map.Entry<String, Integer> entry : tagCount.entrySet()) {
            System.out.println(entry.getKey() + " 標籤共出現 " + entry.getValue() + " 次");
        }
    }
}