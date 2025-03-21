package repository;
import java.util.ArrayList;
import java.util.List;

import classes.Feedback;
import classes.Movie;

public class MovieRepository {
    private static MovieRepository instance;
    private List<Movie> filmes = new ArrayList<>();
    private static int id = 1;
    private static int idFeedback = 1;

    private MovieRepository() {
    }

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }
    public void addFilme(Movie filme) {
        filme = new Movie(id++, filme.getName(), filme.getDescription(), filme.getYear(), filme.getDirector());
        filmes.add(filme);
    }

    public void addFeedback(int idFilme, Feedback feedback) {
        for (Movie filme : filmes) {
            if (filme.getId() == idFilme) {
                feedback = new Feedback(idFeedback++, feedback.getUsername(), feedback.getComment(), feedback.getClassification());
                filme.addFeedback(feedback);
                return;
            }
        }
    }
    public List<Movie> getFilmes() {
        filmes.add(new Movie(1, "Matrix", "Filme de ação", 1999, "Wachowski"));
        filmes.add(new Movie(2, "Matrix Reloaded", "Filme de ação", 2003, "Wachowski"));
        filmes.add(new Movie(3, "Matrix Revolutions", "Filme de ação", 2003, "Wachowski"));
        filmes.add(new Movie(4, "Matrix 4", "Filme de ação", 2021, "Wachowski"));
        return filmes;
    }

    public Movie getFilme(int id) {
        for (Movie filme : filmes) {
            if (filme.getId() == id) {
                return filme;
            }
        }
        return null;
    }

    public void removeFilme(String name) {
        for (Movie filme : filmes) {
            if (filme.getName().equals(name)) {
                filmes.remove(filme);
                return;
            }
        }
    }
}
