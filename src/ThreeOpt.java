public class ThreeOpt {

	public class LLNode{
		LLNode previous;
		LLNode next;
		Node node;
		
		public LLNode(Node[] nodes, int index, LLNode previous){
			node = nodes[index];
			this.previous = previous;
			if(index<nodes.length)
				this.next = new LLNode(nodes, index+1, this);
		}
		
		public void setNext(LLNode next){
			this.next = next;
		}
		
		public void setPrevious(LLNode previous){
			this.previous = previous;
		}
		
		public Path getPath(int nodecount){
			Node[] nodes = new Node[nodecount];
			
			LLNode currentnode = this;
			for (int i = 0; i < nodes.length; i++) {
				nodes[i] = currentnode.node;
				currentnode = currentnode.next;
			}
			return new Path(nodes);
		}
	}
	
	public Path optimizePath3(Path path, DistanceHolder distances) {
		
		Node[] nodes = path.nodes;
		LLNode firstNode = new LLNode(nodes, 0, null);
		for(int i = 0; i< nodes.length; i++){
			firstNode
		}
		
		return null;
	}
	
	
	
	
	
	
	public Path optimizePath(Path path, DistanceHolder distances) {

		// int[][] distances = distanceHolder.distances;

		Node[] nodes = path.nodes;

		Path newPath;

		int skipped = 0;

		double bestDistance = path.distance;
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

					} else if (!(distances.getDistance(nodes[i], nodes[l]) < distances.getDistance(nodes[i], nodes[j]) || distances
							.getDistance(nodes[k], nodes[j]) < distances.getDistance(nodes[k], nodes[l]))) {

						skipped++;
						continue;

					}
					newPath = twoOptSwap(path, i, k, distances);
					newDistance = newPath.distance;
					diff = newDistance - bestDistance;
					if (newDistance < bestDistance) {

						bestDistance = newDistance;
						path = newPath;
						nodes = newPath.nodes;
						continue outerloop;
					}
				}
			}
		} while (diff < 0);

		System.err.println("Skipped: " + skipped);
		// System.err.println();
		return path;
	}

	private Path twoOptSwap(Path oldPath, int first, int second, DistanceHolder distances) {
		// int[][] distances = distanceHolder.distances;

		Node[] oldNodes = oldPath.nodes;
		Node[] newNodePath = new Node[oldNodes.length];

		int distance = 0;

		for (int i = 0; i <= first - 1; i++) {
			newNodePath[i] = oldNodes[i];

			if (i != 0) {
				// distance += distances[newNodePath[i].number][newNodePath[i +
				// 1].number];
				distance += distances.getDistance(newNodePath[i], newNodePath[i - 1]);
			}

		}

		int j = first;
		Node node;
		for (int i = second; i >= first; i--) {
			node = oldNodes[i];
			newNodePath[j] = node;

			// distance += distances[(node.number)][(newNodePath[i -
			// 1].number)];

			if (i != 0 && j != 0) {
				if (newNodePath[j - 1] == null) {
					String hString = "";
				} else {
					distance += distances.getDistance(node, newNodePath[j - 1]);
				}
			}

			// if (i == first) {
			// if (second == oldNodes.length - 1) {
			// // newNodePath[j].distanceToNext = 0;
			// distance += 0;
			// } else {
			// // newNodePath[j].distanceToNext =
			// // (oldPath.calculateDistanceBetweenNodes(oldNodes[i],
			// // oldNodes[second + 1]));
			//
			// distance += distances.getDistance(oldNodes[i], oldNodes[second +
			// 1]);
			//
			// // distance += distances[]
			// }
			// } else if (i == 0) {
			// // newNodePath[j].distanceToNext = i;
			// distance += 0;
			// } else {
			// // newNodePath[j].distanceToNext = (oldNodes[i -
			// // 1].distanceToNext);
			// distance += distances.getDistance(oldNodes[i], oldNodes[i + 1]);
			// }
			j++;
		}

		for (int i = second + 1; i < oldNodes.length; i++) {
			newNodePath[i] = oldNodes[i];

			// if (i != oldNodes.length - 1) {
			// distance += distances[(newNodePath[i].number)][(newNodePath[i -
			// 1].number)];
			distance += distances.getDistance(newNodePath[i], newNodePath[i - 1]);
			// }
		}

		Path newPath = new Path(newNodePath);
		newPath.distance = distance;

		return newPath;
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