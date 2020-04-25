package visitor;

public class Main {

    public static void main(String[] args) {
        FileNode[] fileNodes = {new FileNode("new File", 10), new FileNode("next", 3)};
        DirectoryNode[] directoryNodes = {new DirectoryNode(new FileNode[]{new FileNode("last", 5)}, new DirectoryNode[]{}),
                new DirectoryNode(new FileNode[]{new FileNode("first", 20)}, new DirectoryNode[]{})};
        DirectoryStructureNode node = new DirectoryNode(fileNodes, directoryNodes);
        DirectoryStructure structure = new DirectoryStructure(node);
        for (String name : structure.getFileNames()) {
            System.out.println(name);
        }
        System.out.println(structure.getSize());

    }
}
