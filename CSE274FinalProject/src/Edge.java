
public class Edge {

	// Properties =================================================
	private Vertex source, destination;
	private int timeCost, distanceCost;
	
	// Constructors ===============================================
	public Edge(Vertex source, Vertex destination, int timeCost, int distanceCost) {
		this.source = source;
		this.destination = destination;
		this.timeCost = timeCost;
		this.distanceCost = distanceCost;
	}
	// Methods ====================================================
	public String toString() {
		return String.format("[%s -> %s] Cost: %d", Graph.returnAddress ? source.getAddress() : source.getSymbol(), 
			   Graph.returnAddress ? destination.getAddress() : destination.getSymbol(), Graph.useDistCost ? distanceCost : timeCost);
	}
	
	// Getters/Setters ============================================

	/**
	 * @return the source
	 */
	public Vertex getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Vertex source) {
		this.source = source;
	}

	/**
	 * @return the destination
	 */
	public Vertex getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Vertex destination) {
		this.destination = destination;
	}

	/**
	 * @return the timeCost
	 */
	public int getTimeCost() {
		return timeCost;
	}

	/**
	 * @param timeCost the timeCost to set
	 */
	public void setTimeCost(int timeCost) {
		this.timeCost = timeCost;
	}

	/**
	 * @return the distanceCost
	 */
	public int getDistanceCost() {
		return distanceCost;
	}

	/**
	 * @param distanceCost the distanceCost to set
	 */
	public void setDistanceCost(int distanceCost) {
		this.distanceCost = distanceCost;
	}
	
}
