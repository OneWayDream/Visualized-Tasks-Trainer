package ru.itis.visualtasks.jwtserver.redis.services;

import ru.itis.visualtasks.jwtserver.dto.JwtUserDto;
import ru.itis.visualtasks.jwtserver.models.JwtUser;

public interface RedisUsersService {

    void addTokenToUser(JwtUserDto user, String token);

    void addAllTokensToBlackList(JwtUser user);

}
