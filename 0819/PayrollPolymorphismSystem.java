abstract class Employee {
    private String name;

    Employee(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    abstract int calculatePay();
}

class MonthlyEmployee extends Employee {
    private int salary;

    MonthlyEmployee(String name, int salary) {
        super(name);
        this.salary = Math.max(0, salary);
    }

    @Override
    int calculatePay() {
        return salary;
    }
}

class HourlyEmployee extends Employee {
    private int hours;
    private int hourlyRate;

    HourlyEmployee(String name, int hours, int hourlyRate) {
        super(name);
        this.hours = Math.max(0, hours);
        this.hourlyRate = Math.max(0, hourlyRate);
    }

    @Override
    int calculatePay() {
        return hours * hourlyRate;
    }
}

class SalesEmployee extends Employee {
    private int baseSalary;
    private int salesAmount;
    private double commissionRate;

    SalesEmployee(String name, int baseSalary, int salesAmount, double commissionRate) {
        super(name);
        this.baseSalary = Math.max(0, baseSalary);
        this.salesAmount = Math.max(0, salesAmount);
        this.commissionRate = Math.max(0, commissionRate);
    }

    @Override
    int calculatePay() {
        return baseSalary + (int)(salesAmount * commissionRate);
    }
}

public class PayrollPolymorphismSystem {
    public static void main(String[] args) {
        Employee[] employees = {
            new MonthlyEmployee("Amy", 45000),
            new HourlyEmployee("Ben", 100, 180),
            new SalesEmployee("Cara", 25000, 300000, 0.1)
        };

        int totalSalary = 0;
        Employee highestPaid = employees[0];

        for (Employee emp : employees) {
            int pay = emp.calculatePay();
            System.out.println(emp.getName() + " salary: " + pay);
            totalSalary += pay;
            if (pay > highestPaid.calculatePay()) {
                highestPaid = emp;
            }
        }

        System.out.println("Total Salary: " + totalSalary);
        System.out.println("Highest Paid: " + highestPaid.getName() + " (" + highestPaid.calculatePay() + ")");
    }
}