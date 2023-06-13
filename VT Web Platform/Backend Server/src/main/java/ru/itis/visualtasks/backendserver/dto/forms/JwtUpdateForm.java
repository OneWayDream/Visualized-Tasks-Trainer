package ru.itis.visualtasks.backendserver.dto.forms;

import lombok.Builder;
import lombok.Data;
import ru.itis.visualtasks.backendserver.models.UserRole;
import ru.itis.visualtasks.backendserver.models.UserState;

@Data
@Builder
public class JwtUpdateForm {

    protected Long accountId;
    protected String login;
    protected String mail;
    protected String hashPassword;
    protected UserRole role;
    protected UserState state;

}
