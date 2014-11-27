public class GreedyAlgorithm implements Algorithm {

	@Override
	public int[] findPath(Node[] nodes) {

		int length = nodes.length;
		int[] path = new int[length];
		boolean[] used = new boolean[length];

		path[0] = 0;
		used[0] = true;

		int best;
		for (int i = 1; i < length; i++) {
			best = -1;
			for (int j = 0; j < length; j++) {
				if (!used[j]
						&& (best == -1 || dist(nodes[path[i - 1]], nodes[j]) < dist(nodes[path[i - 1]], nodes[best]))) {
					best = j;
				}
			}
			path[i] = best;
			used[best] = true;

		}

		return path;
	}

	private double dist(Node first, Node second) {
		double absX = Math.abs(first.getX() - second.getX());
		double absY = Math.abs(first.getY() - second.getY());

		return Math.sqrt(absX * absX + absY * absY);
	}

}
