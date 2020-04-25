package client;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        if (args.length == 0 || args.length > 2) {
            return;
        }

        String host = args[0];
        String port = args[1];

        try {
            StringProcessorProxy proxy = StringProcessorProxy.getInstance(host, port);
            System.out.print("Enter string to lowercase: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            System.out.println("response: " + proxy.toLowerCase(input));
            System.out.print("Enter string to trim: ");
            input = scanner.nextLine();
            System.out.println("response:" + proxy.trim(input));
            System.out.print("Enter string to parse double: ");
            input = scanner.nextLine();
            System.out.println("response: " + proxy.parseDouble(input));
        } catch (Throwable e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
