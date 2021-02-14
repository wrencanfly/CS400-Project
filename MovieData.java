import java.util.List;

public class MovieData implements MovieInterface {
    public String title,  director, description;
    public List<String> genres;
    public Float avgVote;
    private Integer year;

    //access methods
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Integer getYear() {
        return year;
    }

    @Override
    public String getDirector() {
        return director;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getGenres() {
        return genres;
    }

    @Override
    public Float getAvgVote() {
        return avgVote;
    }

    //set methods
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setAvgVote(Float avgVote) {
        this.avgVote = avgVote;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    //sort in descending order
    @Override
    public int compareTo(MovieInterface otherMovie) {
        return otherMovie.getAvgVote().compareTo(avgVote);
    }

    //override toString method
    @Override
    public String toString() {
        return "MovieData{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", description='" + description + '\'' +
                ", genres=" + genres +
                ", avgVote=" + avgVote +
                ", year=" + year +
                '}';
    }
}
