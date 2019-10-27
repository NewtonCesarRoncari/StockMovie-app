package br.com.mov.models;

public class Movie {

    private String title;
    private String description;
    private int thumbnail;
    private String studio;
    private String rating;
    private String streamLink;

    public Movie(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public Movie(String title, String description, int thumbnail, String studio, String rating, String streamLink) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.studio = studio;
        this.rating = rating;
        this.streamLink = streamLink;
    }

    //regionGetters and Setters
    public String getTitle() {
        return title;
    }

    public int getThumbnail() {
        return thumbnail;
    }
    //endregion
}
