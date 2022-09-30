package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String sentence) {

        Map<String, Integer> wordsCount = new HashMap<>();
        String[] words = sentence.split(" ");

        if (sentence.length() == 0) {
            return wordsCount;
        }
        for (String word : words) {
            int count = (int) wordsCount.getOrDefault(word, 0);
            wordsCount.put(word, count + 1);
        }
        return wordsCount;
    }

    public static String toString(Map<String, Integer> wordCount) {
        String result = "";
        if (wordCount.size() == 0) {
            return "{}";
        }
        for (String s : wordCount.keySet()) {
            result = result + "\n  " + s + ": " + wordCount.get(s);
        }
        result = "{" + result + "\n}";
        return result;
    }
}
//END
