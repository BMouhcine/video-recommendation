package spideo.recommendation.videorecom.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import spideo.recommendation.videorecom.model.dto.FilmDTO;
import spideo.recommendation.videorecom.model.dto.SerieDTO;
import spideo.recommendation.videorecom.model.dto.VideoDTO;
import spideo.recommendation.videorecom.service.VideoService;

import java.util.List;


@RestController
@RequestMapping(value="/api/video/")
@RequiredArgsConstructor
public class Controller {

    private final VideoService videoService;


    @PostMapping("addVideo")
    ResponseEntity<VideoDTO> addVideo(@RequestBody VideoDTO videoDTO){
        return new ResponseEntity<>(videoService.addVideo(videoDTO), HttpStatus.OK);
    }
    @PostMapping("addFilm")
    ResponseEntity<FilmDTO> addFilm(@RequestBody FilmDTO filmDTO){
        return new ResponseEntity<>(videoService.addFilm(filmDTO), HttpStatus.OK);
    }

    @PostMapping("addSerie")
    ResponseEntity<SerieDTO> addSerie(@RequestBody SerieDTO serieDTO){
        return new ResponseEntity<>(videoService.addSerie(serieDTO), HttpStatus.OK);
    }

    @GetMapping("getVideo")
    ResponseEntity<VideoDTO> getVideo(@RequestParam String id){
        return new ResponseEntity<>(videoService.getVideo(id), HttpStatus.OK);
    }

    @GetMapping("getVideos")
    ResponseEntity<List<VideoDTO>> getVideos(@RequestParam String titre){
        return new ResponseEntity<>(videoService.getVideos(titre), HttpStatus.OK);
    }

    @GetMapping("getFilms")
    ResponseEntity<List<FilmDTO>> getFilms(){
        return new ResponseEntity<>(videoService.getFilms(), HttpStatus.OK);
    }

    @GetMapping("getSeries")
    ResponseEntity<List<SerieDTO>> getSeries(){
        return new ResponseEntity<>(videoService.getSeries(), HttpStatus.OK);
    }

    @DeleteMapping("deleteVideo")
    ResponseEntity<VideoDTO> delVideo(@RequestBody VideoDTO videoDTO){
        return new ResponseEntity<>(videoService.delVideo(videoDTO), HttpStatus.OK);
    }

    @GetMapping("getDeletedVideos")
    ResponseEntity<List<String>> getDelVideos(){
        return new ResponseEntity<>(videoService.getDelVideos(), HttpStatus.OK);
    }


    @GetMapping("getSimilarVideos")
    ResponseEntity<List<VideoDTO>> getSimilarVideos(@RequestBody VideoDTO videoDTO, @RequestParam int minMatchNumber){
        return new ResponseEntity<>(videoService.findSimilarVideos(videoDTO, minMatchNumber), HttpStatus.OK);
    }



}
