public interface Algorithm {

	/**
	 * Used to find a path in the given set of nodes.
	 * 
	 * @param nodes
	 *            set of nodes that the graph is made out of
	 * @return integer array consisting of node visit order
	 */
	public int[] findPath(Node[] nodes);
}
