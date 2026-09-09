import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CourseDependencyGraph {
    private final Map<String, List<String>> prerequisites = new LinkedHashMap<>();

    public void addCourse(String course) {
        if (course != null && !course.isBlank()) {
            prerequisites.putIfAbsent(course, new ArrayList<>());
        }
    }

    public boolean addDependency(String preReq, String course) {
        if (!prerequisites.containsKey(preReq) || !prerequisites.containsKey(course)) return false;
        if (preReq.equals(course)) return false;
        List<String> reqs = prerequisites.get(course);
        if (!reqs.contains(preReq)) {
            reqs.add(preReq);
            return true;
        }
        return false;
    }

    public List<String> getPrerequisites(String course) {
        if (!prerequisites.containsKey(course)) return new ArrayList<>();
        List<String> list = new ArrayList<>(prerequisites.get(course));
        Collections.sort(list);
        return list;
    }

    public List<String> getNextCourses(String course) {
        if (!prerequisites.containsKey(course)) return new ArrayList<>();
        List<String> next = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : prerequisites.entrySet()) {
            if (entry.getValue().contains(course)) {
                next.add(entry.getKey());
            }
        }
        Collections.sort(next);
        return next;
    }

    public int getInDegree(String course) {
        return getPrerequisites(course).size();
    }

    public int getOutDegree(String course) {
        return getNextCourses(course).size();
    }

    public static void main(String[] args) {
        CourseDependencyGraph graph = new CourseDependencyGraph();
        graph.addCourse("CS101");
        graph.addCourse("CS102");
        graph.addCourse("CS201");
        graph.addCourse("CS202");

        graph.addDependency("CS101", "CS102");
        graph.addDependency("CS101", "CS201");
        graph.addDependency("CS102", "CS202");
        graph.addDependency("CS201", "CS202");

        System.out.println("Prerequisites for CS202: " + graph.getPrerequisites("CS202"));
        System.out.println("Next courses after CS101: " + graph.getNextCourses("CS101"));
        System.out.println("CS101 In-Degree: " + graph.getInDegree("CS101"));
        System.out.println("CS101 Out-Degree: " + graph.getOutDegree("CS101"));
        System.out.println("CS202 In-Degree: " + graph.getInDegree("CS202"));
    }
}