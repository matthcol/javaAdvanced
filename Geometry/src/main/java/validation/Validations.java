package validation;

import validation.annotation.Min;
import validation.annotation.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class Validations {

    public static void introspectValidation(Object o){
        Class<?> objectClass = o.getClass();
        System.out.println("Introspect object: " + o);
        System.out.println("\t- simple name: " + objectClass.getSimpleName());
        System.out.println("\t- full name: " + objectClass.getCanonicalName());
        // annotations on the class
        Annotation[] annotations = objectClass.getAnnotations();
        System.out.println("\t- annotations: " + Arrays.toString(annotations));
        // parent:
        Class<?> parentClass = objectClass.getSuperclass();
        System.out.println("\t- parent class: " + parentClass.getCanonicalName());
        // fields
        Field[] fields = objectClass.getFields();
        Field[] declaredFields = objectClass.getDeclaredFields();
        // TODO: fetch decleredFields of parent classes
        System.out.println("\t- fields: " + Arrays.toString(fields));
        System.out.println("\t- declared fields: " + Arrays.toString(declaredFields));
        // fetch annotations fields:
        Arrays.stream(declaredFields)
                .forEach(field -> {
                    Annotation[] annotationFields = field.getAnnotations();
                    System.out.println("\t\t* annotations on "
                            + field.getName()
                            + ": "
                            + Arrays.toString(annotationFields));
                });
        // methods
        Method[] methods = objectClass.getMethods();
        Method[] declaredMethods = objectClass.getDeclaredMethods();
        System.out.println("\t - methods: " + Arrays.toString(methods));
        System.out.println("\t - declared methods: " + Arrays.toString(declaredMethods));

    }

    public static boolean isValid(Object o) {
        Class<?> objectClass = o.getClass();
        Field[] fields = objectClass.getDeclaredFields(); // TODO: + parent fields
        boolean res = true;
        for (Field field: fields) {
            Annotation[] annotationFields = field.getAnnotations();
            for (Annotation annotation: annotationFields) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                switch (annotation) {
                    case NotNull notNullAnnotation -> {
                        System.out.println("\t\t\t@NotNull");
                        res = res && isValidNotNull(o, field, notNullAnnotation);
                    }
                    case Min minAnnotation -> {
                        System.out.println("\t\t\t@Min: "
                                + minAnnotation.value()
                                + " / "
                                + minAnnotation.inclusive()
                        );
                        res = res && isValidMin(o, field, minAnnotation);
                    }
                    default -> System.out.println("Skip annotation: " + annotation);
                }
            }
        }
        return res;
    }

    /**
     *
     * @param field
     * @return
     * @throws ValidationException if there is not getter corresponding to this field
     */
    private static Method getGetterFromField(Field field){
        Class<?> objectClass = field.getDeclaringClass();
        String getterMethodName = "get"
                + field.getName().substring(0,1).toUpperCase()
                + field.getName().substring(1);
        try {
            Method getterMethod =  objectClass.getMethod(getterMethodName);
            return getterMethod;
        } catch (NoSuchMethodException e) {
            throw new ValidationException("NoSuchMethodException",e);
        }
    }
    private static boolean isValidNotNull(Object object, Field field, NotNull notNullAnnotation) {
        Method getterMethod = getGetterFromField(field); // Ex: getCenter from class Circle
        try {
            Object value = getterMethod.invoke(object); // Ex: ((Circle) object).getCenter()
            return Objects.nonNull(value);
        } catch (IllegalAccessException e) {
            throw new ValidationException("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            throw new ValidationException("InvocationTargetException", e);
        }
    }

    private static boolean isValidMin(Object object, Field field, Min minAnootation) {
        Method getterMethod = getGetterFromField(field); // Ex: getCenter from class Circle
        try {
            double value = (Double) getterMethod.invoke(object); // Ex: ((Circle) object).getCenter()
            double minValue = minAnootation.value();
            boolean isInclusive = minAnootation.inclusive();
            return isInclusive ? (value >= minValue) : (value > minValue);
        } catch (ClassCastException e) {
            throw new ValidationException("ClassCastException", e);
        } catch (IllegalAccessException e) {
            throw new ValidationException("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            throw new ValidationException("InvocationTargetException", e);
        }
    }
}
