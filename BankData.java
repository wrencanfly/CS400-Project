//--== CS400 File Header Information ==--
//Name: Yifan Liu
//Email: yliu897@wisc.edu
//Team: red
//Group: AF
//TA: Mu
//Lecturer: Gary
//Notes to Grader: <optional extra notes>

/**
 * BankData stores the ID, age, name, sex, balance and phone number of a individual.
 * @author iveniven
 */
public class BankData implements BankDataInterface{
  private Integer ID, age;
  private String name, phoneNum, sex;
  private Double balance;
  
  @Override
  public Integer getID() {
    return ID;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getPhoneNum() {
    return phoneNum;
  }

  @Override
  public Integer getAge() {
    return age;
  }

  @Override
  public String getSex() {
    return sex;
  }

  @Override
  public Double getBalance() {
    return balance;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public void setPhoneNum(String num) {
    this.phoneNum = num;
  }

  @Override
  public void setSex(String sex) {
    this.sex = sex;
  }

  @Override
  public void setBalance(Double balance) {
    this.balance = balance;
  }

  @Override
  public void setID(Integer ID) {
    this.ID = ID;
  }
  @Override
  public String toString(){
    return "[" + name +"\t" + sex+"\t" + ID+"\t" + phoneNum+"\t" + age+"\t" + balance+ "]";
  }


  @Override
  public int compareTo(BankDataInterface otherData) { 
    return ID.compareTo(otherData.getID());
  }

}
