import java.util.ArrayList;
import java.util.List;

public class StudentIdHashAnalysis {

    public record AnalysisResult(
            int bucketCount,
            int totalKeys,
            int totalCollisions,
            int maxChainLength,
            double averageChainLength
    ) {
        public void printSummary() {
            System.out.println("-------------------------------------------------");
            System.out.printf("Bucket 數量       : %d%n", bucketCount);
            System.out.printf("學號總數 (Keys)   : %d%n", totalKeys);
            System.out.printf("總 Collision 次數 : %d%n", totalCollisions);
            System.out.printf("最長 Chain 長度   : %d%n", maxChainLength);
            System.out.printf("平均 Chain 長度   : %.3f%n", averageChainLength);
            System.out.println("-------------------------------------------------");
        }
    }

    public static AnalysisResult analyze(List<String> studentIds, int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("bucketCount must be positive");
        }

        List<List<String>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        int validKeys = 0;
        if (studentIds != null) {
            for (String id : studentIds) {
                if (id == null) continue;
                int index = Math.floorMod(id.hashCode(), bucketCount);
                buckets.get(index).add(id);
                validKeys++;
            }
        }

        int totalCollisions = 0;
        int maxChain = 0;

        for (List<String> chain : buckets) {
            int len = chain.size();
            if (len > 1) {
                totalCollisions += (len - 1);
            }
            if (len > maxChain) {
                maxChain = len;
            }
        }

        double avgChain = (double) validKeys / bucketCount;
        return new AnalysisResult(bucketCount, validKeys, totalCollisions, maxChain, avgChain);
    }

    public static void main(String[] args) {
        // 模擬資工系與電機系共 30 組學號資料
        List<String> studentIds = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            studentIds.add(String.format("B11209%03d", i)); // 連續學號
            studentIds.add(String.format("B11305%03d", i * 3));
        }

        System.out.println("=== 學號 Hash 碰撞分析實驗 ===");
        System.out.println("測試學號筆數: " + studentIds.size());

        // 比較小型桶 (7) 與較充裕的質數桶 (31)
        AnalysisResult resSmall = analyze(studentIds, 7);
        AnalysisResult resLarge = analyze(studentIds, 31);

        System.out.println("\n【配置 1：較小 Bucket 數量】");
        resSmall.printSummary();

        System.out.println("\n【配置 2：擴大 Bucket 數量】");
        resLarge.printSummary();

        System.out.printf("結論：當 Bucket 從 %d 擴充至 %d 時，總 Collision 次數自 %d 降至 %d，最大 Chain 長度自 %d 縮短為 %d。%n",
                resSmall.bucketCount(), resLarge.bucketCount(),
                resSmall.totalCollisions(), resLarge.totalCollisions(),
                resSmall.maxChainLength(), resLarge.maxChainLength());
    }
}