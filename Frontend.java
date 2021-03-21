// --== CS400 File Header Information ==--
// Name: Steven Yang
// Email: yang558@wisc.edu
// Team: AF red
// Role: Frontend Developer
// TA: Mu
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

/**
 * This is the front end class for our project. It contains the main method and runs the program.
 * User can type different commands to access, modify, or add any users.
 * 
 * @author Steven Yang
 *
 */
public class Frontend {
  
  public static int IDCount = 1199; // current last user's ID in the data set
  
  /**
   * Run the program by starting from the base mode.
   * 
   * @param back backend
   */
  public static void run(Backend back) {
    System.out.println("Welcome!!!");
    System.out.println("This is Bank Account System!");
    System.out.println("------------------------------------------------------");
    BaseMode(back);//enter the base Mode
    System.out.println("Thank you for using our app. See you next time!"); 
    System.out.println("======================================================");
  }
  
  /**
   * Base mode is the mode to start with. Users can search for a particular person’s bank account
   * information by entering the person’s ID or phone number. User can also get the blacklist
   * printed by pressing “B”. Users press ‘A’ to enter the Adding mode. User presses “M” to enter 
   * the Modification mode.
   * 
   * @param back backend
   */
  private static void BaseMode(Backend back) {
    
    Boolean exit=false;
    Scanner scan=new Scanner(System.in);
    while(!exit) {
      System.out.println("You are now in the Base Mode!");
      System.out.println("You can type [A] to enter the Adding Mode.");
      System.out.println("You can type [M] to enter the Modification Mode.");
      System.out.println("You can type [B] to print out the blacklist!");
      System.out.println("You can type [ID number] to search for the bank user's info!");
      System.out.println("You can type [9-digit Phone Number] to search for the bank user's info!");
      System.out.println("You can type [X] to exit the program!");
      
      while (true) {
        String typeIn = scan.next();
        if (typeIn.equals("X")) {
          scan.close();
          exit = true;// renew exit
          break;
          // check x to exit.
        }
        else if (typeIn.equals("A")) {
          AddingMode(back, scan);
          break;
          // check g to enter G mode
        }
        else if (typeIn.equals("M")) {
          ModificationMode(back, scan);
          break;
          // enter r to enter R mode
        }
        else if (typeIn.equals("B")) {
          List<BankDataInterface> bl = back.GetBlacklist();
          for (int i = 0; i < bl.size(); i++) {
            Integer ID = bl.get(i).getID();
            Integer age = bl.get(i).getAge();
            String name = bl.get(i).getName();
            String phoneNum = bl.get(i).getPhoneNum();
            String sex = bl.get(i).getSex();
            Double balance = bl.get(i).getBalance();
            String info = String.format("ID: %s, Name: %s, Sex: %s, Age: %s, Phone Number: %s, Balance: %.1f",ID,name,sex,age,phoneNum,balance);
            System.out.println(info);
            System.out.println("------------------------------------------------------------------------------------");
          }
          System.out.println("Enter [A], [M], [B], [X], valid ID or Phone Number to continue!");
        }
        
        // search with phone number
        else if(typeIn.length() == 9) {
          try {
            BankDataInterface b1 = back.GetMember(typeIn);
            Integer ID = b1.getID();
            Integer age = b1.getAge();
            String name = b1.getName();
            String phoneNum = b1.getPhoneNum();
            String sex = b1.getSex();
            Double balance = b1.getBalance();
            String info = String.format("ID: %s, Name: %s, Sex: %s, Age: %s, Phone Number: %s, Balance: %.1f",ID,name,sex,age,phoneNum,balance);
            System.out.println(info);
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("Enter [A], [M], [B], [X], valid ID or Phone Number to continue!");
          } catch (NoSuchElementException e) {
            System.out.println("This phone number does not exist. Please re-enter.");
          } catch (Exception e) {
            System.out.println("Invalid input. Please re-enter");
          }
        }
        
        // search with ID number
        else if (typeIn.length() == 4) {
          try {
            BankDataInterface b1 = back.GetMember(Integer.valueOf(typeIn));
            Integer ID = b1.getID();
            Integer age = b1.getAge();
            String name = b1.getName();
            String phoneNum = b1.getPhoneNum();
            String sex = b1.getSex();
            Double balance = b1.getBalance();
            String info = String.format("ID: %s, Name: %s, Sex: %s, Age: %s, Phone Number: %s, Balance: %.1f",ID,name,sex,age,phoneNum,balance);
            System.out.println(info);
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("Enter [A], [M], [B], [X], valid ID or Phone Number to continue!");
          } catch (NoSuchElementException e) {
            System.out.println("This ID number does not exist. Please re-enter.");
          } catch (Exception e) {
            System.out.println("Invalid input. Please re-enter");
          }
        }
        
        // check the valid of input
        else {
          System.out.println("Invalid input! You have to type [A], [M], [B], [X], valid ID or Phone Number!");
          continue;
        }
        
      }
    }
  }
  
  /**
   * Adding mode is the mode to add data. In this mode, users input whole information about the
   * client. And if press ‘X’, it will return to the base mode.
   * 
   * @param back backend
   * @param scan scanner to detect user inputs
   */
  private static void AddingMode(Backend back, Scanner scan) {
    System.out.println("This is the Adding Mode! You can add bank user's information by typing in info in this mode!");
    System.out.println("You can type [Yes] to start adding or [X] to go back to the Base Mode!");
    
    while(true) {
      String typeIn = scan.next();
      if (typeIn.equals("X")) break;
      if (typeIn.equals("Yes")) {
        System.out.println("Please enter the information in the following format(using a space and a comma to split):");
        System.out.println("Name, Sex, Phone Number, Age, Balance");
        System.out.println("For example: Nick, male, 123456789, 21, 2000");
        String whiteSpace = scan.nextLine();
        String addInfo = scan.nextLine();
        String[] arrayOfInfo = addInfo.split(", ");
        try {
          back.Insert(arrayOfInfo[0], arrayOfInfo[1], arrayOfInfo[2], Integer.valueOf(arrayOfInfo[3]), Double.parseDouble(arrayOfInfo[4]));
          if (arrayOfInfo[2].length() != 9) {
            throw new DataFormatException();
          }
          System.out.println("Information added! This new user's ID is " + ++IDCount);
          System.out.println("Please type [Yes] to continue adding or [X] to go back to the Base Mode!");
        } catch (IllegalArgumentException e) {
          System.out.println("Invalid inputs: Phone number already exists!");
          System.out.println("Please re-enter the info by typing [Yes] or [X] to go back to the Base Mode!");
        } catch (DataFormatException e) {
          System.out.println("Invalid inputs: Phone number should be 9-digits");
          System.out.println("Please re-enter the info by typing [Yes] or [X] to go back to the Base Mode!");
        } catch (Exception e) {
          System.out.println("Invalid inputs. Please re-enter the info by typing [Yes] or [X] to go back to the Base Mode!");
        }
      }
      
      // check the valid of input
      else {
        System.out.println("Invalid input! Please type [Yes] to add or [X] to go back to the Base Mode!");
        continue;
      }
      
    }
  }
  
  /**
   * Modification mode is the mode to modify balance from clients which is created by a back end
   * developer. Users input the person's ID number, and then if there is not this ID number it will
   * print a warning, and nothing changes here. Otherwise, users can modify the balance from 
   * clients. And if press ‘X’, it will return to the base mode.
   * 
   * @param back backend
   * @param scan scanner to detetc user's inputs
   */
  private static void ModificationMode(Backend back, Scanner scan) {
    System.out.println("This is the Modification Mode! You can edit bank user's balance in this mode!");
    System.out.println("You can type [Yes] to start editing or [X] to go back to the Base Mode!");
    
    while(true) {
      String typeIn = scan.next();
      if (typeIn.equals("X")) break;
      if (typeIn.equals("Yes")) {
        System.out.println("Please enter the valid ID number which you want to edit");
        String IDtoEdit = scan.next();
        System.out.println("Please enter the balance");
        String balance = scan.next();
        try {
          back.EditBalance(Integer.valueOf(IDtoEdit), Double.parseDouble(balance));
          System.out.println("Balance edited successfully for ID: " + IDtoEdit);
          System.out.println("Please type [Yes] to continue editing or [X] to go back to the Base Mode!");
        } catch (NoSuchElementException e) {
          System.out.println("The ID number does not exist. Please re-enter the info by typing [Yes] or [X] to go back to the Base Mode!");
        } catch (Exception e) {
          System.out.println("Invalid inputs. Please re-enter the info by typing [Yes] or [X] to go back to the Base Mode!.");
        }
      }
      
      // check the valid of input
      else {
        System.out.println("Invalid input! Please type [Yes] to edit or [X] to go back to the Base Mode!");
        continue;
      }
      
    }
  }
  
  /**
   * Main method initialize Backend and load the data set
   * 
   * @param args args input arguments if any
   */
  public static void main(String[] args) {
    Backend back;// new Backend
    try {
      FileReader file = new FileReader("./bankDataSet.csv");// the file Reader
      back = new Backend(file);
      run(back);// run the whole System
    } catch (FileNotFoundException e) {
      System.out.println("There is no such file!");
    } catch (IOException e) {
      System.out.println("There is an IOException!");
    } catch (DataFormatException e) {
      System.out.println("There is a DataFormatException!");
    }
  }

}
