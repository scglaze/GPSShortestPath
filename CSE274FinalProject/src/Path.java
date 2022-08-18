
import java.util.ArrayList;

public class Path implements Comparable<Object> {

	// Properties =================================================
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	private int totalTimeCost;
	private int totalDistanceCost;
	
	// Constructors ===============================================
	public Path(Vertex root) {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		vertices.add(root);
		totalTimeCost = 0;
		totalDistanceCost = 0;
	}
	
	// Methods ====================================================
	public boolean containsVertex(Vertex v) {
		for (int i = 0; i < length(); i++) {
			if (get(i).equals(v)) {
				return true;
			}
		}
		return false;
	}
	
	public void append(Vertex destination, int timeCost, int distanceCost) {
		vertices.add(destination);
		edges.add(new Edge(vertices.get(length() - 1), destination, timeCost, distanceCost));
		totalTimeCost += timeCost;
		totalDistanceCost += distanceCost;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Path)) {
			return false;
		}
		Path p = (Path) o;
		if (length() != p.length()) {
			return false;
		}
		for (int i = 0; i < length(); i++) {
			if (!(get(i).equals(p.get(i)))) {
				return false;
			}
		}
		return true;
	}
	
	public void append(Vertex destination) {
		vertices.add(destination);
	}
	
	public int length() {
		return vertices.size();
	}
	
	public String toString() {
		if (length() == 0) {
			return "[]";
		}
		String ret = "[";
		for (int i = 0; i < length(); i++) {
			ret += Graph.returnAddress ? vertices.get(i).getAddress() : vertices.get(i).getSymbol();
			ret += " -> ";
		}
		return ret.substring(0, ret.length() - 4) + "]";
	}
	
	// Getters/Setters ============================================
	public int timeCost() {
		return totalTimeCost;
	}
	
	public int distanceCost() {
		return totalDistanceCost;
	}
	
	public Vertex getSource() {
		return vertices.get(0);
	}
	
	public Vertex getDestination() {
		return vertices.get(vertices.size() - 1);
	}

	public Vertex get(int i) {
		return vertices.get(i);
	}
	
	public void setTimeCost(int timeCost) {
		this.totalTimeCost = timeCost;
	}
	
	public void setDistanceCost(int distanceCost) {
		this.totalDistanceCost = distanceCost;
	}
	
	@Override
	public int compareTo(Object o) {
		Path p = (Path) o;
		if (Graph.useDistCost) {
			if (this.totalDistanceCost < p.totalDistanceCost) return -1;
			else if (this.totalDistanceCost > p.totalDistanceCost) return 1;
			else return 0;
		} else {
			if (this.totalTimeCost < p.totalTimeCost) return -1;
			else if (this.totalTimeCost > p.totalTimeCost) return 1;
			else return 0;
		}
	}
	
}
