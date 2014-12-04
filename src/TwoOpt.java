import java.util.Arrays;

public class TwoOpt {

	private int	MAX_ITERATIONS	= Integer.MAX_VALUE;

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

					boolean bool = swapIfGenerateShorterPath(path, i, k, distances);
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
	

	private boolean swapIfGenerateShorterPath(Path path, int first, int second, int[][] distances) {

		Distances distanceHolder = isNewDistanceShorter(first, second, path, distances);
		if (distanceHolder != null) {
			


			redrawPath(path, first, second);
			
			path.distance += distanceHolder.newDistance - distanceHolder.oldDistance;
			return true;
		} else {
			return false;
		}
	}
	
	public void forceSwap(Path path, int first, int second, int[][] distances) {
		Distances distanceHolder = calculateDistances(first, second, path, distances);
		
		redrawPath(path, first, second);
		
		path.distance += distanceHolder.newDistance - distanceHolder.oldDistance;
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
	
	private class Distances {
		int newDistance;
		int oldDistance;
		
		Distances(int newDistance, int oldDistance) {
			this.newDistance = newDistance;
			this.oldDistance = oldDistance;
		}
	}
	
	/**
	 * Return old distance of the old path and new distance of the new path.
	 * @param first
	 * @param second
	 * @param oldPath
	 * @param distances
	 * @return
	 */
	private Distances calculateDistances(int first, int second, Path oldPath, int[][] distances) {
		Node[] nodes = oldPath.nodes;

		int lastNode = nodes.length - 1;

		if (first == 0 && (second == lastNode || second == lastNode - 1)) {
			// no changes
			return new Distances(0, 0);
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

		return new Distances(newDistance, oldDistance);
	}


	private Distances isNewDistanceShorter(int first, int second, Path oldPath, int[][] distances) {		
		Distances oldAndNewDistance = calculateDistances(first, second, oldPath, distances);

		if (oldAndNewDistance.newDistance < oldAndNewDistance.oldDistance) {

			return oldAndNewDistance;
		} else {
			return null;
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