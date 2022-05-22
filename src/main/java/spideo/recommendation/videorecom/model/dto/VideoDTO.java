package spideo.recommendation.videorecom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO {

    private String id;

    private String titre;

    private List<String> labels;


    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<String> getLabels() {
        return labels;
    }


}
