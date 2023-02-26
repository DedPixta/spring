package com.makos.spring.util;

import lombok.*;

@Getter

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private String message;
    private long timestamp;

}
