package ru.itis.visualtasks.desktopapp.application.entities.server;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthorizationForm {

    private String login;
    private String password;

}
