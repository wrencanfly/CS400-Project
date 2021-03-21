//--== CS400 File Header Information ==--
//Name: Yifan Liu
//Email: yliu897@wisc.edu
//Team: red
//Group: AF
//TA: Mu
//Lecturer: Gary
//Notes to Grader: <optional extra notes>
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.zip.DataFormatException;

public class DataWranglerTests {
  List<BankDataInterface> data;
    @BeforeEach
    public void setup() throws IOException, DataFormatException {
      BankDataReader reader = new BankDataReader();
      data = reader.readDataSet(new FileReader("./bankDataSet.csv"));
      // make sure that it is in the same folder

    }

   /**
   *This method tests the implementation of the getID() by checking
   * whether a given ID is within four digit range
   */
  @Test
  public void testGetId(){
    data.forEach(n -> assertEquals(true, n.getID() >= 1000 && n.getID() <= 9999));
  }

  /**
   *This method tests the implementation of the getPhoneNum() by checking
   *whether a given phoneNum is a 9 digit string
   */
  @Test
  public void testGetPhoneNum(){
    data.forEach(n -> assertEquals(9, n.getPhoneNum().length()));
  }
  /**
   *This method tests the implementation of the readDataSet() by checking
   *whether it successfully read in all the rows of data
   */
  @Test 
  public void testReadDataSet(){
    assertEquals(200, data.size());
  }
  /**
   *This method tests whether there are identical Id
   * Id should not be the same
   */
  @Test
  public void testIdenticalId(){
    HashSet<Integer> idList = new HashSet<>();
    data.forEach(n -> idList.add(n.getID()));
    assertEquals(200, idList.size());
  }
  /**
   *This method tests the implementation of the compareTo() in BankData by checking
   *whether it correctly compare two BankData object.
   */
  @Test
  public void testCompareTo(){
    BankData curr = new BankData();
    BankData other = new BankData();
    curr.setID(2000);
    other.setID(9999);
    assertEquals(true, other.compareTo(curr) > 0);
  }
}
