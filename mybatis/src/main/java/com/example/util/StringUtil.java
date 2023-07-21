package com.example.util;

public class StringUtil {
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(String packageName) {
        return !isEmpty(packageName);
    }
}
