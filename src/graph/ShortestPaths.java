package graph;
import java.util.ArrayList;
//ShortestPaths va associer a chaque sommet vertex son pere previous(vertex) 
//c'est-a-dire le sommet precedent dans le chemin le plus court

public interface ShortestPaths {
	
	public ArrayList<Vertex> getShortestPath(Vertex endVertex);//récupérer le chemin vers le point de départ.

}
