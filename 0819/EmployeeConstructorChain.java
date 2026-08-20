abstract class EmployeeBase {
    private String id;
    private String name;

    EmployeeBase(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("EmployeeBase 建構子被呼叫");
    }

    abstract int calculatePay();
}

class FullTimeEmployee extends EmployeeBase {
    private int monthlySalary;

    FullTimeEmployee(String id, String name, int monthlySalary) {
        super(id, name);
        this.monthlySalary = Math.max(0, monthlySalary);
        System.out.println("FullTimeEmployee 建構子被呼叫");
    }

    @Override
    int calculatePay() { return monthlySalary; }
}

class PartTimeEmployee extends EmployeeBase {
    private int hours, hourlyRate;

    PartTimeEmployee(String id, String name, int hours, int hourlyRate) {
        super(id, name);
        this.hours = Math.max(0, hours);
        this.hourlyRate = Math.max(0, hourlyRate);
        System.out.println("PartTimeEmployee 建構子被呼叫");
    }

    @Override
    int calculatePay() { return hours * hourlyRate; }
}

public class EmployeeConstructorChain {
    public static void main(String[] args) {
        System.out.println("--- 建立全職員工 ---");
        EmployeeBase fte = new FullTimeEmployee("E01", "Alice", 50000);
        
        System.out.println("--- 建立兼職員工 ---");
        EmployeeBase pte = new PartTimeEmployee("E02", "Bob", -10, 200); // 測試負數時數
        
        System.out.println("Bob 薪資: " + pte.calculatePay());
    }
}