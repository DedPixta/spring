package com.makos.spring.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDTO {

    @NotNull(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be 2-30 characters")
    private String username;

    @NotNull(message = "Pass should not be empty")
    private String password;
}
