//--== CS400 File Header Information ==--
//Name: Yifan Liu
//Email: yliu897@wisc.edu
//Team: red
//Group: AF
//TA: Mu
//Lecturer: Gary
//Notes to Grader: <optional extra notes>
public interface BankDataInterface extends Comparable<BankDataInterface>{
  public Integer getID();  // return a 4 digit int

  public String getName(); // return the String representation of phone number(9 digits)

  public String getPhoneNum();

  public Integer getAge();

  public String getSex();

  public Double getBalance(); // return the balance of the current account(can be negative)
  
  public boolean getIsBlack();
  // All the setter method
  public void setName(String name);
  public void setAge(Integer age);
  public void setPhoneNum(String num);
  public void setSex(String sex);
  public void setBalance(Double balance);
  public void setID(Integer ID);
  public void setIsBlack(boolean isBlack);
  public int compareTo(BankDataInterface otherData);
  public String toString();
}
