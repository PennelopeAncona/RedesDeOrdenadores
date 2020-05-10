package Github;
import java.io.IOException;
import java.net.*;
public class TCPServer {
	/**
	 * El Servidor se mantiene escchando indefinidamente 
	 * por eso lo meemos en un loop
	 */
	public static void main(String[] args) {
		ServerSocket listenSocket = null;
		try {
			int serverPort = 4444;	// Corresponde al servidor 
			listenSocket = new ServerSocket(serverPort); // inicializamos el socket con el numero del servidor 
			while(true) {
				Socket clientSocket = listenSocket.accept(); // conectamos con el cliente depsues de haber sido aceptados
				if(clientSocket.isConnected()) {	// comprobamos que el cliente está conectado
					System.out.println("New login"); // verificamos que lo esté a través de un msm
					Connection connection = new Connection(clientSocket); // establecemos la conexion Servidor-Cliente
					connection.run();	// iniciamos la actividad del servidor.
				}
			}
		} catch (IOException e) {
			System.out.println("Listen socket:" + e.getMessage());
		}finally {
			try {
				listenSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
