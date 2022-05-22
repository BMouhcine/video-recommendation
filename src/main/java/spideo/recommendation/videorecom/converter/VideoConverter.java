package spideo.recommendation.videorecom.converter;

import org.springframework.stereotype.Component;
import spideo.recommendation.videorecom.repository.VideoRepository;
import spideo.recommendation.videorecom.model.Film;
import spideo.recommendation.videorecom.model.Label;
import spideo.recommendation.videorecom.model.Serie;
import spideo.recommendation.videorecom.model.Video;
import spideo.recommendation.videorecom.model.dto.FilmDTO;
import spideo.recommendation.videorecom.model.dto.SerieDTO;
import spideo.recommendation.videorecom.model.dto.VideoDTO;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VideoConverter {

    private VideoRepository videoRepository;

    public VideoConverter(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public VideoDTO videoToVideoDto(Video video){
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setLabels(video.getLabels().stream().map(Label::getDesignation).collect(Collectors.toList()));
        videoDTO.setId(video.getId());
        videoDTO.setTitre(video.getTitre());
        return videoDTO;
    }

    public List<VideoDTO> videoToVideoDto(List<Video> videos){
        return !videos.isEmpty() ? videos.stream().map(this::videoToVideoDto).collect(Collectors.toList()) : Collections.emptyList();
    }

    public List<FilmDTO> filmsToFilmDtos(List<Film> films){
        return !films.isEmpty() ? films.stream().map(this::filmToFilmDto).collect(Collectors.toList()) : Collections.emptyList();
    }

    public List<SerieDTO> seriesToSerieDtos(List<Serie> series){
        return !series.isEmpty() ? series.stream().map(this::serieToSerieDto).collect(Collectors.toList()) : Collections.emptyList();
    }


    public Video serieDtoToEntity(VideoDTO videoDTO){
        Video video = new Video();
        video.setId(videoDTO.getId());
        video.setTitre(videoDTO.getTitre());
        video.setLabels(videoDTO.getLabels().stream()
                .map(label -> new Label(label, videoRepository.findById(videoDTO.getId()).orElse(video)))
                .collect(Collectors.toList()));
        return video;
    }

    public List<Video> videoDtoToEntity(List<VideoDTO> videoDTOs){
        if(videoDTOs.get(0) instanceof FilmDTO)
            return videoDTOs.stream().map(fDto -> filmDtoToEntity((FilmDTO) fDto)).collect(Collectors.toList());

        return videoDTOs.stream().map(this::serieDtoToEntity).collect(Collectors.toList());
    }

    public Film filmDtoToEntity(FilmDTO filmDTO){
        Film film = new Film();
        film.setId(filmDTO.getId());
        film.setTitre(filmDTO.getTitre());
        film.setLabels(filmDTO.getLabels().stream()
                .map(label -> new Label(label, videoRepository.findById(filmDTO.getId()).orElse(film)))
                .collect(Collectors.toList()));

        film.setDirector(filmDTO.getDirector());
        film.setReleaseDate(filmDTO.getReleaseDate());
        return film;
    }


    public Serie serieDtoToEntity(SerieDTO serieDTO){
        Serie serie = new Serie();
        serie.setId(serieDTO.getId());
        serie.setTitre(serieDTO.getTitre());
        serie.setLabels(serieDTO.getLabels().stream()
                .map(label -> new Label(label, videoRepository.findById(serieDTO.getId()).orElse(serie)))
                .collect(Collectors.toList()));
        serie.setNumberOfEpisodes(serieDTO.getNumberOfEpisodes());
        return serie;
    }


    public FilmDTO filmToFilmDto(Film film){
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setLabels(film.getLabels().stream().map(Label::getDesignation).collect(Collectors.toList()));
        filmDTO.setId(film.getId());
        filmDTO.setTitre(film.getTitre());
        filmDTO.setDirector(film.getDirector());
        filmDTO.setReleaseDate(film.getReleaseDate());
        return filmDTO;
    }

    public SerieDTO serieToSerieDto(Serie serie){
        SerieDTO serieDTO = new SerieDTO();
        serieDTO.setLabels(serie.getLabels().stream().map(Label::getDesignation).collect(Collectors.toList()));
        serieDTO.setId(serie.getId());
        serieDTO.setTitre(serie.getTitre());
        serieDTO.setNumberOfEpisodes(serie.getNumberOfEpisodes());
        return serieDTO;
    }

}
