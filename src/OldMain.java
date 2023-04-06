import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import graph.Dijkstra;
import graph.ShortestPathsImpl;
import graph.Vertex;

import model.MazeModel;
import model.MazeReadingException;
// This is the old Main , the new Main is the graphicMain
public class OldMain {

	public static void main(String[] args) throws FileNotFoundException, IOException,MazeReadingException{
		MazeModel mazeModel = new MazeModel();
	
		mazeModel.LoadMaze("data/10x10labyrinthe1.maze");
		
		
		Vertex startVertex = null;
		Vertex endVertex= null;
		for (Vertex vertex: mazeModel.getAllVertexes()) {
			if (vertex.isDepartureBox()) {
				if (startVertex!=null ) {
					System.out.println("plusieurs cases de depart sont presentes");
					return;
				}
				startVertex = vertex;
			}
			if (vertex.isArrivalBox()) {
				if (endVertex!=null ) {
					System.out.println("plusieurs cases d'arrivee sont presentes");
					return;
				}
				endVertex=vertex;
			}
		}
		if (startVertex==null || endVertex == null) {
			System.out.println("la case de depart ou la case d'arrivee n'est pas presente");
			return;
			
			
		}
		
		
		
		ShortestPathsImpl shortestPaths= Dijkstra.dijkstra(mazeModel, startVertex, endVertex);
		ArrayList<Vertex> shortestPath = shortestPaths.getShortestPath(endVertex);
		
		


		
		



		for (int y =0; y<mazeModel.getHeight();y++) {
			for(int x=0;x<mazeModel.getWidth();x++) {


				if (shortestPath.contains(mazeModel.getMazeMatrix()[x][y])) {
					System.out.print('U');
				}
				else if(mazeModel.getMazeMatrix()[x][y].isEmptyBox()) {
					
					System.out.print('E');
				}
				else {
					System.out.print('W');
				}

				
			}
			System.out.print('\n');

		}

		
		
		
		
		

	}

}
