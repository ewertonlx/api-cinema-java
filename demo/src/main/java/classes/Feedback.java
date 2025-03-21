package classes;

public class Feedback {
    private int id;
    private String username;
    private String comment;
    private int classification;

    public Feedback() {};

    public Feedback(String username, String comment, int classification) {
        this.username = username;
        this.comment = comment;
        this.classification = classification;
    }

    public Feedback(int id, String username, String comment, int classification) {
        this.id = id;
        this.username = username;
        this.comment = comment;
        this.classification = classification;
    }

    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public int getClassification() {
        return classification;
    }

}
