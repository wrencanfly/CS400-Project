// --== CS400 File Header Information ==--
// Name: Yuanqing Cai
// Email: cai92@wisc.edu
// Team: AF Red
// Role: Data Wrangler
// TA: Mu Cai
// Lecturer: Florian
// Notes to Grader: None
public interface CityDataInterface {
  
  /**
   * get name of the city
   * @return
   */
  public String GetName();
  
  /**
   * get number of connected city
   * @return
   */
  public int GetNumberOfCitiesConnected();
  
  /**
   * get list of connected city
   * @return
   */
  public CityDataInterface[] GetListOfConneectedCity();
  
  /**
   * get the distance of path from this city to target
   * return 0 if no path between two city.
   * @param target
   * @return
   */
  public int GetDistance(CityDataInterface target);
  
  /**
   * get the cost of path from this city to target
   * return 0 if no path between two city
   * @param city
   * @return
   */
  public int GetCost(CityDataInterface target);
  
  /**
   * get list of distance to connected city
   * @return
   */
  public int[] GetListOfDistance();
  
  /**
   * get list of cost to connected city
   * @return
   */
  public int[] GetListOfCost();
  
  /**
   * add the path between this city and target.
   * return true if there is no existed path between two cities and path added
   * return false if there is already a path between two city.
   * @param target
   * @param distance
   * @param cost
   * @return
   */
  public boolean AddPath(CityDataInterface target, int distance, int cost);
  
  /**
   * delete the path from this city to target.
   * return true if the path exist and being deleted
   * return false if the path does exist.
   * @param target
   * @return
   */
  public boolean DeletePath(CityDataInterface target);
  
  /**
   * return true if the two city are the same
   * @param other
   * @return
   */
  public boolean equals(CityDataInterface other);
  /**
   * set the listofconnectedcity to given array
   * @param array
   */
  public void SetListOfConnectedCity(CityDataInterface[] array);
}
