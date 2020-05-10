//package
public class Grafo {
	private int[][] adjMatrix;  // matriz Adjunta
    private final int numVertices; // Numero de nodos
//Grafo se inicializa con la catidad de nodos
    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices];
    }
//Métodos para acceder a la informacion del Grafo
    
    //addNodo introduce el valor en la posicion i j 
    public void addNodo(int i, int j, int value) {
        adjMatrix[i][j] = value;
    }

    public void removeNodo(int i, int j) {
        adjMatrix[i][j] = 0;
    }

    public boolean isNodo(int i, int j) {
        return adjMatrix[i][j] != 0;
    }
    
    public int getNumberOfNodos() {
    	return numVertices;
    }
    
    public int getNodo(int i, int j) {
    	return adjMatrix[i][j];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.numVertices; i++){
            s.append(i + ": ");
            for(int j: adjMatrix[i]){
                s.append(j + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
    
}
