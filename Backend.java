import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

public class Backend implements BackendInterface{

  List<CityDataInterface> cityList;
  CS400Graph<CityDataInterface> distanceGraph;
  CS400Graph<CityDataInterface> costGraph;
  
  private void createDistanceGraph(List<CityDataInterface> cityList) {
    for (int i = 0; i < cityList.size(); i++) {
      if (!distanceGraph.containsVertex(cityList.get(i))) {
        distanceGraph.insertVertex(cityList.get(i));
      }
    }
    
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
  
  private void createCostGraph(List<CityDataInterface> cityList) {
    for (int i = 0; i < cityList.size(); i++) {
      if (!costGraph.containsVertex(cityList.get(i))) {
        costGraph.insertVertex(cityList.get(i));
      }
    }
    
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
  
  public Backend(Reader CityInputFileReader, Reader PathInputFileReader)
      throws FileNotFoundException, IOException, DataFormatException {
    try {
      CityDataReader city = new CityDataReader();
      this.cityList = city.readingCityDataSet(CityInputFileReader);
      city.readingPathDataSet(PathInputFileReader, cityList);
      distanceGraph = new CS400Graph<CityDataInterface>();
      costGraph = new CS400Graph<CityDataInterface>();
      createDistanceGraph(cityList);
      createCostGraph(cityList);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    } catch (IOException e) {
      throw new IOException();
    }
  }
  
  @Override
  public List<CityDataInterface> getShortestPath(String start, String end) {
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
    
    return distanceGraph.shortestPath(startV, endV);
    
  }
  
  @Override
  public int getShortestDistance(String start, String end) {
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
    
    return distanceGraph.getPathCost(startV, endV);
  }

  @Override
  public List<CityDataInterface> getCheapestPath(String start, String end) {
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
    
    return costGraph.shortestPath(startV, endV);
  }

  @Override
  public int getCheapestCost(String start, String end) {
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
    
    return costGraph.getPathCost(startV, endV);
  }
  
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
    
    return startV.AddPath(endV, distance, cost);
  }

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
    
    List<CityDataInterface> connected = getDirectReach(source);
    int originalSize = connected.size();
    for (int i = 0; i < connected.size(); i++) {
      if (connected.get(i).equals(endV)) {
        connected.remove(i);
      }
    }
    
    if (connected.size() == originalSize) {
      return false;
    }
    
    CityDataInterface[] newArray = new CityDataInterface[100];
    for (int i = 0; i < connected.size(); i++) {
      newArray[i] = connected.get(i);
    }
    startV.SetListOfConnectedCity(newArray);
    
    return true;
  }
  
}