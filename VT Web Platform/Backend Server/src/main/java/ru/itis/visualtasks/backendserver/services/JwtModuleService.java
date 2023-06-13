package ru.itis.visualtasks.backendserver.services;

import ru.itis.visualtasks.backendserver.dto.forms.JwtUpdateForm;
import ru.itis.visualtasks.backendserver.dto.forms.TokenRegistrationForm;

public interface JwtModuleService {

    void deleteUserOnAuthorizationServer(Long id);
    void updateUserOnAuthorizationServer(JwtUpdateForm form);
    void registerOnAuthorizationServer(TokenRegistrationForm form);

}
