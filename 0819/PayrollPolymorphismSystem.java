abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 宣告為 abstract，要求子類別必須各自提供實作
    public abstract int calculatePay();
}

class MonthlyEmployee extends Employee {
    private int salary;

    public MonthlyEmployee(String name, int salary) {
        super(name); // 呼叫父類別建構子
        this.salary = Math.max(0, salary); // 避免負數薪資
    }

    @Override
    public int calculatePay() {
        return salary;
    }
}

class HourlyEmployee extends Employee {
    private int hours;
    private int hourlyRate;

    public HourlyEmployee(String name, int hours, int hourlyRate) {
        super(name);
        this.hours = Math.max(0, hours);
        this.hourlyRate = Math.max(0, hourlyRate);
    }

    @Override
    public int calculatePay() {
        return hours * hourlyRate;
    }
}

class SalesEmployee extends Employee {
    private int baseSalary;
    private int sales;
    private int commissionRate; // 業績獎金抽成百分比

    public SalesEmployee(String name, int baseSalary, int sales, int commissionRate) {
        super(name);
        this.baseSalary = Math.max(0, baseSalary);
        this.sales = Math.max(0, sales);
        this.commissionRate = Math.max(0, commissionRate);
    }

    @Override
    public int calculatePay() {
        return baseSalary + (sales * commissionRate / 100);
    }
}

public class PayrollPolymorphismSystem {
    public static void main(String[] args) {
        // 使用父類別陣列存放不同子類別的物件
        Employee[] employees = {
            new MonthlyEmployee("Alice (月薪)", 55000),
            new HourlyEmployee("Bob (時薪)", 120, 220),
            new SalesEmployee("Charlie (業務)", 28000, 500000, 5)
        };

        int totalPayroll = 0;
        int maxPay = 0;
        String maxPayName = "";

        // 多型的威力：用同樣的迴圈處理不同的物件
        for (Employee emp : employees) {
            int pay = emp.calculatePay(); // 動態綁定 (Dynamic dispatch)
            totalPayroll += pay;
            
            // 找出最高薪資
            if (pay > maxPay) {
                maxPay = pay;
                maxPayName = emp.getName();
            }
            System.out.println(emp.getName() + " 薪資: " + pay);
        }

        System.out.println("-------------------------");
        System.out.println("總薪資支出: " + totalPayroll);
        System.out.println("最高薪資員工: " + maxPayName + " (" + maxPay + ")");
    }
}