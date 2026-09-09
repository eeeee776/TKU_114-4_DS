abstract class EmployeeBase {
    private String id;
    private String name;

    EmployeeBase(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("EmployeeBase constructor");
    }

    String getName() {
        return name;
    }

    abstract int calculatePay();
}

class FullTimeEmployee extends EmployeeBase {
    private int salary;

    FullTimeEmployee(String id, String name, int salary) {
        super(id, name);
        this.salary = Math.max(0, salary);
        System.out.println("FullTimeEmployee constructor");
    }

    @Override
    int calculatePay() {
        return salary;
    }
}

class PartTimeEmployee extends EmployeeBase {
    private int hourlyRate;
    private int hours;

    PartTimeEmployee(String id, String name, int hourlyRate, int hours) {
        super(id, name);
        this.hourlyRate = Math.max(0, hourlyRate);
        this.hours = Math.max(0, hours);
        System.out.println("PartTimeEmployee constructor");
    }

    @Override
    int calculatePay() {
        return hourlyRate * hours;
    }
}

public class EmployeeConstructorChain {
    public static void main(String[] args) {
        EmployeeBase fullTime = new FullTimeEmployee("F01", "Amy", 50000);
        System.out.println(fullTime.getName() + " pay: " + fullTime.calculatePay());

        EmployeeBase partTime = new PartTimeEmployee("P01", "Ben", -200, 80);
        System.out.println(partTime.getName() + " pay: " + partTime.calculatePay());
    }
}