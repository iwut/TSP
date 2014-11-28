public class NodeHelper {

	public double calculateDistance(Node first, Node second) {
		double absX = Math.abs(first.getX() - second.getX());
		double absY = Math.abs(first.getY() - second.getY());

		return Math.sqrt(absX * absX + absY * absY);
	}

}
