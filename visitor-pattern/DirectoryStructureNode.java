package visitor;

public abstract class DirectoryStructureNode {
    abstract Object accept(Visitor visitor);
}
