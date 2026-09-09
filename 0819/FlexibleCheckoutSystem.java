interface PricingPolicy {
    int finalPrice(int originalPrice);
}

class StandardPricing implements PricingPolicy {
    @Override
    public int finalPrice(int originalPrice) {
        return Math.max(0, originalPrice);
    }
}

class VipPricing implements PricingPolicy {
    @Override
    public int finalPrice(int originalPrice) {
        return Math.max(0, originalPrice) * 85 / 100;
    }
}

class DiscountPricing implements PricingPolicy {
    @Override
    public int finalPrice(int originalPrice) {
        int price = Math.max(0, originalPrice);
        return price >= 2000 ? price - 300 : price;
    }
}

interface NotificationChannel {
    boolean send(String receiver, String message);
}

class EmailChannel implements NotificationChannel {
    @Override
    public boolean send(String receiver, String message) {
        if (receiver == null || !receiver.contains("@")) return false;
        System.out.println("EMAIL to " + receiver + ": " + message);
        return true;
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public boolean send(String receiver, String message) {
        if (receiver == null || receiver.isBlank()) return false;
        System.out.println("SMS to " + receiver + ": " + message);
        return true;
    }
}

class ConsoleChannel implements NotificationChannel {
    @Override
    public boolean send(String receiver, String message) {
        System.out.println("CONSOLE to " + receiver + ": " + message);
        return true;
    }
}

class CheckoutResult {
    String orderId;
    int originalPrice;
    int finalPrice;
    boolean notificationStatus;

    CheckoutResult(String orderId, int originalPrice, int finalPrice, boolean notificationStatus) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notificationStatus = notificationStatus;
    }

    @Override
    public String toString() {
        return "Result[order=" + orderId + ", original=" + originalPrice + 
               ", final=" + finalPrice + ", notified=" + notificationStatus + "]";
    }
}

class CheckoutService {
    private PricingPolicy pricing;
    private NotificationChannel channel;

    CheckoutService(PricingPolicy pricing, NotificationChannel channel) {
        this.pricing = pricing;
        this.channel = channel;
    }

    CheckoutResult checkout(String orderId, int originalPrice, String receiver) {
        int finalPrice = pricing.finalPrice(originalPrice);
        boolean status = channel.send(receiver, "Order " + orderId + " completed. Final price: " + finalPrice);
        return new CheckoutResult(orderId, originalPrice, finalPrice, status);
    }
}

public class FlexibleCheckoutSystem {
    public static void main(String[] args) {
        CheckoutService s1 = new CheckoutService(new StandardPricing(), new ConsoleChannel());
        CheckoutService s2 = new CheckoutService(new VipPricing(), new EmailChannel());
        CheckoutService s3 = new CheckoutService(new DiscountPricing(), new SmsChannel());
        CheckoutService s4 = new CheckoutService(new VipPricing(), new ConsoleChannel());
        CheckoutService s5 = new CheckoutService(new DiscountPricing(), new EmailChannel());
        CheckoutService s6 = new CheckoutService(new StandardPricing(), new SmsChannel());

        System.out.println(s1.checkout("O01", 1000, "Admin"));
        System.out.println(s2.checkout("O02", 2000, "amy@mail.com"));
        System.out.println(s3.checkout("O03", 2500, "0912345678"));
        System.out.println(s4.checkout("O04", 500, "Counter"));
        System.out.println(s5.checkout("O05", 2200, "invalid_email"));
        System.out.println(s6.checkout("O06", 1500, "0987654321"));
    }
}