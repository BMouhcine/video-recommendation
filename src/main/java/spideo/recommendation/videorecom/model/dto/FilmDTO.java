package spideo.recommendation.videorecom.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class FilmDTO extends VideoDTO {
    @JsonProperty("release_date")
    private Date releaseDate;
    private String director;

    public FilmDTO(String id, String titre, List<String> labels, Date releaseDate, String director) {
        super(id, titre, labels);
        this.releaseDate = releaseDate;
        this.director = director;
    }

    public FilmDTO(String id, String titre, List<String> labels, Date releaseDate) {
        super(id, titre, labels);
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
