package com.project.rolebasedauthentication.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)

public class RegisterUserDto {
    private String email;
    private String password;
    private String fullName;
    //getters and setters
}
