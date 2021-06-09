package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket clientSocket; // socket для общения
    private static Scanner scanner;
    private static BufferedReader in; // поток для чтения из
    private static PrintWriter out; // поток для чтения в

    public static void main(String[] args) {
        int port = 8306;
        String host = "netology.homework";
        try {
            try {
                clientSocket = new Socket(host, port);
                scanner = new Scanner(System.in);
                // создаем потоки ввода/вывода
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
                System.out.println("Напишем сообщение серверу: ");
                String reader = scanner.nextLine();
                //отправим сообщение серверу
                out.write(reader + "\n");
                out.flush();
                //ответ сервера
                System.out.println(in.readLine());
                reader = scanner.nextLine();
                out.write(reader + "\n");
                out.flush();
                System.out.println(in.readLine());
                reader = scanner.nextLine();
                out.write(reader + "\n");
                out.flush();
                System.out.println(in.readLine());
            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }
}