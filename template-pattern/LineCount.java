import java.io.*;
import java.util.*;

public class LineCount extends DirectoryProcessor {
    private static final String USAGE = "USAGE: java LineCount {-r} <dir> <file-pattern>";
    private static final String TOTAL_LABEL = "TOTAL: ";
    private static final int MIN_ARGS = 2;
    private static final int MAX_ARGS = 3;

    private LineCount(String directory, String pattern, boolean recurse) {
        super(directory, pattern, recurse);
    }

    void fileSummary(File file, Reader reader, int curData) throws IOException {
        super.fileSummary(file, reader, curData);
        System.out.println(curData + "  " + file);
    }

    int processNextLine(File file, int curData, Scanner input) {
        input.nextLine();
        return incrementData(curData);
    }

    public static void main(String[] args) {
        List<String> params = new ArrayList<>();
        boolean recurse = init(args, params, MAX_ARGS, MIN_ARGS, USAGE);
        DirectoryProcessor directoryProcessor = new LineCount(params.get(0), params.get(1), recurse);
        directoryProcessor.run(TOTAL_LABEL);
    }
}