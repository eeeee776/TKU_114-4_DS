import java.util.Objects;

final class WalletTransaction {
    private final int sequence;
    private final String type;
    private final int amount;
    private final int balanceAfter;

    WalletTransaction(int sequence, String type, int amount, int balanceAfter) {
        this.sequence = sequence;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    int getSequence() { return sequence; }
    String getType() { return type; }
    int getAmount() { return amount; }

    @Override
    public String toString() {
        return sequence + " [" + type + "] $" + amount + " 餘額: $" + balanceAfter;
    }
}

class DigitalWallet {
    private final String walletId;
    private int balance;
    private final WalletTransaction[] transactions;
    private int transactionCount;

    DigitalWallet(String walletId, int historyCapacity) {
        this.walletId = walletId;
        this.balance = 0;
        this.transactions = new WalletTransaction[Math.max(1, historyCapacity)];
        this.transactionCount = 0;
    }

    // 檢查是否有空間記錄新交易
    boolean hasCapacity() {
        return transactionCount < transactions.length;
    }

    boolean deposit(int amount) {
        if (amount <= 0 || !hasCapacity()) return false;
        balance += amount;
        record("DEPOSIT", amount);
        return true;
    }

    boolean pay(int amount) {
        if (amount <= 0 || amount > balance || !hasCapacity()) return false;
        balance -= amount;
        record("PAY", amount);
        return true;
    }

    // 擴充功能 3 & 4：跨錢包轉帳
    boolean transferTo(DigitalWallet target, int amount) {
        if (target == null || target == this) return false;
        if (amount <= 0 || amount > balance) return false;
        
        // 核心防禦：雙方都必須有空間寫入歷史紀錄，否則交易取消
        if (!this.hasCapacity() || !target.hasCapacity()) return false;

        this.balance -= amount;
        this.record("TRANSFER_OUT", amount);

        target.balance += amount;
        target.record("TRANSFER_IN", amount);

        return true;
    }

    private void record(String type, int amount) {
        transactions[transactionCount] = new WalletTransaction(
                transactionCount + 1, type, amount, balance);
        transactionCount++;
    }

    // 擴充功能 1：搜尋交易
    WalletTransaction findTransaction(int sequence) {
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getSequence() == sequence) {
                return transactions[i];
            }
        }
        return null;
    }

    // 擴充功能 2：依類型統計
    int totalByType(String type) {
        int total = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (Objects.equals(transactions[i].getType(), type)) {
                total += transactions[i].getAmount();
            }
        }
        return total;
    }

    // 擴充功能 5：印出完整對帳單
    void printStatement() {
        System.out.println("=== 錢包 " + walletId + " 對帳單 ===");
        System.out.println("目前餘額: $" + balance);
        for (int i = 0; i < transactionCount; i++) {
            System.out.println(" - " + transactions[i]);
        }
        System.out.println("======================");
    }
}

public class WalletHistoryManager {
    public static void main(String[] args) {
        // 設定容量為 5
        DigitalWallet walletA = new DigitalWallet("W-AAA", 5);
        DigitalWallet walletB = new DigitalWallet("W-BBB", 5);

        walletA.deposit(1000);
        walletA.pay(200);
        
        System.out.println("A 轉帳 300 給 B: " + walletA.transferTo(walletB, 300));
        
        System.out.println("\n--- 輸出報表 ---");
        walletA.printStatement();
        walletB.printStatement();

        System.out.println("\n--- 統計與搜尋 ---");
        System.out.println("A 的轉出總金額: $" + walletA.totalByType("TRANSFER_OUT"));
        
        WalletTransaction t2 = walletA.findTransaction(2);
        System.out.println("A 的第 2 筆交易: " + (t2 != null ? t2 : "找不到"));
    }
}