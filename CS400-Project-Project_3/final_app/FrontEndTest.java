// --== CS400 File Header Information ==--
// Name: Yingwei Song
// Email: ysong279@wisc.edu
// Team: red
// Role: Backend Developer
// TA: Mu Cai
// Lecturer: Florian
// Notes to Grader: none
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.*;

public class FrontEndTest {

    /**
     * This test will pass if users could enter into the Shortest mode successfully and get information correctly.
     * Test will pass if all meets, else fail.
     */
    @Test
    public void testShortestMode(){
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            String input = "S\r\nZhengzhou\r\nBeijing\r\nX\r\nX";

            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);

            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));

            StringReader city_set = new StringReader("Beijing\n" + "Zhengzhou\n" + "Shanghai\n");
            StringReader path_set = new StringReader(
                    "start,target,distance,cost\n"
                            + "Zhengzhou,Beijing,800,200\n"
                            +"Beijing,Shanghai,900,300\n"
            );

            Object frontend = new FrontEnd();
            ((FrontEnd)frontend).baseMode(new Backend(city_set,path_set));

            // set the output back to standard out for running the test
            System.setOut(standardOut);
            // same for standard in
            System.setIn(standardIn);

            String appOutput = outputStreamCaptor.toString();
            Assertions.assertTrue(appOutput.contains("Zhengzhou -> Beijing"));

        } catch (Exception e) {
            e.printStackTrace();
            // test failed
            fail("TEST FAIL");
        }
    }

    /**
     * This test will pass if users could enter into the Money mode successfully and get information correctly.
     * Test will pass if all meets, else fail.
     */
    @Test
    public void testMoneyMode(){
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            String input = "M\r\nZhengzhou\r\nBeijing\r\nX\r\nX";

            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);

            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));

            StringReader city_set = new StringReader("Beijing\n" + "Zhengzhou\n" + "Shanghai\n");
            StringReader path_set = new StringReader(
                    "start,target,distance,cost\n"
                            + "Zhengzhou,Beijing,800,200\n"
                            +"Beijing,Shanghai,900,300\n"
            );

            Object frontend = new FrontEnd();
            ((FrontEnd)frontend).baseMode(new Backend(city_set,path_set));

            // set the output back to standard out for running the test
            System.setOut(standardOut);
            // same for standard in
            System.setIn(standardIn);

            String appOutput = outputStreamCaptor.toString();
            Assertions.assertTrue(appOutput.contains("Zhengzhou -> Beijing"));

        } catch (Exception e) {
            e.printStackTrace();
            // test failed
            fail("TEST FAIL");

        }
    }

    /**
     * This test will pass if users could enter into the Reachable mode successfully and get information correctly.
     * Test will pass if all meets, else fail.
     */
    @Test
    public void testReachableMode(){
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            String input = "R\r\nZhengzhou\r\nX\r\nX";

            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);

            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));

            StringReader city_set = new StringReader("Beijing\n" + "Zhengzhou\n" + "Shanghai\n");
            StringReader path_set = new StringReader(
                    "start,target,distance,cost\n"
                            + "Zhengzhou,Beijing,800,200\n"
                            +"Beijing,Shanghai,900,300\n"
            );

            Object frontend = new FrontEnd();
            ((FrontEnd)frontend).baseMode(new Backend(city_set,path_set));

            // set the output back to standard out for running the test
            System.setOut(standardOut);
            // same for standard in
            System.setIn(standardIn);

            String appOutput = outputStreamCaptor.toString();
            Assertions.assertTrue(appOutput.contains("All the directly reachable cities"));

        } catch (Exception e) {
            e.printStackTrace();
            // test failed
            fail("TEST FAIL");
        }
    }

    /**
     * This test will pass if users could enter into the Money mode successfully and get information correctly.
     * Test will pass if all meets, else fail.
     */
    @Test
    public void testAddMode(){
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            String input = "A\r\nZhengzhou\r\nShanghai\r\n100\r\n100\r\nX\r\nX";

            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);

            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));

            StringReader city_set = new StringReader("Beijing\n" + "Zhengzhou\n" + "Shanghai\n");
            StringReader path_set = new StringReader(
                    "start,target,distance,cost\n"
                            + "Zhengzhou,Beijing,800,200\n"
                            +"Beijing,Shanghai,900,300\n"
            );

            Object frontend = new FrontEnd();
            ((FrontEnd)frontend).baseMode(new Backend(city_set,path_set));

            // set the output back to standard out for running the test
            System.setOut(standardOut);
            // same for standard in
            System.setIn(standardIn);

            String appOutput = outputStreamCaptor.toString();
            Assertions.assertTrue(appOutput.contains("added"));

        } catch (Exception e) {
            e.printStackTrace();
            // test failed
            fail("TEST FAIL");
        }
    }

    /**
     * This test will pass if users could enter into the Delete mode successfully and get information correctly.
     * Test will pass if all meets, else fail.
     */
    @Test
    public void testDeleteMode(){
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            String input = "D\r\nZhengzhou\r\nBeijing\r\nX\r\nX";

            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);

            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));

            StringReader city_set = new StringReader("Beijing\n" + "Zhengzhou\n" + "Shanghai\n");
            StringReader path_set = new StringReader(
                    "start,target,distance,cost\n"
                            + "Zhengzhou,Beijing,800,200\n"
                            +"Beijing,Shanghai,900,300\n"
            );

            Object frontend = new FrontEnd();
            ((FrontEnd)frontend).baseMode(new Backend(city_set,path_set));

            // set the output back to standard out for running the test
            System.setOut(standardOut);
            // same for standard in
            System.setIn(standardIn);

            String appOutput = outputStreamCaptor.toString();
            Assertions.assertTrue(appOutput.contains("deleted"));

        } catch (Exception e) {
            e.printStackTrace();
            // test failed
            fail("TEST FAIL");
        }
    }

}
