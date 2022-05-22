package spideo.recommendation.videorecom.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spideo.recommendation.videorecom.converter.VideoConverter;
import spideo.recommendation.videorecom.model.Video;
import spideo.recommendation.videorecom.model.Film;
import spideo.recommendation.videorecom.model.Serie;
import spideo.recommendation.videorecom.model.VideoArchive;
import spideo.recommendation.videorecom.repository.VideoRepository;
import spideo.recommendation.videorecom.repository.VideoArchiveRepository;
import spideo.recommendation.videorecom.repository.FilmRepository;
import spideo.recommendation.videorecom.repository.SerieRepository;
import spideo.recommendation.videorecom.exception.BadRequestFieldsException;
import spideo.recommendation.videorecom.exception.VideoNotFoundException;
import spideo.recommendation.videorecom.model.dto.FilmDTO;
import spideo.recommendation.videorecom.model.dto.SerieDTO;
import spideo.recommendation.videorecom.model.dto.VideoDTO;
import spideo.recommendation.videorecom.util.ServiceUtils;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    private final VideoArchiveRepository videoArchiveRepository;

    private final FilmRepository filmRepository;

    private final SerieRepository serieRepository;

    private final VideoConverter videoConverter;

    @Override
    public VideoDTO getVideo(String id) {
        Video videoFound = videoRepository.findById(id).orElseThrow(() -> new VideoNotFoundException(id));
        return videoConverter.videoToVideoDto(videoFound);
    }

    @Override
    public List<VideoDTO> getVideos(String titre) {
        if (titre.length() < 3)
            return Collections.emptyList();

        List<Video> videosFound = videoRepository.findAllByTitreContaining(titre).orElseThrow(() -> new VideoNotFoundException(titre));

        return videoConverter.videoToVideoDto(videosFound);
    }

    @Override
    public VideoDTO addVideo(VideoDTO videoDTO) {
        if(!ServiceUtils.isValidRequestBody(videoDTO))
            throw new BadRequestFieldsException("video");
        Video video = videoConverter.serieDtoToEntity(videoDTO);
        videoRepository.save(video);
        return videoConverter.videoToVideoDto(video);
    }

    @Override
    public FilmDTO addFilm(FilmDTO filmDTO) {
        if(!ServiceUtils.isValidRequestBody(filmDTO))
            throw new BadRequestFieldsException("film");
        Film film = videoConverter.filmDtoToEntity(filmDTO);
        filmRepository.save(film);
        return videoConverter.filmToFilmDto(film);
    }

    @Override
    public SerieDTO addSerie(SerieDTO serieDTO) {
        if(!ServiceUtils.isValidRequestBody(serieDTO))
            throw new BadRequestFieldsException("serie");
        Serie serie = videoConverter.serieDtoToEntity(serieDTO);
        serieRepository.save(serie);
        return videoConverter.serieToSerieDto(serie);
    }

    @Override
    public List<FilmDTO> getFilms() {
        return videoConverter.filmsToFilmDtos(filmRepository.findAll());
    }

    @Override
    public List<SerieDTO> getSeries() {
        return videoConverter.seriesToSerieDtos(serieRepository.findAll());
    }

    @Override
    public VideoDTO delVideo(VideoDTO videoDTO) {
        Video videoToDelete = videoRepository.findById(videoDTO.getId()).orElseThrow(() -> new VideoNotFoundException(videoDTO.getId()));
        videoArchiveRepository.save(new VideoArchive(videoToDelete.getId(), new Date().getTime()));
        videoRepository.delete(videoToDelete);
        return videoConverter.videoToVideoDto(videoToDelete);
    }

    @Override
    public List<String> getDelVideos() {
        return videoArchiveRepository.findAll().stream().map(VideoArchive::getId).collect(Collectors.toList());
    }

    @Override
    public List<VideoDTO> findSimilarVideos(VideoDTO videoDTO, int minMatchNumber) {
        return videoConverter.videoToVideoDto(
                videoRepository.findAll().stream()
                        .filter(video -> ServiceUtils.hasMinLabelMatch(minMatchNumber, video.getLabels(), videoDTO))
                        .collect(Collectors.toList())
        );
    }
}
