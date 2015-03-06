package org.kantega.metrics.api;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ThreadLocalContext {

    private static ThreadLocal<Map<String, Object>> values = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    public static void set(String key, Object value) {
        values.get().put(key, value);
    }

    public static void clear(String key) {
        values.get().remove(key);
    }

    public static Object get(String key) {
        return values.get().get(key);
    }
}
