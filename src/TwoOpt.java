public class TwoOpt {

	public Path optimizePath(Path path) {

		Node[] nodes = path.getNodes();

		Path newPath;

		int skipped = 0;

		double bestDistance = path.calculateDistance();
		double newDistance = Integer.MAX_VALUE;
		double diff = 0;
		outerloop: do {
			printPathToSystemErr(path);

			for (int i = 0; i < nodes.length; i++) {
				for (int k = i + 1; k < nodes.length; k++) {
					int j = i - 1;
					int l = k + 1;
					if (k == nodes.length - 1 || i == 0) {
						// continue as normal
					} else if (!(path.calculateDistanceBetweenNodes(nodes[i], nodes[l]) < path
							.calculateDistanceBetweenNodes(nodes[j], nodes[l]) || path.calculateDistanceBetweenNodes(
							nodes[k], nodes[j]) < path.calculateDistanceBetweenNodes(nodes[k], nodes[i]))) {
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

		// TODO PROBLEM HERE, calculateDistance equals bestDistance

		System.err.println("Skipped: " + skipped);
		System.err.println();
		return path;
	}

	private Path twoOptSwap(Path oldPath, int first, int second) {
		Node[] oldNodes = oldPath.getNodes();
		Node[] newNodePath = new Node[oldNodes.length];
		for (int i = 0; i <= first - 1; i++) {
			newNodePath[i] = oldNodes[i];
		}

		int j = first;
		for (int i = second; i >= first; i--) {
			newNodePath[j] = oldNodes[i];
			j++;
		}

		for (int i = second + 1; i < oldNodes.length; i++) {
			newNodePath[i] = oldNodes[i];
		}

		// for (int i = 0; i < newNodePath.length; i++) {
		// if (newNodePath[i] == null) {
		// String hej = "va";
		// }
		// }

		return new Path(newNodePath);
	}

	private void printPathToSystemErr(Path path) {
		Node[] nodes = path.getNodes();

		String str = "" + nodes[0].getNumber();
		for (int i = 1; i < nodes.length; i++) {
			str += "-" + nodes[i].getNumber();
		}

		System.err.println(str);
	}

}
