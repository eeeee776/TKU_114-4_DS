import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CourseDependencyGraph {
    // 紀錄 A -> B，表示 A 是 B 的先修課。Key 是一門課，Value 是它能解鎖的後續課程 (Outgoing)
    private final Map<String, Set<String>> subsequentCourses = new HashMap<>();

    public void addCourse(String course) {
        subsequentCourses.putIfAbsent(course, new HashSet<>());
    }

    // preReq -> course (例如 計算機概論 -> 資料結構)
    public void addDependency(String preReq, String course) {
        if (!subsequentCourses.containsKey(preReq) || !subsequentCourses.containsKey(course)) {
            throw new IllegalArgumentException("Course must be added first");
        }
        subsequentCourses.get(preReq).add(course);
    }

    // 列出某門課的後續課程 (Out-degree mapping)
    public Set<String> getSubsequentCourses(String course) {
        return subsequentCourses.getOrDefault(course, Set.of());
    }

    // 列出某門課的先修課程 (找出所有指向該 course 的節點)
    public Set<String> getPrerequisites(String course) {
        Set<String> prereqs = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : subsequentCourses.entrySet()) {
            if (entry.getValue().contains(course)) {
                prereqs.add(entry.getKey());
            }
        }
        return prereqs;
    }

    public int inDegree(String course) {
        return getPrerequisites(course).size();
    }

    public int outDegree(String course) {
        return getSubsequentCourses(course).size();
    }

    public static void main(String[] args) {
        CourseDependencyGraph graph = new CourseDependencyGraph();
        for (String c : new String[]{"CS101", "CS201", "CS202", "CS301", "MA101"}) {
            graph.addCourse(c);
        }

        graph.addDependency("CS101", "CS201");
        graph.addDependency("CS101", "CS202");
        graph.addDependency("MA101", "CS202");
        graph.addDependency("CS201", "CS301");
        graph.addDependency("CS202", "CS301");

        System.out.println("Prerequisites of CS202: " + graph.getPrerequisites("CS202")); // [CS101, MA101]
        System.out.println("Subsequent to CS101: " + graph.getSubsequentCourses("CS101")); // [CS201, CS202]
        
        System.out.println("CS301 In-degree: " + graph.inDegree("CS301")); // 2
        System.out.println("MA101 Out-degree: " + graph.outDegree("MA101")); // 1
    }
}