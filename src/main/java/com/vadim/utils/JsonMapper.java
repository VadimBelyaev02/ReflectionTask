package com.vadim.utils;

import java.lang.reflect.Field;
import java.util.Iterator;

import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;

public class JsonMapper {

    public static String toJson(Object object) {
        StringBuilder jsonBuilder = new StringBuilder("{\n");

        Field[] fields = object.getClass().getDeclaredFields();

        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);

                jsonBuilder.append("\"").append(field.getName()).append("\": ");
                addObjectToJsonString(jsonBuilder, field.get(object));
                if (i != fields.length - 1) {
                    jsonBuilder.append(",\n");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return jsonBuilder.append("\n}").toString();
    }

    private static void addObjectToJsonString(StringBuilder jsonBuilder, Object value) {
        Class<?> clazz = value.getClass();
        if (value.getClass().equals(String.class) || value.getClass().equals(Character.class)) {
            jsonBuilder.append("\"").append(value).append("\"");
        }
        if (value.getClass().isArray() || Iterable.class.isAssignableFrom(value.getClass())) {
            jsonBuilder.append(toJsonArray(value));
        }
        if (Number.class.isAssignableFrom(clazz) || clazz.isPrimitive() || clazz.equals(Boolean.class)) {
            jsonBuilder.append(value);
        }
    }

    private static String toJsonArray(Object object) {
        StringBuilder jsonBuilder = new StringBuilder("[");

        if (object.getClass().isArray()) {
            int length = getLength(object);
            for (int i = 0; i < length; i++) {
                Object value = get(object, i);
                addObjectToJsonString(jsonBuilder, value);
                if (i != length - 1) {
                    jsonBuilder.append(", ");
                }
            }
        } else if (Iterable.class.isAssignableFrom(object.getClass())) {
            Iterable<?> iterable = (Iterable<?>) object;
            Iterator<?> iterator = iterable.iterator();
            while (iterator.hasNext()) {
                Object value = iterator.next();
                addObjectToJsonString(jsonBuilder, value);
                if (iterator.hasNext()) {
                    jsonBuilder.append(", ");
                }
            }
        }
        return jsonBuilder.append("]").toString();
    }
}