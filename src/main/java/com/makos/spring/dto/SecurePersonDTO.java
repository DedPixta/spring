package com.makos.spring.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecurePersonDTO {

    @NotNull(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be 2-30 characters")
    private String name;

    @NotNull(message = "Pass should not be empty")
    private String pass;

    @Min(value = 1800, message = "Birth year should be greater than 1800")
    @Max(value = 2023, message = "Birth year should be lower than 2023")
    private int birthYear;
}
