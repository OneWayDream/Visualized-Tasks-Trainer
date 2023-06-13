package ru.itis.visualtasks.jwtserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.jwtserver.dto.*;
import ru.itis.visualtasks.jwtserver.entities.JwtRole;
import ru.itis.visualtasks.jwtserver.entities.JwtState;
import ru.itis.visualtasks.jwtserver.exceptions.auth.IncorrectUserDataException;
import ru.itis.visualtasks.jwtserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.jwtserver.exceptions.token.BannedTokenException;
import ru.itis.visualtasks.jwtserver.exceptions.token.IncorrectJwtException;
import ru.itis.visualtasks.jwtserver.redis.services.RedisUsersService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest()
@DisplayName("UserLoginService is working when")
@TestPropertySource(value = "classpath:test.properties")
public class UserLoginServiceTests {
    
    @Autowired
    private UserLoginService userLoginService;

    @MockBean
    private JwtUserService jwtUserService;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private RedisUsersService redisUsersService;
    @MockBean
    private JwtBlacklistService jwtBlacklistService;

    @BeforeEach
    public void setUp(){
        when(jwtUserService.findByLogin(LoginTests.getIncorrectLoginUserLoginForm().getLogin()))
                .thenThrow(EntityNotFoundException.class);
        when(jwtUserService.findByLogin(LoginTests.getCorrectUserLoginForm().getLogin()))
                .thenReturn(LoginTests.getCorrectJwtUserDto());
        when(passwordEncoder.matches(
                LoginTests.getIncorrectPasswordUserLoginForm().getPassword(),
                LoginTests.getCorrectJwtUserDto().getHashPassword())
        ).thenReturn(false);
        when(passwordEncoder.matches(
                LoginTests.getCorrectUserLoginForm().getPassword(),
                LoginTests.getCorrectJwtUserDto().getHashPassword())
        ).thenReturn(true);
        doNothing().when(redisUsersService).addTokenToUser(any(JwtUserDto.class), any(String.class));
        when(jwtBlacklistService.exists(AuthenticateTests.getBannedRefreshToken()))
                .thenReturn(true);
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("login() in working when")
    public class LoginTests {

        @Test
        public void login_with_incorrect_login(){
            assertThrows(IncorrectUserDataException.class,
                    () -> userLoginService.login(getIncorrectLoginUserLoginForm()));
        }

        public static UserAuthorizationForm getIncorrectLoginUserLoginForm() {
            return UserAuthorizationForm.builder()
                    .login("incorrect_login")
                    .password("correct_password")
                    .build();
        }

        @Test
        public void login_with_incorrect_password(){
            assertThrows(IncorrectUserDataException.class,
                    () -> userLoginService.login(getIncorrectPasswordUserLoginForm()));
        }

        public static UserAuthorizationForm getIncorrectPasswordUserLoginForm() {
            return UserAuthorizationForm.builder()
                    .login("correct_login")
                    .password("incorrect_password")
                    .build();
        }

        @Test
        public void login_with_correct_credentials(){
            RefreshTokenResponse actual = userLoginService.login(getCorrectUserLoginForm());
            assertNotNull(actual.getToken());
        }

        public static UserAuthorizationForm getCorrectUserLoginForm() {
            return UserAuthorizationForm.builder()
                    .login("correct_login")
                    .password("correct_password")
                    .build();
        }

        public static JwtUserDto getCorrectJwtUserDto(){
            return JwtUserDto.builder()
                    .id(1L)
                    .accountId(1L)
                    .login("correct_login")
                    .mail("correct_mail")
                    .hashPassword("correct_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("authenticate() in working when")
    public class AuthenticateTests {

        @Test
        public void authenticate_with_incorrect_token(){
            assertThrows(IncorrectJwtException.class, () -> userLoginService.authenticate(getIncorrectRefreshToken()));
        }

        @Test
        public void authenticate_with_banned_token(){
            assertThrows(BannedTokenException.class, () -> userLoginService.authenticate(getBannedRefreshToken()));
        }

        public static String getBannedRefreshToken(){
            return "banned-refresh-token";
        }

        @Test
        public void authenticate_with_correct_token(){
            AccessTokenResponse actual = userLoginService.authenticate(getCorrectRefreshToken());
            assertNotNull(actual.getToken());
        }

        public static String getIncorrectRefreshToken(){
            return "incorrect-refresh-token";
        }

        public static String getCorrectRefreshToken(){
            return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwicmVkaXNfaWQiOiIyNzRkZDZhOC0zYTc0LTQ1ZWQtYWY1My0xMjA4NmE3MjkzYzQiLCJhY2NvdW50X2lkIjo5LCJyb2xlIjoiVVNFUiIsInNhbHQiOiI5MzdkMTM0ZS00MWJkLTQxYTItODQ2OS0zYTRhOWI1OTlhNGQiLCJleHBpcmF0aW9uIjpudWxsLCJzdGF0ZSI6IkFDVElWRSIsImxvZ2luIjoiT25lV2F5RHJlYW0ifQ.696IuoRw0-eR0VabsPQiRBvkvIFeJeP5JD56BV5kNZk";
        }

    }

}
