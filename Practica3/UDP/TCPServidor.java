import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*Protocolo no orientado a la conexion, carece de una petición de conexion y se da por hecho
 * que su red está basada en datagramas*/
class TCPServeridor {
   public static void main(String args[]) throws Exception {
       String confirmacion = "Received"; //mensaje de confirmacion 
       
       DatagramSocket serverSocket = new DatagramSocket(9876); //Inicializa el datagrama recibido 
       
       while(true) {
    	   
    	   //indican el tamaño del buffer que sale y llega
    	   byte[] receiveData = new byte[1024]; 
           byte[] sendData = new byte[1024]; 
           
           //DatagramPacket implementa un servicio de entrega de paquetes sin conexión.
           //por lo tanto la conexión se basa grcias a la información que contiene el paquete
           
           //Construimos un datagramaPacket para recibir paquetes de longitud length
    	   DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
    	   
           serverSocket.receive(receivePacket); //se produce una recepción del mensaje
           
           String sentence = new String(receivePacket.getData()); //Devuelve el búfer de datos
           
           System.out.println("Received: " + sentence); //Mostramos lo que recibimos
           
           /*InetAddress simula una direccion de Protocolo Ip*/
           InetAddress IPAddress = receivePacket.getAddress(); //Devuelve la dirección IP sin formato de este InetAddress objeto IPAdress
           
           //Devuelve el numero de puerto al que está conectado el socket 
           int port = receivePacket.getPort();
           
           sendData = confirmacion.getBytes(); //almacena el msm y lo envía
           
           //Construye el paquete de datagramas para enviar paquetes de la longitud length
           //numero de puerto especificado en el host dado.
           DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); 
          
           //Enviamos el paquete datagrama
           serverSocket.send(sendPacket); 
       }
   }
}
