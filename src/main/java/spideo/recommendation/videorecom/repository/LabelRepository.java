package spideo.recommendation.videorecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spideo.recommendation.videorecom.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, String> {

}
