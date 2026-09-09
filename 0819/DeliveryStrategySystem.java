interface DeliveryMethod {
    int calculateFee();
    String getDescription();
}

class HomeDelivery implements DeliveryMethod {
    @Override
    public int calculateFee() {
        return 120;
    }

    @Override
    public String getDescription() {
        return "Home Delivery (2-3 days)";
    }
}

class StorePickup implements DeliveryMethod {
    @Override
    public int calculateFee() {
        return 60;
    }

    @Override
    public String getDescription() {
        return "Convenience Store Pickup (3-5 days)";
    }
}

class SelfPickup implements DeliveryMethod {
    @Override
    public int calculateFee() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Self Pickup at Store (Available now)";
    }
}

class OrderService {
    private DeliveryMethod deliveryMethod;

    OrderService(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    void processOrder(int orderTotal) {
        int shippingFee = deliveryMethod.calculateFee();
        System.out.println("Order Total: " + orderTotal);
        System.out.println("Delivery: " + deliveryMethod.getDescription());
        System.out.println("Shipping Fee: " + shippingFee);
        System.out.println("Grand Total: " + (orderTotal + shippingFee));
        System.out.println("-------------------------");
    }
}

public class DeliveryStrategySystem {
    public static void main(String[] args) {
        OrderService order1 = new OrderService(new HomeDelivery());
        OrderService order2 = new OrderService(new StorePickup());
        OrderService order3 = new OrderService(new SelfPickup());

        order1.processOrder(1000);
        order2.processOrder(850);
        order3.processOrder(1200);
    }
}