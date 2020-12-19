package com.example.pmcourse.exception;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GeneralApiServerException extends RuntimeException{
    private String message;

    public GeneralApiServerException(String message){
        this.message = message;
    }
}
