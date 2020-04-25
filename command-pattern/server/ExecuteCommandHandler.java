package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import shared.Response;
import shared.command.CommandData;
import shared.command.ICommand;
import shared.command.ToLowerCaseCommand;
import shared.command.TrimCommand;
import shared.command.ParseDoubleCommand;

import java.io.*;

import static java.net.HttpURLConnection.HTTP_OK;

public class ExecuteCommandHandler implements HttpHandler {

    private HttpExchange exchange;

    @Override
    public void handle(HttpExchange exchange) {
        this.exchange = exchange;
        CommandData commandData = deserializeCommandData(exchange);
        ICommand command = getCommand(commandData);
        if (command == null) {
            return;
        }
        try {
            Response response = command.execute();
            exchange.sendResponseHeaders(HTTP_OK, 0);
            serializeResults(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void serializeResults(Response response) throws Exception {
        Writer writer = new OutputStreamWriter(exchange.getResponseBody());
        Gson gson = new Gson();
        gson.toJson(response, writer);
        writer.close();
    }

    private CommandData deserializeCommandData(HttpExchange exchange) {
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        return gson.fromJson(reader, CommandData.class);
    }

    private ICommand getCommand(CommandData commandData) {
        String data = commandData.getData();
        CommandData.CommandType type = commandData.getType();
        switch (type) {
            case trim:
                return new TrimCommand(data);
            case parseDouble:
                return new ParseDoubleCommand(data);
            case toLowerCase:
                return new ToLowerCaseCommand(data);
            default:
                return null;
        }
    }
}
