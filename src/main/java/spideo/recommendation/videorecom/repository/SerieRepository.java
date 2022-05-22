package spideo.recommendation.videorecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spideo.recommendation.videorecom.model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, String> {


}
