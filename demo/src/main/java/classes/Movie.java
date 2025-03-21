package classes;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String name;
    private String description;
    private int year;
    private String director;
    private List<Feedback> feedbacks = new ArrayList<>();
    
    public Movie() {}
    public Movie(String name, String description, int year, String director, List<Feedback> feedbacks) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.director = director;
        this.feedbacks = feedbacks;
    }
    public Movie(int id, String name, String description, int year, String director, List<Feedback> feedbacks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.director = director;
        this.feedbacks = feedbacks;
    }
    public Movie(int id, String name, String description, int year, String director) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.director = director;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getYear() {
        return year;
    }
    public String getDirector() {
        return director;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }
}
