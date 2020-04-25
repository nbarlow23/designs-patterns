package shared.command;

import server.StringProcessor;
import shared.Response;

public class TrimCommand implements ICommand {

    private String data;

    public TrimCommand(String data) {
        this.data = data;
    }

    public Response execute() {
        try {
            StringProcessor stringProcessor = StringProcessor.getInstance();
            String result = stringProcessor.trim(data);
            return new Response(true, result, null);
        } catch (Exception e) {
            return new Response(false, null, e.getMessage());
        }
    }
}
