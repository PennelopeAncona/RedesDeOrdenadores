//package

//El router va a ser el que maneje la tabla de vectores de distancia de cada enrutador 
public class Router {
	
	private int[][] table; //tabla que va a guardar la informacion de los vecinos 
	private int routerId; //identificador del router
	private Grafo red;
	//El contructor va a inicializar el grafo y se le va a pasar el identificador del router
	
	public Router(Grafo grafo, int routerId) {
		red = grafo;
		this.routerId = routerId;
		table = new int[grafo.getNumberOfNodos()][grafo.getNumberOfNodos()];
	
	}
	
	//Establece los vecinos
	public void setNeighbours() {
		for(int i = 0; i < red.getNumberOfNodos(); i++) {
			if(red.isNodo(routerId, i)) {
				table[routerId][i] = red.getNodo(routerId, i);
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
        for (int i = 0; i < table.length; i++){
            s.append(i + ": ");
            for(int j: table[i]){
                s.append(j + " ");
            }
            s.append("\n");
        }
        return s.toString();
	}
	
}
