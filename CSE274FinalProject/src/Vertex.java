
public class Vertex {

	// Properties =================================================
	private String symbol, address;
	private int x, y;
	
	// Constructors ===============================================
	public Vertex(String symbol, String address) {
		this.symbol = symbol;
		this.address = address;
	}
	// Methods ====================================================
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Vertex)) {
			return false;
		}
		Vertex v = (Vertex) o;
		return symbol.equals(v.getSymbol()) 
			   && address.equals(v.getAddress()) 
			   && x == v.getX()
		       && y == v.getY();
	}
	// Getters/Setters ============================================

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

}
