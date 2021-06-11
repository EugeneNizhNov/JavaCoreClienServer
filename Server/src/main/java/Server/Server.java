/*в данном проекте реализованы две задания
по теме Клиент-серверное приложение
 */
package Server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket server;  // socket сервера
    private static Socket clientSocket;  // socket для общения
    private static BufferedReader in; //поток для чтения из
    private static PrintWriter out; //поток для записи в

    public static void main(String[] args) {
        int port = 8306;
        try {
            try {
                server = new ServerSocket(port);
                System.out.println("Сервер запущен");
                System.out.println("Прослушиваем...");
                clientSocket = server.accept();
                // создаем потоки ввода/выввода
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
                    //ожидаем сообщение от client
                    String clintMessage = in.readLine();
                    System.out.println("Сообщение клиента: " + clintMessage);
                    //отправляем сообщение к client
                    out.println(String.format("Hi %s, your port is %d. Как тебя зовут?", clintMessage, clientSocket.getPort()));
                    out.flush();
                    clintMessage = in.readLine();
                    String nameClient = clintMessage;
                    System.out.println("Сообщение клиента: " + clintMessage);
                    out.println(String.format("Hi %s. Ты ребенок? (да/нет)", nameClient));
                    out.flush();
                    clintMessage = in.readLine();
                    System.out.println("Сообщение клиента: " + clintMessage);
                    if (clintMessage.equals("да")) {
                        out.println(String.format("Добро пожаловать в детскую зону, %s! Давай играть.", nameClient));
                        out.flush();
                    } else {
                        out.println(String.format("Добро пожаловать во взрослую зону, %s! Хорошего отдыха.", nameClient));
                        out.flush();
                    }
                } finally {
                    // закрываем socket
                    clientSocket.close();
                    //закрываем поток
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}