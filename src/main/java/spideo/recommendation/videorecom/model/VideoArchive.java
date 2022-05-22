package spideo.recommendation.videorecom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VIDEOARCHIVE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoArchive {
    @Id
    private String id;
    private Long timestamp;

}
