// --== CS400 File Header Information ==--
// Name: Steven Yang
// Email: yang558@wisc.edu
// Team: AF Red
// Role: Backend
// TA: Mu
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

import java.util.List;

/**
 * This is the backend interface that will be used by frontend
 * 
 * @author Steven Yang
 *
 */
public interface BackendInterface {
  /**
   * This method returns the shortest Path between start and end
   * 
   * @param start start city
   * @param end end city
   * @return the shortest Path between start and end
   */
  public List<CityDataInterface> getShortestPath(String start, String end);
  
  /**
   * This method returns the cheapest Path between start and end
   * 
   * @param start start city
   * @param end end city
   * @return the cheapest Path between start and end
   */
  public List<CityDataInterface> getCheapestPath(String start, String end);
  
  /**
   * This method returns the shortest distance between start and end
   * 
   * @param start start city
   * @param end end city
   * @return the shortest distance between start and end
   */
  public int getShortestDistance(String start, String end);
  /**
   * This method returns the cheapest cost between start and end
   * 
   * @param start start city
   * @param end end city
   * @return the cheapest cost between start and end
   */
  public int getCheapestCost(String start, String end);
  
  /**
   * This method returns the list of all directly reachable cities of parameter city
   * 
   * @param city given city
   * @return the list of all directly reachable cities of parameter city
   */
  public List<CityDataInterface> getDirectReach(String city);
  
  /**
   * This method add a path between source and target city
   * 
   * @param source start city
   * @param target end city
   * @param distance distance between start and end
   * @param cost cost between start and end
   * @return true if added successfully, false otherwise
   */
  public boolean addPath(String source, String target, int distance, int cost);
  
  /**
   * This method delete a path between source and target city
   * 
   * @param source start city
   * @param target end city
   * @param distance distance between start and end
   * @param cost cost between start and end
   * @return true if deleted successfully, false otherwise
   */
  public boolean deletePath(String source, String target);
}
