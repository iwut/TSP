import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		new Main();
	}

	public Main() {

		InputReader in = new InputReader();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Node[] nodes = in.readIndata(br);

		Path path = new Path(nodes);

		Algorithm algorithm = new GreedyAlgorithm();

		path = algorithm.findPath(path);

		TwoOpt twoOpt = new TwoOpt();

		path = twoOpt.optimizePath(path);

		printPath(path);
	}

	private void printPath(Path path) {
		Node[] nodes = path.getNodes();
		for (int i = 0; i < nodes.length; i++) {
			System.out.println(nodes[i].getNumber());
		}
	}

}
