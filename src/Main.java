import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	// Visualizer visual;
	private boolean		VISUALIZE	= false;
	private Visualizer	visual;

	public static long	startTime;

	public static void main(String[] args) {
		startTime = System.nanoTime();
		new Main();
	}

	public class NodePath {
		public int[]	path;
		public int		distance;

		public NodePath(int[] path, int distance) {
			this.path = path;
			this.distance = distance;
		}
	}

	public Main() {

		// long starttime = System.currentTimeMillis();
		//
		// Kattio io;

		// io = new Kattio(System.in);

		InputReader in = new InputReader();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Path path = in.readIndata(br);

		DistanceHolder distanceHolder = new DistanceHolder();

		distanceHolder.calculateDistances(path);

		// Path path = in.readIndata(br);

		// if (System.currentTimeMillis() - starttime > 500) {
		// VISUALIZE = true;
		// }

		// if (VISUALIZE) {
		// visual = Visualizer.createAndShowGUI(path);
		// }

		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// Path path = new Path(nodes);

		GreedyAlgorithm algorithm = new GreedyAlgorithm();

		path = algorithm.findPath2(path, distanceHolder);

		TwoOpt twoOpt = new TwoOpt();

		path = twoOpt.optimizePath2(path, distanceHolder.distances);

		// if (VISUALIZE) {
		// visual = Visualizer.createAndShowGUI(path);
		// }

		// // path = algorithm.findPath(path, distanceHolder);
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// visual.updatePath(path);
		// ThreeOpt threeOpt = new ThreeOpt(visual, VISUALIZE, twoOpt);

		// path = threeOpt.optimizePath(path, distanceHolder.distances);

		// if (VISUALIZE) {
		// // System.out.print(visual);
		// visual.updatePath(path);
		// }

		printPath2(path);
	}

	private void printPath(Path path) {
		Node[] nodes = path.getNodes();
		for (int i = 0; i < nodes.length; i++) {
			System.out.println(nodes[i].getNumber());
		}
	}

	private void printPath2(Path path) {
		int[] nodes = path.intNodes;
		for (int i = 0; i < nodes.length; i++) {
			System.out.println(nodes[i]);
		}
	}

}