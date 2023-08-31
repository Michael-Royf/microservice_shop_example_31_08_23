package com.michael.productService.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExceptionResponse {

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "Israel")
    private Date timeStamp;
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private String message;

    public ExceptionResponse(int httpStatusCode, HttpStatus httpStatus, String message) {
        timeStamp = new Date();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
