// --== CS400 File Header Information ==--
// Name: Steven Yang
// Email: yang558@wisc.edu
// Team: AF Red
// Role: Backend
// TA: Mu
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * This is a junit test bench for backend class
 * @author Steven Yang
 *
 */
public class BackendTest {
  Backend back;
  
  /**
   * Create a new backend for each test case
   * 
   * @throws FileNotFoundException when file not found
   * @throws IOException when IOexception
   * @throws DataFormatException when DataFormatException
   */
  @BeforeEach
  public void setup() throws FileNotFoundException, IOException, DataFormatException {

    try {
      FileReader city = new FileReader("src/CityDataSet.csv");
      FileReader path = new FileReader("src/PathDataSet.csv");

      back = new Backend(city, path);
    } catch (FileNotFoundException e) {
      System.out.println("There is no such file!");
    } catch (IOException e) {
      System.out.println("There is an IOException!");
    } catch (DataFormatException e) {
      System.out.println("There is a DataFormatException!");
    }
  }

  /**
   * This method tests the functionality of getShortestPath()
   * The the shortest path between Guangzhou and Beijing should be
   * Guangzhou, Changsha, Wuhan, Zhengzhou, Shijiazhuang, Beijing
   */
  @Test
  public void testShortestPath() {
    // Get the shortest path between Guangzhou and Beijing
    List<CityDataInterface> shortestPath = back.getShortestPath("Guangzhou", "Beijing");
    String result = "[";
    for (int i = 0; i < shortestPath.size() - 1; i++) {
      result = result + shortestPath.get(i).GetName() + ", ";
    }
    result = result + shortestPath.get(shortestPath.size() - 1).GetName() + "]";
    String target = "[Guangzhou, Changsha, Wuhan, Zhengzhou, Shijiazhuang, Beijing]";
    
    if (!result.equals(target)) {
      fail("Shortest Path is incorrect!");
    }
  }
  
  /**
   * This method tests the functionality of getShortestDistance()
   * The shortest distance between Guangzhou and Beijing should be 2200
   */
  @Test
  public void testShortestDistance() {
    // Get the shortest distance between Guangzhou and Beijing
    int shortestDistance = back.getShortestDistance("Guangzhou", "Beijing");
    if (shortestDistance != 2200) {
      fail("Shortest Distance is incorrect!");
    }
  }
  
  /**
   * This method tests the functionality of getCheapestPath()
   * The cheapest path between Guangzhou and Beijing should be
   * Guangzhou, Changsha, Guiyang, Chongqing, Xian, Taiyuan, Beijing
   */
  @Test
  public void testCheapestPath() {
    // Get the cheapest path between Guangzhou and Beijing
    List<CityDataInterface> cheapestPath = back.getCheapestPath("Guangzhou", "Beijing");
    String result = "[";
    for (int i = 0; i < cheapestPath.size() - 1; i++) {
      result = result + cheapestPath.get(i).GetName() + ", ";
    }
    result = result + cheapestPath.get(cheapestPath.size() - 1).GetName() + "]";
    String target = "[Guangzhou, Changsha, Guiyang, Chongqing, Xian, Taiyuan, Beijing]";
    
    if (!result.equals(target)) {
      fail("Cheapest Path is incorrect!");
    }
  }
  
  /**
   * This method tests the functionality of getCheapestCost()
   * The cheapest cost between Guangzhou and Beijing should be 1200
   */
  @Test
  public void testCheapestCost() {
    // Get the cheapest cost between Guangzhou and Beijing
    int cheapestCost = back.getCheapestCost("Guangzhou", "Beijing");
    if (cheapestCost != 1200) {
      fail("Cheapest Cost is incorrect!");
    }
  }
  
  /**
   * This method tests the functionality of getDirectReach()
   * The direct reachable cities of Nanchang should be Guangzhou, Fuzhou, Hangzhou, Hefei
   */
  @Test
  public void testDirectReach() {
    // Test the direct reachable cities of Nanchang
    List<CityDataInterface> direct = back.getDirectReach("Nanchang");
    String result = "[";
    for (int i = 0; i < direct.size() - 1; i++) {
      result = result + direct.get(i).GetName() + ", ";
    }
    result = result + direct.get(direct.size() - 1).GetName() + "]";
    String target = "[Guangzhou, Fuzhou, Hangzhou, Hefei]";
    
    if (!result.equals(target)) {
      fail("Direct Reach is incorrect!");
    }
  }
  
  /**
   * This method tests the functionality of addPath()
   */
  @Test
  public void testAddPath() {
    // Case 1: the same path already exists
    if (back.addPath("Nanchang", "Fuzhou", 500, 300)) {
      fail("Path has already existed.");
    }
    
    // Case 2: Non-exist city
    try {
      back.addPath("Nanchang", "America", 200, 300);
      fail("Add path should throw a NoSuchElementException!");
    } catch (NoSuchElementException e) {
      // case handled.
    }
    
    // Case 3: add two new path between two existing cities "Nanchang" to "Xian" and check direct reachable cities
    if (!back.addPath("Nanchang", "Xian", 200, 300)) {
      fail("Add path should return true!");
    }
    
    if (!back.addPath("Nanchang", "Shanghai", 200, 300)) {
      fail("Add path should return true!");
    }
    List<CityDataInterface> direct = back.getDirectReach("Nanchang");
    String result = "[";
    for (int i = 0; i < direct.size() - 1; i++) {
      result = result + direct.get(i).GetName() + ", ";
    }
    result = result + direct.get(direct.size() - 1).GetName() + "]";
    String target = "[Guangzhou, Fuzhou, Hangzhou, Hefei, Xian, Shanghai]";
    
    if (!result.equals(target)) {
      fail("Path is added incorrectly!");
    }
    
    // Case 4: add a 0 distance and cost path between Guangzhou and Beijing, check new shortest path and cheapest cost
    back.addPath("Guangzhou", "Beijing",0,0);
    List<CityDataInterface> shortestPath = back.getShortestPath("Guangzhou", "Beijing");
    String result2 = "[";
    for (int i = 0; i < shortestPath.size() - 1; i++) {
      result2 = result2 + shortestPath.get(i).GetName() + ", ";
    }
    result2 = result2 + shortestPath.get(shortestPath.size() - 1).GetName() + "]";
    String target2 = "[Guangzhou, Beijing]";
    if (!result2.equals(target2)) {
      fail("Path is added incorrectly!");
    }
    
    int cheapestCost = back.getCheapestCost("Guangzhou", "Beijing");
    if (cheapestCost != 0) {
      fail("Cheapest Cost should be 0");
    }
    
  }
  
  /**
   * This method tests the functionality of deletePath()
   */
  @Test
  public void testDeletePath() {
    // Case 1: the path does not exist
    if (back.deletePath("Nanchang", "Shanghai")) {
      fail("Path does not exist.");
    }
    
    // Case 2: Non-exist city
    try {
      back.deletePath("Nanchang", "America");
      fail("Delete path should throw a NoSuchElementException!");
    } catch (NoSuchElementException e) {
      // case handled.
    }
    
    // Case 3: delete new path between two existing cities "Nanchang" to "Guangzhou"
    if (!back.deletePath("Nanchang", "Guangzhou")) {
      fail("Delete Path should return true");
    }
    
    List<CityDataInterface> direct = back.getDirectReach("Nanchang");
    String result = "[";
    for (int i = 0; i < direct.size() - 1; i++) {
      result = result + direct.get(i).GetName() + ", ";
    }
    result = result + direct.get(direct.size() - 1).GetName() + "]";
    String target = "[Fuzhou, Hangzhou, Hefei]";
    
    if (!result.equals(target)) {
      fail("Path is added incorrectly!");
    }
      
    // Case 4: Delete a path between "Changsha" and "Wuhan", the check again the shortest path between "Guangzhou" and "Beijing"
    back.deletePath("Changsha", "Wuhan");
    List<CityDataInterface> shortestPath = back.getShortestPath("Guangzhou", "Beijing");
    String result2 = "[";
    for (int i = 0; i < shortestPath.size() - 1; i++) {
      result2 = result2 + shortestPath.get(i).GetName() + ", ";
    }
    result2 = result2 + shortestPath.get(shortestPath.size() - 1).GetName() + "]";
    String target2 = "[Guangzhou, Changsha, Nanchang, Hefei, Nanjing, Jinan, Beijing]";
    if (!result2.equals(target2)) {
      fail("Path is added incorrectly!");
    }
    
    // Check the direct Reach for Changsha to make sure it's correctly deleted
    List<CityDataInterface> direct2 = back.getDirectReach("Changsha");
    String result3 = "[";
    for (int i = 0; i < direct2.size() - 1; i++) {
      result3 = result3 + direct2.get(i).GetName() + ", ";
    }
    result3 = result3 + direct2.get(direct2.size() - 1).GetName() + "]";
    String target3 = "[Nanchang, Guiyang]";
    if (!result3.equals(target3)) {
      fail("Path is added incorrectly!");
    }
  }
}
