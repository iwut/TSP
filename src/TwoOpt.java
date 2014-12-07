import java.util.Arrays;

public class TwoOpt {

	private int			MAX_ITERATIONS			= 900;

	private final int	RANDOM_SWAPS			= 20;

	// private long RUN_TIME_IN_NANOSECONDS = 15 * 100000000;
	private long		RUN_TIME_IN_NANOSECONDS	= 1500000000;

	public Path optimizePath(Path path, int[][] distances) {

		Node[] nodes = path.nodes;

		int bestDistance = path.distance;
		int diff = -1;
		int iterations = 0;
		boolean bool = true;
		outerloop: while ((System.nanoTime() - Main.startTime) < RUN_TIME_IN_NANOSECONDS) {

			iterations++;

			if (!bool) {
				System.err.println("For i helvete mads");
				randomSwapNodes(path, distances, RANDOM_SWAPS);
				bestDistance = path.distance;
			}

			for (int i = 0; i < nodes.length; i++) {
				for (int k = i + 1; k < nodes.length; k++) {

					bool = twoOptSwap3(path, i, k, distances);
					diff = path.distance - bestDistance;
					if (bool) {
						bestDistance = path.distance;
						continue outerloop;
					}

				}
			}

		}
		;

		System.err.println("Ran for " + (System.nanoTime() - Main.startTime) + " ns");
		System.err.println("Iterations: " + iterations);
		return path;
	}

	public Path optimizePath2(Path path, int[][] distances) {

		// Node[] nodes = path.nodes;
		int[] nodes = path.intNodes;

		int iterations = 0;
		boolean bool = true;
		outerloop: while ((System.nanoTime() - Main.startTime) < RUN_TIME_IN_NANOSECONDS) {
			// outerloop: while (iterations < MAX_ITERATIONS) {
			iterations++;

			if (!bool) {
				System.err.println("For i helvete mads");
				randomSwapNodes2(path, distances, RANDOM_SWAPS);
			}

			for (int i = 0; i < nodes.length; i++) {
				for (int k = i + 1; k < nodes.length; k++) {

					bool = twoOptSwap4(path, i, k, distances);
					if (bool) {
						continue outerloop;
					}

				}
			}

		}

		System.err.println("Ran for " + (System.nanoTime() - Main.startTime) + " ns");
		System.err.println("Iterations: " + iterations);
		return path;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	

	private void randomSwapNodes(Path path, int[][] distances, int swaps) {

		Node[] nodes = path.nodes;
		// Random rnd = new Random();
		int first;
		int second;
		int beforeFirst;
		int afterSecond;
		int nodesLength = nodes.length;
		for (int i = 0; i < swaps; i++) {
			// first = rnd.nextInt(nodes.length - 45);
			// second = first + rnd.nextInt(nodes.length - first - 15) + 1;

			first = (nodes.length / 5);
			second = (nodes.length / 2);

			beforeFirst = (first - 1) % nodesLength;
			afterSecond = (second + 1) % nodesLength;

			oldDistance += distances[nodes[first].number][nodes[beforeFirst].number]
					+ distances[nodes[second].number][nodes[afterSecond].number];
			newDistance += distances[nodes[beforeFirst].number][nodes[second].number]
					+ distances[nodes[first].number][nodes[afterSecond].number];

			redrawPath(path, first, second);

			path.distance += newDistance - oldDistance;
		}

	}

	private void randomSwapNodes2(Path path, int[][] distances, int swaps) {

		int[] nodes = path.intNodes;
		// Random rnd = new Random();
		int first;
		int second;
		int beforeFirst;
		int afterSecond;
		int nodesLength = nodes.length;
		for (int i = 0; i < swaps; i++) {
			// first = rnd.nextInt(nodes.length - 45);
			// second = first + rnd.nextInt(nodes.length - first - 15) + 1;

			first = (nodes.length / 5);
			second = (nodes.length / 2);

			beforeFirst = (first - 1) % nodesLength;
			afterSecond = (second + 1) % nodesLength;

			oldDistance += distances[nodes[first]][nodes[beforeFirst]] + distances[nodes[second]][nodes[afterSecond]];
			newDistance += distances[nodes[beforeFirst]][nodes[second]] + distances[nodes[first]][nodes[afterSecond]];

			redrawPath2(path, first, second);

			path.distance += newDistance - oldDistance;
		}

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

	private boolean twoOptSwap3(Path oldPath, int first, int second, int[][] distances) {

		if (isNewDistanceShorter(first, second, oldPath, distances)) {

			redrawPath(oldPath, first, second);

			return true;
		} else {
			return false;
		}
	}

	private boolean twoOptSwap4(Path oldPath, int first, int second, int[][] distances) {

		if (isNewDistanceShorter2(first, second, oldPath, distances)) {

			redrawPath2(oldPath, first, second);

			return true;
		} else {
			return false;
		}
	}

	private void redrawPath2(Path oldPath, int first, int second) {

		int[] nodes = oldPath.intNodes;

		int[] test = Arrays.copyOfRange(nodes, first, second + 1);

		int q = test.length - 1;

		int j = first;
		for (int i = test.length - 1; i >= 0; i--) {
			nodes[j] = test[i];
			j++;

		}

		oldPath.distance += newDistance - oldDistance;
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

	private boolean isNewDistanceShorter2(int first, int second, Path oldPath, int[][] distances) {

		int[] nodes = oldPath.intNodes;

		if (first == 0 && (second == nodes.length - 1 || second == nodes.length - 2)) {
			// no changes
			return false;
		}

		int oldDistance = 0;
		int newDistance = 0;

		int lastNode = nodes.length - 1;

		int beforeFirst = first - 1;
		if (first == 0) {
			beforeFirst = lastNode;
		}

		int afterLast = second + 1;

		if (second == lastNode) {
			afterLast = 0;
		}

		oldDistance += distances[nodes[first]][nodes[beforeFirst]] + distances[nodes[second]][nodes[afterLast]];
		newDistance += distances[nodes[beforeFirst]][nodes[second]] + distances[nodes[first]][nodes[afterLast]];

		if (newDistance < oldDistance) {
			this.oldDistance = oldDistance;
			this.newDistance = newDistance;
			return true;
		} else {
			return false;
		}
	}

	private boolean isNewDistanceShorter(int first, int second, Path oldPath, int[][] distances) {

		Node[] nodes = oldPath.nodes;

		if (first == 0 && (second == nodes.length - 1 || second == nodes.length - 2)) {
			// no changes
			return false;
		}

		int oldDistance = 0;
		int newDistance = 0;

		int lastNode = nodes.length - 1;

		int beforeFirst = first - 1;
		if (first == 0) {
			beforeFirst = lastNode;
		}

		int afterLast = second + 1;

		if (second == lastNode) {
			afterLast = 0;
		}

		oldDistance += distances[nodes[first].number][nodes[beforeFirst].number]
				+ distances[nodes[second].number][nodes[afterLast].number];
		newDistance += distances[nodes[beforeFirst].number][nodes[second].number]
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
