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

		Algorithm algorithm = new GreedyAlgorithm();

		int[] path = algorithm.findPath(nodes);

		printPath(path);
	}

	private void printPath(int[] path) {
		for (int i = 0; i < path.length; i++) {
			System.out.println(path[i]);
		}
	}

}
