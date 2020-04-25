package client;

import com.google.gson.Gson;
import shared.Response;
import shared.command.CommandData;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientCommunicator {

    private static ClientCommunicator clientCommunicator = null;
    private static final String POST = "POST";

    static ClientCommunicator getInstance() {

        if (clientCommunicator == null) {
            clientCommunicator = new ClientCommunicator();
        }

        return clientCommunicator;
    }

    private ClientCommunicator() {
    }

    Response postConnect(String host, String port, CommandData commandData) {
        try {
            URL url = new URL("http://" + host + ":" + port + "/");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod(POST);
            httpURLConnection.setDoOutput(true);

            Writer out = new OutputStreamWriter(httpURLConnection.getOutputStream());
            String requestJson = jsonEncode(commandData);
            out.write(requestJson);
            out.close();

            httpURLConnection.connect();
            Reader reader = new InputStreamReader(httpURLConnection.getInputStream());
            return (Response) jsonDecode(reader, Response.class.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Object jsonDecode(Reader reader, String type) throws Exception {
        Class<?> clazz = Class.forName(type);
        Gson gson = new Gson();
        return gson.fromJson(reader, clazz);
    }

    private String jsonEncode(CommandData commandData) {
        Gson gson = new Gson();
        return gson.toJson(commandData);
    }

}
