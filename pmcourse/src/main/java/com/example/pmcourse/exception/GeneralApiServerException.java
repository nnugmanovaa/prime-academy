package com.example.pmcourse.exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GeneralApiServerException extends RuntimeException{
    private String message;
}
