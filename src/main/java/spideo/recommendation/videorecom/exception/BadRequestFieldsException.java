package spideo.recommendation.videorecom.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "One or more fields are invalid while trying to add video")
public class BadRequestFieldsException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(BadRequestFieldsException.class);
    public BadRequestFieldsException(String resourceType){
        super("One or more fields are invalid while trying to add resource :" + resourceType , null, true, false);
        logger.error("Cannot map input fields with resource of type: {}", resourceType);
    }


}
