package com.vadim.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;

public class JsonMapper {

    public static String toJson(Object object) {
        //  StringBuilder jsonBuilder = new StringBuilder("{\n");
        StringBuilder jsonBuilder = new StringBuilder("{");

        Field[] fields = object.getClass().getDeclaredFields();

        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);

                jsonBuilder.append("\"").append(field.getName()).append("\": ");
                addObjectToJsonString(jsonBuilder, field.get(object));
                if (i != fields.length - 1) {
                    //     jsonBuilder.append(",\n");
                    jsonBuilder.append(",");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return jsonBuilder.append("}").toString().trim();
        //   return jsonBuilder.append("\n}").toString();
    }

    private static void addObjectToJsonString(StringBuilder jsonBuilder, Object value) {
        Class<?> clazz = value.getClass();
        if (value.getClass().equals(String.class) || value.getClass().equals(Character.class)) {
            jsonBuilder.append("\"").append(value).append("\"");
        } else if (value.getClass().isArray() || Iterable.class.isAssignableFrom(value.getClass())) {
            jsonBuilder.append(toJsonArray(value));
        } else if (Number.class.isAssignableFrom(clazz) || clazz.isPrimitive() || clazz.equals(Boolean.class)) {
            jsonBuilder.append(value);
        } else {
            jsonBuilder.append(toJson(value));
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

    public static <T> T toModel(String json, Class<T> clazz) {
        Map<String, Object> fieldsValues;
        try {
            fieldsValues = new ObjectMapper().readValue(json, HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return setValues(fieldsValues, clazz);
    }

    private static <T> T setValues(Map<String, Object> map, Class<T> clazz) {
        T object;
        try {
            object = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = map.get(field.getName());
            Class<?> type = field.getType();
            try {
                if (type.equals(Character.class)) {
                    field.set(object, value.toString().charAt(0));
                } else if (Number.class.isAssignableFrom(type) || type.isPrimitive()
                        || type.equals(Boolean.class) || type.equals(String.class)) {
                    field.set(object, value);
                } else {
                    field.set(object, setValues(map, type));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    private static Map<String, Object> retrieveFieldsAndValues(String json) {
        Map<String, Object> map = new HashMap<>();
        String[] split = json.split(":");
        for (int i = 0; i < split.length - 1; i += 2) {
            map.put(split[i], split[i + 1]);
        }
        return map;
    }

}