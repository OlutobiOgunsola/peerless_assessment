package com.peerless.assessment.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
//    private HttpStatus code;
//    private String description;
    private String message;
}
