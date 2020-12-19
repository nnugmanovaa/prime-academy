package com.example.pmcourse.model.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class TaskUpdateDto {
    @NotBlank
    @NotNull
    private String description;

    public TaskUpdateDto(String description) {
        this.description = description;
    }
}
