public class RecursiveArrayStatistics {

    public static int maximum(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        return maximumHelper(values, 0);
    }

    private static int maximumHelper(int[] values, int index) {
        if (index == values.length - 1) {
            return values[index];
        }
        return Math.max(values[index], maximumHelper(values, index + 1));
    }

    public static int minimum(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        return minimumHelper(values, 0);
    }

    private static int minimumHelper(int[] values, int index) {
        if (index == values.length - 1) {
            return values[index];
        }
        return Math.min(values[index], minimumHelper(values, index + 1));
    }

    public static int countAbove(int[] values, int threshold) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        return countAboveHelper(values, threshold, 0);
    }

    private static int countAboveHelper(int[] values, int threshold, int index) {
        if (index >= values.length) {
            return 0;
        }
        int currentCount = (values[index] > threshold) ? 1 : 0;
        return currentCount + countAboveHelper(values, threshold, index + 1);
    }

    public static void main(String[] args) {
        int[] arr = {15, 3, 9, 21, 7};
        System.out.println("Maximum: " + maximum(arr));
        System.out.println("Minimum: " + minimum(arr));
        System.out.println("Count > 10: " + countAbove(arr, 10));
        
        try {
            maximum(new int[]{});
        } catch (IllegalArgumentException e) {
            System.out.println("Empty array test passed: " + e.getMessage());
        }
    }
}