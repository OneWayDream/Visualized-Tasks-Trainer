package ru.itis.visualtasks.jwtserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleAuthorizationForm {

    private String login;
    private String password;

}
