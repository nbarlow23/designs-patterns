package shared.command;

import server.StringProcessor;
import shared.Response;

//Generic
public class ParseDoubleCommand implements ICommand {

    private String data;

    public ParseDoubleCommand(String data) {
        this.data = data;
    }

    @Override
    public Response execute() {
        try {
            StringProcessor stringProcessor = StringProcessor.getInstance();
            Double result = stringProcessor.parseDouble(data);
            return new Response(true, result, null);
        } catch (RuntimeException e) {
            return new Response(false, null, e.getMessage());
        }
    }

}
