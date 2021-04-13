// --== CS400 File Header Information ==--
// Name: Steven Yang
// Email: yang558@wisc.edu
// Team: AF Red
// Role: Backend
// TA: Mu
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

/**
 * The class Backend implements BackendInterface with several methods to modify the data.
 * 
 * @author Steven Yang
 *
 */
public class Backend implements BackendInterface{

  List<CityDataInterface> cityList;
  CS400Graph<CityDataInterface> distanceGraph;
  CS400Graph<CityDataInterface> costGraph;
  
  /**
   * Create a graph with distance
   * 
   * @param cityList The city list
   */
  private void createDistanceGraph(List<CityDataInterface> cityList) {
    // insert all the city vertex
    for (int i = 0; i < cityList.size(); i++) {
      if (!distanceGraph.containsVertex(cityList.get(i))) {
        distanceGraph.insertVertex(cityList.get(i));
      }
    }
    
    // insert all the edges by distance
    for (int i = 0; i < cityList.size(); i++) {
      CityDataInterface[] listOfConnected = cityList.get(i).GetListOfConneectedCity();
      int sizeOfConnectedArray = 0;
      for (int h = 0; h < listOfConnected.length; h++) {
        if (listOfConnected[h] != null) {
          sizeOfConnectedArray = sizeOfConnectedArray + 1;
        } else {
          break;
        }
      }
      
      int[] listOfDistance = cityList.get(i).GetListOfDistance();
      for (int j = 0; j < sizeOfConnectedArray; j++) {
        distanceGraph.insertEdge(cityList.get(i), listOfConnected[j], listOfDistance[j]);
      }
    }
  }
  
  /**
   * Create a graph with cost
   * 
   * @param cityList The city list
   */
  private void createCostGraph(List<CityDataInterface> cityList) {
    // insert all the city vertex
    for (int i = 0; i < cityList.size(); i++) {
      if (!costGraph.containsVertex(cityList.get(i))) {
        costGraph.insertVertex(cityList.get(i));
      }
    }
    
    // insert all the edges by cost
    for (int i = 0; i < cityList.size(); i++) {
      CityDataInterface[] listOfConnected = cityList.get(i).GetListOfConneectedCity();
      int sizeOfConnectedArray = 0;
      for (int h = 0; h < listOfConnected.length; h++) {
        if (listOfConnected[h] != null) {
          sizeOfConnectedArray = sizeOfConnectedArray + 1;
        } else {
          break;
        }
      }
      int[] listOfCost = cityList.get(i).GetListOfCost();
      for (int j = 0; j < sizeOfConnectedArray; j++) {
        costGraph.insertEdge(cityList.get(i), listOfConnected[j], listOfCost[j]);
      }
    }
  }
  
  /**
   * This method is a constructor of backend class
   * 
   * @param CityInputFileReader city data
   * @param PathInputFileReader path data
   * @throws FileNotFoundException when file not found
   * @throws IOException when IOExceptions
   * @throws DataFormatException when DataFormatException
   */
  public Backend(Reader CityInputFileReader, Reader PathInputFileReader)
      throws FileNotFoundException, IOException, DataFormatException {

    CityDataReader city = new CityDataReader();
    this.cityList = city.readingCityDataSet(CityInputFileReader);
    city.readingPathDataSet(PathInputFileReader, cityList);
    distanceGraph = new CS400Graph<CityDataInterface>();
    costGraph = new CS400Graph<CityDataInterface>();
    createDistanceGraph(cityList);
    createCostGraph(cityList);
  }
  
  /**
   * This method returns the shortest Path between start and end
   * 
   * @param start start city
   * @param end end city
   * @return the shortest Path between start and end
   * @throws NoSuchElementException when no path from start to end can be found
   *         including when no vertex containing start or end can be found
   */
  @Override
  public List<CityDataInterface> getShortestPath(String start, String end) {
    // find vertex of start and end 
    CityDataInterface startV = null;
    CityDataInterface endV = null;
    
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(start)) {
        startV = cityList.get(i);
      }
    }
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(end)) {
        endV = cityList.get(i);
      }
    }
    
    if (startV == null || endV == null) {
      throw new NoSuchElementException();
    }
    
    return distanceGraph.shortestPath(startV, endV);
    
  }
  
  /**
   * This method returns the shortest distance between start and end
   * 
   * @param start start city
   * @param end end city
   * @return the shortest distance between start and end
   * @throws NoSuchElementException when no path from start to end can be found
   *         including when no vertex containing start or end can be found
   */
  @Override
  public int getShortestDistance(String start, String end) {
    // find vertex of start and end 
    CityDataInterface startV = null;
    CityDataInterface endV = null;
    
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(start)) {
        startV = cityList.get(i);
      }
    }
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(end)) {
        endV = cityList.get(i);
      }
    }
    
    if (startV == null || endV == null) {
      throw new NoSuchElementException();
    }
    
    return distanceGraph.getPathCost(startV, endV);
  }

  /**
   * This method returns the cheapest Path between start and end
   * 
   * @param start start city
   * @param end end city
   * @return the cheapest Path between start and end
   * @throws NoSuchElementException when no path from start to end can be found
   *         including when no vertex containing start or end can be found
   */
  @Override
  public List<CityDataInterface> getCheapestPath(String start, String end) {
    // find vertex of start and end 
    CityDataInterface startV = null;
    CityDataInterface endV = null;
    
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(start)) {
        startV = cityList.get(i);
      }
    }
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(end)) {
        endV = cityList.get(i);
      }
    }
    
    if (startV == null || endV == null) {
      throw new NoSuchElementException();
    }
    
    return costGraph.shortestPath(startV, endV);
  }

  /**
   * This method returns the cheapest cost between start and end
   * 
   * @param start start city
   * @param end end city
   * @return the cheapest cost between start and end
   * @throws NoSuchElementException when no path from start to end can be found
   *         including when no vertex containing start or end can be found
   */
  @Override
  public int getCheapestCost(String start, String end) {
    // find vertex of start and end 
    CityDataInterface startV = null;
    CityDataInterface endV = null;
    
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(start)) {
        startV = cityList.get(i);
      }
    }
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(end)) {
        endV = cityList.get(i);
      }
    }
    
    if (startV == null || endV == null) {
      throw new NoSuchElementException();
    }
    
    return costGraph.getPathCost(startV, endV);
  }
  
  /**
   * This method returns the list of all directly reachable cities of parameter city
   * 
   * @param city given city
   * @return the list of all directly reachable cities of parameter city
   * @throws NoSuchElementException when no vertex containing city can be found
   */
  @Override
  public List<CityDataInterface> getDirectReach(String city) {
    List<CityDataInterface> directReach = new ArrayList<CityDataInterface>();
    CityDataInterface startV = null;
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(city)) {
        startV = cityList.get(i);
      }
    }
    
    if (startV == null) {
      throw new NoSuchElementException();
    }
    
    CityDataInterface[] array = startV.GetListOfConneectedCity();
    int sizeOfConnectedArray = 0;
    for (int h = 0; h < array.length; h++) {
      if (array[h] != null) {
        sizeOfConnectedArray = sizeOfConnectedArray + 1;
      } else {
        break;
      }
    }
    
    for (int i = 0; i < sizeOfConnectedArray; i++) {
      directReach.add(array[i]);
    }
    
    return directReach;
  }

  /**
   * This method add a path between source and target city
   * 
   * @param source start city
   * @param target end city
   * @param distance distance between start and end
   * @param cost cost between start and end
   * @return true if added successfully (no such path already exists), false otherwise
   * @throws NoSuchElementException no vertex containing start or end can be found
   */
  @Override
  public boolean addPath(String source, String target, int distance, int cost) {
    CityDataInterface startV = null;
    CityDataInterface endV = null;
    
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(source)) {
        startV = cityList.get(i);
      }
    }
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(target)) {
        endV = cityList.get(i);
      }
    }
    
    if (startV == null || endV == null) {
      throw new NoSuchElementException();
    }
    
    if(distanceGraph.insertEdge(startV, endV, distance) && costGraph.insertEdge(startV, endV, cost)) {
      List<CityDataInterface> connected = getDirectReach(source);
      connected.add(endV);
      CityDataInterface[] newArray = new CityDataInterface[100];
      for (int i = 0; i < connected.size(); i++) {
        newArray[i] = connected.get(i);
      }
      startV.SetListOfConnectedCity(newArray);
      
      return true;
    }
    return false;
  }

  /**
   * This method delete a path between source and target city
   * 
   * @param source start city
   * @param target end city
   * @param distance distance between start and end
   * @param cost cost between start and end
   * @return true if deleted successfully (such path exists), false otherwise
   * @throws NoSuchElementException no vertex containing start or end can be found
   */
  @Override
  public boolean deletePath(String source, String target) {
    CityDataInterface startV = null;
    CityDataInterface endV = null;
    
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(source)) {
        startV = cityList.get(i);
      }
    }
    for (int i = 0; i < cityList.size(); i++) {
      if (cityList.get(i).GetName().equals(target)) {
        endV = cityList.get(i);
      }
    }
    
    if (startV == null || endV == null) {
      throw new NoSuchElementException();
    }
    
    if(distanceGraph.removeEdge(startV, endV) && costGraph.removeEdge(startV, endV)) {
      List<CityDataInterface> connected = getDirectReach(source);
      for (int i = 0; i < connected.size(); i++) {
        if(connected.get(i).equals(endV)) {
          connected.remove(i);
        }
      }
      CityDataInterface[] newArray = new CityDataInterface[100];
      for (int i = 0; i < connected.size(); i++) {
        newArray[i] = connected.get(i);
      }
      startV.SetListOfConnectedCity(newArray);
      
      return true;
    }
    
    return false;
  }
  
}
