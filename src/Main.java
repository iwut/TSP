import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	private static final boolean VISUALIZE = true;
	public static void main(String[] args) {
		new Main();
	}

	public Main() {

		InputReader in = new InputReader();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        Visualizer visual = null;
        
		Node[] nodes = in.readIndata(br);
		if(VISUALIZE)
			visual = Visualizer.createAndShowGUI(nodes);
		
		Path path = new Path(nodes);

		Algorithm algorithm = new GreedyAlgorithm();

		path = algorithm.findPath(path);
		if(VISUALIZE)
			//System.out.print(visual);
			visual.updatePath(path.getNodes());

		printPath(path);
	}

	private void printPath(Path path) {
		Node[] nodes = path.getNodes();
		for (int i = 0; i < nodes.length; i++) {
			System.out.println(nodes[i].getNumber());
		}
	}

}
