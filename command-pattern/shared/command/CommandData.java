package shared.command;

public class CommandData {

    public enum CommandType {
        toLowerCase, trim, parseDouble
    }

    private CommandType type;
    private String data;

    public CommandData(CommandType type, String data) {
        this.type = type;
        this.data = data;
    }

    public CommandType getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
