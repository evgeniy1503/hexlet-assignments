package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static List findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map> result = new ArrayList<>(books);
        for (Map <String, String> book : books) {
           for (Map.Entry<String, String> param : where.entrySet()) {
               if (!book.containsValue(param.getValue())) {
                   result.remove(book);
               }
           }
        }
        return result;
    }
}
//END
