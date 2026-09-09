import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList(
            "Amy", null, "", "Ben", "Amy", "  ", "Cara", "Ben", "Dan"
        ));

        System.out.println("清理前: " + list);

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name == null || name.isBlank()) {
                iterator.remove();
            }
        }

        Set<String> uniqueNames = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (String name : list) {
            if (!uniqueNames.add(name)) {
                duplicates.add(name);
            }
        }

        System.out.println("清理後 (無空白/null): " + list);
        System.out.println("重複出現的姓名: " + duplicates);
    }
}