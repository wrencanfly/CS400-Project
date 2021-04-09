import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;

public class CityDataReader {
  public List<CityDataInterface> readingCityDataSet(Reader CityInputFileReader)
          throws FileNotFoundException, IOException {
    BufferedReader reader = new BufferedReader(CityInputFileReader);
    List<CityDataInterface> resultList = new ArrayList<CityDataInterface>();
    String data = reader.readLine();
    while(data != null && data.length() != 0) {
      CityData individualData = new CityData(data);
      resultList.add(individualData);
      data = reader.readLine();
    }
    return resultList;
  }
  
  public void readingPathDataSet(Reader PathInputFileReader, List<CityDataInterface> cityList) 
          throws FileNotFoundException, IOException, DataFormatException {
    BufferedReader reader = new BufferedReader(PathInputFileReader);
    List<String> headerLine = Arrays.asList(reader.readLine().split("\\s*,\\s*"));
    String data = reader.readLine();
    while(data != null && data.length() != 0) {
      List<String> row = Arrays.asList(data.split("\\s*,\\s*"));
      if(row.size() != headerLine.size()) {
        throw new DataFormatException("Data format error");
      }
      CityDataInterface start=null;
      CityDataInterface target=null;
      for(int i=0;i<cityList.size();i++) {
        if(cityList.get(i).GetName().equals(row.get(0))) {
          start = cityList.get(i);
          break;
        }
      }
      for(int i=0;i<cityList.size();i++) {
        if(cityList.get(i).GetName().equals(row.get(1))) {
          target = cityList.get(i);
          break;
        }
      }
      start.AddPath(target, Integer.parseInt(row.get(2)), Integer.parseInt(row.get(3)));
    }
  }
 /* public static void main(String args[]) throws FileNotFoundException, IOException {
    CityDataReader test = new CityDataReader();

    FileReader cityfile = new FileReader("src/City.csv");
    List<CityDataInterface> a = test.readingCityDataSet(cityfile);
    for(int i=0;i<a.size();i++) {
      System.out.println(a.get(i).GetName());
    }
    System.out.println(a.size());
  }*/
}
