public class Node {

	public double	x;
	public double	y;
	public double	distanceToNext;

	public int		number;

	public Node(double x, double y, int number, double distanceToNext) {
		this.x = x;
		this.y = y;
		this.number = number;
		this.distanceToNext = distanceToNext;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getNumber() {
		return number;
	}

	public void setDistanceToNext(double distance) {
		this.distanceToNext = distance;
	}

	public double getDistanceToNext() {
		return distanceToNext;
	}

}