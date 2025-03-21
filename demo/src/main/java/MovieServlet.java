import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import classes.Movie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.MovieRepository;
import services.MovieService;

@WebServlet("/filmes")
public class MovieServlet extends HttpServlet {
    private MovieRepository repository;
    private MovieService filmeService;

    @Override
    public void init() throws ServletException {
        this.repository = MovieRepository.getInstance();
        this.filmeService = new MovieService(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Movie> filmes = filmeService.getFilmes();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(filmes);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Movie filme = mapper.readValue(req.getReader(), Movie.class);
        filmeService.addFilme(filme);
        resp.setStatus(201);
    }
}