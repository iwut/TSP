import java.util.Random;

public class ThreeOpt {
	Visualizer v;
	boolean b;
	
	public ThreeOpt(Visualizer v, boolean b){
		this.v = v;
		this.b = b;
	}
	public Path optimizePath(Path path, DistanceHolder distances) {

		// int[][] distances = distanceHolder.distances;

		Node[] nodes = path.nodes;

		Path newPath = null;

		int skipped = 0;

		double bestDistance = 10000000; //path.distance;
		double newDistance = Integer.MAX_VALUE;
		double diff = 10000;
//		outerloop: do {
			// printPathToSystemErr(path);
		boolean search = true;
		outerloop: while(search){
			//diff = 100000;
			search = false;
			nodes = path.nodes;
			for (int i = 0; i+1 < nodes.length; i++) {
				for (int j = i+1; (j+1 < nodes.length)&&(j!=i); j++) {
					for (int k = 0; (k < 1); k++) {
						for (int m = 0; (m+1 < nodes.length)&&(m!=j)&&(m!=i); m++) {
							//i = starting node one
							//j = starting node two
							//k = 1 if the first new connection should be used, 2 if the second new connection
							//m = starting node three

							double distance = 0;
							
							distance += distances.getDistance(nodes[i+1-k], nodes[j+1-k]);
							distance += distances.getDistance(nodes[j+k], nodes[m+1]);
							distance += distances.getDistance(nodes[i+k], nodes[m]);
							
							double distance2 = 0;
							distance2 += distances.getDistance(nodes[i], nodes[i+1]);
							distance2 += distances.getDistance(nodes[j], nodes[j+1]);
							distance2 += distances.getDistance(nodes[m], nodes[m+1]);
							
							
							if(distance<distance2){
								
								search = true;
								newPath = twoOptSwap(path, i, j, distances);
								newPath = twoOptSwap(newPath, i+k, m, distances);
								path = newPath;
								
								
//								make(i, j)
//								make(i+1, j+1)
//								reverse(i+1, j)
//								
//								make(i+k, m)
//								make(j+k, m+1)
//								reverse(m, j+1)
								
								if(b){
//									try {
//										Thread.sleep(1);
										v.updatePath(path);
//									} catch (InterruptedException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
								}
								System.out.println("Optimized path");
								//continue outerloop;
							}else{
								skipped++;
								continue;
							}
							
							
//							newDistance = newPath.distance;
//							diff = newDistance - bestDistance;
//							if (newDistance < bestDistance) {
//
//								bestDistance = newDistance;
//								path = newPath;
//								
//							}
							
						}
					}
				}
			}
			}
		
			System.err.println("Skipped: " + skipped);
			
			return path;
			
//			for (int i = 0; i < nodes.length; i++) {
//				for (int k = i + 1; k < nodes.length; k++) {
//					int j = i - 1;
//					int l = k + 1;
//					if (k == nodes.length - 1 || i == 0) {
//						// continue as normal
//
//					} else if (!(distances.getDistance(nodes[i], nodes[l]) < distances.getDistance(nodes[i], nodes[j]) || distances
//							.getDistance(nodes[k], nodes[j]) < distances.getDistance(nodes[k], nodes[l]))) {
//
//						skipped++;
//						continue;
//
//					}
//					newPath = twoOptSwap(path, i, k, distances);
//					newDistance = newPath.distance;
//					diff = newDistance - bestDistance;
//					if (newDistance < bestDistance) {
//
//						bestDistance = newDistance;
//						path = newPath;
//						nodes = newPath.nodes;
//						continue outerloop;
//					}
//				}
//			}
//		} while (diff < 0);

		
		// System.err.println();
//		return path;
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