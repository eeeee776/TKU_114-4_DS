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
        return "會員編號: " + memberId + ", 姓名: " + name + ", Email: " + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LibraryMember)) {
            return false;
        }
        LibraryMember other = (LibraryMember) obj;
        return Objects.equals(this.memberId, other.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}

public class MemberEqualityPractice {
    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember("M001", "采依", "abc@gmail.com");
        LibraryMember m2 = new LibraryMember("M001", "采依", "xyz@yahoo.com");

        System.out.println(m1);
        System.out.println(m2);

        System.out.println("\n--- 比較結果 ---");
        System.out.println("使用 == 比較: " + (m1 == m2)); 
        System.out.println("使用 equals 比較: " + m1.equals(m2)); 
        
        System.out.println("與 null 比較: " + m1.equals(null)); 
    }
}