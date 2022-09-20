package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere (List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>> result = new ArrayList<>();

        for (Map<String, String> book : books) {
            boolean temp = false;
            for (Map.Entry<String, String> paramBook: where.entrySet()) {
                String keyWhere = paramBook.getKey();
                String valueWhere = paramBook.getValue();
                String valueBook = book.get(keyWhere);
                if (valueBook.equals(valueWhere)) {
                    temp = true;
                } else {
                    temp = false;
                    break;
                }
            }

            if (temp) {
                result.add(book);
            }
        }
        return result;
    }
}
//END
