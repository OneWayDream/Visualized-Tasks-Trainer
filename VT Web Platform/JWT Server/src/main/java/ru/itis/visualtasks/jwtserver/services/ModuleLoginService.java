package ru.itis.visualtasks.jwtserver.services;

import ru.itis.visualtasks.jwtserver.dto.AccessTokenResponse;
import ru.itis.visualtasks.jwtserver.dto.ModuleAuthorizationForm;
import ru.itis.visualtasks.jwtserver.dto.RefreshTokenResponse;

public interface ModuleLoginService {

    RefreshTokenResponse login(ModuleAuthorizationForm emailPasswordDto);
    AccessTokenResponse authenticate(RefreshTokenResponse refreshTokenDto);

}
