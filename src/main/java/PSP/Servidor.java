package PSP;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main (String[] args){
        try {
            InetSocketAddress dir = new InetSocketAddress("localhost", 8080);
            ServerSocket servidor = new ServerSocket();
            servidor.bind(dir);

            System.out.println("esperando conexiones ");
            Socket socket = servidor.accept();
            System.out.println("cliente conectado");

            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
            escritor.println("Hola, cliente");

            socket.close();
            servidor.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
