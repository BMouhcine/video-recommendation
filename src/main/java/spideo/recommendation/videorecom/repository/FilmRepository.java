package spideo.recommendation.videorecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spideo.recommendation.videorecom.model.Film;


@Repository
public interface FilmRepository extends JpaRepository<Film, String> {


}
