import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordIndexSystem {
    public static void main(String[] args) {
        String[] sentences = {
            "Java is a programming language.",
            "Java is widely used, and it is powerful."
        };

        Map<String, Integer> wordCounts = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        for (String sentence : sentences) {
            // 將字串轉小寫並使用 regex 去除逗號與句點
            String cleanSentence = sentence.toLowerCase().replaceAll("[,.]", "");
            String[] words = cleanSentence.split("\\s+");

            for (String word : words) {
                if (!word.isBlank()) {
                    uniqueWords.add(word);
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        }

        System.out.println("=== 所有不重複單字 ===");
        System.out.println(uniqueWords);

        System.out.println("\n=== 出現至少兩次的單字 ===");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() >= 2) {
                System.out.println(entry.getKey() + " : " + entry.getValue() + " 次");
            }
        }
    }
}