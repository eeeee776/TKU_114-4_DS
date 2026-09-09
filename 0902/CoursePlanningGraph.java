import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoursePlanningGraph {
    private final Map<String, List<String>> graph = new HashMap<>();

    public boolean addCourse(String courseCode) {
        if (courseCode == null || courseCode.isBlank()) return false;
        String code = courseCode.trim().toUpperCase();
        if (graph.containsKey(code)) return false;
        graph.put(code, new ArrayList<>());
        return true;
    }

    public boolean addPrerequisite(String prerequisite, String course) {
        if (prerequisite == null || course == null) return false;
        String pre = prerequisite.trim().toUpperCase();
        String crs = course.trim().toUpperCase();
        if (!graph.containsKey(pre) || !graph.containsKey(crs)) return false;
        if (pre.equals(crs)) return false;

        List<String> dependents = graph.get(pre);
        if (!dependents.contains(crs)) {
            dependents.add(crs);
            return true;
        }
        return false;
    }

    public boolean isReachable(String from, String to) {
        if (from == null || to == null) return false;
        String f = from.trim().toUpperCase();
        String t = to.trim().toUpperCase();
        if (!graph.containsKey(f) || !graph.containsKey(t)) return false;
        if (f.equals(t)) return true;

        Set<String> visited = new HashSet<>();
        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push(f);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (!visited.add(current)) continue;
            if (current.equals(t)) return true;

            for (String next : graph.get(current)) {
                if (!visited.contains(next)) {
                    stack.push(next);
                }
            }
        }
        return false;
    }

    public List<String> getAffectedCourses(String changedCourse) {
        List<String> result = new ArrayList<>();
        if (changedCourse == null) return result;
        String start = changedCourse.trim().toUpperCase();
        if (!graph.containsKey(start)) return result;

        Set<String> visited = new HashSet<>();
        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (!visited.add(current)) continue;
            if (!current.equals(start)) {
                result.add(current);
            }
            for (String next : graph.get(current)) {
                if (!visited.contains(next)) {
                    stack.push(next);
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        CoursePlanningGraph plan = new CoursePlanningGraph();
        plan.addCourse("CS101");
        plan.addCourse("CS102");
        plan.addCourse("CS201");
        plan.addCourse("CS301");

        plan.addPrerequisite("CS101", "CS102");
        plan.addPrerequisite("CS101", "CS201");
        plan.addPrerequisite("CS201", "CS301");

        System.out.println("CS101 can reach CS301: " + plan.isReachable("CS101", "CS301"));
        System.out.println("CS102 can reach CS301: " + plan.isReachable("CS102", "CS301"));
        System.out.println("Affected by CS101 change: " + plan.getAffectedCourses("CS101"));
    }
}