package org.example.stringTasks;

public class LongestCommonPrefix {

    public static String CommonPrefixSolution(String[] strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }

        String prefix = strings[0];
        for (int i = 1; i < strings.length; i++) {
            while (!strings[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
            if (prefix.isEmpty()) {
                break;
            }
        }
        return prefix;
    }
}
