public class Path {
	private Node[]	nodes;

	public Path(Node[] nodes) {
		this.nodes = nodes;
	}

	public Node[] getNodes() {
		return nodes;
	}

	public double calculateDistanceBetweenNodes(Node first, Node second) {
		double absX = Math.abs(first.getX() - second.getX());
		double absY = Math.abs(first.getY() - second.getY());

		return Math.sqrt(absX * absX + absY * absY);
	}

	public double calculateDistance() {
		double length = 0;

		for (int i = 1; i < nodes.length; i++) {
			length += calculateDistanceBetweenNodes(nodes[i - 1], nodes[i]);
		}

		return length;
	}

}
