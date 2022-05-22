package spideo.recommendation.videorecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spideo.recommendation.videorecom.model.Video;

import java.util.List;
import java.util.Optional;

@Repository

public interface VideoRepository extends JpaRepository<Video, String> {

    Optional<List<Video>> findAllByTitreContaining(@Param("titre") String titre);

}
