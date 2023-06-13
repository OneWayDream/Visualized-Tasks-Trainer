package ru.itis.visualtasks.jwtserver.redis.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.jwtserver.dto.JwtUserDto;
import ru.itis.visualtasks.jwtserver.entities.JwtRole;
import ru.itis.visualtasks.jwtserver.entities.JwtState;
import ru.itis.visualtasks.jwtserver.models.JwtUser;
import ru.itis.visualtasks.jwtserver.redis.repositories.RedisUsersRepository;
import ru.itis.visualtasks.jwtserver.redis.repositories.models.RedisUser;
import ru.itis.visualtasks.jwtserver.services.JwtBlacklistService;
import ru.itis.visualtasks.jwtserver.services.JwtUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest()
@DisplayName("RedisUserService is working when")
@TestPropertySource(value = "classpath:test.properties")
public class RedisUserServiceTests {

    @Autowired
    private RedisUsersService redisUsersService;

    @MockBean
    private JwtUserService userService;
    @MockBean
    private JwtBlacklistService blacklistService;
    @MockBean
    private RedisUsersRepository redisUsersRepository;

    @BeforeEach
    public void setUp(){
        when(redisUsersRepository.save(any(RedisUser.class))).thenReturn(
                new RedisUser());
        when(redisUsersRepository.findById(RedisUserServiceTests.AddTokenToUser.getExistingUser().getRedisId()))
                .thenReturn(Optional.ofNullable(AddTokenToUser.getExistingRedisUser()));
        when(userService.update(any(JwtUserDto.class))).thenReturn(
                new JwtUserDto());
        when(redisUsersRepository.findById(RedisUserServiceTests.AddAllTokensToBlacklist.getNotExistingUser().getRedisId()))
                .thenReturn(Optional.empty());
        when(redisUsersRepository.findById(RedisUserServiceTests.AddAllTokensToBlacklist.getExistingUser().getRedisId()))
                .thenReturn(Optional.ofNullable(AddAllTokensToBlacklist.getExistingRedisUser()));
        doNothing().when(blacklistService).add(any(String.class));
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("addTokenToUser() in working when")
    public class AddTokenToUser{

        @Test
        public void add_token_to_a_new_user(){
            redisUsersService.addTokenToUser(getNewUser(), getNewUserToken());
        }

        public static JwtUserDto getNewUser(){
            return JwtUserDto.builder()
                    .accountId(1L)
                    .hashPassword("hash-password")
                    .login("NewLogin")
                    .mail("new_mail@gmail.com")
                    .role(JwtRole.USER)
                    .state(JwtState.ACTIVE)
                    .build();
        }

        public static String getNewUserToken(){
            return "new-user-token";
        }

        @Test
        public void add_token_to_existing_user(){
            redisUsersService.addTokenToUser(getExistingUser(), getExistingUserToken());
        }

        public static JwtUserDto getExistingUser(){
            return JwtUserDto.builder()
                    .redisId("existing_user_id")
                    .accountId(2L)
                    .hashPassword("hash-password")
                    .login("ExistingLogin")
                    .mail("existing_mail@gmail.com")
                    .role(JwtRole.USER)
                    .state(JwtState.ACTIVE)
                    .build();
        }

        public static String getExistingUserToken(){
            return "existing-user-token";
        }

        public static RedisUser getExistingRedisUser(){
            List<String> tokens = new ArrayList<>();
            tokens.add("refresh-token");
            tokens.add("access-token");
            return RedisUser.builder()
                    .id("existing_user_id")
                    .userId(2L)
                    .tokens(tokens)
                    .build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("addAllTokensToBlackList() in working when")
    public class AddAllTokensToBlacklist{

        @Test
        public void for_not_existing_user(){
            assertThrows(IllegalArgumentException.class,
                    () -> redisUsersService.addAllTokensToBlackList(getNotExistingUser()));
        }

        public static JwtUser getNotExistingUser(){
            return JwtUser.builder()
                    .redisId("not_existing_id")
                    .id(3L)
                    .login("test")
                    .mail("test@gmail.com")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .hashPassword("test")
                    .isDeleted(false)
                    .build();
        }

        @Test
        public void for_existing_user(){
            redisUsersService.addAllTokensToBlackList(getExistingUser());
        }

        public static JwtUser getExistingUser(){
            return JwtUser.builder()
                    .redisId("existing_id")
                    .id(4L)
                    .login("test")
                    .mail("test@gmail.com")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .hashPassword("test")
                    .isDeleted(false)
                    .build();
        }

        public static RedisUser getExistingRedisUser(){
            List<String> tokens = new ArrayList<>();
            tokens.add("refresh-token");
            tokens.add("access-token");
            return RedisUser.builder()
                    .id("existing_id")
                    .userId(4L)
                    .tokens(tokens)
                    .build();
        }

        @Test
        public void for_user_without_id(){
            redisUsersService.addAllTokensToBlackList(getUserWithoutId());
        }

        public static JwtUser getUserWithoutId() {
            return JwtUser.builder()
                    .id(5L)
                    .login("test")
                    .mail("test@gmail.com")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .hashPassword("test")
                    .isDeleted(false)
                    .build();
        }

    }

}
