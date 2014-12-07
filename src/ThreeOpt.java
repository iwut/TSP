import java.util.Random;

public class ThreeOpt {
	Visualizer v;
	boolean bool;
	TwoOpt2 twoOpt;
	
	public ThreeOpt(Visualizer v, boolean b, TwoOpt2 twoOpt){
		this.v = v;
		this.bool = b;
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
			for (int i = 0; i< nodes.length; i++) {
				for (int j = 0; j< nodes.length; j++) {
					for (int k = 0; (k < 2); k++) {
						for (int m = 0; m< nodes.length; m++) {
							//i = starting node one
							//j = starting node two
							//k = 1 if the first new connection should be used, 2 if the second new connection
							//m = starting node three
							if(i!=j&&i!=m&&j!=m){
								
								
								int avar,bvar,cvar;
								if(i<j){
									avar = i;
									bvar = j;
									cvar = m;
								}else{
									avar = j;
									bvar = i;
									cvar = m;
								}
								
								
								int aLeft = (avar-1+nodes.length)%nodes.length;
								int aRight = (avar+1+nodes.length)%nodes.length;
								
								int bLeft = (bvar-1+nodes.length)%nodes.length;
								int bRight = (bvar+1)%nodes.length;
								
								int cLeft = (cvar-1+nodes.length)%nodes.length;
								int cRight = (cvar+1+nodes.length)%nodes.length;
								
								int temp = 0;
								
								
								int a = avar;
								int b = bvar;
								int c = cvar;
								
								int a1 = aLeft;
								int a2 = aRight;
								
								int b1 = bLeft;
								int b2 = bRight;
								
								int c1 = cLeft;
								int c2 = cRight;
								
								int interval = j-i;
								int start = i;
//								int cFlipped = start+(interval-c);


								int distance = 0;
								
								int distance2 = 0;
								System.out.println("Starting...");
								if(a>c){
									if(k==0){
										System.out.println("Case1");
										distance += distances[nodes[c1].number][nodes[cvar].number];
										distance += distances[nodes[a1].number][nodes[avar].number];
										distance += distances[nodes[bvar].number][nodes[b2].number];
										
										b = avar;
										a = bvar;
										b1 = aRight;
										a2 = bLeft;
										
										temp = a;
										a = c;
										c = temp;
										
										temp = a2;
										a2 = c2;
										c2 = temp;
										
										b1 = cvar+((bvar-cvar)-b1);//invertedb1;
										b = cvar+((bvar-cvar)-b);//invertedb;
										a1 = cvar+((bvar-cvar)-a1);//inverteda1;
										
										distance2 += distances[nodes[a1].number][nodes[a].number];
										distance2 += distances[nodes[b2].number][nodes[c].number];
										distance2 += distances[nodes[c1].number][nodes[a].number];
										
									}
									else{
										//IMPOSSIBLE, SINCE IT WOULD YIELD 4 NEW EDGES
//										distance += distances[nodes[c1].number][nodes[cvar].number];
//										distance += distances[nodes[b1].number][nodes[bvar].number];
//										distance += distances[nodes[a1].number][nodes[avar].number];
//										
//										b = avar;
//										b1 = aRight;
//										a2 = bLeft;
//										a = bvar;
//										
//										temp = c;
//										c = b;
//										b = temp;
//										
//										temp = a1;
//										a1 = c2;
//										c2 = temp;
//										
//										distance2 += distances[nodes[c1].number][nodes[b].number];
//										distance2 += distances[nodes[b].number][nodes[a1].number];
//										distance2 += distances[nodes[c].number][nodes[b2].number];
									}
								}else if(b>c){
//									c = a+((b-a)-c); //flip c between a and b
//									cLeft = a+((b-a)-cLeft);
//									cRight = a+((b-a)-cRight);
									
									if(k==0){
										System.out.println("Case2");
										distance += distances[nodes[a1].number][nodes[avar].number];
										distance += distances[nodes[b2].number][nodes[bvar].number];
										distance += distances[nodes[c2].number][nodes[cvar].number];
										//a1 unchanged
										b = avar;
										b1 = aRight;
										c2 = avar+((bvar-avar)-c2);//invertedc2;
										c = avar+((bvar-avar)-c);//invertedc;
										c1 = avar+((bvar-avar)-c1);//invertedc1;
										a2 = bLeft;
										a = bvar;
										
										temp = a;
										a = c;
										c = a;
										
										temp = a2;
										a2 = c1;
										c1 = temp;
										//b2 unchanged
										distance2 += distances[nodes[a1].number][nodes[a].number];
										distance2 += distances[nodes[b2].number][nodes[b].number];
										distance2 += distances[nodes[c2].number][nodes[c].number];
									}
									else{
										System.out.println("Case3");
										distance += distances[nodes[a1].number][nodes[avar].number];
										distance += distances[nodes[b2].number][nodes[bvar].number];
										distance += distances[nodes[c1].number][nodes[cvar].number];
										
										b = avar;
										b1 = aRight;
										c2 = avar+((bvar-avar)-c2);//invertedc2;
										c = avar+((bvar-avar)-c);//invertedc;
										c1 = avar+((bvar-avar)-c1);//invertedc1;
										a2 = bLeft;
										a = bvar;
										
										temp = c;
										c = b;
										b = temp;
										
										temp = c2;
										c2 = b1;
										b1 = temp;
										
										distance2 += distances[nodes[a1].number][nodes[a].number];
										distance2 += distances[nodes[b2].number][nodes[b].number];
										distance2 += distances[nodes[c1].number][nodes[c].number];
									}
								} else{
									if(k==0){
										//IMPOSSIBLE, SINCE IT WOULD YIELD 4 NEW EDGES
//										b = avar;
//										b1 = aRight;
//										a2 = bLeft;
//										a = bvar;
//										
//										temp = c;
//										c = a;
//										a = temp;
//										
//										temp = c1;
//										c1 = b2;
//										b2 = temp;
										
									}
									else{//A-B, B-C
										System.out.println("Case4");
										
										System.out.println("1Indexes: a:" + a + ",  b:" + b + ",  c:" + c);
										
										int anum = nodes[avar].number;
										int bnum = nodes[bvar].number;
										int cnum = nodes[cvar].number;
										
										distance += distances[nodes[a1].number][nodes[avar].number];
										distance += distances[nodes[b2].number][nodes[bvar].number];
										distance += distances[nodes[c2].number][nodes[cvar].number];
										
										//a1 unchanged
										b = avar;
										b1 = aRight;
										a2 = bLeft;
										a = bvar;
										
										temp = c;
										c = b;
										b = temp;
										
										temp = c1;
										c1 = b1;
										b1 = temp;
										
										b2 = avar+((cvar-avar)-b2);//invertedb2;
										a = avar+((cvar-avar)-a);//inverteda;
										a2 = avar+((cvar-avar)-a2);//inverteda2;
										
										distance2 += distances[nodes[a1].number][nodes[a].number];
										distance2 += distances[nodes[b2].number][nodes[b].number];
										distance2 += distances[nodes[c2].number][nodes[c].number];
										

										int a2num = nodes[a].number;
										int b2num = nodes[b].number;
										int c2num = nodes[c].number;
										
										System.out.println("2Indexes: a:" + a + ",  b:" + b + ",  c:" + c);
										
										if(distance>distance2){
											System.out.print("");
										}
									}
								}
								
								if(distance>distance2){
									System.out.println("Swapping A:" + path.nodes[avar].number + ", B:" + path.nodes[bvar].number + ", C:" + path.nodes[cvar].number);
									search = true;
									int originalDist = path.distance;
									twoOpt.forceSwap(path, avar, bvar, distances);
									
									System.out.println("Swapping A:" + path.getNodes()[avar].number + ", B:" + path.getNodes()[bvar].number + ", C:" + path.getNodes()[cvar].number);
									
									if(k==0){
										twoOpt.forceSwap(path, a, b, distances);
									}else{
										twoOpt.forceSwap(path, b, c, distances);
									}
									int doneDist = path.distance;
									System.out.println("Swapping A:" + path.getNodes()[avar].number + ", B:" + path.getNodes()[bvar].number + ", C:" + path.getNodes()[cvar].number);
									
									
									System.out.println("Calculated diff: " + (distance-distance2) + "  Actual diff:" + (originalDist-doneDist));
	//								path = newPath;
									
									
	//								make(i, j)
	//								make(i+1, j+1)
	//								reverse(i+1, j)
	//								
	//								make(i+k, m)
	//								make(j+k, m+1)
	//								reverse(m, j+1)
									
									if(bool){
										v.updatePath(path);
	//									try {
	//										//Thread.sleep(1);
	//										
	//									} catch (InterruptedException e) {
	//										// TODO Auto-generated catch block
	//										e.printStackTrace();
	//									}
									}
									continue outerloop;
								}else{
									skipped++;
									continue;
								}
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