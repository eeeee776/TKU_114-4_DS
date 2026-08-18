class Account {
    private String id;
    private int balance;

    Account(String id, int balance) {
        this.id = id;
        this.balance = Math.max(0, balance);
    }

    boolean withdraw(int amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }

    void deposit(int amount) {
        if (amount > 0) balance += amount;
    }

    @Override
    public String toString() {
        return "帳戶 " + id + " (餘額: $" + balance + ")";
    }
}

class TransferService {
    static boolean transfer(Account source, Account target, int amount) {
        // 1. 防禦性檢查：物件是否為 null、是否為同一個物件 (Alias)
        if (source == null || target == null || source == target) {
            return false;
        }
        
        // 2. 金額必須合理，且必須先扣款成功，才能將錢存入目標帳戶
        if (amount <= 0) {
            return false;
        }
        
        // 如果來源帳戶餘額不足，withdraw 會回傳 false，此時立即中斷，保護雙方狀態
        if (!source.withdraw(amount)) {
            return false;
        }
        
        // 3. 來源扣款成功，執行目標入帳
        target.deposit(amount);
        return true;
    }
}

public class AccountTransferService {
    public static void main(String[] args) {
        Account accA = new Account("A100", 1000);
        Account accB = new Account("B200", 500);

        System.out.println("初始狀態: " + accA + ", " + accB);

        System.out.println("\n測試 1 - 正常轉帳 300: " + TransferService.transfer(accA, accB, 300));
        System.out.println(accA + " | " + accB);

        System.out.println("\n測試 2 - 餘額不足轉帳 9000: " + TransferService.transfer(accA, accB, 9000));
        System.out.println(accA + " | " + accB); // 狀態不變

        System.out.println("\n測試 3 - 同帳戶轉帳 (Alias): " + TransferService.transfer(accA, accA, 100));
        System.out.println(accA); // 狀態不變

        System.out.println("\n測試 4 - Null 目標: " + TransferService.transfer(accA, null, 100));
        System.out.println(accA); // 狀態不變
    }
}