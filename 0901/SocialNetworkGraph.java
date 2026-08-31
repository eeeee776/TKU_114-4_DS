import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SocialNetworkGraph {
    private final Map<String, Set<String>> network = new HashMap<>();

    public void addUser(String user) {
        network.putIfAbsent(user, new HashSet<>());
    }

    public void addFriend(String user1, String user2) {
        if (!network.containsKey(user1) || !network.containsKey(user2) || user1.equals(user2)) return;
        network.get(user1).add(user2);
        network.get(user2).add(user1);
    }

    public void unfriend(String user1, String user2) {
        if (!network.containsKey(user1) || !network.containsKey(user2)) return;
        network.get(user1).remove(user2);
        network.get(user2).remove(user1);
    }

    public Set<String> getFriends(String user) {
        return network.getOrDefault(user, Set.of());
    }

    public Set<String> getMutualFriends(String user1, String user2) {
        Set<String> mutual = new HashSet<>(getFriends(user1));
        mutual.retainAll(getFriends(user2));
        return mutual;
    }

    public Set<String> getIsolatedUsers() {
        Set<String> isolated = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : network.entrySet()) {
            if (entry.getValue().isEmpty()) {
                isolated.add(entry.getKey());
            }
        }
        return isolated;
    }

    public static void main(String[] args) {
        SocialNetworkGraph sns = new SocialNetworkGraph();
        for (String u : new String[]{"Alice", "Bob", "Charlie", "David", "Eve"}) {
            sns.addUser(u);
        }

        sns.addFriend("Alice", "Bob");
        sns.addFriend("Alice", "Charlie");
        sns.addFriend("Bob", "Charlie");
        sns.addFriend("David", "Bob");
        
        // Eve 沒朋友
        System.out.println("Bob's friends: " + sns.getFriends("Bob"));
        System.out.println("Alice & David mutual friends: " + sns.getMutualFriends("Alice", "David"));
        System.out.println("Isolated users: " + sns.getIsolatedUsers());
        
        sns.unfriend("Alice", "Bob");
        System.out.println("Bob's friends after unfriending Alice: " + sns.getFriends("Bob"));
    }
}