package ru.itis.visualtasks.desktopapp.application.loaders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import ru.itis.visualtasks.desktopapp.application.entities.project.AuthorizationInfo;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class AuthorizationInfoLoaderTests {

    @Test
    public void get_authorization_info(){
        AuthorizationInfo authorizationInfo = AuthorizationInfoLoader.getAuthorizationInfo();
        Assertions.assertNull(authorizationInfo.getLogin());
    }

    @Test
    public void get_default_authorization_info(){
        Assertions.assertEquals(new AuthorizationInfo(),
                AuthorizationInfoLoader.getDefaultAuthorizationInfo());
    }

    @Test
    public void save_authorization_info(){
        AuthorizationInfo authorizationInfo = AuthorizationInfo.builder()
                .login("AnotherUser")
                .build();
        AuthorizationInfoLoader.saveAuthorizationInfo(authorizationInfo);
        authorizationInfo = AuthorizationInfoLoader.getAuthorizationInfo();
        Assertions.assertEquals("AnotherUser", authorizationInfo.getLogin());
        saveDefaultAuthorizationInfo();
    }

    private void saveDefaultAuthorizationInfo() {
        AuthorizationInfo defaultAuthorizationInfo = AuthorizationInfo.builder()
                .login("TestUser")
                .build();
        AuthorizationInfoLoader.saveAuthorizationInfo(defaultAuthorizationInfo);
    }

}
