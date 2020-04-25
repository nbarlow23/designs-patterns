import java.io.*;
import java.util.*;
import java.util.regex.*;

public class FileSearch extends DirectoryProcessor {
    private static final String TOTAL_LABEL = "\nTOTAL MATCHES: ";
    private static final String USAGE = "USAGE: java FileSearch {-r} <dir> <file-pattern> <search-pattern>";
    private static final int MIN_ARGS = 3;
    private static final int MAX_ARGS = 4;
    private Matcher _searchMatcher;

    private FileSearch(String directory, String pattern, boolean recurse, String searchPattern) {
        super(directory, pattern, recurse);
        _searchMatcher = Pattern.compile(searchPattern).matcher("");
    }

    void fileSummary(File file, Reader reader, int curData) throws IOException {
        super.fileSummary(file, reader, curData);
        if (curData > INITIAL) {
            System.out.println("MATCHES: " + curData);
        }
    }

    int processNextLine(File file, int curData, Scanner input) {
        String line = input.nextLine();
        _searchMatcher.reset(line);
        if (_searchMatcher.find()) {
            curData = incrementData(curData);
            if (curData == 1) {
                System.out.println();
                System.out.println("FILE: " + file);
            }
            System.out.println(line);
        }
        return curData;
    }

    public static void main(String[] args) {
        List<String> params = new ArrayList<>();
        boolean recurse = init(args, params, MAX_ARGS, MIN_ARGS, USAGE);
        DirectoryProcessor directoryProcessor = new FileSearch(params.get(0), params.get(1), recurse, params.get(2));
        directoryProcessor.run(TOTAL_LABEL);
    }
}