package reflect;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ClassTools {

    public static Field[] getAllDeclaredField(Class<?> aClass){
        Field[] fields = aClass.getDeclaredFields();
        if (aClass != Object.class) {
            Field[] ancestorFields = getAllDeclaredField(aClass.getSuperclass());
            int previousLength = fields.length;
            fields = Arrays.copyOf(fields, previousLength + ancestorFields.length);
            for (int i = 0; i < ancestorFields.length; i++) {
                fields[previousLength + i ] = ancestorFields[i];
            }
        }
        return fields;
    }
}
