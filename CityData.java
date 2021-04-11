
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

  @Override
  public String GetName() {
    return this.name;
  }

  @Override
  public int GetNumberOfCitiesConnected() {
    return this.numberOfCitiesConnected;
  }

  @Override
  public CityDataInterface[] GetListOfConneectedCity() {
    return this.ListOfConnectedCity;
  }

  @Override
  public int GetDistance(CityDataInterface target) {
    for(int i=0;i<this.numberOfCitiesConnected;i++) {
      if(this.ListOfConnectedCity[i].equals(target))
        return this.ListOfDistance[i];
    }
    return 0;
  }

  @Override
  public int GetCost(CityDataInterface target) {
    for(int i=0;i<this.numberOfCitiesConnected;i++) {
      if(this.ListOfConnectedCity[i].equals(target)) {
        return this.ListOfCost[i];
      }
    }
    return 0;
  }

  @Override
  public int[] GetListOfDistance() {
    return this.ListOfDistance;
  }

  @Override
  public int[] GetListOfCost() {
    return this.ListOfCost;
  }

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

  @Override
  public boolean equals(CityDataInterface other) {
    return this.GetName().equals(other.GetName());
  }

  @Override
  public void SetListOfConnectedCity(CityDataInterface[] array) {
    this.ListOfConnectedCity = array;
    
  }
  


  
}
