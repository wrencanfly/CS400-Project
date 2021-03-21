//--== CS400 File Header Information ==--
//Name: Yuanqing Cai
//Email: cai92@wisc.edu
//Team: red
//Group: AF
//TA: Mu
//Lecturer: Florian
//Notes to Grader: <optional extra notes>
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
/*
 * This class contain 5 junit tests for Backend of Bank app. The junit tests include tests for GetMember,
 * resetBalance,GetBlackList and addMember.
 */
public class BackEndDeveloperTests {
	/**
	 * Test the GetMember method which use member's id (Integer) to search and give the member's information.
	 */
	@Test
	public void Test1() {
		try {
			Backend test = new Backend();
			test.Insert("Rory", "male", "11111111", 18, 10000.0);
			test.Insert("Jessica", "female", "22222222", 19, -10000.0);
			test.Insert("Lilly", "female", "33333333", 20, 200.0);
			test.Insert("Jeffery", "male", "44444444", 21, -2000.0);
			test.Insert("David", "male", "55555555", 17, 50.0);
			test.Insert("Eve", "female", "66666666", 18, -50.0);		
			assertEquals("[Rory	male	1000	11111111	18	10000.0]",test.GetMember(1000).toString());
			assertEquals("[Lilly	female	1002	33333333	20	200.0]",test.GetMember(1002).toString());
		}catch(Exception e) {
			fail("There is an Exception so the test fail");
		}
	}
	/**
	 * Test the GetMember method which use member's phone number (String) to search and give the member's information.
	 */
	@Test
	public void Test2() {
		try {
			Backend test = new Backend();
			test.Insert("Rory", "male", "11111111", 18, 10000.0);
			test.Insert("Jessica", "female", "22222222", 19, -10000.0);
			test.Insert("Lilly", "female", "33333333", 20, 200.0);
			test.Insert("Jeffery", "male", "44444444", 21, -2000.0);
			test.Insert("David", "male", "55555555", 17, 50.0);
			test.Insert("Eve", "female", "66666666", 18, -50.0);	
			assertEquals("[Jessica	female	1001	22222222	19	-10000.0]",test.GetMember("22222222").toString());
			assertEquals("[Jeffery	male	1003	44444444	21	-2000.0]",test.GetMember("44444444").toString());
			}catch(Exception e) {
				fail("There is an Exception so the test fail");
			}
	}
	/**
	 * Test the AddMember method which add a member into Red-Black tree and HashTable with the given information
	 */
	@Test
	public void Test3() {
		try {
			Backend test = new Backend();
			test.Insert("Rory", "male", "11111111", 18, 10000.0);
			test.Insert("Jessica", "female", "22222222", 19, -10000.0);
			test.Insert("Lilly", "female", "33333333", 20, 200.0);
			test.Insert("Jeffery", "male", "44444444", 21, -2000.0);
			test.Insert("David", "male", "55555555", 17, 50.0);
			test.Insert("Eve", "female", "66666666", 18, -50.0);
			
			test.Insert("Chris","male","77777777",20,-100.0);
			assertEquals("[Chris	male	1006	77777777	20	-100.0]",test.GetMember(1006).toString());
			assertEquals("[Chris	male	1006	77777777	20	-100.0]",test.GetMember("77777777").toString());
			}catch(Exception e) {
				fail("There is an Exception so the test fail");
			}
	}
	/**
	 * Test the ResetBalance method which reset the balance of the member with given id.
	 */
	@Test
	public void Test4() {
		try {
			Backend test = new Backend();
			test.Insert("Rory", "male", "11111111", 18, 10000.0);
			test.Insert("Jessica", "female", "22222222", 19, -10000.0);
			test.Insert("Lilly", "female", "33333333", 20, 200.0);
			assertEquals(10000.0,test.GetMember(1000).getBalance(),0);
			test.EditBalance(1000, -10000.0);
			assertEquals(-10000.0, test.GetMember(1000).getBalance(),0);
		}catch(Exception e) {
			fail("There is an Exception so the test fail");
		}
	}
	/**
	 * Test the GetBlackList method which return a list of member whose balance is lower than the given value.
	 */
	@Test
	public void Test5() {
		try {
			Backend test = new Backend();
			test.Insert("Rory", "male", "11111111", 18, 10000.0);
			test.Insert("Jessica", "female", "22222222", 19, -10000.0);
			test.Insert("Lilly", "female", "33333333", 20, 200.0);
			test.Insert("Jeffery", "male", "44444444", 21, -2000.0);
			test.Insert("David", "male", "55555555", 17, 50.0);
			test.Insert("Eve", "female", "66666666", 18, -50.0);
			List<BankDataInterface> comp = new ArrayList<BankDataInterface>();
			comp.add(test.GetMember(1001));
			comp.add(test.GetMember(1003));
			assertEquals(comp,test.GetBlacklist());
		}catch(Exception e) {
			fail("There is an Exception so the test fail");
		}	
	}

}
