import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginActivityReport {
    private record LoginRecord(String account, String ipAddress) {}

    public static void analyzeLogins(List<LoginRecord> logs) {
        Map<String, Integer> accountCounts = new HashMap<>();
        Set<String> uniqueIps = new HashSet<>();

        if (logs == null) return;

        for (LoginRecord log : logs) {
            // 統計登入次數
            accountCounts.merge(log.account(), 1, Integer::sum);
            // 收集獨立 IP
            uniqueIps.add(log.ipAddress());
        }

        System.out.println("=== 系統登入報告 ===");
        System.out.println("獨立 IP 來源總數: " + uniqueIps.size());
        System.out.println("不同 IP 列表: " + uniqueIps);
        
        System.out.println("\n[異常重複登入警報] (登入大於等於 3 次)");
        boolean hasAlert = false;
        for (Map.Entry<String, Integer> entry : accountCounts.entrySet()) {
            if (entry.getValue() >= 3) {
                System.out.println("警告: 帳號 " + entry.getKey() + " 嘗試登入了 " + entry.getValue() + " 次");
                hasAlert = true;
            }
        }
        if (!hasAlert) System.out.println("無異常登入帳號。");
    }

    public static void main(String[] args) {
        List<LoginRecord> logs = List.of(
            new LoginRecord("admin", "192.168.1.100"),
            new LoginRecord("user1", "192.168.1.101"),
            new LoginRecord("admin", "192.168.1.100"),
            new LoginRecord("user2", "192.168.1.105"),
            new LoginRecord("admin", "10.0.0.5"),
            new LoginRecord("admin", "192.168.1.100") // admin 登入 4 次
        );

        analyzeLogins(logs);
    }
}