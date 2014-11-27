public class GreedyAlgorithm implements Algorithm {

	@Override
	public Path findPath(Path oldPath) {

		Node[] nodes = oldPath.getNodes();

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
						&& (best == -1 || oldPath.calculateDistanceBetweenNodes(path[i - 1], nodes[j]) < oldPath
								.calculateDistanceBetweenNodes(path[i - 1], nodes[best]))) {
					best = j;
				}
			}
			path[i] = nodes[best];
			used[best] = true;

		}

		return new Path(path);
	}

}
