import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class LoginRecord {
    String account;
    String ip;

    LoginRecord(String account, String ip) {
        this.account = account;
        this.ip = ip;
    }
}

public class LoginActivityReport {
    public static void main(String[] args) {
        LoginRecord[] logs = {
            new LoginRecord("alice", "192.168.1.10"),
            new LoginRecord("bob", "10.0.0.5"),
            new LoginRecord("alice", "192.168.1.11"),
            new LoginRecord("alice", "192.168.1.12"),
            new LoginRecord("alice", "192.168.1.13"),
            new LoginRecord("charlie", "10.0.0.5"),
            new LoginRecord("bob", "10.0.0.8")
        };

        Map<String, Integer> accountCounts = new HashMap<>();
        Set<String> uniqueIps = new HashSet<>();

        for (LoginRecord log : logs) {
            if (log.account != null) {
                accountCounts.merge(log.account, 1, Integer::sum);
            }
            if (log.ip != null) {
                uniqueIps.add(log.ip);
            }
        }

        System.out.println("Total Unique IPs: " + uniqueIps.size());
        System.out.println("Abnormal Logins (Count > 3):");
        
        for (Map.Entry<String, Integer> entry : accountCounts.entrySet()) {
            if (entry.getValue() > 3) {
                System.out.println("- " + entry.getKey() + " logged in " + entry.getValue() + " times.");
            }
        }
    }
}