import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialNetworkGraph {
    private final Map<String, Set<String>> network = new LinkedHashMap<>();

    public boolean addUser(String user) {
        if (user == null || user.isBlank()) return false;
        return network.putIfAbsent(user, new LinkedHashSet<>()) == null;
    }

    public boolean addFriend(String user1, String user2) {
        if (!network.containsKey(user1) || !network.containsKey(user2) || user1.equals(user2)) return false;
        boolean added1 = network.get(user1).add(user2);
        boolean added2 = network.get(user2).add(user1);
        return added1 && added2;
    }

    public boolean removeFriend(String user1, String user2) {
        if (!network.containsKey(user1) || !network.containsKey(user2)) return false;
        boolean rem1 = network.get(user1).remove(user2);
        boolean rem2 = network.get(user2).remove(user1);
        return rem1 && rem2;
    }

    public List<String> getMutualFriends(String user1, String user2) {
        if (!network.containsKey(user1) || !network.containsKey(user2)) return new ArrayList<>();
        Set<String> mutual = new LinkedHashSet<>(network.get(user1));
        mutual.retainAll(network.get(user2));
        List<String> result = new ArrayList<>(mutual);
        Collections.sort(result);
        return result;
    }

    public List<String> getIsolatedUsers() {
        List<String> isolated = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : network.entrySet()) {
            if (entry.getValue().isEmpty()) {
                isolated.add(entry.getKey());
            }
        }
        Collections.sort(isolated);
        return isolated;
    }

    public static void main(String[] args) {
        SocialNetworkGraph sn = new SocialNetworkGraph();
        sn.addUser("Amy");
        sn.addUser("Ben");
        sn.addUser("Cara");
        sn.addUser("Dan");
        sn.addUser("Eve");

        sn.addFriend("Amy", "Ben");
        sn.addFriend("Amy", "Cara");
        sn.addFriend("Ben", "Cara");
        sn.addFriend("Cara", "Dan");

        System.out.println("Mutual of Amy & Cara: " + sn.getMutualFriends("Amy", "Cara"));
        System.out.println("Mutual of Ben & Dan: " + sn.getMutualFriends("Ben", "Dan"));
        System.out.println("Isolated Users: " + sn.getIsolatedUsers());

        sn.removeFriend("Amy", "Ben");
        System.out.println("Mutual of Amy & Cara after unfriend: " + sn.getMutualFriends("Amy", "Cara"));
    }
}