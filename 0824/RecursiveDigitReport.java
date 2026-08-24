public class RecursiveDigitReport {

    public static int digitSum(int number) {
        number = Math.abs(number);
        if (number < 10) {
            return number;
        }
        return (number % 10) + digitSum(number / 10);
    }

    public static int digitCount(int number) {
        if (number == 0) {
            return 1;
        }
        number = Math.abs(number);
        if (number < 10) {
            return 1;
        }
        return 1 + digitCount(number / 10);
    }

    public static int countDigit(int number, int target) {
        number = Math.abs(number);
        int currentMatch = (number % 10 == target) ? 1 : 0;
        
        if (number < 10) {
            return currentMatch;
        }
        return currentMatch + countDigit(number / 10, target);
    }

    public static void main(String[] args) {
        int[] testCases = {50205, 0, -731};
        
        for (int test : testCases) {
            System.out.println("--- 測試數值: " + test + " ---");
            System.out.println("digitSum: " + digitSum(test));
            System.out.println("digitCount: " + digitCount(test));
            System.out.println("countDigit (找 0): " + countDigit(test, 0));
            System.out.println("countDigit (找 5): " + countDigit(test, 5));
            System.out.println();
        }
    }
}