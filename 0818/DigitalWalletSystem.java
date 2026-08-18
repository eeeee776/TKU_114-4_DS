class DigitalWallet {
    private String walletId;
    private String owner;
    private int balance;
    private int transactionCount;

    DigitalWallet(String walletId, String owner) {
        this.walletId = (walletId == null || walletId.isBlank()) ? "Unknown" : walletId;
        this.owner = (owner == null || owner.isBlank()) ? "Unknown" : owner;
        this.balance = 0;
        this.transactionCount = 0;
    }

    boolean deposit(int amount) {
        if (amount <= 0) return false;
        balance += amount;
        transactionCount++;
        return true;
    }

    boolean pay(int amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        transactionCount++;
        return true;
    }

    boolean refund(int amount) {
        if (amount <= 0) return false;
        balance += amount;
        transactionCount++;
        return true;
    }

    void printSummary() {
        System.out.printf("錢包 [%s] 擁有者: %s | 餘額: $%d | 總交易次數: %d%n", 
                          walletId, owner, balance, transactionCount);
    }
}

public class DigitalWalletSystem {
    public static void main(String[] args) {
        DigitalWallet myWallet = new DigitalWallet("W1001", "Bob");

        System.out.println("1. 正常儲值 1000: " + myWallet.deposit(1000));
        System.out.println("2. 正常付款 300: " + myWallet.pay(300));
        System.out.println("3. 餘額不足付款 900: " + myWallet.pay(900));
        System.out.println("4. 負數金額付款 -50: " + myWallet.pay(-50));
        System.out.println("5. 正常退款 150: " + myWallet.refund(150));

        myWallet.printSummary(); // 預期餘額：1000 - 300 + 150 = 850，交易次數：3
    }
}