package com.liori.utils.word;

public class CamelCaseUtil {

    public static String changeUnderlineToCamelCaseFirstLowerCase(String word) {
        int length = word.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = word.charAt(i);
            if (i == 0 && Character.isUpperCase(c)) {
                sb.append(Character.toLowerCase(c));
            } else {
                if (c == '_') {
                    if (++i < length) {
                        sb.append(Character.toUpperCase(word.charAt(i)));
                    }
                } else {
                    sb.append(c);
                }
            }

        }

        return sb.toString();
    }

    public static String changeUnderlineToCamelCase(String word) {
        int length = word.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = word.charAt(i);
            if (i == 0 && Character.isLowerCase(c)) {
                sb.append(Character.toUpperCase(c));
            } else {
                if (c == '_') {
                    if (++i < length) {
                        sb.append(Character.toUpperCase(word.charAt(i)));
                    }
                } else {
                    sb.append(c);
                }
            }

        }

        return sb.toString();
    }

    public static String changeCamelCaseToUnderline(String word) {
        int length = word.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = word.charAt(i);
            if (i == 0) {
                sb.append(Character.toLowerCase(c));
            } else {
                if (Character.isUpperCase(c)) {
                    sb.append("_");
                    sb.append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}
