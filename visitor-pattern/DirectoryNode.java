package visitor;

public class DirectoryNode extends DirectoryStructureNode {
    private FileNode[] fileNodes;
    private DirectoryNode[] directoryNodes;

    public DirectoryNode(FileNode[] fileNodes, DirectoryNode[] directoryNodes) {
        this.fileNodes = fileNodes;
        this.directoryNodes = directoryNodes;
    }

    public FileNode[] getFileNodes() {
        return fileNodes;
    }

    public DirectoryNode[] getDirectoryNodes() {
        return directoryNodes;
    }

    @Override
    Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
