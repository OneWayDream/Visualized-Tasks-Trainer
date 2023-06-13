package ru.itis.visualtasks.desktopapp.application.managers.server;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.core.TestPageFrame;
import ru.itis.visualtasks.desktopapp.application.entities.server.UserAuthorizationForm;
import ru.itis.visualtasks.desktopapp.exceptions.server.UserNotAuthorizedException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class AuthorizationManagerTests {

    @BeforeAll
    public static void beforeAll(){
        Application.changePage(new TestPageFrame());
        AccountInformationManager.resetAuthenticationInformation();
    }

    @Test
    @Order(1)
    public void sign_in(){
        Assertions.assertFalse(AuthorizationManager.isAuthorized());
        AuthorizationManager.signIn(UserAuthorizationForm.builder()
                .login("OneWayDream")
                .password("qwerty007")
                .build());
        Assertions.assertTrue(AuthorizationManager.isAuthorized());
        Assertions.assertEquals("OneWayDream", AuthorizationManager.getAccountName());
    }

    @Test
    @Order(2)
    public void log_out(){
        AuthorizationManager.logOut();
        Assertions.assertFalse(AuthorizationManager.isAuthorized());
        Assertions.assertThrows(UserNotAuthorizedException.class, AuthorizationManager::getAccountName);
    }

}
