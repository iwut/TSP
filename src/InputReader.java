import java.io.BufferedReader;

public class InputReader {

	public Path readIndata(BufferedReader in) {

		int nodeAmount = 0;
		try {
			nodeAmount = Integer.parseInt(in.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Node[] nodes = new Node[nodeAmount];

		String line;
		String[] coords;
		double x;
		double y;
		// int i = 0;

		try {
			for (int i = 0; i < nodeAmount; i++) {
				// while (in.ready()) {
				line = in.readLine();
				coords = line.split(" ");

				x = Double.parseDouble(coords[0]);
				y = Double.parseDouble(coords[1]);

				nodes[i] = new Node(x, y, i, -1);

				// i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Path path = new Path(nodes);

		// for (int i = 0; i < nodes.length; i++) {
		// Node node = nodes[i];
		// double distance = 0;
		// if (i != nodeAmount - 1) {
		// distance = path.calculateDistanceBetweenNodes(node, nodes[i + 1]);
		// }
		// node.setDistanceToNext(distance);
		// }

		return path;
	}

	public Path readIndata2(Kattio in) {

		int nodeAmount = in.getInt();

		Node[] nodes = new Node[nodeAmount];

		double x;
		double y;

		int i = 0;
		while (in.hasMoreTokens()) {
			if (i == 999) {
				String h = "";
			}
			x = in.getDouble();
			y = in.getDouble();
			nodes[i] = new Node(x, y, i, -1);
			i++;
			
		}

		Path path = new Path(nodes);

		return path;
	}
}
