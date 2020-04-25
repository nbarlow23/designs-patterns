package visitor;

public class FileNode extends DirectoryStructureNode {
    private String name;
    private int size;

    public FileNode(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
