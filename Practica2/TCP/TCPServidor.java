import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TCPServidor {
	public static void main(String[] args) {
		 
        ServerSocket servidor = null; // sirve para crear los socket como objet ServerSocket
        Socket sc = null;
        DataInputStream in; //permite recibir los datos del cliente
        DataOutputStream out; //permite sacar los datos 
 
        //puerto de nuestro servidor 5000
        final int PUERTO = 5000;
 //try sirve para controlar la ejecucion del programa interno
        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(PUERTO); // indica el puerto por el que se establecerán las comunicaciones
            
            System.out.println("Servidor iniciado");
 
            //Siempre estara escuchando peticiones
            while (true) {
 
                //Espero a que un cliente se conecte
                sc = servidor.accept(); // este método va a crear la conexion entre Cliente-Servidor
 // se crean las instancias del onjeto DataInputStream y DataOutStream
                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());
 
               
                //Leo el mensaje que me envia
                String mensaje = in.readUTF();
 
                System.out.println(mensaje);
 
                //Le envio un mensaje
                out.writeUTF("¡Hola mundo desde el servidor!");
                
                //Cierro el socket
                sc.close();
                System.out.println("Cliente desconectado");
 
            }
 
        } catch (IOException ex) {
            Logger.getLogger(TCPServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
	
}
