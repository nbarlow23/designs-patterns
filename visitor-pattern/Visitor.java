package visitor;

public interface Visitor {
    Object visit(DirectoryNode node);
    Object visit(FileNode node);
}
