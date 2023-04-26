package ru.itis.visualtasks.jwtserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.visualtasks.jwtserver.entities.JwtRole;
import ru.itis.visualtasks.jwtserver.entities.JwtState;

@Data
@NoArgsConstructor
@SuperBuilder
public abstract class JwtEntityDto {

    private Long id;
    private String login;
    private String hashPassword;
    private JwtState state;
    private JwtRole role;
    private String redisId;

}
