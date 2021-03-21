//--== CS400 File Header Information ==--
//Name: Yuanqing Cai
//Email: cai92@wisc.edu
//Team: red
//Group: AF
//TA: Mu
//Lecturer: Florian
//Notes to Grader: <optional extra notes>
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
/**
 * The Class Backend implements BackendInterface with several methods to modify the data.
 * @author Rory Cai
 *
 */
public class Backend implements BackendInterface{
	RedBlackTree<BankDataInterface> tree;
	//The RedBlackTree stored the BankData
	HashTableMap<String,Integer> map;
	//The HashTableMap store phone as key and id as value
	/**
	 * method to put all data in csv into HashTableMap with phone as key and id as value
	 * @param banklist
	 */
	private void putDataIntoHashTable(List<BankDataInterface> banklist) {
		for(int i=0;i<banklist.size();i++) {
			String key = banklist.get(i).getPhoneNum();
			Integer value = banklist.get(i).getID();
			if(!map.containsKey(key))
				map.put(key, value);
		}
	}
	/**
	 * method to put all data in csv into RedBlackTree
	 * @param banklist
	 */
	private void putDataIntoRedBlackTree(List<BankDataInterface> banklist) {
		for(int i=0;i<banklist.size();i++) {
			if(!tree.contains(banklist.get(i)))
				tree.insert(banklist.get(i));
		}
	}
	/**
	 * The constructor with File Reader which read the data in the given file and insert the data into RedBlackTree and HashTableMap
	 * Will catch the exception throw by dataReader.
	 * @param inputFileReader
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws DataFormatException
	 */
	public Backend(Reader inputFileReader) throws FileNotFoundException,IOException, DataFormatException{
		try {
			BankDataReader bank = new BankDataReader();
			List<BankDataInterface> banklist = bank.readDataSet(inputFileReader);
			tree = new RedBlackTree<BankDataInterface>();
			map = new HashTableMap<String, Integer>();
			putDataIntoRedBlackTree(banklist);//put data into RedBlackTree
			putDataIntoHashTable(banklist);//put data into HashTable
		}catch(FileNotFoundException e) {
			throw new FileNotFoundException();
		}catch(IOException e) {
			throw new IOException();
		}catch(DataFormatException e) {
			throw new DataFormatException();
		}
	}
	/**
	 * None argument constructor with no data in the RedBlackTree and HashTable
	 */
	public Backend() {
		tree = new RedBlackTree<BankDataInterface>();
		map = new HashTableMap<String, Integer>();
	}
	/**
	 * GetMember method which return the data of the member with given phone.
	 * Use the given phone to get the id from the HashTableMap
	 * Then call GetMember(id) to get the BankData.
	 * if phone is null throw NullPointerException
	 * if no bankData has given phone, throw NoSuchElementException
	 * @return BankDataInterface
	 * @param String phone
	 */
	@Override
	public BankDataInterface GetMember(String phone) {
		if(phone==null) throw new NullPointerException();
		if(!map.containsKey(phone)) throw new NoSuchElementException();
		else {
			Integer id = map.get(phone);
			return GetMember(id);
		}
	}

	/**
	 * GetMember method which return the data of the member with given id.
	 * Use the Given id to find the BankData in the Red Balck Tree.
	 * if the id is null, throw NullPointerException
	 * if the no member has given id, throw NoSuchElementException
	 * @return BankDataInterface
	 * @param Integer id
	 */
	@Override
	public BankDataInterface GetMember(Integer id) {
		if(id==null)throw new NullPointerException();
		List<BankDataInterface> AllData = tree.getAllDataInSequence();
		for(int i=0;i<AllData.size();i++) {
			if(AllData.get(i).getID().equals(id))
				return AllData.get(i);
		}
		throw new NoSuchElementException();
		
	}
	
	/**
	 * Insert method which insert a new Member into RedBlackTree and HashTableMap with the given value
	 * The id of the new Member is size of RedBalckTee + 1.
	 * throw IllegalArgumentException if the phone already exist
	 * @param String name
	 * @param Stirng sex
	 * @param String phone
	 * @param Integer age
	 * @param Double balance
	 */
	@Override
	public void Insert(String name, String sex, String phone, Integer age, Double balance) {
		if(map.containsKey(phone)) throw new IllegalArgumentException();
		BankData newMember= new BankData();
		int id = tree.size()+1000;
		newMember.setID(id);
		newMember.setAge(age);
		newMember.setBalance(balance);
		newMember.setName(name);
		newMember.setPhoneNum(phone);
		newMember.setSex(sex);
		tree.insert(newMember);
		map.put(phone,id);
	}
	/**
	 * The EditBalance method which edit the balance of the member with given id to the new Given Balance.
	 * throw NullPointerException if id is null
	 * throw NoSuchElementException if id does not exist
	 * @param Integer id
	 * @param Double balance
	 * @throws NullPointerException
	 * @throws NoSuchElementException
	 */
	@Override
	public void EditBalance(Integer id, Double balance) {
		try {
			GetMember(id).setBalance(balance);
			GetMember(id).setIsBlack(balance<=(-1000));
		}catch(NullPointerException e) {
			throw new NullPointerException();
		}catch(NoSuchElementException e) {
			throw new NoSuchElementException();
		}
	}
	/**
	 * The GetBlackList method return the List of BankData which has balance less than -1000
	 * @return List<BankDataInterface>
	 */
	@Override
	public List<BankDataInterface> GetBlacklist() {
		List<BankDataInterface> BlackList = new ArrayList<BankDataInterface>();
		for(int i=0;i<tree.getAllDataInSequence().size();i++) {
			if(tree.getAllDataInSequence().get(i).getIsBlack()==true)
				BlackList.add(tree.getAllDataInSequence().get(i));
		}
		return BlackList;
	}
}
