import java.util.Arrays;

public class TwoOpt2 {
	
	final int SWAPS = 10;
	
	private final int ITERATIONS = 600;
	private final long		RUN_TIME_IN_NANOSECONDS	= 1500000000;

	public void run(Path path, int[][] distances) {
		int[] nodes = path.intNodes;

		int nodesLength = nodes.length;

		int minChange;
		int change = 0;
		int minI = 0;
		int minJ = 0;
		int afterSecond;
		boolean bool = false;
		int first;
		int second;
		int iterations = 0;
		do {
			iterations++;
			minChange = 0;
			bool = false;

			for (int i = 0; i < nodesLength - 2; i++) {
				for (int j = i + 2; j < nodesLength; j++) {
					afterSecond = (j + 1) % nodesLength;

					change = distances[nodes[i]][nodes[j]] + distances[nodes[i + 1]][nodes[afterSecond]]
							- distances[nodes[i]][nodes[i + 1]] - distances[nodes[j]][nodes[afterSecond]];
					if (minChange > change) {
						minChange = change;
						minI = i;
						minJ = j;
						bool = true;
					}
				}
			}
			// apply move
			if (bool) {
				redrawPath(path, minI, minJ);
				path.distance += change;
			} else {
				for (int i = 0; i < SWAPS; i++) {
					first = 0;
//					second = nodes.length - 1;
					first = (nodes.length / 5);
					second = (nodes.length / 2);
					
					System.err.println("For i helvedte mads");

					afterSecond = (second + 1) % nodesLength;

					try {
					redrawPath(path, first, second);

					path.distance += distances[nodes[first]][nodes[second]]
							+ distances[nodes[first + 1]][nodes[afterSecond]]
							- distances[nodes[first]][nodes[first + 1]] - distances[nodes[second]][nodes[afterSecond]];
					} catch (ArrayIndexOutOfBoundsException e) {
						// do nothing
					}
				}
			}
		} while ((System.nanoTime() - Main.startTime) < RUN_TIME_IN_NANOSECONDS);
//		} while (iterations < ITERATIONS);
	}
	
	public void forceSwap(Path path, int first, int second, int[][] distances) {
		int[] nodes = path.intNodes;
		int nodesLength = nodes.length;
		
		int afterSecond = (second + 1) % nodesLength;

		try {
		redrawPath(path, first, second);

		path.distance += distances[nodes[first]][nodes[second]]
				+ distances[nodes[first + 1]][nodes[afterSecond]]
				- distances[nodes[first]][nodes[first + 1]] - distances[nodes[second]][nodes[afterSecond]];
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
	}

	
	
	private void redrawPath(Path path, int minI, int minJ) {
		int[] nodes = path.intNodes;

		int[] test = Arrays.copyOfRange(nodes, minI + 1, minJ + 1);

		int j = minI + 1;
		for (int i = test.length - 1; i >= 0; i--) {
			nodes[j] = test[i];
			j++;
		}
	}



}
