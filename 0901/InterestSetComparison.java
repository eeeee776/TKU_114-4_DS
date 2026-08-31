import java.util.HashSet;
import java.util.Set;

public class InterestSetComparison {
    
    // 聯集 (Union)
    public static Set<String> union(Set<String> s1, Set<String> s2) {
        Set<String> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    // 交集 (Intersection)
    public static Set<String> intersection(Set<String> s1, Set<String> s2) {
        Set<String> result = new HashSet<>(s1);
        result.retainAll(s2);
        return result;
    }

    // 只有 s1 有的 (First-only / Difference)
    public static Set<String> firstOnly(Set<String> s1, Set<String> s2) {
        Set<String> result = new HashSet<>(s1);
        result.removeAll(s2);
        return result;
    }

    // 只有 s2 有的 (Second-only / Difference)
    public static Set<String> secondOnly(Set<String> s1, Set<String> s2) {
        return firstOnly(s2, s1);
    }

    public static void main(String[] args) {
        Set<String> alice = Set.of("Reading", "Coding", "Hiking", "Music");
        Set<String> bob = Set.of("Coding", "Gaming", "Music", "Cooking");

        System.out.println("Alice's interests: " + alice);
        System.out.println("Bob's interests: " + bob);
        System.out.println("-------------------------");
        System.out.println("Union (綜合興趣): " + union(alice, bob));
        System.out.println("Intersection (共同興趣): " + intersection(alice, bob));
        System.out.println("Only Alice likes: " + firstOnly(alice, bob));
        System.out.println("Only Bob likes: " + secondOnly(alice, bob));
    }
}