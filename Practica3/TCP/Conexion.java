import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Connection extends Thread{

	private DataInputStream in;
	private DataOutputStream out;
	private Socket clientSocket;
	
	/**
	 * Establece la conexión cliente-Servidor
	 * A través del Thread podemos crear multiples conexiones con
	 * el servidor
	 * 
	 */
	public Connection(Socket clientSocket) {
		try {
			this.clientSocket = clientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Connection: " + e.getMessage()); // Error Message
		}
	}
	
	/**
	 *  run() toma el mensaje del cliente y lo envía de vuelta en formato UPPERCASE
	 */
	public void run() {
		try {
			String data = in.readUTF();
			System.out.println("recibido : " + data);
			out.writeUTF(data.toUpperCase()); // Aqui se regresa en dicho formato.
		} catch (IOException e) {
			System.out.println("readline: " + e.getMessage());
		}finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
