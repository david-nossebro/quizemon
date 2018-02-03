package com.quizemon;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResponseExceptions {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find the resource")
    public static class NotFoundException extends RuntimeException {
    }

}
