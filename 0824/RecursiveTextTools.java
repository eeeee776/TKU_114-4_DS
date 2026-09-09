public class RecursiveTextTools {
    static String reverse(String text) {
        if (text == null || text.length() <= 1) return text;
        return reverse(text.substring(1)) + text.charAt(0);
    }

    static boolean isPalindrome(String text) {
        if (text == null) return false;
        String cleanText = text.replaceAll("\\s+", "").toLowerCase();
        return checkPalindrome(cleanText, 0, cleanText.length() - 1);
    }

    private static boolean checkPalindrome(String text, int start, int end) {
        if (start >= end) return true;
        if (text.charAt(start) != text.charAt(end)) return false;
        return checkPalindrome(text, start + 1, end - 1);
    }

    static int countCharacter(String text, char target) {
        if (text == null || text.isEmpty()) return 0;
        int count = (Character.toLowerCase(text.charAt(0)) == Character.toLowerCase(target)) ? 1 : 0;
        return count + countCharacter(text.substring(1), target);
    }

    public static void main(String[] args) {
        System.out.println(reverse("Hello"));
        System.out.println(isPalindrome("Level"));
        System.out.println(isPalindrome("A nut for a jar of tuna"));
        System.out.println(isPalindrome(""));
        System.out.println(isPalindrome("a"));
        System.out.println(isPalindrome("Not a palindrome"));
        System.out.println(countCharacter("banana", 'a'));
        System.out.println(countCharacter("Apple", 'A'));
    }
}