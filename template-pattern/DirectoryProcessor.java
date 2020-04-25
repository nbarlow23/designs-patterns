import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

abstract class DirectoryProcessor {
    static final int INITIAL = 0;
    private String _directory;
    private boolean _recurse;
    private Matcher _matcher;
    private int _totalData;

    DirectoryProcessor(String directory, String pattern, boolean recurse) {
        _directory = directory;
        _recurse = recurse;
        _totalData = 0;
        _matcher = Pattern.compile(pattern).matcher("");
    }

    void run(String totalLabel) {
        processDirectory(new File(_directory));
        printTotal(totalLabel);
    }

    private void printTotal(String label) {
        System.out.println(label + _totalData);
    }

    private void processDirectory(File dir) {
        if (!dir.isDirectory()) {
            nonDir(dir);
            return;
        }

        if (!dir.canRead()) {
            unreadableDir(dir);
            return;
        }

        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                if (file.canRead()) {
                    processFile(file);
                }
                else {
                    unreadableFile(file);
                }
            }
        }

        if (_recurse) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file);
                }
            }
        }
    }

    private void processFile(File file) {
        String fileName = getFileName(file);
        _matcher.reset(fileName);
        if (_matcher.find()) {
            try {
                readFile(file);
            } catch (IOException e) {
                unreadableFile(file);
            }
        }
    }

    private void readFile(File file) throws IOException {
        Reader reader = new BufferedReader(new FileReader(file));
        int curData = 0;
        try {
            Scanner input = new Scanner(reader);
            curData = processAllLines(file, curData, input);
        } finally {
            fileSummary(file, reader, curData);
        }
    }

    void fileSummary(File file, Reader reader, int curData) throws IOException {
        reader.close();
    }

    private int processAllLines(File file, int curData, Scanner input) {
        while (input.hasNextLine()) {
            curData = processNextLine(file, curData, input);
        }
        return curData;
    }

    abstract int processNextLine(File file, int curData, Scanner input);

    int incrementData(int curData) {
        ++curData;
        ++_totalData;
        return curData;
    }

    private String getFileName(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            return "";
        }
    }

    private void nonDir(File dir) {
        System.out.println(dir + " is not a directory");
    }

    private void unreadableDir(File dir) {
        System.out.println("Directory " + dir + " is unreadable");
    }

    private void unreadableFile(File file) {
        System.out.println("File " + file + " is unreadable");
    }

    private static void initParams(String[] args, List<String> params, boolean recurse) {
        int startIdx = 0;
        if (recurse) {
            startIdx++;
        }

        for (int i = startIdx; i < args.length; i++) {
            params.add(args[i]);
        }
    }

    private static boolean validNonRecursive(int length, int minArgs) {
        return length == minArgs;
    }

    private static boolean validRecursive(String[] args, int maxArgs) {
        return args.length == maxArgs && hasRecursiveArg(args);
    }

    private static boolean hasRecursiveArg(String[] args) {
        return args[INITIAL].equals("-r");
    }

    private static boolean invalidArgs(String[] args, boolean recurse, int minArgs) {
        return !(recurse || validNonRecursive(args.length, minArgs));
    }

    private static void usage(String usageString) {
        System.out.println(usageString);
    }

    static boolean init(String[] args, List<String> params, int maxArgs, int minArgs, String usage) {
        boolean recurse = validRecursive(args, maxArgs);

        if (invalidArgs(args, recurse, minArgs)) {
            usage(usage);
            System.exit(0);
        }

        initParams(args, params, recurse);
        return recurse;
    }
}