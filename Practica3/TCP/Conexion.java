import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Connection extends Thread{

	private DataInputStream in; // permite la entrada de datos
	private DataOutputStream out; //permite la salida de informacion
	private Socket clientSocket; // cliente socket
	
	/**
	 * Establece la conexión cliente-Servidor
	 * A través del Thread podemos crear multiples conexiones con
	 * el servidor
	 * 
	 */
	public Connection(Socket clientSocket) {
		try {
			this.clientSocket = clientSocket;
			//guardamos los datos de entrada y salida en sus respectivas variables in y out
			// del cliente socket
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			
		} catch (IOException e) {
			// Si la conexion no se produce sacamos el mesaje de error de conexion
			System.out.println("Conexion: " + e.getMessage()); 
		}
	}
	
	/**
	 *  run() toma el mensaje del cliente y lo envía de vuelta en formato UPPERCASE
	 */
	public void run() {
		try {
			// guardamos en data el flujo de informacion de entrada del cliente socket
			String data = in.readUTF();
			//imprimimos la informacioin recibida
			System.out.println("recibido : " + data);
			out.writeUTF(data.toUpperCase()); // Aqui se regresa en dicho formato.
		} catch (IOException e) {
			System.out.println("line: " + e.getMessage());
		}finally {
			try {
				//cerramos el socket y liberamos conexion
				clientSocket.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}
}

