package ru.itis.visualtasks.desktopapp.application.entities.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorizationInfo {

    private String login;
    private String refreshToken;
    private String refreshExpirationDate;
    private String accessToken;
    private String accessExpirationDate;

}
