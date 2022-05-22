package spideo.recommendation.videorecom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import spideo.recommendation.videorecom.controller.Controller;
import spideo.recommendation.videorecom.exception.BadRequestFieldsException;
import spideo.recommendation.videorecom.exception.VideoNotFoundException;
import spideo.recommendation.videorecom.model.Label;
import spideo.recommendation.videorecom.model.VideoArchive;
import spideo.recommendation.videorecom.model.dto.FilmDTO;
import spideo.recommendation.videorecom.model.dto.SerieDTO;
import spideo.recommendation.videorecom.model.dto.VideoDTO;
import spideo.recommendation.videorecom.service.VideoService;
import java.util.Collections;
import java.util.Date;

@WebMvcTest(Controller.class)
class AddVideoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;



    private final Label L1 = new Label("LABEL-DUMMY");
    private final VideoDTO V1DTO = new VideoDTO("ID-DUMMY", "TITLE-DUMMY", Collections.singletonList(L1.getDesignation()));
    private final FilmDTO F1DTO = new FilmDTO("ID-DUMMY", "TITLE-DUMMY", Collections.singletonList(L1.getDesignation()), new Date(), "DIRECTOR-DUMMY");
    private final FilmDTO INVALID_F1DTO = new FilmDTO("ID-DUMMY", "TITLE-DUMMY", Collections.singletonList(L1.getDesignation()), new Date());
    private final SerieDTO S1DTO = new SerieDTO("ID-DUMMY", "TITLE-DUMMY", Collections.singletonList(L1.getDesignation()), 3);
    private final VideoArchive VARCHIVE = new VideoArchive("ID-DUMMY", new Date().getTime());

    @Test
    void getVideoByIdTest() throws Exception {

        Mockito.when(videoService.getVideo(Mockito.anyString())).thenReturn(V1DTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/video/getVideo?id=1").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    void getVideosByTitre() throws Exception {

        Mockito.when(videoService.getVideos(Mockito.anyString())).thenReturn(Collections.singletonList(V1DTO));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/video/getVideos?titre=dummy").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }


    @Test
    void getFilms() throws Exception {

        Mockito.when(videoService.getFilms()).thenReturn(Collections.singletonList(F1DTO));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/video/getFilms").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    void getSeries() throws Exception {

        Mockito.when(videoService.getSeries()).thenReturn(Collections.singletonList(S1DTO));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/video/getSeries").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    void getDeletedVideos() throws Exception {

        Mockito.when(videoService.getDelVideos()).thenReturn(Collections.singletonList(VARCHIVE.getId()));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/video/getDeletedVideos").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(Collections.singletonList(VARCHIVE.getId()))))
                .andReturn();
    }


    @Test
    void getSimilarVideos() throws Exception {

        Mockito.when(videoService.findSimilarVideos(Mockito.any(VideoDTO.class), Mockito.anyInt())).thenReturn(Collections.singletonList(V1DTO));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/video/getSimilarVideos?minMatchNumber=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(V1DTO))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(Collections.singletonList(V1DTO))))
                .andReturn();
    }


    @Test
    void addVideo() throws Exception {

        Mockito.when(videoService.addVideo(Mockito.any(VideoDTO.class))).thenReturn(V1DTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/video/addVideo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(V1DTO))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(V1DTO))).andReturn();
    }

    @Test
    void addFilm() throws Exception {

        Mockito.when(videoService.addFilm(Mockito.any(FilmDTO.class))).thenReturn(F1DTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/video/addFilm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(F1DTO))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(F1DTO)))
                .andReturn();
    }


    @Test
    void addSerie() throws Exception {

        Mockito.when(videoService.addSerie(Mockito.any(SerieDTO.class))).thenReturn(S1DTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/video/addSerie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(S1DTO))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(S1DTO)))
                .andReturn();
    }



    @Test
    void deleteVideo() throws Exception {

        Mockito.when(videoService.delVideo(Mockito.any(VideoDTO.class))).thenReturn(V1DTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/video/deleteVideo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(V1DTO))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(V1DTO))).andReturn();
    }
    @Test
    void receive404_WhenVideoDoesNotExist() throws Exception {

        Mockito.when(videoService.getVideo(Mockito.anyString())).thenThrow(VideoNotFoundException.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/video/getVideo?id=dummy-test-id")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
    }

    @Test
    void receiveBadRequest_WhenWrongFields() throws Exception {

        Mockito.when(videoService.addFilm(Mockito.any(FilmDTO.class))).thenThrow(BadRequestFieldsException.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/video/addFilm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(INVALID_F1DTO))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }






    private String asJsonString(final Object obj) throws Exception {
        ObjectWriter ow = new ObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(obj);
    }

}
