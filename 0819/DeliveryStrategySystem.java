interface DeliveryMethod {
    int calculateFee(int amount);
    String getEstimatedTime();
}

class HomeDelivery implements DeliveryMethod {
    @Override
    public int calculateFee(int amount) {
        return amount >= 1000 ? 0 : 120; // 滿千免運
    }

    @Override
    public String getEstimatedTime() {
        return "約 1-2 個工作天配達";
    }
}

class StorePickup implements DeliveryMethod {
    @Override
    public int calculateFee(int amount) {
        return amount >= 600 ? 0 : 60;
    }

    @Override
    public String getEstimatedTime() {
        return "約 2-3 個工作天送達指定門市";
    }
}

class SelfPickup implements DeliveryMethod {
    @Override
    public int calculateFee(int amount) {
        return 0; // 自取免運費
    }

    @Override
    public String getEstimatedTime() {
        return "立即準備，可隨時至實體店面取貨";
    }
}

class OrderService {
    private DeliveryMethod deliveryMethod;

    public OrderService(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void processOrder(int amount) {
        System.out.println("訂單金額: " + amount);
        System.out.println("運費: " + deliveryMethod.calculateFee(amount));
        System.out.println("預估時間: " + deliveryMethod.getEstimatedTime());
        System.out.println("---------------------------");
    }
}

public class DeliveryStrategySystem {
    public static void main(String[] args) {
        System.out.println("[宅配服務]");
        OrderService homeService = new OrderService(new HomeDelivery());
        homeService.processOrder(800);

        System.out.println("[超商取貨服務]");
        OrderService storeService = new OrderService(new StorePickup());
        storeService.processOrder(800);

        System.out.println("[自取服務]");
        OrderService pickupService = new OrderService(new SelfPickup());
        pickupService.processOrder(800);
    }
}