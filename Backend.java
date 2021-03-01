import java.util.zip.DataFormatException;
import java.io.*;
import java.nio.CharBuffer;
import java.util.*;
//--== CS400 File Header Information ==--
//Name: Yifan Liu
//Email: yliu897@wisc.edu
//Team: red
//Group: AF
//TA: Mu
//Lecturer: Gary
//Notes to Grader: <optional extra notes>

public class Backend implements BackendInterface {
  // The hash table that takes genre as key
  private HashTableMap <String, List<MovieInterface>> genreTable;
  // The hash table that takes rating as key
  private HashTableMap<Integer, List<MovieInterface>> ratingTable;
  // The list that stores selected MovieInterface by user's selection 
  private List<MovieInterface> chosenMovieList = new ArrayList<MovieInterface>();
  // The list contains the userInput parsed in from addGenre and addAvgRating
  private List<String> genreInputList = new ArrayList<String>();
  private List<String> ratingInputList = new ArrayList<String>();
  // The ArrayList that contains all the distinct genre
  private List<String> allGenre = new ArrayList<String>();
  /**
   * This is the constructor that will be used by the front end.
   * The constructor that reads in the List of data from the MovieDataReader
   * and stores the data into the specific hash table.  
   * Both of the hash table will use List<MovieInterface> as the value.
   * For example, in the genreTable, one of the key might be "Action", 
   * and the value of it will be a List<MovieInterface> that contains 
   * all the movies with genre "Action"
   * @param fileReader the parameter that reads a Reader object containing the csv path
   * @throws IOException if cannot find file
   * @throws DataFormatException if file's data format is wrong
   */
  public Backend(String[] args) throws IOException, DataFormatException {
    MovieDataReader movie = new MovieDataReader(); 
    List<MovieInterface> movieList = movie.readDataSet(new FileReader(args[0])); 
    allGenre = genreCount(movieList); // list contains all the distinct genre
    int numGenre = allGenre.size();  // the num of distinct genre
    genreTable = new HashTableMap <String, List<MovieInterface>>(numGenre);
    ratingTable = new HashTableMap <Integer, List<MovieInterface>>();
    // put the movie data into the genreTable
    for(int i = 0; i < movieList.size(); i++) {
      for(int j = 0; j < movieList.get(i).getGenres().size(); j++){
        String key = movieList.get(i).getGenres().get(j).strip().replaceAll("\"", "");
        if(!genreTable.containsKey(key)) {
          genreTable.put(key, getGenreList(movieList, key));
          if(genreTable.size() == numGenre) {
            break;
          }
        }
      }
    }
    // put the movie data into the ratingTable
    for(int i = 0; i < movieList.size(); i++) {
      int key = movieList.get(i).getAvgVote().intValue();
      if(!ratingTable.containsKey(key)){
        ratingTable.put(key, getRatingList(movieList, key));
        if(ratingTable.size() == 10) {
          break;
        }
      }
    }
  }
  
  /**
   * This constructor is implemented for the test.
   * The constructor that reads in a StringReader/FileReder as the input and stores the data 
   * into the hash table.
   * And the code to transform the String into the list<MovieInterface>
   * is part of the code in MovieDataReader class. Since this constructor cannot 
   * call the MovieDataReader
   * @param stringReader contains the data formatted as String
   * @throws IOException 
   * @throws DataFormatException 
   */
  public Backend(Reader stringReader) throws IOException, DataFormatException  {
    String result = "";
    int k;
    while((k = stringReader.read())!= -1) {
      result += "" + (char)k;
    };
    // rawList split the String by line
    String [] rawList = result.split("\n");
    List<String> header = splitLine(rawList[0]);
    List<MovieInterface> movieList = new ArrayList<>();
    /*
     * This for loop is written by the data wrangler
     * It is part of the code from MovieDataReader
     */
    for (int i = 1; i < rawList.length; i++) {
      String line = rawList[i];
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
      movieList.add(md);   //combine the line into list
    }
    
    allGenre = genreCount(movieList); // list contains all the distinct genre
    int numGenre = allGenre.size();  // the num of distinct genre
    genreTable = new HashTableMap <String, List<MovieInterface>>(numGenre);
    ratingTable = new HashTableMap <Integer, List<MovieInterface>>();
    // put the movie data into the genreTable
    for(int i = 0; i < movieList.size(); i++) {
      for(int j = 0; j < movieList.get(i).getGenres().size(); j++){
        String key = movieList.get(i).getGenres().get(j).strip().replaceAll("\"", "");
        if(!genreTable.containsKey(key)) {
          genreTable.put(key, getGenreList(movieList, key));
          if(genreTable.size() == numGenre) {
            break;
          }
        }
      }
    }
    // put the movie data into the ratingTable
    for(int i = 0; i < movieList.size(); i++) {
      int key = movieList.get(i).getAvgVote().intValue();
      if(!ratingTable.containsKey(key)){
        ratingTable.put(key, getRatingList(movieList, key));
        if(ratingTable.size() == 10) {
          break;
        }
      }
    }      
  }
  /**
   * Copy from MovieDataReader to format the String into a list
   * This method is written by the data wrangler. 
   * Use regex to make sure comma between ""'s will not split
   * @param line long string with information
   * @return line separated
   */
  private List<String> splitLine(String line) {
      return new ArrayList(Arrays.asList(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")));
  }
  /**
   * The getRatingList loops through the movieList and returns a list of MovieInterface whose
   * rating matches our search.   
   * @param movieList the list that contains the data set 
   * @param rating the rating that we search for 
   * @return the list of MovieInterface contains the specific rating range
   */
  private static List<MovieInterface> getRatingList(List<MovieInterface> movieList, int rating){
    List<MovieInterface> ratingList = new ArrayList<MovieInterface>();
    for(int i = 0; i < movieList.size(); i++) {
      if(movieList.get(i).getAvgVote().intValue() == rating) {
        ratingList.add(movieList.get(i));
      }   
    }
    return ratingList;
  }
  /**
   * Similar to getRatingList, getGenreList loops through the movieList and returns a list of 
   * MovieInterface whose genre matches our search 
   * @param movieList the list that contains the data set 
   * @param genre the genre we search for 
   * @return the list of MovieInterface contains the specific genre
   */
  private static List<MovieInterface> getGenreList(List<MovieInterface> movieList, String genre){
    List<MovieInterface> genreList = new ArrayList<MovieInterface>();
    for(int i = 0; i < movieList.size(); i++) {
      for(int j = 0; j < movieList.get(i).getGenres().size(); j ++) {
        if(movieList.get(i).getGenres().get(j).replaceAll("\"", "").strip().equals(genre)) {
          genreList.add(movieList.get(i));
        }   
      }
    }
    return genreList;
  }
  /**
   * The genreCount method uses a hash set to store all the genres into the set
   * and return a ArrayList created from the hash set 
   * @param movieList the list that contains the data set 
   * @return an ArrayList of all the distinct genres
   */
  private static List<String> genreCount(List<MovieInterface> movieList) {
    HashSet <String> genreCount = new HashSet <String>(); // Avoid duplicates
    for(int i = 0; i < movieList.size(); i++) {
      for(int j = 0; j < movieList.get(i).getGenres().size(); j++) {
        genreCount.add(movieList.get(i).getGenres().get(j).replaceAll("\"", "").strip());
      }
    }
    return new ArrayList<String>(genreCount);
  }
  /**
   * Add the genre into the genreInputList and store the selected MovieInterface
   * into the chosenMovieList
   * @param genre the genre to be added 
   */
  @Override
  public void addGenre(String genre) {
    if(genre != null) { // To avoid null pointer exception
      genre = genre.strip();
      if (allGenre.contains(genre)) { // only adding valid genre
        genreInputList.add(genre); // add the genre into the genreInputList
        if (chosenMovieList.size() == 0) {
          chosenMovieList = deepcopy(genreTable.get(genre));
        } else {
          List<MovieInterface> temp = genreTable.get(genre);
          for (int i = chosenMovieList.size() - 1; i >= 0; i--) {
            if (!temp.contains(chosenMovieList.get(i))) {
              chosenMovieList.remove(i);
            }
          }
        }
      }
    }
  }
  /**
   * Add the rating into the ratingInputList and store the selected MovieInterface
   * into the chosenMovieList
   * @param rating the rating to be added 
   */
  @Override
  public void addAvgRating(String rating) {
    if(rating != null) {
      int avgRating = Integer.parseInt(rating);
      if(avgRating >= 0 && avgRating <= 10){  // avoid invalid input
        ratingInputList.add(rating);
        try {
        chosenMovieList.addAll(deepcopy(ratingTable.get(avgRating)));
        }catch(NoSuchElementException e) {
           // 0 and 10 are not among the key
        }
      }  
    }
        
  }
  /**
   * Remove genre from the the genreInputList and generate the correct 
   * chosenMovieList based on the genre selected
   * @param genre the genre to be removed
   */
  @Override
  public void removeGenre(String genre) {
    if(genre != null) {
      genre = genre.strip();
      genreInputList.remove(genre); 
      if(genreInputList.size() > 3) {
        chosenMovieList.removeAll(chosenMovieList);
      }
      else if(genreInputList.size() == 0) {
        chosenMovieList.removeAll(chosenMovieList);
      }
      else {
        chosenMovieList = deepcopy(genreTable.get(genreInputList.get(0)));
        for(int i = 1; i < genreInputList.size(); i++) {
          for(int j = 0; i < chosenMovieList.size(); j++) {
            if(!genreTable.get(genreInputList.get(i)).contains(chosenMovieList.get(i))) {
              chosenMovieList.remove(i);
            }
          }         
        }
      }
    }
  }
  
  /**
   * Remove rating from the the ratingInputList and generate the correct 
   * chosenMovieList based on the genre selected
   * @param genre the genre to be removed
   */
  @Override
  public void removeAvgRating(String rating) {
    if(rating != null) {
      ratingInputList.remove(rating);
      int avgRating = Integer.parseInt(rating);
      for(int i = chosenMovieList.size() - 1; i >= 0; i--) {
        if(chosenMovieList.get(i).getAvgVote().intValue() == avgRating) {
          chosenMovieList.remove(chosenMovieList.get(i));         
        }
      }
    }   
  }
  /**
   * A helper method to enable deep copy of the List structure
   * Since ArrayList cannot be directly copied using = or clone,
   * this method copy the content of the given ArrayList and return a
   * new List containing the same content
   * This method make it possible for altering the content of chosenMovieList
   * without making any changes to the list inside the hash table 
   * @param list list of MovieInterface to copy from
   * @return a new ArrayList containing the exact contents of list param
   */
  private static List<MovieInterface> deepcopy(List<MovieInterface> list) {
    List<MovieInterface> temp = new ArrayList<MovieInterface>();
    for(int i = 0; i < list.size(); i++) {
      temp.add(list.get(i));
    }
    return temp;
    
  }
  /**
   * 
   * @return the genreInputList that contains the genres the user select
   */
  @Override
  public List<String> getGenres() {
    return genreInputList;
  } 
  /**
   * 
   * @return return the ratingInputList that contains the rating the user select
   */
  @Override
  public List<String> getAvgRatings() {
    return ratingInputList;
  }
  
  /**
   * Get the number of movie currently in the chosenMovieList
   * @return the size of chosenMovieList
   */
  @Override
  public int getNumberOfMovies() {
    return chosenMovieList.size();
  }
  /**
   * 
   * @return the list contains all the distinct genre
   */
  @Override
  public List<String> getAllGenres() {
    return allGenre;
  }
  /**
   * The getThreeMovies method will return a list of movie starting from 
   * the startingIndex and ends at startingIndex + 3 if startingIndex + 3 is not 
   * out of bounds. Otherwise, it will return a list of movie start from startingIndex
   * and ends at last index of the list. It might return a empty list if there is 
   * no element in the chosenMovieList or the startingIndex is out of bounds
   * @param startingIndex the first index to slice through the list
   * @return a list of three movies starting at (and including) the movie 
   * at the startingIndex of the resulting set ordered by average movie rating 
   * in descending order.
   */
  @Override
  public List<MovieInterface> getThreeMovies(int startingIndex) {
    List <MovieInterface> output = new ArrayList<MovieInterface>();
    // sort the chosenMovieList so that its rating 
    Collections.sort(chosenMovieList);
    // make sure that the startingIndex is valid
    // (non negative and within the size of chosenMovieList)
    if(startingIndex > chosenMovieList.size() - 1) {
      return output;
    }
    if(chosenMovieList.size() - startingIndex > 3) {
      output = chosenMovieList.subList(startingIndex, startingIndex + 3);
    }
    else {
      output = chosenMovieList.subList(startingIndex, chosenMovieList.size());
    }
    return output;
  }
  /**
   * This method clears chosenMovieList, genreInputList, and ratingInputList.
   * It can be used by the front end developer to switch between different mode.
   * It will clear all the user selection of genres and ratings and the the list 
   * containing the selected movies.
   */
  public void clear() {
    chosenMovieList.removeAll(chosenMovieList);
    genreInputList.removeAll(genreInputList);
    ratingInputList.removeAll(ratingInputList);
  }
  public static void main(String[] args) throws IOException, DataFormatException {
    Reader file = new FileReader("/Users/iveniven/eclipse-workspace/P1/src/movies.csv");
    Backend back = new Backend(file);
    for(int i = 0; i <= 10; i++) {
      back.addAvgRating(String.valueOf(i));
    }
    for(int i = 2; i <= 9; i++) {
      back.removeAvgRating(String.valueOf(i));
    }
    System.out.println(back.getThreeMovies(0));
  }
}
