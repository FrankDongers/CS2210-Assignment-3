
/**
* @param Name: Frank Dong
* @param Date: Nov 17, 2016
* @param Purpose: Node class to represent the nodes of the binary search tree
*/
public class Node {
	private Node left;
	private Node right;
	private Node parent;
	private Record data;
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: Serves as a construction for the node class, setting the values of left, right, parent and the data
	 * @param Input: Node givenLeft, Node givenRight, Node givenParent, Record givenData
	 * @param Output: None
	 */
	public Node(Node givenLeft, Node givenRight, Node givenParent, Record givenData){
		this.left = givenLeft;
		this.right = givenRight;
		this.parent = givenParent;
		this.data = givenData;
	}
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: returns the left child of node
	 * @param Input: None
	 * @param Output: None
	 */
	public Node getLeft(){
		return this.left;
	}
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: returns the right child of node
	 * @param Input: None
	 * @param Output: None
	 */
	public Node getRight(){
		return this.right;
	}
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: returns the parent of node
	 * @param Input: None
	 * @param Output: None
	 */
	public Node getParent(){
		return this.parent;
	}
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: returns the Record data stored within node
	 * @param Input: None
	 * @param Output: None
	 */
	public Record getData(){
		return this.data;
	}
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: sets the left node value
	 * @param Input: Node node
	 * @param Output: None
	 */
	public void setLeft(Node node){
		this.left = node;
	}
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: sets the right node value
	 * @param Input: Node node
	 * @param Output: None
	 */
	public void setRight(Node node){
		this.right = node;
	}
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: sets the parent node value
	 * @param Input: Node node
	 * @param Output: None
	 */
	public void setParent(Node node){
		this.parent = node;
	}
	
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: sets the data value
	 * @param Input: Record data
	 * @param Output: None
	 */
	public void setData(Record data){
		this.data = data;
	}
}
