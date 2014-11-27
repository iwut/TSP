public class GreedyAlgorithm implements Algorithm {

	@Override
	public Node[] findPath(Node[] nodes) {

		int length = nodes.length;
		Node[] path = new Node[length];
		boolean[] used = new boolean[length];

		path[0] = nodes[0];
		used[0] = true;

		int best;
		for (int i = 1; i < length; i++) {
			best = -1;
			for (int j = 0; j < length; j++) {
				if (!used[j]
						&& (best == -1 || path[i - 1].calculateDistanceToNode(nodes[j]) < path[i - 1]
								.calculateDistanceToNode(nodes[best]))) {
					best = j;
				}
			}
			path[i] = nodes[best];
			used[best] = true;

		}

		return path;
	}

}
