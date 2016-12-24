/**
* @param Name: Frank Dong
* @param Date: Nov 18, 2016
* @param Purpose: A class which implements the database through an ordered dictionary.
*/
public class OrderedDictionary implements OrderedDictionaryADT{
	    
	private Node root = new Node(null, null, null, null);
	
	/**
	* @param Name: Frank Dong
	* @param Date: Nov 18, 2016
	* @param Purpose: Acts as a helper method, searching for values and returning it if found
	* 				  or null if value is not found
	* @param Input: key k, Node current
	* @param Output: Node current
	*/
	/* Acts as a helper method to search for dictionary entries*/
	private Node TreeSearch(Key k, Node current)
	{
		//If current node is equal to null return the current node
		if (current.getData() == null)
		{
			return current;
		}
		
		//If k is less than current value, TreeSearch of left node
		if (k.compareTo(current.getData().getKey()) == -1)
		{
			return TreeSearch(k, current.getLeft());
		}
		
		//If k equal to current value, return current value
		else if (k.compareTo(current.getData().getKey()) == 0)
		{
			return current;
		}
		
		//Else TreeSearch of right node
		else
		{
			return TreeSearch(k, current.getRight());
		}
	}
	
	/**
	* @param Name: Frank Dong
	* @param Date: Nov 17, 2016
	* @param Purpose: Determines the in order traversal successor of a node (should be passed a right node value
	* @param Input: Node current
	* @param Output: Null or current
	*/
    private Node inOrderSuccessor (Node current)
    {
    	//If root is null, return null
    	if (this.root.getData() == null)
    	{
    		return null;
    	}
    	
    	else
    	{
    		//find the leftmost value and return it
	    	 while (current.getLeft().getData() != null)
	    	 {
	    		 current = current.getLeft();
	    	 }
	    	 
	    	 return current;
    	}
    }
    

	/**
	* @param Name: Frank Dong
	* @param Date: Nov 17, 2016
	* @param Purpose: Returns the Record object with key k, or returns null if such a record is
					  not in the dictionary.
	* @param Input: Key k
	* @param Output: null or record object with key k
	*/
	public Record find (Key k)
	{
		//Call search tree function with Key k and our root value
		Node treeSearchValue = TreeSearch(k, this.root);
		
		//If value has been found, return the found value
		if (treeSearchValue.getData() != null)
		{
			return (treeSearchValue.getData());
		}
		
		//Else return null (value has not been found)
		else
		{
			return (null);
		}
	}
	
	/**
	* @param Name: Frank Dong
	* @param Date: Nov 17, 2016
	* @param Purpose: 	Inserts r into the ordered dictionary. It throws a DictionaryException 
    					if a record with the same key as r is already in the dictionary.
	* @param Input: Record r
	* @param Output: None
	*/
	public void insert (Record r) throws DictionaryException
	{
		// If tree is empty (root is null)
		if (this.root.getData() == null)
		{
			//Create left and right leaf nodes and set parent equal to null
			Node Leftleaf = new Node(null, null, this.root, null);
			Node Rightleaf = new Node(null, null, this.root, null);
			Node rootParent = null;
			
			//Set root information
			this.root.setData(r);
			this.root.setLeft(Leftleaf);
			this.root.setRight(Rightleaf);
			this.root.setParent(rootParent);
		}
		else
		{
			//Call search tree function to determine where node is or if node is within tree
			Node current = TreeSearch(r.getKey(), this.root);
			
			//Declare leaf values with parent equalled to current
			Node Leftleaf = new Node(null, null, current, null);
			Node Rightleaf = new Node(null, null, current, null);
			
			//If current value is already in tree, throw exception
			if (current.getData() != null && current.getData().getKey().compareTo(r.getKey()) == 0)
			{
				throw new DictionaryException("Error, key k is already in the tree");
			}
			
			//Else insert the value (set r data to currents data, and left and right nodes of current
			//equal to leaf)
			else
			{
				current.setData(r);
				current.setLeft(Leftleaf);
				current.setRight(Rightleaf);
			}
		}
	}
		
	/**
	* @param Name: Frank Dong
	* @param Date: Nov 17, 2016
	* @param Purpose: Removes the record with Key k from the dictionary. It throws a 
	* 				  DictionaryException if the record is not in the dictionary.
	* @param Input: Key k
	* @param Output: None
	*/
	public void remove (Key k) throws DictionaryException
	{
		//Search for value within tree
		Node value = TreeSearch(k, this.root);
		
		//if value is not found return null
		if (find(k) == null)
		{
			throw new DictionaryException("Error, record is not in the dictionary");
		}
		else
		{
			//If root is equal to the k
			if (this.root.getData().getKey() == k)
			{
				//If root has right data element
				if (this.root.getRight().getData() != null)
				{
					//Find successor of right node of root
					Node removeValue = inOrderSuccessor(this.root.getRight());
					
					//Set roots data into the successor of the right tree
					this.root.setData(removeValue.getData());
					Node leaf = new Node(null, null, removeValue.getParent(), null);
					
					//sets parent of successor of right tree to leaf
					removeValue.getParent().setLeft(leaf);
					
					//Set remove values parent to null
					removeValue.setParent(null);
				}
				
				//Else if left value is not null, shift up left value by 1
				else if (this.root.getLeft().getData() != null)
				{
					this.root = this.root.getLeft();
				}
				
				//Else if both children are leafs, set root to new node with null values
				else
				{
					this.root = new Node(null, null, null, null);
				}
				return;
			}
			Node parent = value.getParent();
			
			//If value does not have a left node and value is within tree
			if (value.getLeft().getData() == null && parent.getData() != null)
			{
				//If parent is smaller than value insert to right of parent
				if (parent.getData().getKey().compareTo(value.getData().getKey()) < 0)
				{
					value.setParent(null);
					parent.setRight(value.getRight());
				}
				
				//else insert to the left of parent
				else
				{
					value.setParent(null);
					parent.setLeft(value.getRight());
				}
			}
			
			//If value does not have a right node and value is within tree
			else if (value.getRight().getData() == null && parent.getData() != null)
			{
				//If parent is smaller than value insert to right of parent
				if (parent.getData().getKey().compareTo(value.getData().getKey()) < 0)
				{
					value.setParent(null);
					parent.setRight(value.getLeft());
				}
				
				//else insert to the left of parent
				else
				{
					value.setParent(null);
					parent.setLeft(value.getLeft());
				}
			}
			else
			{
				//Find the in order successor of the right node of value
				Node followingNode = inOrderSuccessor(value.getRight());
				
				//Set values data to following nodes data
				value.setData(followingNode.getData());
				
				//remove following node
				remove(followingNode.getData().getKey());
			}
		}
	}

	
	/**
	* @param Name: Frank Dong
	* @param Date: Nov 17, 2016
	* @param Purpose:  Returns the successor of k (the record from the ordered dictionary 
	*				   with smallest key larger than k); it returns null if the given key has
	* 				   no successor. The given key DOES NOT need to be in the dictionary.
	* @param Input: Key k
	* @param Output: returns a record value of the successor or null
	*/
	public Record successor (Key k)
	{
		boolean didInsert = false;
		
		//The tree is empty return null
		if (this.root.getRight().getData() == null)
		{
			return null;
		}
		
		//Search for key value in tree
		Node p = TreeSearch(k, this.root);
		
		//If key is the largest key return null
		if (p.getData() == largest())
		{
			return null;
		}
		
		//If key is not in the dictionary, insert it
		if (p.getData() == null)
		{
			Record insertKey = new Record(k, "");
			try {
				this.insert(insertKey);
			} catch (DictionaryException e) {
				e.printStackTrace();
			}
			didInsert = true;
		}

		if (p.getRight().getData() != null)
		{
			//Find the left most child on the right child of p
			p = inOrderSuccessor(p.getRight());
			
			//Remove the key if it was not in the dictionary
			if (didInsert == true)
			{
				try {
					remove(k);
				} catch (DictionaryException e) {
					e.printStackTrace();
				}
			}
			
			//Return the value of node p
			return p.getData();
		}
		else
		{
			Node parent = p.getParent();
			
			while (parent.getRight() == p)
			{
				p = parent;
				parent = p.getParent();
			}
			
			//Remove the inserted value if k was not in tree
			if (didInsert == true)
			{
				try {
					remove(k);
				} catch (DictionaryException e) {
					e.printStackTrace();
				}
			}
			
			//Return the parent's data value
			return parent.getData();
		}
	}

	/**
	* @param Name: Frank Dong
	* @param Date: Nov 17, 2016
	* @param Purpose: Returns the predecessor of k (the record from the ordered dictionary 
	       			  with largest key smaller than k; it returns null if the given key has 
	       			  no predecessor. The given key DOES NOT need to be in the dictionary.
	* @param Input: Key k
	* @param Output: returns a record value of the predecessor or null
	*/
	    public Record predecessor (Key k)
	    {
	    	boolean didInsert = false;
			
			//The tree is empty return null
			if (this.root.getRight().getData() == null)
			{
				return null;
			}
			
			//Search for key value in tree
			Node p = TreeSearch(k, this.root);
			
			//If key is the smallest key return null
			if (p.getData() == smallest())
			{
				return null;
			}
			
			//If key is not in the dictionary, insert it
			if (p.getData() == null)
			{
				Record insertKey = new Record(k, "");
				try {
					this.insert(insertKey);
				} catch (DictionaryException e) {
					e.printStackTrace();
				}
				didInsert = true;
			}

			if (p.getLeft().getData() != null)
			{
				//Find the left most child on the right child of p
				p = p.getLeft();
				
				//Remove the key if it was not in the dictionary
				if (didInsert == true)
				{
					try {
						remove(k);
					} catch (DictionaryException e) {
						e.printStackTrace();
					}
				}
				
				//Return the value of node p
				return p.getData();
			}
			else
			{
				Node parent = p.getParent();
				
				//Check to see while node is left child of the parent loop, else we have found the predecessor
				while (parent.getLeft() == p)
				{
					p = parent;
					parent = p.getParent();
				}
				
				//Remove the inserted value if k was not in tree
				if (didInsert == true)
				{
					try {
						remove(k);
					} catch (DictionaryException e) {
						e.printStackTrace();
					}
				}
				
				//Return the parent's data value
				return parent.getData();
			}
	    }

		/**
		* @param Name: Frank Dong
		* @param Date: Nov 17, 2016
		* @param Purpose: Returns the record with smallest key in the ordered dictionary. 
		* 				  Returns null if the dictionary is empty.
		* @param Input: None
		* @param Output: Record current (smallest value in tree) or null
		*/
	    public Record smallest ()
	    {
	    	//Check to see if tree is empty
	    	if (this.root.getData() == null)
	    	{
	    		return null;
	    	}
	    	else
	    	{
		    	 Node current = this.root;
		    	 
		    	 //iterate through tree until we find the leftmost value -
		    	 //which will be the smallest value in the tree
		    	 while (current.getLeft().getData() != null)
		    	 {
		    		 current = current.getLeft();
		    	 }
		    	 
		    	 return current.getData();
	    	}
	    }

		/**
		* @param Name: Frank Dong
		* @param Date: Nov 17, 2016
		* @param Purpose: Returns the record with largest key in the ordered dictionary. 
		* 				  Returns null if the dictionary is empty.
		* @param Input: None
		* @param Output: Record current (largest value in tree) or null
		*/
	    public Record largest ()
	    {
	    	//Check to see if tree is empty
	    	if (this.root.getData() == null)
	    	{
	    		return null;
	    	}
	    	else
	    	{
	    		Node current = this.root;
		    	
		    	 //iterate through tree until we find the rightmost value -
		    	 //which will be the largest value in the tree
		    	while (current.getRight().getData() != null)
		    	{
		    		current = current.getRight();
		    	}
		    	
		    	return current.getData();
	    	}
	    }
	}
