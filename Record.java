/**
  * @param Name: Frank Dong
  * @param Date: Nov 17, 2016
  * @param Purpose: Represents a record in the database
  */
public class Record {
	
	private Key keyValue;
	private String dataValue;
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: Constructor for the Record class, setting the values of the key and data
	 * @param Input: Key k, String data
	 * @param Output: None
	 */
	public Record(Key k, String data){
		this.keyValue = k;
		this.dataValue = data;
	}
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: Will return the value of key
	 * @param Input: None
	 * @param Output: this.keyValue
	 */
	public Key getKey()
	{
		return this.keyValue;
	}
	
	/**
	 * @param Name: Frank Dong
	 * @param Date: Nov 17, 2016
	 * @param Purpose: Will return the data stored in the record class
	 * @param Input: None
	 * @param Output: this.dataValue
	 */
	public String getData()
	{
		return this.dataValue;
	}
}
