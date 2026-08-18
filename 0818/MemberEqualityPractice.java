import java.util.Objects;

class LibraryMember {
    private String memberId;
    private String name;
    private String email;

    LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "LibraryMember{id='" + memberId + "', name='" + name + "', email='" + email + "'}";
    }

    @Override
    public boolean equals(Object other) {
        // 1. 如果是指向同一個記憶體位址，直接回傳 true
        if (this == other) {
            return true;
        }
        
        // 2. 如果 other 是 null，或者型別不對，回傳 false（使用 Java 16+ 的 instanceof 模式比對）
        if (!(other instanceof LibraryMember member)) {
            return false;
        }
        
        // 3. 比較領域模型 (Domain identity)，這裡只看 memberId 是否相同
        return Objects.equals(this.memberId, member.memberId);
    }

    @Override
    public int hashCode() {
        // equals 依賴了哪些欄位，hashCode 就必須依賴相同的欄位
        return Objects.hash(memberId);
    }
}

public class MemberEqualityPractice {
    public static void main(String[] args) {
        // 建立兩個 memberId 相同，但 email 不同的物件
        LibraryMember m1 = new LibraryMember("M100", "Alice", "alice@old-email.com");
        LibraryMember m2 = new LibraryMember("M100", "Alice", "alice@new-email.com");

        System.out.println("m1: " + m1);
        System.out.println("m2: " + m2);
        
        System.out.println("\n--- 比較結果 ---");
        // 預期為 false：因為使用 new 產生了兩塊獨立的記憶體，Reference 不同
        System.out.println("m1 == m2 的結果: " + (m1 == m2));
        
        // 預期為 true：因為我們覆寫了 equals，只要 memberId 相同就視為同一人
        System.out.println("m1.equals(m2) 的結果: " + m1.equals(m2));
        
        // 邊界條件：與 null 比較必須安全回傳 false，不可當機 (NullPointerException)
        System.out.println("m1.equals(null) 的結果: " + m1.equals(null));
    }
}