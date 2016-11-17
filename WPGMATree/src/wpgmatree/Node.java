
package wpgmatree;

/**
 * 
 * @author Patrick Solis
 * Prof. Phillip Heller
 * CS123A
 * Bioinformatics Project: BReastCAncer
 */

public class Node {
	String name;
	float score;
	Node right;
	Node parent;
	Node left;
	int level;
	
	/**
	 * Creates new Node
	 * @param name Name of node
	 * @param score Score of node
	 */
	public Node(String name, float score)
	{
		this.name = name;
		this.score = score;
		left = null;
		right = null;
		level = 1;
	}
	
	/**
	 * Sets score for node. If Node has any child nodes
	 * it reduces current score from child score
	 * @param score new score for node
	 */
	public void setScore(float score)
	{
		if(left != null || right != null)
		{
			this.score = score - left.score;
		}
		else
		{
			this.score = score;
		}
	}
}
