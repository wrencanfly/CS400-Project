import java.util.zip.DataFormatException;
import java.io.*;
import java.util.*;
//--== CS400 File Header Information ==--
//Name: Yifan Liu
//Email: yliu897@wisc.edu
//Team: red
//Group: AF
//TA: Mu 
//Lecturer: Gary
//Notes to Grader: <optional extra notes>

/**
 * @author iveniven
 *
 */
public class Backend implements BackendInterface {
  private HashTableMap <String, List<MovieInterface>> genreTable;
  private HashTableMap<Integer, List<MovieInterface>> ratingTable;
  private List<MovieInterface> chosenMovieList = new ArrayList<MovieInterface>();
  private List<String> userInputList = new ArrayList<String>();
  private List<String> allGenre = new ArrayList<String>();
  /**
   * The constructor that reads in the List of data from the MovieDataReader
   * and stores the data into the hash table.  
   * @param args the parameter that reads in the command line argument
   * @throws IOException if cannot find file
   * @throws DataFormatException if file's data format is wrong
   */
  public Backend(String[] args) throws IOException, DataFormatException {
    MovieDataReader movie = new MovieDataReader(); 
    List<MovieInterface> movieList = movie.readDataSet(new FileReader(args[0])); //fixeme 
    allGenre = genreCount(movieList); // list contains all the distinct genre
    int numGenre = allGenre.size();  // the num of distinct genre
    genreTable = new HashTableMap <String, List<MovieInterface>>(numGenre);
    ratingTable = new HashTableMap <Integer, List<MovieInterface>>();
    // put the movie data into the hash table with the key genre
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
    // put the movie data into the hash table with the key rating
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
   * The genreCount method uses a hash set to count the number of different genres
   * in the movieList so that a hash table that matched its size can be created 
   * @param movieList the list that contains the data set 
   * @return the number of distinct genre
   */
  private static List<String> genreCount(List<MovieInterface> movieList) {
    HashSet <String> genreCount = new HashSet <String>(); // Count the number of genre
    for(int i = 0; i < movieList.size(); i++) {
      for(int j = 0; j < movieList.get(i).getGenres().size(); j++) {
        genreCount.add(movieList.get(i).getGenres().get(j).replaceAll("\"", "").strip());
      }
    }
    return new ArrayList<String>(genreCount);
  }
  
  @Override
  public void addGenre(String genre) {
    if(genre != null) { // To avoid null pointer exception
      genre = genre.strip();
      userInputList.add(genre); // add the genre into the outputList
      if(chosenMovieList.size() == 0) { 
        chosenMovieList = genreTable.get(genre);
      }
      else {
        List<MovieInterface> temp = genreTable.get(genre);
        for(int i = chosenMovieList.size() - 1; i >= 0; i--) {
            if(!temp.contains(chosenMovieList.get(i))) {
              chosenMovieList.remove(i);
            }
          
        }
      }     
    }  
  }
  @Override
  public void addAvgRating(String rating) {
    if(rating != null) {
      userInputList.add(rating);
      int avgRating = Integer.parseInt(rating);
      if(chosenMovieList.size() == 0) { 
        chosenMovieList = ratingTable.get(avgRating);
      }
      else {
        List<MovieInterface> temp = ratingTable.get(avgRating);
        for(int i = chosenMovieList.size() - 1; i >= 0; i--) {
          if(!temp.contains(chosenMovieList.get(i))) {
            chosenMovieList.remove(i);
          }
        }
      }
    }    
  }
  //  
  @Override
  public void removeGenre(String genre) {
//    if(genre != null) {
//      genre = genre.strip();
//      userInputList.remove(genre); 
//      List<MovieInterface> temp = genreTable.get(genre);
//      for(int i = temp.size() - 1; i >= 0; i--) {
//        if(!chosenMovieList.contains(temp.get(i))) {
//          chosenMovieList.add(temp.get(i));
//        }
//      }
//    }
    
  }
  //   
  @Override
  public void removeAvgRating(String rating) {
//    if(rating != null) {
//      userInputList.remove(rating);
//      int avgRating = Integer.parseInt(rating);
//      List<MovieInterface> temp = ratingTable.get(avgRating);
//      for(int i = temp.size() - 1; i >= 0; i--) {
//        if(!chosenMovieList.contains(temp.get(i))) {
//          chosenMovieList.add(temp.get(i));
//        }
//      }
//    }   
  }

  @Override
  public List<String> getGenres() {
    return userInputList;
  }

  @Override
  public List<String> getAvgRatings() {
    return userInputList;
  }

  @Override
  public int getNumberOfMovies() {
    return chosenMovieList.size();
  }

  @Override
  public List<String> getAllGenres() {
    return allGenre;
  }

  @Override
  public List<MovieInterface> getThreeMovies(int startingIndex) {
    Collections.sort(chosenMovieList);
    
    return null;
  }

}
