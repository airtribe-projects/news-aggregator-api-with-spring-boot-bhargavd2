package com.example.news_aggregator.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class UserDto {

    @NotEmpty(message = "username is mandatory")
    @NotNull(message = "username is mandatory")
    private String username;

    @NotEmpty(message = "password is mandatory")
    @NotNull(message = "password is mandatory")
    @Size(min = 4,max=10,message = "Password should be between 4 and 10 chars")
    private String password;

}
