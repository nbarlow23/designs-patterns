package shared.command;

import server.StringProcessor;
import shared.Response;

public class ToLowerCaseCommand implements ICommand {

    private String data;

    public ToLowerCaseCommand(String data) {
        this.data = data;
    }

    public Response execute() {
        try {
            StringProcessor stringProcessor = StringProcessor.getInstance();
            String result = stringProcessor.toLowerCase(data);
            return new Response(true, result, null);
        } catch (Exception e) {
            return new Response(false, null, e.getMessage());
        }
    }
}
