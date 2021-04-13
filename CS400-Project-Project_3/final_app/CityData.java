// --== CS400 File Header Information ==--
// Name: Yuanqing Cai
// Email: cai92@wisc.edu
// Team: AF Red
// Role: Data Wrangler
// TA: Mu Cai
// Lecturer: Florian
// Notes to Grader: None
public class CityData implements CityDataInterface{
  String name;
  int numberOfCitiesConnected;
  CityDataInterface[] ListOfConnectedCity = new CityDataInterface[100];
  int[] ListOfDistance = new int[100];
  int[] ListOfCost = new int[100];
  public CityData(String name) {
    this.name = name;
    this.numberOfCitiesConnected = 0;
  }
  /**
   * get name of the city
   * @return
   */
  @Override
  public String GetName() {
    return this.name;
  }
  /**
   * get number of connected city
   * @return
   */
  @Override
  public int GetNumberOfCitiesConnected() {
    return this.numberOfCitiesConnected;
  }
  /**
   * get list of connected city
   * @return
   */
  @Override
  public CityDataInterface[] GetListOfConneectedCity() {
    return this.ListOfConnectedCity;
  }
  /**
   * get the distance of path from this city to target
   * return 0 if no path between two city.
   * @param target
   * @return
   */
  @Override
  public int GetDistance(CityDataInterface target) {
    for(int i=0;i<this.numberOfCitiesConnected;i++) {
      if(this.ListOfConnectedCity[i].equals(target))
        return this.ListOfDistance[i];
    }
    return 0;
  }
  /**
   * get the cost of path from this city to target
   * return 0 if no path between two city
   * @param city
   * @return
   */
  @Override
  public int GetCost(CityDataInterface target) {
    for(int i=0;i<this.numberOfCitiesConnected;i++) {
      if(this.ListOfConnectedCity[i].equals(target)) {
        return this.ListOfCost[i];
      }
    }
    return 0;
  }
  /**
   * get list of distance to connected city
   * @return
   */
  @Override
  public int[] GetListOfDistance() {
    return this.ListOfDistance;
  }
  /**
   * get list of cost to connected city
   * @return
   */
  @Override
  public int[] GetListOfCost() {
    return this.ListOfCost;
  }
  /**
   * add the path between this city and target.
   * return true if there is no existed path between two cities and path added
   * return false if there is already a path between two city.
   * @param target
   * @param distance
   * @param cost
   * @return
   */
  @Override
  public boolean AddPath(CityDataInterface target, int distance, int cost) {
    for(int i=0;i<this.numberOfCitiesConnected;i++) {
      if(this.ListOfConnectedCity[i].equals(target))
        return false;
    }
    this.ListOfConnectedCity[this.numberOfCitiesConnected] = target;
    this.ListOfDistance[this.numberOfCitiesConnected] = distance;
    this.ListOfCost[this.numberOfCitiesConnected] = cost;
    this.numberOfCitiesConnected++;
    return true;
  }
  /**
   * This method delete a path between source and target city
   * 
   * @param source start city
   * @param target end city
   * @param distance distance between start and end
   * @param cost cost between start and end
   * @return true if deleted successfully, false otherwise
   */
  @Override
  public boolean DeletePath(CityDataInterface target) {
    for(int i=0;i<this.numberOfCitiesConnected;i++) {
      if(this.ListOfConnectedCity[i].equals(target)) {
        for(int j=i;j<this.numberOfCitiesConnected-1;j++) {
          this.ListOfConnectedCity[j] = this.ListOfConnectedCity[j+1];
          this.ListOfDistance[j] = this.ListOfDistance[j+1];
          this.ListOfCost[j] = this.ListOfCost[j+1];
        }
        this.numberOfCitiesConnected--;
        return true;
      }
    }
    return false;
  }
  /**
   * return true if the two city are the same
   * @param other
   * @return
   */
  @Override
  public boolean equals(CityDataInterface other) {
    return this.GetName().equals(other.GetName());
  }
  /**
   * set the listofconnectedcity to given array
   * @param array
   */
  @Override
  public void SetListOfConnectedCity(CityDataInterface[] array) {
    this.ListOfConnectedCity = array;
    
  }
  


  
}
