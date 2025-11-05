package PSP;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {

    public static void main (String[] args){
        InetSocketAddress dir = new InetSocketAddress("localhost", 7080);

        try {
            Socket socket = new Socket();
            socket.connect(dir);
            System.out.println("conectado al servidor");

            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            String mensaje = lector.readLine();
            System.out.println("Servidor dice:" + mensaje);

            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
