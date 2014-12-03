import java.util.Arrays;

public class TwoOpt {

	private int	MAX_ITERATIONS	= 245;

	public Path optimizePath2(Path path, int[][] distances) {

		Node[] nodes = path.nodes;

		int skipped = 0;

		int bestDistance = path.distance;
		int diff = -1;
		int iterations = 0;
		outerloop: while (diff < 0 && iterations < MAX_ITERATIONS) {
			iterations++;

			for (int i = 0; i < nodes.length; i++) {
				for (int k = i + 1; k < nodes.length; k++) {

					boolean bool = twoOptSwap3(path, i, k, distances);
					diff = path.distance - bestDistance;
					if (bool) {
						bestDistance = path.distance;
						continue outerloop;
					}

				}
			}

		}
		;

		System.err.println("Iterations: " + iterations);
		System.err.println("Skipped: " + skipped);
		return path;
	}

	public Path optimizePath(Path path, DistanceHolder distances) {

		// int[][] distances = distanceHolder.distances;

		Node[] nodes = path.nodes;

		Path newPath;

		int skipped = 0;

		double bestDistance = path.distance;
		double newDistance = Integer.MAX_VALUE;
		double diff = Integer.MAX_VALUE;
		outerloop: do {

			for (int i = 0; i < nodes.length; i++) {
				for (int k = i + 1; k < nodes.length; k++) {
					int j = i - 1;
					if (i == 0) {
						j = nodes.length - 1;
					}

					int l = k + 1;
					if (k == nodes.length - 1) {
						l = 0;
					}
					if (!(distances.getDistance(nodes[i], nodes[l]) < distances.getDistance(nodes[i], nodes[j]) || distances
							.getDistance(nodes[k], nodes[j]) < distances.getDistance(nodes[k], nodes[l]))) {

						skipped++;
						continue;

					}

					boolean bool = twoOptSwap2(path, i, k, distances);
					diff = path.distance - bestDistance;
					if (bool) {

						// System.err.println(diff);
						// nodes = path.nodes;
						bestDistance = path.distance;
						continue outerloop;
					}

				}
			}

		} while (diff < 0);

		System.err.println("Skipped: " + skipped);
		return path;
	}

	private Path twoOptSwap(Path oldPath, int first, int second, DistanceHolder distances) {

		int distance = oldPath.distance;

		Node[] oldNodes = oldPath.nodes;
		Node[] newNodePath = new Node[oldNodes.length];

		if (first == 0) {
			distance -= distances.getDistance(oldNodes[first], oldNodes[oldNodes.length - 1]);
			distance += distances.getDistance(oldNodes[oldNodes.length - 1], oldNodes[second]);
		} else {
			distance -= distances.getDistance(oldNodes[first - 1], oldNodes[first]);
			distance += distances.getDistance(oldNodes[first - 1], oldNodes[second]);
		}

		if (second == oldNodes.length - 1) {
			distance -= distances.getDistance(oldNodes[second], oldNodes[0]);
			distance += distances.getDistance(oldNodes[first], oldNodes[0]);
		} else {
			distance -= distances.getDistance(oldNodes[second], oldNodes[second + 1]);
			distance += distances.getDistance(oldNodes[first], oldNodes[second + 1]);
		}

		// int distance = 0;

		for (int i = 0; i <= first - 1; i++) {
			newNodePath[i] = oldNodes[i];

			// if (i != 0) {
			//
			// distance += distances.getDistance(newNodePath[i], newNodePath[i -
			// 1]);
			// }

		}

		int j = first;
		Node node;
		for (int i = second; i >= first; i--) {
			node = oldNodes[i];
			newNodePath[j] = node;

			// if (i != 0 && j != 0) {
			// if (newNodePath[j - 1] == null) {
			// String hString = "";
			// } else {
			// distance += distances.getDistance(node, newNodePath[j - 1]);
			// }
			// }

			j++;
		}

		for (int i = second + 1; i < oldNodes.length; i++) {
			newNodePath[i] = oldNodes[i];

			// distance += distances.getDistance(newNodePath[i], newNodePath[i -
			// 1]);
		}

		Path newPath = new Path(newNodePath);
		newPath.distance = distance;

		return newPath;
	}

	private boolean twoOptSwap3(Path oldPath, int first, int second, int[][] distances) {

		if (isNewDistanceShorter(first, second, oldPath, distances)) {

			redrawPath(oldPath, first, second);

			return true;
		} else {
			return false;
		}
	}

	private void redrawPath(Path oldPath, int first, int second) {

		Node[] nodes = oldPath.nodes;

		Node[] test = Arrays.copyOfRange(nodes, first, second + 1);

		int q = test.length - 1;

		int j = first;
		for (int i = test.length - 1; i >= 0; i--) {
			nodes[j] = test[i];
			j++;

		}

		oldPath.distance += newDistance - oldDistance;
	}

	int	newDistance;
	int	oldDistance;

	private boolean isNewDistanceShorter(int first, int second, Path oldPath, int[][] distances) {

		Node[] nodes = oldPath.nodes;

		int lastNode = nodes.length - 1;

		if (first == 0 && (second == lastNode || second == lastNode - 1)) {
			// no changes
			return false;
		}

		int beforeFirst = first - 1;
		if (first == 0) {
			beforeFirst = lastNode;
		}

		int afterLast = second + 1;

		if (second == lastNode) {
			afterLast = 0;
		}

		int oldDistance = distances[nodes[first].number][nodes[beforeFirst].number]
				+ distances[nodes[second].number][nodes[afterLast].number];
		int newDistance = distances[nodes[beforeFirst].number][nodes[second].number]
				+ distances[nodes[first].number][nodes[afterLast].number];

		if (newDistance < oldDistance) {
			this.oldDistance = oldDistance;
			this.newDistance = newDistance;
			return true;
		} else {
			return false;
		}
	}

	private boolean twoOptSwap2(Path oldPath, int first, int second, DistanceHolder distances) {

		int oldDistance = oldPath.distance;

		int distance = oldPath.distance;

		Node[] nodes = oldPath.nodes;
		Node[] newNodePath = new Node[nodes.length];

		// if (first == 0 && (second == nodes.length - 1 || second ==
		// nodes.length - 2)) {
		// // no changes
		// return false;
		// }

		if (first == 0) {
			distance -= distances.getDistance(nodes[first], nodes[nodes.length - 1]);
			distance += distances.getDistance(nodes[nodes.length - 1], nodes[second]);
		} else {
			distance -= distances.getDistance(nodes[first - 1], nodes[first]);
			distance += distances.getDistance(nodes[first - 1], nodes[second]);
		}

		if (second == nodes.length - 1) {
			distance -= distances.getDistance(nodes[second], nodes[0]);
			distance += distances.getDistance(nodes[first], nodes[0]);
		} else {
			distance -= distances.getDistance(nodes[second], nodes[second + 1]);
			distance += distances.getDistance(nodes[first], nodes[second + 1]);
		}

		if (distance < oldDistance) {

			Node[] test = new Node[second - first + 1];

			int q = 0;
			for (int i = second; i >= first; i--) {
				test[q] = nodes[i];
				q++;
			}

			int j = 0;
			for (int i = first; i <= second; i++) {
				nodes[i] = test[j];
				j++;

			}

			oldPath.distance = distance;

			// printPathToSystemErr(oldPath);

			return true;
		} else {
			return false;
		}
	}

	private void printPathToSystemErr(Path path) {
		Node[] nodes = path.nodes;

		String str = "" + nodes[0].getNumber();
		for (int i = 1; i < nodes.length; i++) {
			str += "-" + nodes[i].getNumber();
		}

		System.err.println(str);
	}

}