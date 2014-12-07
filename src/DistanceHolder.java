public class DistanceHolder {

	public int[][]	distances;

	// public int[] intNodes;

	public void calculateDistances(Path path) {
		Node[] nodes = path.nodes;
		int nodeAmount = nodes.length;

		int[][] distances = new int[nodeAmount][nodeAmount];
		int[] intNodes = new int[nodes.length];

		int distance;
		for (int i = 0; i < nodeAmount; i++) {
			for (int j = i + 1; j < nodeAmount; j++) {
				if (i != j) {

					distance = calculateDistanceBetweenNodes(nodes[i], nodes[j]);

					distances[i][j] = distance;
					distances[j][i] = distance;
				}
			}
			intNodes[i] = nodes[i].number;
			
		}

		path.intNodes = intNodes;
		path.nodes = nodes;

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

	public int getDistance(int first, int second) {
		return distances[first][second];
	}

}
