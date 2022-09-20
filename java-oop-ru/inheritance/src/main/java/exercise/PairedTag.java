package exercise;



import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private String bodyTag;
    private List<Tag> singleTags;

    public PairedTag(String tagName, Map<String, String> attribute, String bodyTag, List<Tag> singleTags) {
        super(tagName, attribute);
        this.bodyTag = bodyTag;
        this.singleTags = singleTags;

    }
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());

        String singleTag = this.singleTags.stream().map(Tag::toString).collect(Collectors.joining());

        result.append(singleTag);
        result.append(this.bodyTag)
                .append("</")
                .append(this.tagName)
                .append(">");

        return result.toString();
    }


}
// END
