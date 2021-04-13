// --== CS400 File Header Information ==--
// Name: Yuanqing Cai
// Email: cai92@wisc.edu
// Team: AF Red
// Role: Data Wrangler
// TA: Mu Cai
// Lecturer: Florian
// Notes to Grader: None
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class DataWranglerTest { 
	List<CityDataInterface> cityList;
	 /**
	  * Setup the CityDataReader and Read the data from csv File and return the city list;
	  * @throws FileNotFoundException
	  * @throws IOException
	  * @throws DataFormatException
	  */
	@BeforeEach
	public  void setup() throws FileNotFoundException, IOException, DataFormatException{
		CityDataReader Test = new CityDataReader();
		FileReader city = new FileReader("./CityDataSet.csv");
		FileReader path = new FileReader("./PathDataSet.csv");
		cityList = Test.readingCityDataSet(city);//read city data
		Test.readingPathDataSet(path, cityList); //read path data
	}
	/**
	 * Test the total number of City added to the List is 30
	 */
	@Test
	public void TestNumberOfCity() {
		assertEquals(cityList.size(),30);
	}
	/**
	 * Test the Name of the City in the City List is correct (GetName() method)
	 */
	@Test
	public void TestGetNameOfCityData() {
		assertEquals(cityList.get(0).GetName(),"Beijing");
		assertEquals(cityList.get(29).GetName(),"Wulumuqi");
		assertEquals(cityList.get(5).GetName(),"Changchun");
	}
	/**
 	 * Test the GetListofConnectedCity() return the correct list of connected City (choose Nanjin to Test)
	 */
	@Test
	public void TestGetListOfConnectedCity() {
		CityDataInterface[] connectedCity = cityList.get(6).GetListOfConneectedCity();
		assertEquals(connectedCity[0].GetName(),"Hefei");
		assertEquals(connectedCity[1].GetName(),"Jinan");
	}
	/**
	 * Test the GetListofDistance() return the correct list of distance (choose Nanjin to Test)
	 */
	@Test
	public void TestGetListOfDistance() {
		int[] connectedCityDistance = cityList.get(6).GetListOfDistance();
		assertEquals(connectedCityDistance[0],600);
		assertEquals(connectedCityDistance[1],400);
	}
	/**
	 * Test the GetListOfCost() return the correct list of cost (choose Nanjin to Test)
	 */
	@Test
	public void TestGetListOfCost() {
		int[] connectedCityCost = cityList.get(6).GetListOfCost();
		assertEquals(connectedCityCost[0],300);
		assertEquals(connectedCityCost[1],200);
	}

}
