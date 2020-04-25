package visitor;

public class DirectoryStructure {
    private DirectoryStructureNode root;

    public DirectoryStructure(DirectoryStructureNode root) {
        this.root = root;
    }

    public int getSize() {
        return (int) root.accept(new SizeVisitor());
    }

    public String[] getFileNames() {
        return (String[]) root.accept(new NamesVisitor());
    }
}
