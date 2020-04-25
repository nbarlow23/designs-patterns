package client;

import shared.command.CommandData;
import shared.IStringProcessor;
import shared.Response;

public class StringProcessorProxy implements IStringProcessor {
    private static StringProcessorProxy stringProcessorProxy = null;
    private ClientCommunicator clientCommunicator = ClientCommunicator.getInstance();
    private String host;
    private String port;

    public static StringProcessorProxy getInstance(String host, String port) {
        if (stringProcessorProxy == null) {
            stringProcessorProxy = new StringProcessorProxy(host, port);
        }
        return stringProcessorProxy;
    }

    private StringProcessorProxy(String host, String port) {
        setHost(host);
        setPort(port);
    }

    public String toLowerCase(String input) {
        CommandData commandData = new CommandData(CommandData.CommandType.toLowerCase, input);
        Response response = clientCommunicator.postConnect(host, port, commandData);
        if (!response.isSuccess()) {
            throw new RuntimeException(response.getErrorInfo());
        }
        return (String) response.getData();
    }

    public String trim(String input) {
        CommandData commandData = new CommandData(CommandData.CommandType.trim, input);
        Response response = clientCommunicator.postConnect(host, port, commandData);
        if (!response.isSuccess()) {
            throw new RuntimeException(response.getErrorInfo());
        }
        return (String) response.getData();
    }

    public Double parseDouble(String input) {
        CommandData commandData = new CommandData(CommandData.CommandType.parseDouble, input);
        Response response = clientCommunicator.postConnect(host, port, commandData);
        if (!response.isSuccess()) {
            throw new RuntimeException(response.getErrorInfo());
        }
        return (Double) response.getData();
    }

    private void setHost(String host) {
        this.host = host;
    }

    private void setPort(String port) {
        this.port = port;
    }
}
