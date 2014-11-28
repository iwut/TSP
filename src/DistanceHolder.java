public class DistanceHolder {

	public int[][]	distances;

	public void calculateDistances(Path path) {
		Node[] nodes = path.nodes;
		int nodeAmount = nodes.length;

		int[][] distances = new int[nodeAmount][nodeAmount];

		int distance;
		for (int i = 0; i < nodeAmount; i++) {
			for (int j = i + 1; j < nodeAmount; j++) {
				if (i == j) {
					distances[i][j] = 0;
				} else {

					distance = calculateDistanceBetweenNodes(nodes[i], nodes[j]);

					distances[i][j] = distance;
					distances[j][i] = distance;
				}
			}
		}

		this.distances = distances;
	}

	private int calculateDistanceBetweenNodes(Node first, Node second) {
		double absX = first.x - second.x;
		double absY = first.y - second.y;

		return (int) Math.round(Math.sqrt(absX * absX + absY * absY));
	}

	public int getDistance(Node first, Node second) {
		return distances[first.number][second.number];
	}

}
