package spideo.recommendation.videorecom.service;


import spideo.recommendation.videorecom.model.dto.FilmDTO;
import spideo.recommendation.videorecom.model.dto.SerieDTO;
import spideo.recommendation.videorecom.model.dto.VideoDTO;

import java.util.List;

public interface VideoService {
    VideoDTO getVideo(String id);

    List<VideoDTO> getVideos(String titre);

    VideoDTO addVideo(VideoDTO videoDTO);

    FilmDTO addFilm(FilmDTO filmDTO);

    SerieDTO addSerie(SerieDTO serieDTO);

    List<FilmDTO> getFilms();

    List<SerieDTO> getSeries();

    VideoDTO delVideo(VideoDTO videoDTO);

    List<String> getDelVideos();

    List<VideoDTO> findSimilarVideos(VideoDTO videoDTO, int minMatchNumber);



}
