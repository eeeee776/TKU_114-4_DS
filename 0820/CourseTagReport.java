import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseTagReport {
    public static void main(String[] args) {
        String[] inputTags = {"Java", "Python", "Java", "C++", "Python", "Java", "Go"};

        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();

        for (String tag : inputTags) {
            list.add(tag);
            set.add(tag);
            map.put(tag, map.getOrDefault(tag, 0) + 1);
        }

        System.out.println("List (原始順序, 可重複): " + list);
        System.out.println("Set (不重複標籤, 無特定順序): " + set);
        System.out.println("Map (標籤次數統計): " + map);
    }
}