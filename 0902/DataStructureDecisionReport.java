public class DataStructureDecisionReport {
    enum Scenario {
        FIND_USER_BY_ID, 
        GET_HIGHEST_PRIORITY_TASK,
        GET_SHORTEST_PATH, 
        MAINTAIN_BROWSER_HISTORY,
        MESSAGE_QUEUE, 
        SORTED_PAGINATION,
        FIND_CONNECTED_FRIENDS, 
        FAST_INDEX_ACCESS,
        DETECT_CYCLES, 
        UNIQUE_VISITORS,
        SCHEDULE_NEXT_EVENT,
        RANGE_SEARCH_BY_DATE
    }

    static class Decision {
        String structure;
        String bigO;
        String reason;

        Decision(String structure, String bigO, String reason) {
            this.structure = structure;
            this.bigO = bigO;
            this.reason = reason;
        }
        @Override
        public String toString() { return String.format("[%s] (O: %s) - %s", structure, bigO, reason); }
    }

    static Decision evaluate(Scenario s) {
        if (s == null) return new Decision("Unknown", "N/A", "無效的需求");
        return switch (s) {
            case FIND_USER_BY_ID -> new Decision("HashMap", "O(1) Avg", "精確比對 Key，需要極快的查找速度。");
            case GET_HIGHEST_PRIORITY_TASK, SCHEDULE_NEXT_EVENT -> new Decision("PriorityQueue (Heap)", "O(log n) Add/Poll", "只需要動態維持並取出最大/最小值，不需完全排序。");
            case GET_SHORTEST_PATH, FIND_CONNECTED_FRIENDS, DETECT_CYCLES -> new Decision("Graph (Adjacency List)", "O(V+E)", "處理多對多實體之間的複雜關係與走訪。");
            case MAINTAIN_BROWSER_HISTORY -> new Decision("ArrayDeque (Stack)", "O(1)", "後進先出 (LIFO)，只需操作單一端點。");
            case MESSAGE_QUEUE -> new Decision("ArrayDeque (Queue)", "O(1)", "先進先出 (FIFO)，維持訊息處理順序。");
            case SORTED_PAGINATION, RANGE_SEARCH_BY_DATE -> new Decision("TreeMap (Balanced BST)", "O(log n)", "需要維護元素的排序狀態，並支援範圍提取。");
            case FAST_INDEX_ACCESS -> new Decision("ArrayList", "O(1) Get", "頻繁依賴連續記憶體的 Index 讀取。");
            case UNIQUE_VISITORS -> new Decision("HashSet", "O(1) Avg", "只需記錄存在與否並自動排除重複。");
        };
    }

    public static void main(String[] args) {
        System.out.println("=== 資料結構選擇診斷報告 ===");
        for (Scenario scenario : Scenario.values()) {
            System.out.printf("%-26s -> %s%n", scenario.name(), evaluate(scenario));
        }
        System.out.printf("%-26s -> %s%n", "NULL_SCENARIO", evaluate(null));
    }
}