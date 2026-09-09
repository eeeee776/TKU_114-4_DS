import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordIndexSystem {
    public static void main(String[] args) {
        String[] sentences = {
            "Java is an object-oriented programming language.",
            "Java, java, and more Java!",
            "Learning Java is fun, and programming is great."
        };

        Map<String, Integer> wordCounts = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        for (String sentence : sentences) {
            String[] words = sentence.toLowerCase().replace(".", "").replace(",", "").split("\\s+");
            for (String word : words) {
                if (!word.isBlank()) {
                    uniqueWords.add(word);
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        }

        System.out.println("--- 總不重複單字數: " + uniqueWords.size() + " ---");
        System.out.println("--- 出現至少兩次的單字 ---");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() >= 2) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }
}