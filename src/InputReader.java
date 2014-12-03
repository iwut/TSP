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
		int x;
		int y;
		// int i = 0;

		try {
			for (int i = 0; i < nodeAmount; i++) {
				// while (in.ready()) {
				line = in.readLine();
				coords = line.split(" ");

				x = (int) Double.parseDouble(coords[0]);
				y = (int) Double.parseDouble(coords[1]);

				nodes[i] = new Node(x, y, i, -1);

				// i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Path path = new Path(nodes);

		return path;
	}

	// public NodePath readIndata3(BufferedReader in) {
	//
	// int nodeAmount = 0;
	//
	// try {
	// nodeAmount = Integer.parseInt(in.readLine());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// int[] path = new int[nodeAmount];
	//
	// String line;
	// String[] coords;
	// int x;
	// int y;
	// // int i = 0;
	//
	// try {
	// for (int i = 0; i < nodeAmount; i++) {
	// // while (in.ready()) {
	// line = in.readLine();
	// coords = line.split(" ");
	//
	// x = (int) Double.parseDouble(coords[0]);
	// y = (int) Double.parseDouble(coords[1]);
	//
	// path[i] = i
	//
	// // i++;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// Path path = new Path(nodes);
	//
	// return path;
	// }

	public Path readIndata2(Kattio in) {

		int nodeAmount = in.getInt();

		Node[] nodes = new Node[nodeAmount];

		int x;
		int y;

		int i = 0;
		while (in.hasMoreTokens()) {
			if (i == 999) {
				String h = "";
			}
			x = (int) in.getDouble();
			y = (int) in.getDouble();
			nodes[i] = new Node(x, y, i, -1);
			i++;

		}

		Path path = new Path(nodes);

		return path;
	}
}