package visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NamesVisitor implements Visitor {
    @Override
    public Object visit(DirectoryNode node) {
        List<String> names = new ArrayList<>();

        for (FileNode fileNode : node.getFileNodes()) {
            names.add((String) fileNode.accept(this));
        }

        for (DirectoryNode directoryNode : node.getDirectoryNodes()) {
            Object namesAsObject = directoryNode.accept(this);
            String[] namesAsArray = (String[]) namesAsObject;
            names.addAll(Arrays.asList(namesAsArray));
        }

        return names.toArray(new String[0]);
    }

    @Override
    public Object visit(FileNode node) {
        return node.getName();
    }
}
