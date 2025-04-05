package services;
import java.util.List;

import classes.Feedback;
import classes.Movie;
import repository.MovieRepository;

public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    
    public void addMovie(Movie movie) {
        if (movie.getName() == null || movie.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome do filme é obrigatório");
        }
        if (movie.getYear() < 1895) {
            throw new IllegalArgumentException("O ano do filme deve ser maior que 1895");
        }
        if (movie.getDirector() == null || movie.getDirector().isEmpty()) {
            throw new IllegalArgumentException("O diretor do filme é obrigatório");
        }
        movieRepository.addMovie(movie);
    }
    
    public void addFeedback(int idMovie, Feedback feedback) {
        if (feedback.getClassification() < 0 || feedback.getClassification() > 5) {
            throw new IllegalArgumentException("A classificação deve ser um valor entre 0 e 5");
        }
        movieRepository.addFeedback(idMovie, feedback);
    }

    public List<Movie> getMovies() {
        return movieRepository.getMovies();
    }
    
    public Movie getMovie(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O id do filme deve ser maior que 0");
        }
        return movieRepository.getMovie(id);
    }
    
    public void removeFilme(String name) {
        movieRepository.removeMovie(name);
    }
}
