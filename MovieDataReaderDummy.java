import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// --== CS400 File Header Information ==--
// Author: CS400 Course Staff
// Email: heimerl@cs.wisc.edu / dahl@cs.wisc.edu
// Notes: This dummy class is part of the starter archive for Project One
//        in spring 2021. You can extend it to work on your Project One Final
//        App.
public class MovieDataReaderDummy implements MovieDataReaderInterface {

    /**
     * Method that reades movie data in CSV format from the Redaer provided. The dummy implementations
     * will always return the same 3 sets of movies.
     */
    @Override
    public List<MovieInterface> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException {
        ArrayList<MovieInterface> movies = new ArrayList<MovieInterface>();
        movies.add(new MovieInterface() {

            @Override
            public String getTitle() {
                return "Plan 9 from Outer Spacce";
            }

            @Override
            public Integer getYear() {
                return 1959;
            }

            @Override
            public List<String> getGenres() {
                return Arrays.asList(new String[] { "Action", "Comedy" });
            }

            @Override
            public String getDirector() {
                return "Ed Wood";
            }

            @Override
            public String getDescription() {
                return "Residents of California's San Fernando Valley are under attack by flying saucers from outer space.";
            }

            @Override
            public Float getAvgVote() {
                return 5.3f;
            }

            @Override
            public int compareTo(MovieInterface otherMovie) {
                if (this.getTitle().equals(otherMovie.getTitle())) {
                    return 0;
                    // sort by rating
                } else if (this.getAvgVote() < otherMovie.getAvgVote()) {
                    return +1;
                } else {
                    return -1;
                }
            }

        });

        movies.add(new MovieInterface() {

            @Override
            public String getTitle() {
                return "La battaglia di Long Tan";
            }

            @Override
            public Integer getYear() {
                return 2019;
            }

            @Override
            public List<String> getGenres() {
                return Arrays.asList(new String[] { "Action", "Drama", "War" });
            }

            @Override
            public String getDirector() {
                return "Kriv Stenders";
            }

            @Override
            public String getDescription() {
                return "In August 1966, in a Vietnamese rubber plantation called Long Tan, 108 young and inexperienced Australian and New Zealand soldiers are fighting for their lives against 2500 North Vietnamese and Viet Cong soldiers.";
            }

            @Override
            public Float getAvgVote() {
                return 6.8f;
            }

            @Override
            public int compareTo(MovieInterface otherMovie) {
                if (this.getTitle().equals(otherMovie.getTitle())) {
                    return 0;
                    // sort by rating
                } else if (this.getAvgVote() < otherMovie.getAvgVote()) {
                    return +1;
                } else {
                    return -1;
                }
            }

        });

        movies.add(new MovieInterface() {

            @Override
            public String getTitle() {
                return "Fatima";
            }

            @Override
            public Integer getYear() {
                return 2020;
            }

            @Override
            public List<String> getGenres() {
                return Arrays.asList(new String[] { "Drama" });
            }

            @Override
            public String getDirector() {
                return "Marco Pontecorvo";
            }

            @Override
            public String getDescription() {
                return "Based on historical events, three young shepherds in F??tima, Portugal, report visions of the Virgin Mary, inspiring believers and angering officials of the Church and the government, who try to force them to recant their story.";
            }

            @Override
            public Float getAvgVote() {
                return 6.3f;
            }

            @Override
            public int compareTo(MovieInterface otherMovie) {
                if (this.getTitle().equals(otherMovie.getTitle())) {
                    return 0;
                    // sort by rating
                } else if (this.getAvgVote() < otherMovie.getAvgVote()) {
                    return +1;
                } else {
                    return -1;
                }
            }

        });

        return movies;
    }

}
