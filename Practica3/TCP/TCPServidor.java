
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
			// Corresponde al servidor 
			int serverPort = 4444;	
			// inicializamos el socket con el numero del servidor 
			listenSocket = new ServerSocket(serverPort); 
			while(true) {
				
				// conectamos con el cliente depsues de haber sido aceptados
				Socket clientSocket = listenSocket.accept(); 
				
				// comprobamos que el cliente está conectado
				if(clientSocket.isConnected()) {	
					
					// verificamos que lo esté a través de un msm
					System.out.println("Nueno inicio de sesion: "); 
					
					// establecemos la conexion Servidor-Cliente
					Connection connection = new Connection(clientSocket); 
					
					// iniciamos la actividad del servidor.
					connection.run();	
				}
			}
		} catch (IOException e) {
			System.out.println("Socket escuchado:" + e.getMessage());
		}finally {
			try {
				//cerramos el socket
				listenSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
