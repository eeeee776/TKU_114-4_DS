import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InterestSetComparison {
    public static Set<String> getUnion(Set<String> set1, Set<String> set2) {
        Set<String> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static Set<String> getIntersection(Set<String> set1, Set<String> set2) {
        Set<String> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public static Set<String> getFirstOnly(Set<String> set1, Set<String> set2) {
        Set<String> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    public static Set<String> getSecondOnly(Set<String> set1, Set<String> set2) {
        Set<String> result = new HashSet<>(set2);
        result.removeAll(set1);
        return result;
    }

    private static List<String> sorted(Set<String> set) {
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }

    public static void main(String[] args) {
        Set<String> amy = Set.of("Music", "Art", "Sports", "Reading");
        Set<String> ben = Set.of("Gaming", "Art", "Reading", "Travel");

        System.out.println("Amy: " + sorted(amy));
        System.out.println("Ben: " + sorted(ben));
        System.out.println("Union: " + sorted(getUnion(amy, ben)));
        System.out.println("Intersection: " + sorted(getIntersection(amy, ben)));
        System.out.println("Only Amy: " + sorted(getFirstOnly(amy, ben)));
        System.out.println("Only Ben: " + sorted(getSecondOnly(amy, ben)));
    }
}