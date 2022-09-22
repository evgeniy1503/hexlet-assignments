package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {

    public static boolean scrabble(String symbol, String word) {

        String lowerWord = word.toLowerCase();
        ArrayList<String> listWordSymbol = new ArrayList<>(List.of(lowerWord.split("")));
        ArrayList<String> listSymbol = new ArrayList<>(List.of(symbol.split("")));

        for (String s : listSymbol) {
            listWordSymbol.remove(s);
        }
        return listWordSymbol.isEmpty();
    }
}
//END
