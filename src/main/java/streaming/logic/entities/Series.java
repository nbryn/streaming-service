package streaming.logic.entities;

import java.util.HashMap;
import java.util.Map;

public class Series extends Media {

    private Map<Integer, Integer> seasons;
    private String span;

    public Series(String title, int release, String span, double rating) {
        super(title, release, rating);
        this.span = span;

        seasons = new HashMap<>();
    }

    public String getSpan() {
        return this.span;
    }

    public Map<Integer, Integer> getSeasons() {
        return this.seasons;
    }

    public void addSeason(int season, int episodes) {
        seasons.put(season, episodes);
    }

    @Override
    public String toString() {

        return "Series: Name: " + super.getTitle() + " Year: " + super.getRelease() + " Span: " + getSpan() + " Genre: " + super.getGenre() + " Rating: " + super.getRating();
    }
}
