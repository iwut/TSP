public class TwoOpt {

	public Path optimizePath(Path path) {

		Node[] nodes = path.nodes;

		Path newPath;

		int skipped = 0;

		double bestDistance = path.calculateDistance();
		double newDistance = Integer.MAX_VALUE;
		double diff = 0;
		outerloop: do {
			// printPathToSystemErr(path);

			for (int i = 0; i < nodes.length; i++) {
				for (int k = i + 1; k < nodes.length; k++) {
					int j = i - 1;
					int l = k + 1;
					if (k == nodes.length - 1 || i == 0) {
						// continue as normal
					} else if (!(path.calculateDistanceBetweenNodes(nodes[i], nodes[l]) < path
							.calculateDistanceBetweenNodes(nodes[j], nodes[i]) || path.calculateDistanceBetweenNodes(
							nodes[k], nodes[j]) < path.calculateDistanceBetweenNodes(nodes[k], nodes[l]))) {

						// } else if
						// (!(path.calculateDistanceBetweenNodes(nodes[j],
						// nodes[k]) < path
						// .calculateDistanceBetweenNodes(nodes[i], nodes[k]) ||
						// path.calculateDistanceBetweenNodes(
						// nodes[l], nodes[i]) <
						// path.calculateDistanceBetweenNodes(nodes[l],
						// nodes[j]))) {

						skipped++;
						continue;

					}
					newPath = twoOptSwap(path, i, k);
					newDistance = newPath.calculateDistance();
					diff = newDistance - bestDistance;
					if (newDistance < bestDistance) {

						bestDistance = newDistance;
						path = newPath;
						nodes = newPath.getNodes();
						continue outerloop;
					}
				}
			}
		} while (diff < 0);

		System.err.println("Skipped: " + skipped);
		// System.err.println();
		return path;
	}

	private Path twoOptSwap(Path oldPath, int first, int second) {
		Node[] oldNodes = oldPath.nodes;
		Node[] newNodePath = new Node[oldNodes.length];
		for (int i = 0; i <= first - 1; i++) {
			newNodePath[i] = oldNodes[i];

			if (i == first - 1) {
				newNodePath[i].distanceToNext = (oldPath.calculateDistanceBetweenNodes(oldNodes[i], oldNodes[second]));
			}
		}

		int j = first;
		Node node;
		for (int i = second; i >= first; i--) {
			node = oldNodes[i];
			newNodePath[j] = node;

			if (i == first) {
				if (second == oldNodes.length - 1) {
					newNodePath[j].distanceToNext = 0;
				} else {
					newNodePath[j].distanceToNext = (oldPath.calculateDistanceBetweenNodes(oldNodes[i],
							oldNodes[second + 1]));
				}
			} else if (i == 0) {

			}

			if (i == 0) {
				newNodePath[j].distanceToNext = i;
			} else {
				newNodePath[j].distanceToNext = (oldNodes[i - 1].distanceToNext);
			}
			j++;
		}

		for (int i = second + 1; i < oldNodes.length; i++) {
			newNodePath[i] = oldNodes[i];
		}

		return new Path(newNodePath);
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