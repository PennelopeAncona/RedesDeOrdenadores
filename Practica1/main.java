//package...
public class main {

	 public static void main(String[] args) {
	
		 Grafo grafo = new Grafo(7);
	 //A través de addNodo vamos a introducir el valor en las posiciones i j 
	             grafo.addNodo(0,1,2);
		         grafo.addNodo(0,2,5);
		         grafo.addNodo(0,6,1);
		         grafo.addNodo(1,0,2);
		         grafo.addNodo(1,2,3);
		         grafo.addNodo(1,6,3);
		         grafo.addNodo(2,1,3);
		         grafo.addNodo(2,3,1);
		         grafo.addNodo(2,5,1);
                 grafo.addNodo(2,6,3);
		         grafo.addNodo(3,2,1);
		         grafo.addNodo(3,5,2);
		         grafo.addNodo(5,2,1);
		         grafo.addNodo(5,6,1);
		         grafo.addNodo(6,0,1);
		         grafo.addNodo(6,1,2);
		         grafo.addNodo(6,1,2);
		         grafo.addNodo(6,2,3);
		         grafo.addNodo(6,5,1);
		         
     //Imprimimos el grafo qu acabamos de introducir	         
		         System.out.println(grafo);  
		         
		         Router ro = new Router(grafo,6);
		         ro.setNeighbours();
		         System.out.println(ro);
		         
		         VectorDistancia vd = new VectorDistancia(grafo);
		         System.out.println(vd);
		         
	  
		         
    //Aplicamos los algoritmos
		         Inundacion in = new Inundacion(grafo);
		         in.inicio();
		         System.out.println(in);
		         
	             
	 }
}
