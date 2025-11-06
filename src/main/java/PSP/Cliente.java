package PSP;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {

    public static void main (String[] args){
        InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

        try {
            Socket socket = new Socket();
            socket.connect(dir);
            System.out.println("conectado al servidor escribe adios para salir");

            BufferedReader teclado = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            String mensaje;

            while (true) {
                System.out.print("Mensaje ; ");
                mensaje = teclado.readLine();

                escritor.println(mensaje);

                if (mensaje.equalsIgnoreCase("adios")) {
                    break;
                }


                String respuestaServidor = lector.readLine();
                if (respuestaServidor != null) {
                    System.out.println(respuestaServidor);
                } else {
                    System.out.println("El servidor ha cerrado la conexión inesperadamente.");
                    break;
                }
            }
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
