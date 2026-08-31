public class IntegratedStructureAudit {
    
    enum ProposedStructure {
        ARRAY_LIST, LINKED_LIST, STACK, QUEUE, HASH_MAP, TREE_MAP, PRIORITY_QUEUE, GRAPH
    }

    static class AuditResult {
        boolean isOptimal;
        String feedback;

        AuditResult(boolean isOptimal, String feedback) {
            this.isOptimal = isOptimal;
            this.feedback = feedback;
        }
        @Override
        public String toString() {
            return (isOptimal ? "[PASS] " : "[WARN] ") + feedback;
        }
    }

    static AuditResult audit(String scenario, ProposedStructure proposed) {
        if (scenario == null || proposed == null) return new AuditResult(false, "情境或提案為空。");

        if (scenario.contains("網路爬蟲") && scenario.contains("待處理網址")) {
            if (proposed == ProposedStructure.QUEUE) return new AuditResult(true, "Queue (BFS) 適合確保先爬取淺層網頁 (FIFO, O(1))。");
            if (proposed == ProposedStructure.STACK) return new AuditResult(true, "Stack (DFS) 也可，但會優先鑽向深層網頁 (LIFO, O(1))。");
            return new AuditResult(false, "建議使用 Queue 或 Stack。");
        }

        if (scenario.contains("排行榜") || scenario.contains("Top 10")) {
            if (proposed == ProposedStructure.PRIORITY_QUEUE) return new AuditResult(true, "PriorityQueue 適合動態維持極值，Add/Poll 皆為 O(log n)。");
            if (proposed == ProposedStructure.ARRAY_LIST) return new AuditResult(false, "ArrayList 每次插入並排序需要 O(n log n)，效能不佳。");
            if (proposed == ProposedStructure.TREE_MAP) return new AuditResult(true, "TreeMap 可以保持排序，O(log n)，也是個好選擇。");
        }

        if (scenario.contains("快取") || scenario.contains("依 ID 快速尋找")) {
            if (proposed == ProposedStructure.HASH_MAP) return new AuditResult(true, "HashMap 提供 O(1) 的查詢速度，最適合 Key-Value 尋找。");
            return new AuditResult(false, proposed + " 查詢速度可能需要 O(n) 或 O(log n)，非最佳解。");
        }

        if (scenario.contains("社交網路") || scenario.contains("推薦好友")) {
            if (proposed == ProposedStructure.GRAPH) return new AuditResult(true, "Graph (Adjacency List) 非常適合多對多關係與 BFS 擴展。");
            return new AuditResult(false, proposed + " 無法有效表達與遊走多對多關係節點。");
        }

        return new AuditResult(false, "未知的驗證情境，需要進一步人工審查。");
    }

    public static void main(String[] args) {
        System.out.println("--- 架構審查結果 ---");
        
        // 情境 1：快取系統
        System.out.println("情境: 依 ID 快速尋找商品快取");
        System.out.println("提案 HashMap: " + audit("依 ID 快速尋找商品快取", ProposedStructure.HASH_MAP));
        System.out.println("提案 ArrayList: " + audit("依 ID 快速尋找商品快取", ProposedStructure.ARRAY_LIST));
        System.out.println();

        // 情境 2：爬蟲系統
        System.out.println("情境: 網路爬蟲的待處理網址池");
        System.out.println("提案 Queue: " + audit("網路爬蟲的待處理網址池", ProposedStructure.QUEUE));
        System.out.println();

        // 情境 3：遊戲排行榜
        System.out.println("情境: 遊戲即時排行榜 Top 10");
        System.out.println("提案 PriorityQueue: " + audit("遊戲即時排行榜 Top 10", ProposedStructure.PRIORITY_QUEUE));
        System.out.println("提案 ArrayList: " + audit("遊戲即時排行榜 Top 10", ProposedStructure.ARRAY_LIST));
        System.out.println();
        
        // 邊界案例
        System.out.println("邊界案例 (Null 參數): " + audit(null, null));
        System.out.println("邊界案例 (未知情境): " + audit("設計一個文字編輯器", ProposedStructure.STACK));
    }
}