package spideo.recommendation.videorecom.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class SerieDTO extends VideoDTO {
    @JsonProperty("number_of_episodes")
    private int numberOfEpisodes;

    public SerieDTO(String id, String titre, List<String> labels, int numberOfEpisodes) {
        super(id, titre, labels);
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }


}
