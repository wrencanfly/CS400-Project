//--== CS400 File Header Information ==--
//Name: Yifan Liu
//Email: yliu897@wisc.edu
//Team: red
//Group: AF
//TA: Mu
//Lecturer: Gary
//Notes to Grader: <optional extra notes>
import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

public class BankDataReader{
    /**
     * This method reads in the csv file and put the data into the list of BankData
     * @param inputFileReader a FileReader object that reads in the path name
     * @return a list that contains BankData
     * @throws FileNotFoundException
     * @throws IOException
     * @throws DataFormatException
     */
    public List<BankDataInterface> readDataSet(Reader inputFileReader)
            throws FileNotFoundException, IOException, DataFormatException {
        BufferedReader reader = new BufferedReader(inputFileReader);
        // get the header line
        List<String> headerLine = Arrays.asList(reader.readLine().split("\\s*,\\s*"));
        List<BankDataInterface> resultList = new ArrayList<BankDataInterface>();

        String data = reader.readLine();

        while (data != null && data.length() != 0){
            List<String> row = Arrays.asList(data.split("\\s*,\\s*"));
            if(row.size() != headerLine.size()){
                throw new DataFormatException("data formatting error");
            }
            BankData individualData = new BankData();
            setData(individualData,row);
            resultList.add(individualData);
            data = reader.readLine();
        }
        return resultList;
    }

    /**
     * Set the name, sex. id. phone, age, balance into the BankData
     * @param individualData an instance of BankData to contain the data
     * @param row the list that contains the name, sex. id. phone, age, balance
     */
    public static void setData(BankData individualData, List<String> row){
        individualData.setName(row.get(0));
        individualData.setSex(row.get(1));
        individualData.setID(Integer.parseInt(row.get(2)));
        individualData.setPhoneNum(row.get(3));
        individualData.setAge(Integer.parseInt(row.get(4)));
        individualData.setBalance(Double.parseDouble(row.get(5)));
    }

}
