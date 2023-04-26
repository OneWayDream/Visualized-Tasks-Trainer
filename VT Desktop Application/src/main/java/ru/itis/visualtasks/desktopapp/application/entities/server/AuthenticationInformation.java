package ru.itis.visualtasks.desktopapp.application.entities.server;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationInformation {

    private String accessToken;
    private String accessExpirationDate;

}
