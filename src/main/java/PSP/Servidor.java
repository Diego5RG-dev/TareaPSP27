package PSP;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main (String[] args){
        try {
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);
            ServerSocket servidor = new ServerSocket();
            servidor.bind(dir);

            System.out.println("esperando conexiones ");
            Socket socket = servidor.accept();
            System.out.println("cliente conectado " + socket.getInetAddress().getHostAddress());

            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );


            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);

            String mensajeCliente;

            while (true) {
                mensajeCliente = lector.readLine();

                if (mensajeCliente == null || mensajeCliente.equalsIgnoreCase("adios")) {
                    System.out.println("Cliente ha terminado la conversación o cerrado la conexión.");
                    break;
                }

                System.out.println("Cliente dice: " + mensajeCliente);

                String respuestaEco = "ECO: " + mensajeCliente;
                escritor.println(respuestaEco);
                System.out.println("Enviando:  " + respuestaEco);
            }

            socket.close();
            servidor.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
