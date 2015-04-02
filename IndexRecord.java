/*
This class has 2 fields: a Key (String) and a RecordNumber (int); This class includes appropriate
constructors, etc. The key depends on which field and the recordNumber is the reference to the big structure
*/

public class IndexRecord
{
	private String key;  //String value
	private int recordNumber;  //where in Big Array

	
//parameterized constructor
	public IndexRecord(String key, int recordNumber)
	{
		this.key = key;
		this.recordNumber = recordNumber;
	}
		
//setter for key
	public void setKey(String key)
	{
		this.key = key;
	}
	
//getter for key
	public String getKey()
	{
		return key;
	}
	
//setter for recordNumber
	public void setRecordNumber(int recordNumber)
	{
		this.recordNumber = recordNumber;
	}
	
//getter for recordNumber
	public int getRecordNumber()
	{
		return recordNumber;
	}
	
//toString 
	public String toString()
	{
		return "Key: " + key + "  Record Number: " + recordNumber;
	}
	
	
//compareTo method
//returns (-1) if this<other, (0) if value is equal and (1) if this>other
//puts string to all lower to compare
	public int compareTo(String key)
	{
		return (this.key.toLowerCase().compareTo(key.toLowerCase()));
	}
		
	
}//end IndexRecord class
