package spideo.recommendation.videorecom.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "No such video")
public class VideoNotFoundException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(VideoNotFoundException.class);
    private final String searchParam;
    public VideoNotFoundException(String searchParam){
        super("No such video found with param: " + searchParam, null, true, false);
        this.searchParam = searchParam;
        logger.error("Cannot find video with param {}", searchParam);
    }

    public String getSearchParam() {
        return searchParam;
    }

}
