package ru.itis.visualtasks.desktopapp.application.managers.server;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.server.AuthenticationInformation;
import ru.itis.visualtasks.desktopapp.application.entities.server.SignInInformation;
import ru.itis.visualtasks.desktopapp.application.entities.server.UserAuthorizationForm;
import ru.itis.visualtasks.desktopapp.exceptions.server.SessionExpiredException;

import java.time.LocalDateTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class TokensManagerTests {

    private static final String USER_LOGIN = "User";
    private static final String REFRESH_TOKEN = "refresh-token";
    private static final String REFRESH_EXPIRATION_DATE = LocalDateTime.now().minusHours(1).toString();
    private static final String ACCESS_TOKEN = "access-token";
    private static final String ACCESS_EXPIRATION_DATE = LocalDateTime.now().minusHours(1).toString();

    @BeforeAll
    public static void beforeAll(){
        AccountInformationManager.resetAuthenticationInformation();
    }

    @Test
    @Order(1)
    public void get_expired_refresh_token(){
        AccountInformationManager.updateSignInInformation(SignInInformation.builder()
                .login(USER_LOGIN)
                .refreshToken(REFRESH_TOKEN)
                .refreshExpirationDate(REFRESH_EXPIRATION_DATE)
                .build());
        Assertions.assertThrows(SessionExpiredException.class, TokensManager::getRefreshToken);
    }

    @Test
    @Order(2)
    public void get_actual_refresh_token(){
        ServerAuthorizationManager.signIn(UserAuthorizationForm.builder()
                .login("OneWayDream")
                .password("qwerty007")
                .build());
        Assertions.assertNotNull(AccountInformationManager.getRefreshToken());
    }

    @Test
    @Order(3)
    public void get_expired_access_token(){
        AccountInformationManager.updateAuthenticationInformation(AuthenticationInformation.builder()
                .accessToken(ACCESS_TOKEN)
                .accessExpirationDate(ACCESS_EXPIRATION_DATE)
                .build());
        String oldAccessToken = AccountInformationManager.getAccessToken();
        Assertions.assertNotEquals(oldAccessToken, TokensManager.getAccessToken());
    }

    @Test
    @Order(4)
    public void get_actual_access_token(){
        Assertions.assertEquals(AccountInformationManager.getAccessToken(), TokensManager.getAccessToken());
    }

    @AfterAll
    public static void afterAll(){
        AccountInformationManager.resetAuthenticationInformation();
    }

}
