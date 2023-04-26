package ru.itis.visualtasks.jwtserver.redis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.visualtasks.jwtserver.dto.JwtUserDto;
import ru.itis.visualtasks.jwtserver.models.JwtUser;
import ru.itis.visualtasks.jwtserver.redis.repositories.RedisUsersRepository;
import ru.itis.visualtasks.jwtserver.redis.repositories.models.RedisUser;
import ru.itis.visualtasks.jwtserver.services.JwtBlacklistService;
import ru.itis.visualtasks.jwtserver.services.JwtUserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RedisUsersServiceImpl implements RedisUsersService {

    private final JwtUserService userService;
    private final JwtBlacklistService blacklistService;
    private final RedisUsersRepository redisUsersRepository;

    @Override
    public void addTokenToUser(JwtUserDto user, String token) {
        String redisId = user.getRedisId();

        RedisUser redisUser;
        if (redisId != null) {
            redisUser = redisUsersRepository.findById(redisId).orElseThrow(IllegalArgumentException::new);
            if (redisUser.getTokens() == null) {
                redisUser.setTokens(new ArrayList<>());
            }
            redisUser.getTokens().add(token);
        } else {
            redisUser = RedisUser.builder()
                    .userId(user.getId())
                    .tokens(Collections.singletonList(token))
                    .build();
        }
        redisUsersRepository.save(redisUser);

        if (redisId == null){
            user.setRedisId(redisUser.getId());
            userService.update(user);
        }
    }

    @Override
    public void addAllTokensToBlackList(JwtUser user) {
        if (user.getRedisId() != null) {
            RedisUser redisUser = redisUsersRepository.findById(user.getRedisId())
                    .orElseThrow(IllegalArgumentException::new);

            List<String> tokens = redisUser.getTokens();
            for (String token : tokens) {
                blacklistService.add(token);
            }
            redisUser.getTokens().clear();
            redisUsersRepository.save(redisUser);
        }
    }
}
