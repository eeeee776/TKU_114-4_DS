public class RecursiveCallReport {
    static int sum(int[] data, int index) {
        if (data == null || index >= data.length) {
            System.out.println("index=" + index + " Base Case -> return 0");
            return 0;
        }
        int currentValue = data[index];
        int recursiveResult = sum(data, index + 1);
        int returnValue = currentValue + recursiveResult;
        System.out.println("index=" + index + ", current=" + currentValue + 
                           ", recResult=" + recursiveResult + " -> return " + returnValue);
        return returnValue;
    }

    public static void main(String[] args) {
        System.out.println("--- General Array ---");
        sum(new int[]{10, 20, 30}, 0);

        System.out.println("\n--- Single Element ---");
        sum(new int[]{42}, 0);

        System.out.println("\n--- Empty Array ---");
        sum(new int[]{}, 0);
    }
}