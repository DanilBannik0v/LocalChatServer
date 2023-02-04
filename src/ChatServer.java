import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;
    final int serverPort = 1234;


    public ChatServer() throws IOException {
        serverSocket = new ServerSocket(serverPort);
    }

    public void run(){
        try {
            System.out.println("Waiting");
            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                clients.add(new Client(socket,this));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAll(String message){
        for (Client client: clients) {
            client.receive(message);
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}