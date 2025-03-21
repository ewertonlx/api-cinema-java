package services;
import java.util.List;

import classes.Feedback;
import classes.Movie;
import repository.MovieRepository;

public class MovieService {
    private MovieRepository filmeRepository;

    public MovieService(MovieRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }
    
    public void addFilme(Movie filme) {
        if (filme.getName() == null || filme.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome do filme é obrigatório");
        }
        if (filme.getYear() < 1895) {
            throw new IllegalArgumentException("O ano do filme deve ser maior que 1895");
        }
        if (filme.getDirector() == null || filme.getDirector().isEmpty()) {
            throw new IllegalArgumentException("O diretor do filme é obrigatório");
        }
        filmeRepository.addFilme(filme);
    }
    
    public void addFeedback(int idFilme, Feedback feedback) {
        if (feedback.getClassification() < 0 || feedback.getClassification() > 5) {
            throw new IllegalArgumentException("A classificação deve ser um valor entre 0 e 5");
        }
        filmeRepository.addFeedback(idFilme, feedback);
    }

    public List<Movie> getFilmes() {
        return filmeRepository.getFilmes();
    }
    
    public Movie getFilme(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O id do filme deve ser maior que 0");
        }
        return filmeRepository.getFilme(id);
    }
    
    public void removeFilme(String name) {
        filmeRepository.removeFilme(name);
    }
}
