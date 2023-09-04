package com.michael.orderService.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponseForOpenFeign {
    private String errorMessage;
    private String errorCode;
}
