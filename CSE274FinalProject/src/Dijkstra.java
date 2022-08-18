import java.util.Set;

/**
 * CSE 274 Final Project
 * 
 * This is a class that contains a static method that takes two Vertex objects as parameters and 
 * returns the shortest path between those two vertices in the graph. 
 */
public class Dijkstra {
		
	public static Path shortestPath(Vertex source, Vertex destination) {
		
		// Creates a graph based on the information in the given file
		Graph graph = new Graph("MapInformationXY.txt");
		
		if (source.equals(destination)) {
			return new Path(source);
		}
		
		// Custom data structure; a Priority Queue implemented with a Heap
		HeapPriorityQueue<Path> paths = new HeapPriorityQueue<Path>();
		
		// Initially adding the neighbors of the source vertex
		Set<Edge> neighboringEdges = graph.getNeighboringEdges(source);
		if (neighboringEdges != null) {
			for (Edge neighbor : neighboringEdges) {
				Path temp = new Path(source);
				temp.append(neighbor.getDestination(), neighbor.getTimeCost(), neighbor.getDistanceCost());
				paths.add(temp);
			}
		} else {
			return null;
		}
		
		// Searching the graph and find the shortest path from the source Vertex to the destination Vertex.
		while (paths.size() > 0) {
			// Removing the path at the front of the Priority Queue
			Path p = paths.remove();
									
			// Checking if this Path is a desired path
			if (p.getDestination().equals(destination)) {
				return p;
			}
			
			// If not, add all possible paths that come from p to paths.
			for (Edge neighbor : graph.getNeighboringEdges(p.getDestination())) {
				Path path = new Path(source);
				for (int i = 1; i < p.length(); i++) {
					path.append(p.get(i));
				}
				// Making sure that the path doesn't already contain the vertex.
				if (!(path.containsVertex(neighbor.getDestination()))) {
					path.append(neighbor.getDestination());
					path.setDistanceCost(p.distanceCost() + neighbor.getDistanceCost());
					path.setTimeCost(p.timeCost() + neighbor.getTimeCost());
					paths.add(path);
				}
			}			
		}
		
		// If code reaches this point then no possible path was found between the two vertices
		return null;
	}
	
}
