public class Path {
	public Node[] nodes;

	public Path(Node[] nodes) {
		this.nodes = nodes;
	}

	public Node[] getNodes() {
		return nodes;
	}

	public double calculateDistanceBetweenNodes(Node first, Node second) {
		double absX = first.x - second.x;
		double absY = first.y - second.y;

		return Math.sqrt(absX * absX + absY * absY);
	}

	public double calculateDistance() {
		double length = 0;

		for (int i = 0; i < nodes.length; i++) {
			length += nodes[i].distanceToNext;
		}

		// for (int i = 1; i < nodes.length; i++) {
		// length += calculateDistanceBetweenNodes(nodes[i - 1], nodes[i]);
		// }

		return length;
	}

}
