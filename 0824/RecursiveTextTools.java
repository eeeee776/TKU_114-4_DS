public class RecursiveTextTools {

    public static String reverse(String text) {
        if (text == null) return null;
        return reverseHelper(text, text.length() - 1);
    }

    private static String reverseHelper(String text, int index) {
        if (index < 0) return "";
        return text.charAt(index) + reverseHelper(text, index - 1);
    }

    public static boolean isPalindrome(String text) {
        if (text == null) return false;
        // 預處理：轉小寫並去除所有空白
        String cleaned = text.replaceAll("\\s+", "").toLowerCase();
        return isPalindromeHelper(cleaned, 0, cleaned.length() - 1);
    }

    private static boolean isPalindromeHelper(String text, int start, int end) {
        if (start >= end) return true; // Base case: 長度為 0 或 1 時皆為迴文
        if (text.charAt(start) != text.charAt(end)) return false;
        return isPalindromeHelper(text, start + 1, end - 1);
    }

    public static int countCharacter(String text, char target) {
        if (text == null) return 0;
        return countCharHelper(text, target, 0);
    }

    private static int countCharHelper(String text, char target, int index) {
        if (index >= text.length()) return 0;
        int currentMatch = (Character.toLowerCase(text.charAt(index)) == Character.toLowerCase(target)) ? 1 : 0;
        return currentMatch + countCharHelper(text, target, index + 1);
    }

    public static void main(String[] args) {
        System.out.println("Reverse 'Java': " + reverse("Java"));
        
        System.out.println("isPalindrome 'Level': " + isPalindrome("Level"));
        System.out.println("isPalindrome 'A nut for a jar of tuna': " + isPalindrome("A nut for a jar of tuna"));
        
        System.out.println("countCharacter 'Banana' 找 'a': " + countCharacter("Banana", 'a'));
    }
}