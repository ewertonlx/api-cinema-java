package repository;
import java.util.ArrayList;
import java.util.List;

import classes.Feedback;
import classes.Movie;

public class MovieRepository {
    private static MovieRepository instance;
    private List<Movie> movies = new ArrayList<>();
    
    private static int id = 1;
    private static int idFeedback = 1;

    private MovieRepository() {
        movies.add(new Movie(id++, "Matrix", "Filme de ação", 1999, "Wachowski"));
        movies.add(new Movie(id++, "Matrix Reloaded", "Filme de ação", 2003, "Wachowski"));
        movies.add(new Movie(id++, "Matrix Revolutions", "Filme de ação", 2003, "Wachowski"));
        movies.add(new Movie(id++, "Matrix 4", "Filme de ação", 2021, "Wachowski"));
    }

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }
    public void addMovie(Movie movie) {
        movie = new Movie(id++, movie.getName(), movie.getDescription(), movie.getYear(), movie.getDirector());
        movies.add(movie);
    }

    public void addFeedback(int idMovie, Feedback feedback) {
        for (Movie movie : movies) {
            if (movie.getId() == idMovie) {
                feedback = new Feedback(idFeedback++, feedback.getUsername(), feedback.getComment(), feedback.getClassification());
                movie.addFeedback(feedback);
                return;
            }
        }
    }
    public List<Movie> getMovies() {
        return movies;
    }

    public Movie getMovie(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public void removeMovie(String name) {
        for (Movie movie : movies) {
            if (movie.getName().equals(name)) {
                movies.remove(movie);
                return;
            }
        }
    }
}
