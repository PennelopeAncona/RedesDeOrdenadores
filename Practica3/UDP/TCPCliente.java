import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket; //simula un pocket para enviar y recibir paquetes
import java.net.InetAddress;
public class TCPCliente {

	public static void main(String args[]) throws Exception {
		   long latencia = 0;
		   String latenciaTotal;
		   int paquetesAenviar= 3000;
		   
	       FileOutputStream fos = null;
		   fos = new FileOutputStream("./UDPLatency.csv"); //documento csv donde se van a almacenar las latencias
		   
		   for (int i = 0; i < paquetesAenviar; i++) { //mientras que hayan paquetes a enviar
			   //inicializacion del socket datagrama para que sea vinculado al puerto 
		       DatagramSocket clientSocket = new DatagramSocket(); 
		       
		       //InetAddress representa una direccion de red IP. A través de ella determinamos
		       //la direccion IP del host dado un nombre 
		       InetAddress IPAddress = InetAddress.getByName("localhost"); 
		       
		       //tamaño de los buffer que envian y reciben la informacion
		       byte[] sendData = new byte[1024]; 
		       byte[] receiveData = new byte[1024]; 
		       
		       //mensaje a enviar
		       String sentence = "Hi desde Cliente"; 
		       
		     //guardamos el texto en sendData
		       sendData = sentence.getBytes(); 
		       
		       /*
		        * Creamos el datagrama de paquetes que vamos a enviar,el datagrama
		        * almacenará el mensaje que a a enviar, la longitud del mensaje, el numero de puerto
		        * que se encuentra almacenado en el Host
		        */
	    	   DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876); //Datagram Packet with the message, length, IP address and port
	    	 
	    	   //Enviamos el datagrama que construimos
	    	   clientSocket.send(sendPacket); 
	    	   
	    	   /*
	    	    El protocolo UDP al tener pocas cabeceras se le puede añadir otra cabecera que mida
	    	    en tiempo real de transmision de los paquetes datagramas
	    	    */
		       long sentMillis = System.nanoTime(); //obtenemos el tiempo del sistema
		       
		       //Inicializamos un paquete de datagrama para recibir los paquetes de cierta longitud
		       DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
		       
		       //cliente recibe como mensaje el datagrama
		       clientSocket.receive(receivePacket); 
		       
		       //Guardamos el tiempo que tardó en ser recibido
		       long receivedMillis = System.nanoTime(); 
		       
		       /*La latencia viene expresada como la diferencia del tiempo de recepcion y el envio*/
		       latencia = receivedMillis - sentMillis; 
		       
		       //Convertimos la latencia total en String para sacarlo por pantalla
		       latenciaTotal = Integer.toString((int) latencia) + "\n"; 
		       
		       
		       String confirmationSentence = new String(receivePacket.getData()); 
		       //imprimimos la confirmacion 
		       System.out.println("Servidor: " + confirmationSentence); 
		     //cerramos el socket del cliente
		       clientSocket.close(); 
		       
		       //Procedemos a guardar las latencias en el documento, si no existe salta una 
		       //Excepcion
		       try {
		    	   fos.write(latenciaTotal.getBytes()); 
		       } catch (FileNotFoundException e) {
		    	   e.printStackTrace();
		       } catch (IOException e) {
		    	   e.printStackTrace();
		       }
		   }
		   try { //Cuando hayamos terminado cerramos el documento
			   fos.close();
		   } catch (IOException e) {
		   }
	   }
}
