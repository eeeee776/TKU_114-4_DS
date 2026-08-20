abstract class Device {
    abstract void runDiagnostic();
}

class Laptop extends Device {
    @Override
    void runDiagnostic() { System.out.println("筆電：檢查記憶體與硬碟..."); }
}

class Router extends Device {
    @Override
    void runDiagnostic() { System.out.println("路由器：檢查網路連線狀態..."); }
}

class Printer extends Device {
    @Override
    void runDiagnostic() { System.out.println("印表機：檢查墨水與紙張..."); }

    void cleanPrintHead() { System.out.println("印表機 (專屬動作)：清洗印字頭..."); }
}

public class DeviceInspectionSystem {
    public static void main(String[] args) {
        Device[] devices = {
            new Laptop(), new Printer(), new Router(), new Printer()
        };

        for (Device device : devices) {
            device.runDiagnostic(); // 多型操作
            
            // 安全的向下轉型 (Pattern Matching for instanceof)
            if (device instanceof Printer printer) {
                printer.cleanPrintHead();
            }
        }
    }
}