public class RecursiveDigitReport {
    static int digitSum(int n) {
        int absN = Math.abs(n);
        if (absN < 10) return absN;
        return absN % 10 + digitSum(absN / 10);
    }

    static int digitCount(int n) {
        int absN = Math.abs(n);
        if (absN < 10) return 1;
        return 1 + digitCount(absN / 10);
    }

    static int countDigit(int n, int target) {
        int absN = Math.abs(n);
        if (absN < 10) return absN == target ? 1 : 0;
        int current = (absN % 10 == target) ? 1 : 0;
        return current + countDigit(absN / 10, target);
    }

    public static void main(String[] args) {
        int[] tests = {50205, 0, -731};
        for (int val : tests) {
            System.out.println("Value: " + val);
            System.out.println("Sum: " + digitSum(val));
            System.out.println("Count: " + digitCount(val));
            System.out.println("Count of '0': " + countDigit(val, 0));
            System.out.println("Count of '5': " + countDigit(val, 5));
            System.out.println("---");
        }
    }
}