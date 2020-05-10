import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
public class TCPClient {
	private static long[] latencia;	// almacena la latencia
	//private final static int NUMBER = 400;	//atributo que almacena el máximo de mensajes para enviar
	
	public static void main(String[] args) {
		latencia = new long[400];
		Socket s = null;
		try {
			for(int i = 0; i < 400; i++) {
				int serverPort = 4444;	// Puerto del servidor 7896
				s = new Socket("localhost", serverPort);  // Establece una conexión con este host y se conecta al puerto del servidor.
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out.writeUTF("hola"); // Envia mensaje al servidor
				long timeSentInNanoseconds = System.nanoTime();
				System.out.println(in.readLine()); // Recibe mensaje del servidor
				long timeReceiveInNanoseconds = System.nanoTime();
				latencia[i] = timeReceiveInNanoseconds - timeSentInNanoseconds; // Calcula la latencia del mansaje
			}
			writeFile(); // Las latencias serán almacenadas en un archivo
		} //captura los errores que se produzcan 
		catch (UnknownHostException e) {
			System.out.println("Socket: " + e.getMessage());
		}catch (EOFException e) {
			System.out.println("EOF: " + e.getMessage());
		}catch (IOException e) {
			System.out.println("Readline: " + e.getMessage());
		}finally {
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("Close: " + e.getMessage());
				}
			}
		}
	}
	
	/**
	 * mete las latencias en formato csv.
	 */
	public static void writeFile() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("./TCPLatency.csv");
			String file = "";
			for(int i = 0; i < 400; i++) {
				file = Integer.toString((int) latencia[i]) + "\n";
				try {
					fos.write(file.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido abrir el archivo.");
		} catch (IOException e1) {
			e1.printStackTrace(); //imprime el nombre de la clase y el numro de línea donde ocurrio
			//el problema de la excepción 
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
