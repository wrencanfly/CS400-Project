import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

public class MovieDataReader implements MovieDataReaderInterface {
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

            //set params.
            md.setTitle(row.get(0));
            md.setYear(Integer.parseInt(row.get(2)));
            md.setGenres(Arrays.asList(row.get(3).split(",")));
            md.setDirector(row.get(7));
            md.setDescription(row.get(11));
            md.setAvgVote(Float.parseFloat(row.get(12)));

            list.add(md);   //combine the line into list
            line = reader.readLine();   //read next line
        }
        return list;
    }

    private List<String> splitLine(String line) {
        //using regex, to make sure comma between ""'s will not split
        return new ArrayList(Arrays.asList(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")));
    }
}
