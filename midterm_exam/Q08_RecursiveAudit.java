package midterm_exam;
public class Q08_RecursiveAudit {
    public static int sumValid(int[] data, int index) {
        // recursion-proof C8-41
        if (data == null || index < 0 || index >= data.length) return 0;
        int val = data[index];
        int current = (val >= 0 && val <= 100) ? val : 0;
        return current + sumValid(data, index + 1);
    }

    public static int countOccurrences(int[] data, int index, int target) {
        if (data == null || index < 0 || index >= data.length) return 0;
        int match = (data[index] == target) ? 1 : 0;
        return match + countOccurrences(data, index + 1, target);
    }

    public static boolean isPalindrome(String text, int left, int right) {
        if (text == null) return false;
        if (left >= right) return true;
        if (Character.toLowerCase(text.charAt(left)) != Character.toLowerCase(text.charAt(right))) {
            return false;
        }
        return isPalindrome(text, left + 1, right - 1);
    }
}
