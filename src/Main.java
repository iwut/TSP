import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

	Visualizer visual;
	private boolean VISUALIZE = false;
	
	
	public static void main(String[] args) {
		new Main();
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
		
		//Node[] nodes = in.readIndata(br);
		
		if(System.currentTimeMillis()-starttime>500){
			VISUALIZE = true;
		}
		
		if(VISUALIZE)
			visual = Visualizer.createAndShowGUI(path);
		
		
		
		//Path path = new Path(nodes);


		//Algorithm algorithm = new GreedyAlgorithm();

		//path = algorithm.findPath(path, distanceHolder);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		visual.updatePath(path);
		ThreeOpt threeOpt = new ThreeOpt(visual, VISUALIZE);

		path = threeOpt.optimizePath(path, distanceHolder);

		if(VISUALIZE)
			//System.out.print(visual);
			visual.updatePath(path);

		printPath(path);
	}

	private void printPath(Path path) {
		Node[] nodes = path.getNodes();
		for (int i = 0; i < nodes.length; i++) {
			System.out.println(nodes[i].getNumber());
		}
	}

}