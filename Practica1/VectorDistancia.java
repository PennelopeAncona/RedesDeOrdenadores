//package 
public class VectorDistancia {

	//El algoritmo de Enrutado por Vector Distancia intercambia información entre los vecinos
	//Para ello se crean tablas dinamicas que se van a actualizar con cada iteración. En ellas
	//va a haber una linea de entrada con su coste y la métrica que puede ser diversa. 
	//Para establecer los costes se solicitn las tablas con los costas a cada uno de los vecinos
	
	private Grafo red;
	private Router[] routers;
	
	/*Inicializamos el algoritmo con el grafo que va a tener los routers*/
	public VectorDistancia(Grafo grafo) {
		red = grafo;
		routers = new Router[grafo.getNumberOfNodos()];
		InicializarRouters();
	}
	//Establecemos los nodos vecinos en sus tablas 
	public void setNeighbourRoutersTable() {
		for(int i = 0; i < routers.length; i++) {
			routers[i].setNeighbours();
		}
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		for(int i = 0; i < routers.length; i++) {
			output.append(routers[i] + "\n\n");
		}
		return output.toString();
	}
	
	//Inicializamos los routers en la red
	private void InicializarRouters() {
		for(int i = 0; i < routers.length; i++) {
			routers[i] = new Router(red, i);
		}
	}
}
