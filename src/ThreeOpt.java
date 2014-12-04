import java.util.Random;

public class ThreeOpt {
	Visualizer v;
	boolean b;
	TwoOpt twoOpt;
	
	public ThreeOpt(Visualizer v, boolean b, TwoOpt twoOpt){
		this.v = v;
		this.b = b;
		this.twoOpt = twoOpt;
	}
	public Path optimizePath(Path path, int[][] distances) {

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
			System.err.println("New loop");
			//diff = 100000;
			search = false;
			nodes = path.nodes;
			for (int i = 0; i+1 < nodes.length; i++) {
				for (int j = i+1; (j+1 < nodes.length)&&(j!=i); j++) {
					for (int k = 0; (k <= 1); k++) {
						for (int m = 0; (m+1 < nodes.length)&&(m!=j)&&(m!=i); m++) {
							//i = starting node one
							//j = starting node two
							//k = 1 if the first new connection should be used, 2 if the second new connection
							//m = starting node three

							int distance = 0;
							
							distance += distances[nodes[i+1-k].number][nodes[j+1-k].number];
							distance += distances[nodes[j+k].number][nodes[m+1].number];
							distance += distances[nodes[i+k].number][nodes[m].number];

							
							int distance2 = 0;
							distance2 += distances[nodes[i].number][nodes[i+1].number];
							distance2 += distances[nodes[j].number][nodes[j+1].number];
							distance2 += distances[nodes[m].number][nodes[m+1].number];
							
							
							
							if(distance<distance2){
								
								search = true;
								twoOpt.forceSwap(path, i, j, distances);
								twoOpt.forceSwap(path, i+k, m, distances);
//								path = newPath;
								continue outerloop;
								
//								make(i, j)
//								make(i+1, j+1)
//								reverse(i+1, j)
//								
//								make(i+k, m)
//								make(j+k, m+1)
//								reverse(m, j+1)
								
//								if(b){
////									try {
////										Thread.sleep(1);
//										//v.updatePath(path);
////									} catch (InterruptedException e) {
////										// TODO Auto-generated catch block
////										e.printStackTrace();
////									}
//								}
							}else{
								skipped++;
								continue;
							}
							
							

							
						}
					}
				}
			}
			}
		
			System.err.println("Skipped: " + skipped);
			
			return path;
		
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