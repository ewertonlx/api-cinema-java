package routes;
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
    private MovieService movieService;

    @Override
    public void init() throws ServletException {
        this.repository = MovieRepository.getInstance();
        this.movieService = new MovieService(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Movie> movies = movieService.getMovies();
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                ObjectMapper mapper = new ObjectMapper();
                String json;
                Map<String, Object> movieData = new HashMap<>();
                movieData.put("movie", movie);
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
        List<Movie> movies = movieService.getMovies();
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movieService.addFeedback(id, feedback);
                resp.setStatus(201);
                return;
            }
        }
        resp.setStatus(404);
    }
}