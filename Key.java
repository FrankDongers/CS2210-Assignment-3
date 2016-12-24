/**
  * @param Name: Frank Dong
  * @param Date: Nov 17, 2016
  * @param Purpose: Represents the key attribute of a record in the database
  */
public class Key {
	private String wordValue;
	private int typeValue;
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: Constructor for the key class, setting the values of word and type
	 * @param Input: String word, int type
	 * @param Output: None
	 */
	public Key(String word, int type){
		this.wordValue = word;
		this.typeValue = type;
	}
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: Returns the value of word
	 * @param Input: None
	 * @param Output: this.wordValue
	 */
	public String getWord(){
		return this.wordValue;
	}
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: Returns the value of type
	 * @param Input: None
	 * @param Output: this.typeValue
	 */
	public int getType(){
		return this.typeValue;
	}
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: Compares a key value with the current key value
	 * @param Input: Key k
	 * @param Output: 0 if key is equal to k, -1 if key is smaller than k and 1 otherwise
	 */
	public int compareTo(Key k){
		if (this.wordValue.equals(k.wordValue) && this.typeValue == k.typeValue){
			return 0;
		}
		else if (this.wordValue.compareTo(k.wordValue) < 0 || (this.wordValue.equals(k.wordValue) && this.typeValue < k.typeValue)){
			return -1;
		}
		else
		{
			return 1;
		}
	}
}
