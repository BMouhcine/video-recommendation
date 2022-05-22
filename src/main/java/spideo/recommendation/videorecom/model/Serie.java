package spideo.recommendation.videorecom.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "serie")
@PrimaryKeyJoinColumn(name = "video")
public class Serie extends Video {
    @JsonProperty("number_of_episodes")
    private int numberOfEpisodes;


    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }
}
