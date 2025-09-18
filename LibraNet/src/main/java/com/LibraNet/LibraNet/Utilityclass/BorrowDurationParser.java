package com.LibraNet.LibraNet.Utilityclass;

public class BorrowDurationParser {

    public static int parsing(String input) {
        if (input == null) throw new IllegalArgumentException("Duration is missing");
        String s = input.trim().toLowerCase();
        if (s.matches("^\\d+$")) {
            return Integer.parseInt(s);
        }
        if (s.matches("^\\d+\\s*days?$")) {
            return Integer.parseInt(s.replaceAll("[^0-9]", ""));
        }
        throw new IllegalArgumentException("Could not parse duration: " + input);
    }
}
