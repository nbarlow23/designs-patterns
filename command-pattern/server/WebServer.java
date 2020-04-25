package server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {
    public static void main(String[] args) {
        try {

            if (args.length == 0 || args.length > 1) {
                return;
            }

            int port = Integer.parseInt(args[0]);
            System.out.println("server listening on port: " + port);
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            httpServer.createContext("/", new ExecuteCommandHandler());
            httpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
