package ru.itis.visualtasks.jwtserver.services;

import ru.itis.visualtasks.jwtserver.dto.AccessTokenResponse;
import ru.itis.visualtasks.jwtserver.dto.RefreshTokenResponse;
import ru.itis.visualtasks.jwtserver.dto.UserAuthorizationForm;

public interface UserLoginService {

    RefreshTokenResponse login(UserAuthorizationForm emailPasswordDto);
    AccessTokenResponse authenticate(RefreshTokenResponse refreshTokenDto);

}
