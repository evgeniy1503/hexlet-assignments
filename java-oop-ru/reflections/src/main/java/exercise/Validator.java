package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class Validator {
    public static List<String> validate(Address address) {

        List<String> notValidFields = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        String fieldValue;

        for (Field field : fields) {
            Annotation annotationField = field.getAnnotation(NotNull.class);
            field.setAccessible(true);
            try {
                fieldValue = (String) field.get(address);
                if (fieldValue == null && annotationField != null) {
                    notValidFields.add(field.getName().toString());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return notValidFields;
    }
}
// END
