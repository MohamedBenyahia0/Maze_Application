package graph;
import java.util.ArrayList;
import java.util.Collections;
//ShortestPaths va associer a chaque sommet vertex son pere previous(vertex) 
// c'est-a-dire le sommet precedent dans le chemin le plus court
import java.util.HashMap;

public class ShortestPathsImpl extends HashMap<Vertex,Vertex> implements ShortestPaths{
	public ShortestPathsImpl() {
		super();
	}

	
	public ArrayList<Vertex> getShortestPath(Vertex endVertex ){

		ArrayList<Vertex> shortestPath = new ArrayList<Vertex>();
		
		for(Vertex vertex = endVertex; vertex!= null;vertex = this.get(vertex)) {
			shortestPath.add(vertex);
				
			
			
			
		}
		

		return shortestPath;
	}
}
