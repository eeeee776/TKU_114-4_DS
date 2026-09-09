abstract class Device {
    private String name;

    Device(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    abstract void runDiagnostic();
}

class Laptop extends Device {
    Laptop(String name) {
        super(name);
    }

    @Override
    void runDiagnostic() {
        System.out.println("Checking CPU and RAM for Laptop: " + getName());
    }
}

class Router extends Device {
    Router(String name) {
        super(name);
    }

    @Override
    void runDiagnostic() {
        System.out.println("Checking network connection for Router: " + getName());
    }
}

class Printer extends Device {
    Printer(String name) {
        super(name);
    }

    @Override
    void runDiagnostic() {
        System.out.println("Checking ink levels for Printer: " + getName());
    }

    void cleanPrintHead() {
        System.out.println("Cleaning print head for Printer: " + getName());
    }
}

public class DeviceInspectionSystem {
    public static void main(String[] args) {
        Device[] devices = {
            new Laptop("Office-Laptop"),
            new Router("Main-Router"),
            new Printer("Lobby-Printer"),
            new Printer("Sales-Printer")
        };

        for (Device d : devices) {
            d.runDiagnostic();
            
            if (d instanceof Printer printer) {
                printer.cleanPrintHead();
            }
        }
    }
}