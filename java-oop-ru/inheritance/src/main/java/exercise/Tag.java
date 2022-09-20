package exercise;

import java.util.Map;

// BEGIN
public abstract class Tag {

    protected String tagName;
    protected Map<String, String> attributes;

    public Tag(String tagName, Map<String, String> attribute) {
        this.tagName = tagName;
        this.attributes = attribute;
    }
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("<")
                .append(this.tagName);
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            result.append(" ")
                    .append(attribute.getKey())
                    .append("=\"")
                    .append(attribute.getValue())
                    .append("\"");
        }
        result.append(">");
        return result.toString();
    }
}
// END
