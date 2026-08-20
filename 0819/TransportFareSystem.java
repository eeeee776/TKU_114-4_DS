abstract class Transport {
    private String routeName;

    Transport(String routeName) {
        this.routeName = routeName;
    }

    String getRouteName() {
        return routeName;
    }

    abstract int calculateFare(int distance);
}

class Bus extends Transport {
    Bus(String routeName) { super(routeName); }

    @Override
    int calculateFare(int distance) {
        return 15; // 公車固定費率範例
    }
}

class Taxi extends Transport {
    Taxi(String routeName) { super(routeName); }

    @Override
    int calculateFare(int distance) {
        return 85 + (Math.max(0, distance - 1) * 20); // 計程車里程計費範例
    }
}

public class TransportFareSystem {
    public static void main(String[] args) {
        Transport[] transports = {
            new Bus("307"), new Taxi("Uber"), new Bus("299"), new Taxi("55688")
        };

        for (Transport t : transports) {
            System.out.println(t.getRouteName() + " 票價：" + t.calculateFare(5));
        }
    }
}