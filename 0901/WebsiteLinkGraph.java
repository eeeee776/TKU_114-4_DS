import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebsiteLinkGraph {
    // Map 的 Key 存網址，Value 存該網址對外的所有連結 (Outgoing Links)
    private final Map<String, Set<String>> outgoingLinks = new HashMap<>();

    public void addPage(String url) {
        outgoingLinks.putIfAbsent(url, new HashSet<>());
    }

    public void addLink(String fromUrl, String toUrl) {
        if (!outgoingLinks.containsKey(fromUrl) || !outgoingLinks.containsKey(toUrl)) {
            throw new IllegalArgumentException("Page must be added first.");
        }
        outgoingLinks.get(fromUrl).add(toUrl);
    }

    public Set<String> getOutgoingLinks(String url) {
        return outgoingLinks.getOrDefault(url, Set.of());
    }

    public int getIncomingCount(String url) {
        if (!outgoingLinks.containsKey(url)) return 0;
        int count = 0;
        for (Set<String> links : outgoingLinks.values()) {
            if (links.contains(url)) count++;
        }
        return count;
    }

    public List<String> getPagesWithNoIncoming() {
        List<String> result = new ArrayList<>();
        for (String url : outgoingLinks.keySet()) {
            if (getIncomingCount(url) == 0) result.add(url);
        }
        return result;
    }

    public List<String> getPagesWithNoOutgoing() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : outgoingLinks.entrySet()) {
            if (entry.getValue().isEmpty()) result.add(entry.getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        WebsiteLinkGraph web = new WebsiteLinkGraph();
        String[] pages = {"Home", "About", "Contact", "Blog", "HiddenPage"};
        for (String p : pages) web.addPage(p);

        web.addLink("Home", "About");
        web.addLink("Home", "Contact");
        web.addLink("Home", "Blog");
        web.addLink("About", "Home");
        web.addLink("Blog", "Home");
        // HiddenPage 無人連結，Contact 沒有連出去

        System.out.println("Home 對外連結: " + web.getOutgoingLinks("Home"));
        System.out.println("Home 被連結次數: " + web.getIncomingCount("Home"));
        System.out.println("無外部連結指向的頁面 (孤島): " + web.getPagesWithNoIncoming());
        System.out.println("無對外連結的頁面 (死胡同): " + web.getPagesWithNoOutgoing());
    }
}