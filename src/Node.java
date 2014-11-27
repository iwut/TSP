public class Node {

	private double	x;
	private double	y;

	private int		number;

	public Node(double x, double y, int number) {
		this.x = x;
		this.y = y;
		this.number = number;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getNumber() {
		return number;
	}

	public double calculateDistanceToNode(Node node) {
		double absX = Math.abs(x - node.getX());
		double absY = Math.abs(y - node.getY());

		return Math.sqrt(absX * absX + absY * absY);
	}

}
