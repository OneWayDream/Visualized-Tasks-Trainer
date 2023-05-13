package ru.itis.visualtasks.desktopapp.application.managers.server;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.server.UserAuthorizationForm;
import ru.itis.visualtasks.desktopapp.exceptions.server.BannedTokenException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class ServerAuthorizationManagerTests {

    @BeforeAll
    public static void beforeAll(){
        AccountInformationManager.resetAuthenticationInformation();
    }

    @Test
    @Order(1)
    public void sign_in(){
        ServerAuthorizationManager.signIn(UserAuthorizationForm.builder()
                        .login("OneWayDream")
                        .password("qwerty007")
                .build());
        Assertions.assertNotNull(AccountInformationManager.getRefreshToken());
    }

    @Test
    @Order(2)
    public void authenticate(){
        ServerAuthorizationManager.authenticate(AccountInformationManager.getRefreshToken());
        Assertions.assertEquals("OneWayDream", AccountInformationManager.getAccountLogin());
        Assertions.assertNotNull(AccountInformationManager.getAccessToken());
    }

    @Test
    @Order(3)
    public void logOut(){
        String refreshToken = AccountInformationManager.getRefreshToken();
        ServerAuthorizationManager.logOut(AccountInformationManager.getAccessToken(),
                AccountInformationManager.getRefreshToken());
        Assertions.assertThrows(BannedTokenException.class, () -> ServerAuthorizationManager.authenticate(refreshToken));
    }

    @AfterAll
    public static void afterAll(){
        AccountInformationManager.resetAuthenticationInformation();
    }

}
