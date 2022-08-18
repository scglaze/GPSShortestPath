import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Graph {

	// Properties =================================================
	public static boolean useDistCost;
	public static boolean returnAddress;
	
	private String filename;
	private Map<Vertex, Set<Edge>> graph;
	
	// Constructors ===============================================
	public Graph(String filename) {
		this.filename = filename;
        graph = new HashMap<Vertex, Set<Edge>>();
        readFile();
	}
	
	// Methods ====================================================
	public void readFile() {
        try {
            // Creates a scanner
            Scanner file = new Scanner(new File(filename));
            String line = file.nextLine();
            
            // Mapping the symbol of the vertex to the vertex itself for easy access within this method
            // Improves time complexity
            Map<String, Vertex> vertices = new HashMap<String, Vertex>();
            
            // Skips lines until the Nodes are reached
            while (!line.equals("<Nodes>")) { line = file.nextLine(); }
            
            // Skips two lines of header text in the file
            file.nextLine();
            line = file.nextLine();
            
            // Creates Vertex objects (each of which contains a symbol and an address property)
            while (!line.equals("</Nodes>")) {
                String[] s = line.split("\t");
                Vertex v = new Vertex(s[0], s[1]);
                v.setX(Integer.parseInt(s[2]));
                v.setY(Integer.parseInt(s[3]));
                vertices.put(s[0], v);
                line = file.nextLine();
            }
            
            // Skips lines until Edges are reached
            while (!line.equals("<Edges>")) { line = file.nextLine(); }
            file.nextLine();
            
            // Creates Edge objects (each of which contains a source, destination, time cost, 
            // and distance cost property) and adds them to a set
            line = file.nextLine();
            String[] s = line.split("\t");
            while (!line.equals("</Edges>")) {
                Vertex v = vertices.get(s[0]);
                Set<Edge> edges = new HashSet<Edge>();

                do {
                    Vertex destination = vertices.get(s[1]);
                    edges.add(new Edge(v, destination, Integer.parseInt(s[2]), Integer.parseInt(s[3])));
                    line = file.nextLine();
                    s = line.split("\t");
                } while (s[0].equals(v.getSymbol()));
                
                // When the next line contains a different source Vertex, the current set of Edges are
                // added to the graph Map as values of the corresponding Vertex key (the source Vertex)
                graph.put(v, edges);
                
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception f) {
            f.printStackTrace();
        }
    }
	
	public String toString() {
		String ret = "Edges\n";
		for (Vertex vertex : graph.keySet()) {
			for (Edge edge : graph.get(vertex)) {
				ret += edge.toString() + "\n";
			}
		}
		return ret;
	}
	
	// Getters/Setters ============================================
	public Set<Vertex> getVertices() {
		return graph.keySet();
	}
	
	public Set<Edge> getNeighboringEdges(Vertex source) {
		for (Vertex v : graph.keySet()) {
			if (v.equals(source)) {
				return graph.get(v);
			}
		}
		return new HashSet<Edge>();
	}
	
	public Vertex findVertex(String address) {
		Set<Vertex> vertices = getVertices();
		for (Vertex vertex : vertices) {
			if (vertex.getSymbol().equals(address)) {
				return vertex;
			}
		}
		return null;
	}
	
}
