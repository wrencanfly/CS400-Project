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
   * Method to put data into genreTable
   * @param movieList movieList want to edit
   * @param genreTable genreTable needed to be edited
   * @param numGenre number of genre
   */
  public void putDataIntoGenreTable(List<MovieInterface> movieList,HashTableMap <String, List<MovieInterface>> genreTable, int numGenre){
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
  }

  /**
   * Method to put data into RatingTable
   * @param movieList movieList movieList want to edit
   */
  public void putDataIntoRatingTable(List<MovieInterface> movieList){
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
    genreTable = new HashTableMap<String, List<MovieInterface>>(numGenre);
    ratingTable = new HashTableMap<Integer, List<MovieInterface>>();
    putDataIntoGenreTable(movieList, genreTable, numGenre);
    putDataIntoRatingTable(movieList);
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
    List<String> header = MovieDataReader.splitLine(rawList[0]);
    List<MovieInterface> movieList = new ArrayList<>();
    /*
     * This for loop is written by the data wrangler
     * It is part of the code from MovieDataReader
     */
    for (int i = 1; i < rawList.length; i++) {
      String line = rawList[i];
      List<String> row = MovieDataReader.splitLine(line);
      //DataFormat check
      if (row.size() != header.size()) {
          throw new DataFormatException("Wrong Data format, please check.");
      }
      MovieData md = new MovieData();
      MovieDataReader.setParams(md,row);  //set params
      movieList.add(md);   //combine the line into list
    }

    allGenre = genreCount(movieList); // list contains all the distinct genre
    int numGenre = allGenre.size();  // the num of distinct genre
    genreTable = new HashTableMap <String, List<MovieInterface>>(numGenre);
    ratingTable = new HashTableMap <Integer, List<MovieInterface>>();
    putDataIntoGenreTable(movieList,genreTable,numGenre);
    putDataIntoRatingTable(movieList);
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
   * Add the genre into the genreInputList 
   * @param genre the genre to be added 
   */
  @Override
  public void addGenre(String genre) {
    if (genre != null) { // To avoid null pointer exception
      genre = genre.strip();
      if (allGenre.contains(genre) && !genreInputList.contains(genre)) { // only adding valid genre
        genreInputList.add(genre); // add the genre into the genreInputList
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
      if(avgRating >= 0 && avgRating <= 10 && !ratingInputList.contains(rating)){  
        // avoid invalid input
        ratingInputList.add(rating);
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
    }  
  }
  private List<MovieInterface> generateMovieList() {
      List<MovieInterface> temp = new ArrayList<MovieInterface>();
      // two situation to return empty list
      if (ratingInputList.size() == 0 && genreInputList.size() == 0) {
        return temp; 
      }
      else if(genreInputList.size() > 3) {
        return temp;
      }
      // add all selected rating movies into temp
      for (String rating : ratingInputList) {
        try {
          Integer key = Integer.parseInt(rating);
          temp.addAll(deepcopy(ratingTable.get(key)));
        } catch (NoSuchElementException e) {
          // 0 and 10 are not in the list
        }
      }
      // choose the genre with all rating
      if(genreInputList.size() == 0) {
        return temp;
      }
      List<MovieInterface> genreList = deepcopy(genreTable.get(genreInputList.get(0)));
      for (int i = 1; i < genreInputList.size(); i++) {
        List<MovieInterface> tempGenreList = deepcopy(genreTable.get(genreInputList.get(i)));
        for(int j = genreList.size() - 1; j >= 0; j--) {
          if(!tempGenreList.contains(genreList.get(j))){
            genreList.remove(j);
          }
        }
      }
      if(ratingInputList.size() == 0) {
        return genreList;
      }
      for(int i = temp.size() - 1; i >= 0; i--) {
        if(!genreList.contains(temp.get(i))) {
          temp.remove(i);
        }
      }
      return temp;

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
    List<MovieInterface> newList = new ArrayList<MovieInterface>();
    for(int i = 0; i < list.size(); i++) {
      newList.add(list.get(i));
    }
    return newList;
    
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
    chosenMovieList = generateMovieList();
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
    chosenMovieList = generateMovieList();
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
  
}