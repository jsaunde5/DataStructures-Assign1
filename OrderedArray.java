
//this class is comprised of Index Records. There are three fields of Ordered Arrays. Each
//ordered array keeps the data of index records in order using compareTo() in IndexRecord.

public class OrderedArray 
{
	private int numElems;
	private IndexRecord [] data;
	private int field;  //Fname, Lname, ID

//constructor
	public OrderedArray(int size, int field)
	{
		numElems = 0;
		data = new IndexRecord [size];  //maxSize
		this.field = field;
	}
	
//insert into ordered array from dataStructureRecord. insert into indexPosition of big array
	public void insert (DataStructureRecord dataStructRecord, int indexPosition)
	{
		int j;
		String key = "";
		switch (this.field)  //get the key for appropriate field
		{
			case 1:
				key = dataStructRecord.getFirstName();
				break;
			case 2:
				key = dataStructRecord.getLastName();
				break;
			case 3:
				key = dataStructRecord.getID();
				break;
			default:
				break;
		}//end switch
	//insert in order
		for(j = numElems-1; j>=0; j--)
		{
			if(data[j].compareTo(key) < 0) //use compareTo(string)
				break;
			else
				data[j+1] = data[j];
		}
		data [j+1] = new IndexRecord(key, indexPosition);
		numElems++;
	}
	
//delete from ordArray (does each field separately)
	public void deleteRec(OrderedArray delRec, int index) 
	{
		int where = -10;  //initialize
		int j;
		int numElems = getElems();  //of ordered array
		where = linSearch(index, numElems, data);  //where in OrderedArray
		
		for (j = where; j <= numElems-2; j++)  //move up each index after where
			data[j] = data[j+1];
		
		numElems--;  //decrement numElems and update for OrderedArray
		setNelems(numElems);
	}
	
//linear search. Only used for deleteRec
//search for recordNumber (index in bigArray)
	public int linSearch(int keyVal, int nElems, IndexRecord[] data)
	{
		int j;
		for(j = nElems-1; j>=0; j--)  //nElems of ordered array
		{
			if (data[j].getRecordNumber() == keyVal)
				break;
		}
		return j;
	}

//return array of IndexRecord
	public IndexRecord[] getData() 
	{
		return this.data;
	}
	
//return numElems of ordered array
	public int getElems()
	{
		return this.numElems;
	}
	
//setNelems of OrderedArray
	public void setNelems(int numElems)
	{
		this.numElems = numElems;
	}
	
			
//printArray(ascending/decending, big array)
//iterating through index numbers of this field's OrderedArray
//only print from big array of those in this field's OrderedArray (skips if not in ordered array)
	public void printArray(int order, DataStructure bigData)
	{
		int nElems = this.getElems();  //get number of elems in this field of OrdredArray
		if (order == 1) //ascending
		{
			for (int i = 0; i < nElems; i ++)
			{   
				DataStructureRecord dsr = bigData.GetRecord(data[i].getRecordNumber());
				System.out.println(dsr.toString());	 //print single record of all fields	
			}
		}
		else //descending
		{
			for (int i = nElems-1; i >= 0; i--)
			{
				DataStructureRecord dsr = bigData.GetRecord(data[i].getRecordNumber());
				System.out.println(dsr.toString());	
			}
		}
	}
			
			
			
			
}
