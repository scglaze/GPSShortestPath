
public class Tester {

	public static void main(String[] args) {
		
		String file = "MapInformationXY.txt";
		Graph g = new Graph(file);
		g.useDistCost = false;
		Graph.returnAddress = true;
		System.out.println(g.toString());
		
		Vertex v = new Vertex("A", "A");
		Path path = new Path(v);
		path.append(new Vertex("B", "B"), 2, 4);
		path.append(new Vertex("C", "B"), 2, 4);
		path.append(new Vertex("D", "B"), 2, 4);
		path.append(new Vertex("E", "B"), 2, 4);
		System.out.println(path.toString());
		System.out.println(path.timeCost());
		System.out.println(path.distanceCost());
	}
}
