public class RecursiveCallReport {
    
    public static int sum(int[] data) {
        return sum(data, 0, 0);
    }

    private static int sum(int[] data, int index, int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "enter sum(index=" + index + ")");

        // Base case: index 達到陣列長度
        if (index >= data.length) {
            System.out.println(indent + "base case: return 0");
            return 0;
        }

        int current = data[index];
        System.out.println(indent + "current value = " + current);
        
        // Recursive step
        int recursiveResult = sum(data, index + 1, depth + 1);
        int total = current + recursiveResult;
        
        System.out.println(indent + "return " + current + " + " + recursiveResult + " = " + total);
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=== Test 1: Normal Array [10, 20, 30] ===");
        int[] normal = {10, 20, 30};
        System.out.println("Final Result: " + sum(normal));

        System.out.println("\n=== Test 2: Single Element Array [5] ===");
        int[] single = {5};
        System.out.println("Final Result: " + sum(single));

        System.out.println("\n=== Test 3: Empty Array [] ===");
        int[] empty = {};
        System.out.println("Final Result: " + sum(empty));
    }
}