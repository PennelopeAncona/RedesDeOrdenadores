//package
/*
 * Librerias para generar numeros aleatorios que se refieren a nodos
 * demas para crear listas, colas 
 * */
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Queue;
//import java.util.Random;
import java.util.Random;

/*
 * El algoritmo de Inundación consiste en transmitir los paquetes 
 * que le llegan a un nodo hacia todos los nodos vecinos, excepto
 * por la ruta por la que llegó el paquete.
 * */
public class Inundacion {

	private Queue<Integer> toVisit; // Cola de routers a visitar
	private Queue<Integer> notToVisit; // Cola de routers para no visitar porque no son vecinos o por donde vienen
	private Grafo red;
	private int[] paquetesTransmitidos;  // Paquetes transmitidos por cada router
	private int[] paquetesRecibidos;	 // Paquetes recibidos por cada router
	private StringBuilder outputIterations; 
	
	public Inundacion(Grafo red) {
		toVisit = new LinkedList<>();
		notToVisit = new LinkedList<>();
		this.red = red;
		outputIterations = new StringBuilder(); //Genera un cadena aleatoria
		paquetesTransmitidos = new int[red.getNumberOfNodos()];
		paquetesRecibidos = new int[red.getNumberOfNodos()];
	}
	/*
	 * Al generar un numero aleatorio indicamos desde donde va a comenzar la 
	 * transmision de los paquetes.
	 * Al haber mayor cantidad de iteracciones nos aseguramos de que 
	 * se llegue a todos los routers de la red 
	 */
	public void inicio() {
		int primerRouter = NumeroRandom(); // El número que se genere indicará el nodo
		toVisit.add(primerRouter);
		notToVisit.add(-1);	// Añade a la cola de no visitar -1 que indica que debe visitar todos los vecinos.
		for(int i = 0; i < red.getNumberOfNodos() + 10; i++) {
			int routerToVisit = toVisit.remove();
			int routerNotToVisit = notToVisit.remove();
			visitarRouter(routerToVisit, routerNotToVisit);
		}
	}
	/*
	 * Este metodo muestra in informe de los paquetes recibidos y
	 * enviados por cada nodo (router). Al mismo tiempo el string de paquetes 
	 * y sus vecinos ignorados
	 * */
	@Override
	public String toString() {
		generarInforme();
		return outputIterations.toString();
	}
	
	// Primero encontramos los vecinos de un determinado nodo/router y los añade a la cola 
	//para posteriormente ser visitarlos. Ya que no vamos a volver a enviar el paquete al 
	//nodo en el que se esta, se añade a la cola de no visitar el nodo en el que se está.
	//Los contadores indican los paquetes recibidos y transmitidos por el router.
	private void visitarRouter(int router, int routerNotToVisit) {
		outputIterations.append("Router " + router + ": "); //append lo usamos para añadir el valor al final (string)
		for(int visitingRouter = 0; visitingRouter < red.getNumberOfNodos(); visitingRouter++) {
			if(routerNotToVisit != visitingRouter && red.isNodo(router, visitingRouter)) {
				toVisit.add(visitingRouter);
				paquetesTransmitidos[router]++;
				paquetesRecibidos[visitingRouter]++;
				outputIterations.append(visitingRouter + " | ");
				notToVisit.add(router);
			}
		}
		outputIterations.append(" ignore router: " + (routerNotToVisit == -1 ? "None" : routerNotToVisit) + "\n");
	}
	// Genera numeros aleatorios desde el 0 hasta el numeo de routers o nodos en la red
	private int NumeroRandom() {
		Random generate = new SecureRandom(); 
		int max = red.getNumberOfNodos();
		return generate.nextInt(max);
	}
	
	//Crea un informe de lo que hacen los routers en red de grafo desde la posicion del router
	//hasta los paquetes que recibe y transmite el router.
	private void generarInforme() {
		for(int i = 0; i < red.getNumberOfNodos(); i++) {
			outputIterations.append("\nRouter " + i + ": ");
			outputIterations.append("\nPaquetes Transmitidos: " + paquetesTransmitidos[i]);
			outputIterations.append("\nPaquetes Recibidos: " + paquetesRecibidos[i] + "\n\n");
		}
	}
	
}
