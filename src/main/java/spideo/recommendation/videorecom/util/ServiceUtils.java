package spideo.recommendation.videorecom.util;

import spideo.recommendation.videorecom.model.Label;
import spideo.recommendation.videorecom.model.dto.FilmDTO;
import spideo.recommendation.videorecom.model.dto.SerieDTO;
import spideo.recommendation.videorecom.model.dto.VideoDTO;
import java.util.List;


public final class ServiceUtils {

    private ServiceUtils(){

    }

    static boolean isNotBlankOrNull(String value){
        return value != null && !value.isEmpty();
    }
    public static boolean isValidRequestBody(FilmDTO filmDTO){
        return isValidRequestBody((VideoDTO) filmDTO) && isNotBlankOrNull(filmDTO.getDirector())
                && isNotBlankOrNull(String.valueOf(filmDTO.getReleaseDate()));
    }

    public static boolean isValidRequestBody(SerieDTO serieDTO){
        return isValidRequestBody((VideoDTO) serieDTO) && serieDTO.getNumberOfEpisodes() > 0;
    }

    public static boolean isValidRequestBody(VideoDTO videoDTO){
        return isNotBlankOrNull(videoDTO.getId()) && isNotBlankOrNull(videoDTO.getTitre()) && !videoDTO.getLabels().isEmpty();
    }

    public static boolean hasMinLabelMatch(int minMatchNumber, List<Label> labels, VideoDTO videoDTO){
        if(videoDTO.getLabels().size() < minMatchNumber)
            return false;
        long count = 0;
        for(String label: videoDTO.getLabels()){
            count += labels.stream().map(Label::getDesignation).filter(labelDesignation -> labelDesignation.equals(label)).count();
        }
        return count >= minMatchNumber;
    }

}
