public class GreedyAlgorithm implements Algorithm {

	@Override
	public Path findPath(Path oldPath, DistanceHolder distances) {

		// int[][] distances = distanceHolder.distances;

		Node[] nodes = oldPath.getNodes();

		int length = nodes.length;
		Node[] path = new Node[length];
		boolean[] used = new boolean[length];

		path[0] = nodes[0];
		used[0] = true;

		int pathLength = 0;

		int best;

		for (int i = 1; i < length; i++) {
			best = -1;
			for (int j = 0; j < length; j++) {

				if (!used[j]
						&& (best == -1 || distances.getDistance(path[i - 1], nodes[j]) < distances.getDistance(
								path[i - 1], nodes[best]))) {
					best = j;
				}
			}
			path[i] = nodes[best];
			pathLength += distances.getDistance(path[i], path[i - 1]);
			used[best] = true;

		}

		Path greedyPath = new Path(path);
		greedyPath.distance = pathLength;

		return greedyPath;
	}

}