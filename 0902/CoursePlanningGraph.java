import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoursePlanningGraph {
    // key: 課程, value: 依賴這門課的後續課程清單 (A -> B 代表修 B 之前必須先修 A)
    private Map<String, List<String>> graph = new HashMap<>();

    public void addCourse(String course) {
        graph.putIfAbsent(course, new ArrayList<>());
    }

    public void addPrerequisite(String prereq, String advanced) {
        addCourse(prereq);
        addCourse(advanced);
        graph.get(prereq).add(advanced);
    }

    // 利用 Iterative DFS 尋找所有受影響的課程 (Connected Reachability)
    public List<String> getAffectedCourses(String failedCourse) {
        List<String> affected = new ArrayList<>();
        if (!graph.containsKey(failedCourse)) return affected;

        ArrayDeque<String> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        stack.push(failedCourse);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            
            if (!visited.add(current)) continue;
            if (!current.equals(failedCourse)) {
                affected.add(current); // 不包含觸發的源頭課程本身
            }

            for (String next : graph.getOrDefault(current, List.of())) {
                if (!visited.contains(next)) {
                    stack.push(next);
                }
            }
        }
        return affected;
    }

    public static void main(String[] args) {
        CoursePlanningGraph planning = new CoursePlanningGraph();
        
        planning.addPrerequisite("微積分一", "微積分二");
        planning.addPrerequisite("微積分二", "工程數學");
        planning.addPrerequisite("微積分二", "物理學");
        planning.addPrerequisite("基礎程式", "資料結構");
        planning.addPrerequisite("資料結構", "演算法");
        planning.addCourse("通識課"); // 孤立節點

        System.out.println("一般案例 (微積分一 被當掉，受影響的課): " + planning.getAffectedCourses("微積分一"));
        System.out.println("一般案例 (資料結構 被當掉，受影響的課): " + planning.getAffectedCourses("資料結構"));
        System.out.println("邊界案例 (無後續課程，如 通識課): " + planning.getAffectedCourses("通識課"));
        System.out.println("邊界案例 (Missing Vertex): " + planning.getAffectedCourses("不存在的課"));
        
        // 邊界案例: Cycle 處理 (現實中不可能有循環先修，但演算法必須防止無限迴圈)
        planning.addPrerequisite("A", "B");
        planning.addPrerequisite("B", "C");
        planning.addPrerequisite("C", "A");
        System.out.println("邊界案例 (惡意 Cycle，A被當): " + planning.getAffectedCourses("A"));
    }
}