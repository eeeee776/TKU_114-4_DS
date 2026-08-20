// --- Pricing Policy (計價策略) ---
interface PricingPolicy {
    int finalPrice(int originalPrice);
}

class StandardPricing implements PricingPolicy {
    @Override
    public int finalPrice(int originalPrice) { return Math.max(0, originalPrice); }
}

class VipPricing implements PricingPolicy {
    @Override
    public int finalPrice(int originalPrice) { return Math.max(0, originalPrice) * 85 / 100; }
}

class Discount2000Pricing implements PricingPolicy {
    @Override
    public int finalPrice(int originalPrice) {
        int price = Math.max(0, originalPrice);
        return price >= 2000 ? price - 300 : price;
    }
}

// --- Notification Channel (通知頻道) ---
interface NotificationChannel {
    boolean send(String receiver, String message);
}

class EmailChannel implements NotificationChannel {
    @Override
    public boolean send(String receiver, String message) {
        if (receiver == null || !receiver.contains("@")) return false;
        System.out.println("EMAIL to [" + receiver + "] : " + message);
        return true;
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public boolean send(String receiver, String message) {
        if (receiver == null || receiver.isBlank()) return false;
        System.out.println("SMS to [" + receiver + "] : " + message);
        return true;
    }
}

class ConsoleChannel implements NotificationChannel {
    @Override
    public boolean send(String receiver, String message) {
        System.out.println("CONSOLE (Counter) -> " + message);
        return true;
    }
}

// --- Checkout System (結帳服務) ---
class CheckoutResult {
    public final String orderId;
    public final int originalPrice;
    public final int finalPrice;
    public final boolean notificationSent;

    public CheckoutResult(String orderId, int originalPrice, int finalPrice, boolean notificationSent) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notificationSent = notificationSent;
    }

    @Override
    public String toString() {
        return String.format("Result: Order %s | 原價 %d -> 實付 %d | 通知成功: %b",
                orderId, originalPrice, finalPrice, notificationSent);
    }
}

class CheckoutService {
    private final PricingPolicy pricing;
    private final NotificationChannel channel;

    CheckoutService(PricingPolicy pricing, NotificationChannel channel) {
        this.pricing = pricing;
        this.channel = channel;
    }

    CheckoutResult checkout(String orderId, int originalPrice, String receiver) {
        if (orderId == null || orderId.isBlank() || originalPrice < 0) {
            return new CheckoutResult(orderId, originalPrice, 0, false);
        }
        int amount = pricing.finalPrice(originalPrice);
        boolean sent = channel.send(receiver, "您的訂單 " + orderId + " 已成立，實付金額: " + amount);
        return new CheckoutResult(orderId, originalPrice, amount, sent);
    }
}

public class FlexibleCheckoutSystem {
    public static void main(String[] args) {
        // 建立至少六種計價與通知的組合測試
        CheckoutService[] combinations = {
            new CheckoutService(new StandardPricing(), new ConsoleChannel()),
            new CheckoutService(new VipPricing(), new EmailChannel()),
            new CheckoutService(new Discount2000Pricing(), new SmsChannel()),
            new CheckoutService(new StandardPricing(), new EmailChannel()),
            new CheckoutService(new VipPricing(), new SmsChannel()),
            new CheckoutService(new Discount2000Pricing(), new ConsoleChannel())
        };

        System.out.println(combinations[0].checkout("O201", 1500, "counter"));
        System.out.println(combinations[1].checkout("O202", 2000, "vip@example.com"));
        System.out.println(combinations[2].checkout("O203", 2500, "0987654321"));
        System.out.println(combinations[3].checkout("O204", 800, "invalid_email")); // 測試失敗通知
        System.out.println(combinations[4].checkout("O205", 1200, "0911222333"));
        System.out.println(combinations[5].checkout("O206", 1999, "admin"));
    }
}