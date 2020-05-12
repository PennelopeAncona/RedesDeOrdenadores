import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
public class UDPClient {
	private static long[] latencia;	// almacena la latencia	
	public static void main(String[] args) {
		latencia = new long[200];
		/*
		 * Con la libreria Socket implmentamos sockets de clientes 
		 */
		Socket s = null;
		try {
			for(int i = 0; i < 200; i++) { 
				
				// creamos un servidor con un puerto especificado
				int serverPort = 4444;	
				
				// Establece una conexión con este host y se conecta al puerto del servidor.
				s = new Socket("localhost", serverPort);  
				
				//vamos a crear un flujo de salida del socket s
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				
				//leemos la secuencia de caractereas de entrada para almacenarlos en el buffer in
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				
				// Envia mensaje al servidor siguiendo la codificacion UTF-8 para no crear caracteres indeseados
				out.writeUTF(""); 
				
				/*
				 * Como se hizo en el protocolo UDP se va a tomar el tiempo que se tarda en recibir
				 * y enviar el mensaje para posteriormente calcular su latencia que viene siendo
				 * la diferencia de ambas
				 */
				long timeSentInNanoseconds = System.nanoTime();
				System.out.println(in.readLine()); // Recibe mensaje del servidor
				long timeReceiveInNanoseconds = System.nanoTime();
				latencia[i] = timeReceiveInNanoseconds - timeSentInNanoseconds; // Calcula la latencia del mansaje
			}
			//Una vez calculadas las latencias se van a guardar
			writeFile(); // Las latencias serán almacenadas en un archivo
		} //captura los errores que se produzcan desde que no exista el host, 
		//error que concierna al archivo desde la entrada hasta la salida del mismo
		catch (UnknownHostException e) {
			System.out.println("Socket: " + e.getMessage());
		}catch (EOFException e) {
			System.out.println("EOF: " + e.getMessage());
		}catch (IOException e) {
			System.out.println("Readline: " + e.getMessage());
		}finally {
			if(s != null) {
				try {
					s.close(); //Cerramos el archivo
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
			fos = new FileOutputStream("./TCPLatencia.csv");
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
