package spideo.recommendation.videorecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spideo.recommendation.videorecom.model.VideoArchive;

import java.util.List;

@Repository
public interface VideoArchiveRepository extends JpaRepository<VideoArchive, String> {

    List<VideoArchive> findAll();
}
