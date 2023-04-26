package ru.itis.visualtasks.jwtserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthorizationForm {

    private String login;
    private String mail;
    private String password;

}
