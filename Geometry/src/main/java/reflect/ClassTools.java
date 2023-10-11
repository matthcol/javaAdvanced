package reflect;

import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

public class ClassTools {

    public static Field[] getAllDeclaredField(Class<?> aClass){
        Field[] fields = aClass.getDeclaredFields();
        if (aClass != Object.class) {
            Field[] ancestorFields = getAllDeclaredField(aClass.getSuperclass());
            // merge fields
            fields = Stream.concat(
                    Arrays.stream(fields),
                    Arrays.stream(ancestorFields)
            ).toArray(Field[]::new);
        }
        return fields;
    }

    // Stream<Form>, Point.class => Stream<Point>
    // Stream<T>, Class<U> => Stream<U>
    public static <T,U> Stream<U> filterByType(Stream<T> stream, Class<U> uType){
        return stream.filter((T t) -> uType.isInstance(t))
                .map((T t) -> uType.cast(t));
    }
}
