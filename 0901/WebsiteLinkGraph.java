import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WebsiteLinkGraph {
    private final Map<String, List<String>> graph = new LinkedHashMap<>();

    public void addPage(String url) {
        if (url != null && !url.isBlank()) {
            graph.putIfAbsent(url, new ArrayList<>());
        }
    }

    public boolean addLink(String from, String to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)) return false;
        List<String> links = graph.get(from);
        if (!links.contains(to)) {
            links.add(to);
            return true;
        }
        return false;
    }

    public List<String> getOutgoingLinks(String url) {
        return new ArrayList<>(graph.getOrDefault(url, new ArrayList<>()));
    }

    public int getIncomingCount(String url) {
        if (!graph.containsKey(url)) return 0;
        int count = 0;
        for (List<String> links : graph.values()) {
            if (links.contains(url)) count++;
        }
        return count;
    }

    public List<String> getNoIncomingPages() {
        List<String> result = new ArrayList<>();
        for (String url : graph.keySet()) {
            if (getIncomingCount(url) == 0) {
                result.add(url);
            }
        }
        Collections.sort(result);
        return result;
    }

    public List<String> getNoOutgoingPages() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            if (entry.getValue().isEmpty()) {
                result.add(entry.getKey());
            }
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        WebsiteLinkGraph web = new WebsiteLinkGraph();
        web.addPage("Index");
        web.addPage("About");
        web.addPage("Contact");
        web.addPage("Secret");

        web.addLink("Index", "About");
        web.addLink("Index", "Contact");
        web.addLink("About", "Contact");

        System.out.println("Index Outgoing: " + web.getOutgoingLinks("Index"));
        System.out.println("Contact Incoming Count: " + web.getIncomingCount("Contact"));
        System.out.println("No Incoming Pages: " + web.getNoIncomingPages());
        System.out.println("No Outgoing Pages: " + web.getNoOutgoingPages());
    }
}