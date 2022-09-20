package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String sentence) {

        String[] words = sentence.split(" ");
        Map<String, Integer> wordCount = new HashMap<>();

        if (sentence.length() == 0) {
            return wordCount;
        }
        for (String word : words) {
            int count = (int) wordCount.getOrDefault(word, 0);
            wordCount.put(word,count + 1);
        }
        return wordCount;
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
