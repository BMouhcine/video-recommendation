package spideo.recommendation.videorecom.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table
@IdClass(LabelKey.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Label {
    @Id
    private String designation;

    @Id
    @ManyToOne
    @JoinColumn(name="video_id", nullable = false)
    private Video video;


    public Label() {
    }

    public Label(String designation) {
        this.designation = designation;
    }

    public Label(String designation, Video video) {
        this.designation = designation;
        this.video = video;
    }


    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
