// --== CS400 File Header Information ==--
// Name: Steven Yang
// Email: yang558@wisc.edu
// Team: AF red
// Role: Frontend Developer
// TA: Mu
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

/**
 * This class contains a set of tests for the front end of the Bank Account System project.
 */
public class FrontEndDeveloperTests {

  /**
   * Test if program will exit when user presses “X”.
   * This test runs the front end and redirects its output to a string. It then
   * passes in 'X' as a command. When the front end exists, the tests succeeds.
   * If 'X' does not exist the app, the test will not terminate (it won't fail
   * explicitly in this case). The test fails explicitly if the front end is
   * not instantiated or if an exception occurs.
   * 
   * *UPDATE 3/21: Change test data input to a correct format
   */
  @Test
  public void enterXToExit() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
        // set the input stream to our input (with an x to test of the program exists)
        String input = "X";
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        // set the output to the stream captor to read the output of the front end
        System.setOut(new PrintStream(outputStreamCaptor));
        // instantiate when front end is implemented
        Object frontend = new Frontend();
        ((Frontend)frontend).run(new Backend(new StringReader(
            "Bob, Male, 1000, 111111111, 20, 20000\n"
            + "Alice, Female, 1001, 222222222, 22, -20000\n"
            + "Steven, Male, 1002, 333333333, 20, 500000\n"
            )));
        // set the output back to standard out for running the test
        System.setOut(standardOut);
        // same for standard in
        System.setIn(standardIn);
        if (frontend == null) {
            // test fails
            fail("Test Fails");
        }
    } catch (Exception e) {
        // make sure stdin and stdout are set correctly after we get exception in test
        System.setOut(standardOut);
        System.setIn(standardIn);
        e.printStackTrace();
        // test failed
        fail("Test Fails");
    }
  }
  
  /**
   * Test if the program will print any bank account information initially.
   * This test runs the front end and redirects its output to a string. It then
   * passes in 'X' as a command to exit the app. The test succeeds if the front
   * end does not contain any of the three Bank Account information by default. 
   * It fails if any of those three information are present in the string or an exception occurs.
   */
  @Test
  public void testInitialOutput() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
        // set the input stream to our input (with an x to test of the program exists)
        String input = "X";
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        // set the output to the stream captor to read the output of the front end
        System.setOut(new PrintStream(outputStreamCaptor));
        // instantiate when front end is implemented
        Object frontend = new Frontend();
        ((Frontend)frontend).run(new Backend(new StringReader(
            "Bob, Male, 1000, 111111111, 20, 20000\n"
            + "Alice, Female, 1001, 222222222, 22, -20000\n"
            + "Steven, Male, 1002, 333333333, 20, 500000\n"
            )));
        // set the output back to standard out for running the test
        System.setOut(standardOut);
        // same for standard in
        System.setIn(standardIn);
        String appOutput = outputStreamCaptor.toString();
        if (frontend == null || appOutput.contains("Bob")
                        || appOutput.contains("1000")
                        || appOutput.contains("Male")) {
            // test failed
            fail("Test Fails");
        }
    } catch (Exception e) {
        // make sure stdin and stdout are set correctly after we get exception in test
        System.setOut(standardOut);
        System.setIn(standardIn);
        e.printStackTrace();
        // test failed
        fail("Test Fails");
    }
  }
  
  /**
   * Test if the program will print out blacklist correctly.
   * This test runs the front end and redirects its output to a string. It then passes "B" to
   * print out the black list and then "X" to exit the program. The program succeeds if it prints
   * out the correct ID whose balance is under black list range. It fails if the correct ID is not 
   * printed, the front end has not been instantiated (is null), or there is an exception.
   */
  @Test
  public void testBlackList() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
        // set the input stream to our input (with an x to test of the program exists)
        String input = "B" + System.lineSeparator() + "X";
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        // set the output to the stream captor to read the output of the front end
        System.setOut(new PrintStream(outputStreamCaptor));
        // instantiate when front end is implemented
        Object frontend = new Frontend();
        ((Frontend)frontend).run(new Backend(new StringReader(
            "Bob, Male, 1000, 111111111, 20, 20000\n"
            + "Alice, Female, 1001, 222222222, 22, -20000\n"
            + "Steven, Male, 1002, 333333333, 20, 500000\n"
            )));
        // set the output back to standard out for running the test
        System.setOut(standardOut);
        // same for standard in
        System.setIn(standardIn);
        String appOutput = outputStreamCaptor.toString();
        if (frontend == null || !appOutput.contains("Alice, Sex: Female, Age: 22, Phone Number: 222222222, Balance: -20000.0")) {
            // test failed
            fail("Test Fails");
        }
    } catch (Exception e) {
        // make sure stdin and stdout are set correctly after we get exception in test
        System.setOut(standardOut);
        System.setIn(standardIn);
        e.printStackTrace();
        // test failed
        fail("Test Fails");
    }
  }
  
  /**
   * Test if program goes into Adding mode when pressing “A”.
   * This test runs the front end and redirects its output to a string. It then passes "A" to enter
   * Adding Mode, then "X" back to Base Mode, then another "X" to exit. The program succeeds if it
   * goes into the Adding Mode. It fails if it does not go into Adding mode, the front end has not
   * been instantiated (is null), or there is an exception.
   */
  @Test
  public void testGoToAddingMode() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
        // set the input stream to our input (with an x to test of the program exists)
        String input = "A" + System.lineSeparator() + "X" + System.lineSeparator() + "X";
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        // set the output to the stream captor to read the output of the front end
        System.setOut(new PrintStream(outputStreamCaptor));
        // instantiate when front end is implemented
        Object frontend = new Frontend();
        ((Frontend)frontend).run(new Backend(new StringReader(
            "Bob, Male, 1000, 111111111, 20, 20000\n"
            + "Alice, Female, 1001, 222222222, 22, -20000\n"
            + "Steven, Male, 1002, 333333333, 20, 500000\n"
            )));
        // set the output back to standard out for running the test
        System.setOut(standardOut);
        // same for standard in
        System.setIn(standardIn);
        String appOutput = outputStreamCaptor.toString();
        if (frontend == null || !appOutput.contains("This is the Adding Mode!")) {
            // test failed
            fail("Test Fails");
        }
    } catch (Exception e) {
        // make sure stdin and stdout are set correctly after we get exception in test
        System.setOut(standardOut);
        System.setIn(standardIn);
        e.printStackTrace();
        // test failed
        fail("Test Fails");
    }
  }
  
  /**
   * Test if program goes into Modification mode when pressing “M”.
   * This test runs the front end and redirects its output to a string. It then passes "M" to enter
   * Modification Mode, then "X" back to Base Mode, then another "X" to exit. The program succeeds 
   * if it goes into the Modification Mode. It fails if it does not go into Adding mode, the front 
   * end has not been instantiated (is null), or there is an exception.
   */
  @Test
  public void testGoToModificationMode() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
        // set the input stream to our input (with an x to test of the program exists)
        String input = "M" + System.lineSeparator() + "X" + System.lineSeparator() + "X";
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        // set the output to the stream captor to read the output of the front end
        System.setOut(new PrintStream(outputStreamCaptor));
        // instantiate when front end is implemented
        Object frontend = new Frontend();
        ((Frontend)frontend).run(new Backend(new StringReader(
            "Bob, Male, 1000, 111111111, 20, 20000\n"
            + "Alice, Female, 1001, 222222222, 22, -20000\n"
            + "Steven, Male, 1002, 333333333, 20, 500000\n"
            )));
        // set the output back to standard out for running the test
        System.setOut(standardOut);
        // same for standard in
        System.setIn(standardIn);
        String appOutput = outputStreamCaptor.toString();
        if (frontend == null || !appOutput.contains("This is the Modification Mode!")) {
            // test failed
            fail("Test Fails");
        }
    } catch (Exception e) {
        // make sure stdin and stdout are set correctly after we get exception in test
        System.setOut(standardOut);
        System.setIn(standardIn);
        e.printStackTrace();
        // test failed
        fail("Test Fails");
    }
  }
}
