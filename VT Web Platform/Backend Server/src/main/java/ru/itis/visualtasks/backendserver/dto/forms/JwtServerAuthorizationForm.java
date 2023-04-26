package ru.itis.visualtasks.backendserver.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtServerAuthorizationForm {

    private String login;
    private String password;

}
