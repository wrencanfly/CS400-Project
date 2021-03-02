// --== CS400 File Header Information ==--
// Name: Yingwei Song
// Email: ysong279@wisc.edu
// Team: AF red
// Role: Data Wrangler
// TA: Mu
// Lecturer: Florian
// Notes to Grader: none
import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

public class MovieDataReader implements MovieDataReaderInterface {
    /**
     * Main method to regulate and list the information from .csv
     * @param inputFileReader the file's address
     * @return regulated and listed information from .csv
     * @throws FileNotFoundException if cannot find file
     * @throws IOException if cannot find file
     * @throws DataFormatException if file's data format is wrong
     */
    @Override
    public List<MovieInterface> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException {

        BufferedReader reader = new BufferedReader(inputFileReader);
        List<String> header = splitLine(reader.readLine());
        List<MovieInterface> list = new ArrayList<>();

        //Write the data in to ArrayList
        String line = reader.readLine();
        while (line != null && !"".equals(line)) {
            List<String> row = splitLine(line);

            //DataFormat check
            if (row.size() != header.size()) {
                throw new DataFormatException("Wrong Data format, please check.");
            }
            MovieData md = new MovieData();
            setParams(md,row);  //setParams
            list.add(md);   //combine the line into list
            line = reader.readLine();   //read next line
        }
        return list;
    }

    /**
     * Use regex to make sure comma between ""'s will not split
     * @param line long string with information
     * @return line separated
     */
    public static List<String> splitLine(String line) {
        return new ArrayList(Arrays.asList(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")));
    }

    /**
     * Set parameters
     * @param md MovieData want to set
     * @param row the row in reading
     */
    public static void setParams(MovieData md, List<String> row){
        //set params.
        md.setTitle(row.get(0));
        md.setYear(Integer.parseInt(row.get(2)));
        md.setGenres(Arrays.asList(row.get(3).split(",")));
        md.setDirector(row.get(7));
        md.setDescription(row.get(11));
        md.setAvgVote(Float.parseFloat(row.get(12)));
    }
}