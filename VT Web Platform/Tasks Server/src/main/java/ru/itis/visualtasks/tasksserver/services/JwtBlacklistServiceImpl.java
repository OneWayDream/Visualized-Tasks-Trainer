package ru.itis.visualtasks.tasksserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.visualtasks.tasksserver.redis.repositories.BlacklistRepository;

@RequiredArgsConstructor
@Service
public class JwtBlacklistServiceImpl implements JwtBlacklistService {

    protected final BlacklistRepository blacklistRepository;

    @Override
    public boolean exists(String token) {
        return blacklistRepository.exists(token);
    }

}
