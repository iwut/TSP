import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	// Visualizer visual;
	private boolean	VISUALIZE	= false;

	public static void main(String[] args) {
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

		long starttime = System.currentTimeMillis();

		Kattio io;

		io = new Kattio(System.in);

		InputReader in = new InputReader();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Path path = in.readIndata(br);

		DistanceHolder distanceHolder = new DistanceHolder();

		distanceHolder.calculateDistances(path);

		// Node[] nodes = in.readIndata(br);

		// if(System.currentTimeMillis()-starttime>500){
		// VISUALIZE = true;
		// }

		// if (VISUALIZE) {
		// visual = Visualizer.createAndShowGUI(path);
		// }

		// Path path = new Path(nodes);

		Algorithm algorithm = new GreedyAlgorithm();

		path = algorithm.findPath(path, distanceHolder);

		TwoOpt twoOpt = new TwoOpt();

		path = twoOpt.optimizePath2(path, distanceHolder.distances);

		// if (VISUALIZE) {
		// // System.out.print(visual);
		// visual.updatePath(path);
		// }

		printPath(path);
	}

	private void printPath(Path path) {
		Node[] nodes = path.getNodes();
		for (int i = 0; i < nodes.length; i++) {
			System.out.println(nodes[i].getNumber());
		}
	}

}