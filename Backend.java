import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;



//--== CS400 File Header Information ==--
//Name: Yuanqing Cai
//Email: cai92@wisc.edu
//Team: red
//Group: AF
//TA: Mu
//Lecturer: Florian
//Notes to Grader: <optional extra notes>
public class Backend implements BackendInterface{
	
	RedBlackTree<BankDataInterface> tree;
	HashTableMap<String,Integer> map;
	
	
	private void putDataIntoHashTable(List<BankDataInterface> banklist) {
		for(int i=0;i<banklist.size();i++) {
			String key = banklist.get(i).getPhoneNum();
			Integer value = banklist.get(i).getID();
			if(!map.containsKey(key))
				map.put(key, value);
		}
	}
	
	private void putDataIntoRedBlackTree(List<BankDataInterface> banklist) {
		for(int i=0;i<banklist.size();i++) {
			if(!tree.contains(banklist.get(i)))
				tree.insert(banklist.get(i));
		}
	}
	
	
	
	public Backend(Reader inputFileReader) throws FileNotFoundException,IOException, DataFormatException{
		try {
			BankDataReader bank = new BankDataReader();
			List<BankDataInterface> banklist = bank.readDataSet(inputFileReader);
			tree = new RedBlackTree<BankDataInterface>();
			map = new HashTableMap<String, Integer>();
			putDataIntoRedBlackTree(banklist);
			putDataIntoHashTable(banklist);
		}catch(FileNotFoundException e) {
			throw new FileNotFoundException();
		}catch(IOException e) {
			throw new IOException();
		}catch(DataFormatException e) {
			throw new DataFormatException();
		}
	}
	
	public Backend() {
		tree = new RedBlackTree<BankDataInterface>();
		map = new HashTableMap<String, Integer>();
	}
	
	@Override
	public BankDataInterface GetMember(String phone) {
		if(phone==null) throw new NullPointerException();
		if(!map.containsKey(phone)) throw new NoSuchElementException();
		else {
			Integer id = map.get(phone);
			return GetMember(id);
		}
	}


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
	

	@Override
	public void Insert(String name, String sex, String phone, Integer age, Double balance) throws DataFormatException {
	//	if(!sex.equals("male")||!sex.equals("female")) throw new DataFormatException();
		BankData newMember= new BankData();
		newMember.setID(tree.size()+1);
		newMember.setAge(age);
		newMember.setBalance(balance);
		newMember.setName(name);
		newMember.setPhoneNum(phone);
		newMember.setSex(sex);
		tree.insert(newMember);
	}

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
