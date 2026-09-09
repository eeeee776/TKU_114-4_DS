public class RecursiveArrayStatistics {
    static int maximum(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Invalid array");
        return maxHelper(arr, 0);
    }

    private static int maxHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        return Math.max(arr[index], maxHelper(arr, index + 1));
    }

    static int minimum(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Invalid array");
        return minHelper(arr, 0);
    }

    private static int minHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        return Math.min(arr[index], minHelper(arr, index + 1));
    }

    static int countAbove(int[] arr, int target) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Invalid array");
        return countHelper(arr, target, 0);
    }

    private static int countHelper(int[] arr, int target, int index) {
        if (index == arr.length) return 0;
        int count = arr[index] > target ? 1 : 0;
        return count + countHelper(arr, target, index + 1);
    }

    public static void main(String[] args) {
        int[] data = {15, 8, 22, 4, 19, 31, 2};
        System.out.println("Max: " + maximum(data));
        System.out.println("Min: " + minimum(data));
        System.out.println("Above 15: " + countAbove(data, 15));
    }
}