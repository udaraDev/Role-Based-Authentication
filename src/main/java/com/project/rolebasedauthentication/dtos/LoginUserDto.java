package com.project.rolebasedauthentication.dtos;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;
    private String password;

    //getters and setters
}
