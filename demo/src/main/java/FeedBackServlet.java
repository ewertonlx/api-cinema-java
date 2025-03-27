import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import classes.Feedback;
import classes.Movie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.MovieRepository;
import services.MovieService;

@WebServlet("/feedback")
public class FeedBackServlet extends HttpServlet {
    private MovieRepository repository;
    private MovieService filmeService;

    @Override
    public void init() throws ServletException {
        this.repository = MovieRepository.getInstance();
        this.filmeService = new MovieService(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Movie> filmes = filmeService.getFilmes();
        for (Movie filme : filmes) {
            if (filme.getId() == id) {
                ObjectMapper mapper = new ObjectMapper();
                String json;
                Map<String, Object> movieData = new HashMap<>();
                movieData.put("movie", filme);
                json = mapper.writeValueAsString(movieData);
                resp.setContentType("application/json");
                resp.getWriter().write(json);
                return;
            }
        }
        resp.setStatus(404);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        int id = Integer.parseInt(req.getParameter("id"));
        Feedback feedback = mapper.readValue(req.getReader(), Feedback.class);
        List<Movie> filmes = filmeService.getFilmes();
        for (Movie filme : filmes) {
            if (filme.getId() == id) {
                filmeService.addFeedback(id, feedback);
                resp.setStatus(201);
                return;
            }
        }
        resp.setStatus(404);
    }
}