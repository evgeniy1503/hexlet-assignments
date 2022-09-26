package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {

    protected String tagName;
    protected Map<String, String> attributes;

    public Tag(String tagName, Map<String, String> attribute) {
        this.tagName = tagName;
        this.attributes = attribute;
    }
    public String stringifyAttributes() {
        StringBuilder result = new StringBuilder();


        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            result.append(" ")
                    .append(attribute.getKey())
                    .append("=\"")
                    .append(attribute.getValue())
                    .append("\"");
        }
        return result.toString();
    }
}
// END
