import java.io.BufferedReader;

public class InputReader {

	public Node[] readIndata(BufferedReader in) {
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

				nodes[i] = new Node(x, y, i);

				// i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nodes;
	}

}
