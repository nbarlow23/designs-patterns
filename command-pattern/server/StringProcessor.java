package server;

import shared.IStringProcessor;

public class StringProcessor implements IStringProcessor {

    private static StringProcessor stringProcessor = null;
    public static StringProcessor getInstance() {
        if (stringProcessor == null) {
            stringProcessor = new StringProcessor();
        }

        return stringProcessor;
    }

    private StringProcessor() {

    }

    public String toLowerCase(String string) {
        return string.toLowerCase();
    }

    public String trim(String string) {
        return string.trim();
    }

    public Double parseDouble(String string) {
        return Double.parseDouble(string);
    }
}
