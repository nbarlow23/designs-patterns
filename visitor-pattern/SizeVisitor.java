package visitor;

public class SizeVisitor implements Visitor {
    @Override
    public Object visit(DirectoryNode node) {
        int size = 0;

        for (FileNode fileNode : node.getFileNodes()) {
            size += (int) (fileNode.accept(this));
        }

        for (DirectoryNode directoryNode : node.getDirectoryNodes()) {
            size += (int) (directoryNode.accept(this));
        }

        return size;
    }

    @Override
    public Object visit(FileNode node) {
        return node.getSize();
    }
}
