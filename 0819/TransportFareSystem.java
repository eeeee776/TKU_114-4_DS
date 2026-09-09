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
    Bus(String routeName) {
        super(routeName);
    }

    @Override
    int calculateFare(int distance) {
        return 15;
    }
}

class Taxi extends Transport {
    Taxi(String routeName) {
        super(routeName);
    }

    @Override
    int calculateFare(int distance) {
        return 70 + Math.max(0, distance - 1) * 25;
    }
}

public class TransportFareSystem {
    public static void main(String[] args) {
        Transport[] transports = {
            new Bus("307"),
            new Bus("262"),
            new Taxi("Local"),
            new Taxi("Airport")
        };

        for (Transport t : transports) {
            System.out.println(t.getRouteName() + " fare: " + t.calculateFare(5));
        }
    }
}