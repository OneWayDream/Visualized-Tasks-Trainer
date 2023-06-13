package ru.itis.visualtasks.desktopapp.application.managers.server;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.server.AuthenticationInformation;
import ru.itis.visualtasks.desktopapp.application.entities.server.SignInInformation;
import ru.itis.visualtasks.desktopapp.exceptions.server.UserNotAuthorizedException;

import java.time.LocalDateTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class AccountInformationManagerTests {

    private static final String USER_LOGIN = "User";
    private static final String REFRESH_TOKEN = "refresh-token";
    private static final String REFRESH_EXPIRATION_DATE = LocalDateTime.now().plusHours(1).toString();
    private static final String ACCESS_TOKEN = "access-token";
    private static final String ACCESS_EXPIRATION_DATE = LocalDateTime.now().plusHours(1).toString();

    @BeforeAll
    public static void beforeAll(){
        AccountInformationManager.resetAuthenticationInformation();
    }

    @Test
    @Order(1)
    public void get_default_account_information(){
        Assertions.assertThrows(UserNotAuthorizedException.class, AccountInformationManager::getAccountLogin);
        Assertions.assertNull(AccountInformationManager.getRefreshToken());
        Assertions.assertNull(AccountInformationManager.getRefreshExpirationDate());
        Assertions.assertThrows(UserNotAuthorizedException.class, AccountInformationManager::getAccessToken);
        Assertions.assertThrows(UserNotAuthorizedException.class, AccountInformationManager::getAccessExpirationDate);
        Assertions.assertFalse(AccountInformationManager.isUserAuthorized());
    }

    @Test
    @Order(2)
    public void update_sign_in_information(){
        AccountInformationManager.updateSignInInformation(SignInInformation.builder()
                        .login(USER_LOGIN)
                        .refreshToken(REFRESH_TOKEN)
                        .refreshExpirationDate(REFRESH_EXPIRATION_DATE)
                .build());
        Assertions.assertThrows(UserNotAuthorizedException.class, AccountInformationManager::getAccountLogin);
        Assertions.assertEquals(REFRESH_TOKEN, AccountInformationManager.getRefreshToken());
        Assertions.assertEquals(LocalDateTime.parse(REFRESH_EXPIRATION_DATE),
                AccountInformationManager.getRefreshExpirationDate());
        Assertions.assertFalse(AccountInformationManager.isUserAuthorized());
    }

    @Test
    @Order(3)
    public void update_auth_information(){
        AccountInformationManager.updateAuthenticationInformation(AuthenticationInformation.builder()
                .accessToken(ACCESS_TOKEN)
                .accessExpirationDate(ACCESS_EXPIRATION_DATE)
                .build());
        Assertions.assertEquals(USER_LOGIN, AccountInformationManager.getAccountLogin());
        Assertions.assertEquals(REFRESH_TOKEN, AccountInformationManager.getRefreshToken());
        Assertions.assertEquals(LocalDateTime.parse(REFRESH_EXPIRATION_DATE),
                AccountInformationManager.getRefreshExpirationDate());
        Assertions.assertEquals(ACCESS_TOKEN, AccountInformationManager.getAccessToken());
        Assertions.assertEquals(LocalDateTime.parse(ACCESS_EXPIRATION_DATE),
                AccountInformationManager.getAccessExpirationDate());
        Assertions.assertTrue(AccountInformationManager.isUserAuthorized());
    }

    @AfterAll
    public static void afterAll(){
        AccountInformationManager.resetAuthenticationInformation();
    }

}
