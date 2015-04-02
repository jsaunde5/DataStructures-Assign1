/*
this data structure is comprised of an array of DataStructureRecord. This structure is large enough to hold 100
records. We have an index for the ID, for the LastName and for the FirstName. Each index creates an Ordered
array for each field. This class also contains methods for organizing data structure.
*/

public class DataStructure 
{
	private DataStructureRecord [] data;
	private int nextRec;
	private int nElems;
	private OrderedArray FnameIndex;
	private OrderedArray LnameIndex;
	private OrderedArray IDindex;
	
//DataStructure constructor with size 100
	public DataStructure()
	{
		data = new DataStructureRecord [100];
		nextRec = 0;  
		nElems = 0;
		FnameIndex = new OrderedArray(100, 1);  //size 100, field type
		LnameIndex = new OrderedArray(100, 2);
		IDindex = new OrderedArray(100, 3);
	}
//get ordered array of each field
	public OrderedArray getFnameIndex()
	{
		return FnameIndex;
	}	
	public OrderedArray getLnameIndex()
	{
		return LnameIndex;
	}
	public OrderedArray getIDindex()
	{
		return IDindex;
	}
	
//get nElems of big array
	public int getNelems()
	{
		return nElems;
	}
//get record within big array at specific index
	public DataStructureRecord GetRecord(int index)
	{
		return this.data[index];
	}
	
//calling findID(), Return false if ID is not found.
	public boolean search(String tempID) 
	{
		return ((findID(tempID)) != -1); 
	}

//findID method. Returns -1 if ID is not found. Returns index of record in big array
//using Binary search to search ID
	public int findID(String keyVal) 
	{
		int lo = 0;
		int elems = IDindex.getElems();  //numElems from IDindex (not myStructure)
		int hi = elems-1;
		int mid;
		while(lo<=hi)
		{
			mid = (lo+hi)/2;
			if(IDindex.getData()[mid].getKey().equals(keyVal))  //data is IDindex[], key is ID
				return IDindex.getData()[mid].getRecordNumber();  //found it, return recordNumber (index of big array)
			if (IDindex.getData()[mid].compareTo(keyVal) > 0)
				hi = mid-1;
			else
				lo = lo+1;				
		}		
		return -1;  //not found
	}


//insert into big array
//check if deleteStack is empty. Insert in popped index from stack if available
	public void insert(String name1, String name2, String tempID, Stack deleteStack) 
	{
		if (deleteStack.isEmpty())
		{
			nextRec = nElems;	
			nElems++; //only add nElems if put at end of Big Array (for lastRecord)
		}
		else
		{
			nextRec = deleteStack.pop();  //put new record in deleted index
		}
	//create new dataStructureRecord	
		DataStructureRecord newRecord = new DataStructureRecord( name1,  name2,  tempID);
		FnameIndex.insert(newRecord, nextRec);  //(newDataStrcRec, reference to big array)
		LnameIndex.insert(newRecord, nextRec);
		IDindex.insert(newRecord, nextRec);
		data[nextRec] = newRecord;  //big Array
	}

//different listings: Ascending/Descending order by field type fName, lName, ID
//using printArray method in OrderedArray class to print each record
	public void listIt(int field, int order) 
	{
	// 1 -> First Name
		if(field == 1)
			FnameIndex.printArray(order, this); //pass dataStructure instance into printArray
	// 2 -> Last Name	
		else if(field == 2)
			LnameIndex.printArray(order, this);
	// 3 -> ID	
		else
			IDindex.printArray(order, this);
	}

//takes in index from Big Array and uses deleteRec to delete from each OrderedArray from recordNumber index
	public void delete(int index) 
	{
		FnameIndex.deleteRec(getFnameIndex(), index);
		LnameIndex.deleteRec(getLnameIndex(), index);
		IDindex.deleteRec(getIDindex(), index);	
	}


}//end DataStructure class
