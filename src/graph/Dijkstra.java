package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Dijkstra {
	public static ShortestPathsImpl dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) {
		ShortestPathsImpl shortestPaths = new ShortestPathsImpl();
		ProcessedVertexesImpl processedVertexes = new ProcessedVertexesImpl();
		processedVertexes.add(startVertex);
		Vertex pivotVertex = startVertex;
		MinDistanceImpl minDistance = new MinDistanceImpl();
		minDistance.put(startVertex, 0);

		for (int y=0;y<graph.getHeight();y++) {
			for(int x=0;x<graph.getWidth();x++) {
				if (graph.getMazeMatrix()[x][y].isDepartureBox()) {
					minDistance.put(graph.getMazeMatrix()[x][y], 0);
				} else {
					minDistance.put(graph.getMazeMatrix()[x][y], Integer.MAX_VALUE);
				}

				
			}

		}

		while (processedVertexes.contains(endVertex) == false && graph.getSuccessors(pivotVertex)!=null) {

			for (Vertex succVertex : graph.getSuccessors(pivotVertex)) {
				if (processedVertexes.contains(succVertex)) {
					continue;
				}
				else {
					if ((minDistance.get(pivotVertex) + graph.getDistance(pivotVertex, succVertex)) < minDistance
							.get(succVertex)) {
						minDistance.put(succVertex,
								minDistance.get(pivotVertex) + graph.getDistance(pivotVertex, succVertex));
						shortestPaths.put(succVertex, pivotVertex);

					}
					
				}

			}

			Collection<Integer> collection = minDistance.values();
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			ArrayList<Vertex> minDistanceVertexSorted = new ArrayList<Vertex>();
			for (int k : collection) {
				arrayList.add(k);

			}
			Collections.sort(arrayList);
			
			
			for (int p : arrayList) {
				for (Vertex nextVertex : minDistance.keySet()) {
					if (minDistance.get(nextVertex).equals(p) && minDistanceVertexSorted.contains(nextVertex)==false  ) {
						minDistanceVertexSorted.add(nextVertex);
					}
				}
			}
			for (Vertex nextVertex :minDistanceVertexSorted) {
				if(processedVertexes.contains(nextVertex)==false) {
					pivotVertex = nextVertex;
					break;
				}
			}
			processedVertexes.add(pivotVertex);

		}
		return shortestPaths;

	}
}
