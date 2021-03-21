//--== CS400 File Header Information ==--
//Name: Yuanqing Cai
//Email: cai92@wisc.edu
//Team: red
//Group: AF
//TA: Mu
//Lecturer: Florian
//Notes to Grader: <optional extra notes>
import java.util.List;
import java.util.zip.DataFormatException;

public interface BackendInterface {
	public BankDataInterface GetMember(String phone);
	public BankDataInterface GetMember(Integer id);
	public void Insert(String name, String sex, String phone, Integer age, Double balance)throws DataFormatException ;
	public void EditBalance(Integer id, Double balance);
	public List<BankDataInterface> GetBlacklist();
	

}
