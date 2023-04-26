package ru.itis.visualtasks.desktopapp.application.entities.server;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInInformation {

    private String login;
    private String refreshToken;
    private String refreshExpirationDate;

}
