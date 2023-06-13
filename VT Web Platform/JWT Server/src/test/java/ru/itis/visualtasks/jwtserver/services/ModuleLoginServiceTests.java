package ru.itis.visualtasks.jwtserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.jwtserver.dto.AccessTokenResponse;
import ru.itis.visualtasks.jwtserver.dto.JwtModuleDto;
import ru.itis.visualtasks.jwtserver.dto.ModuleAuthorizationForm;
import ru.itis.visualtasks.jwtserver.dto.RefreshTokenResponse;
import ru.itis.visualtasks.jwtserver.entities.JwtRole;
import ru.itis.visualtasks.jwtserver.entities.JwtState;
import ru.itis.visualtasks.jwtserver.exceptions.auth.IncorrectUserDataException;
import ru.itis.visualtasks.jwtserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.jwtserver.exceptions.token.IncorrectJwtException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest()
@DisplayName("ModuleLoginService is working when")
@TestPropertySource(value = "classpath:test.properties")
public class ModuleLoginServiceTests {

    @Autowired
    private ModuleLoginService moduleLoginService;

    @MockBean
    private JwtModuleService jwtModuleService;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        when(jwtModuleService.findByLogin(LoginTests.getIncorrectLoginModuleLoginForm().getLogin()))
                .thenThrow(EntityNotFoundException.class);
        when(jwtModuleService.findByLogin(LoginTests.getCorrectModuleLoginForm().getLogin()))
                .thenReturn(LoginTests.getCorrectJwtModuleDto());
        when(passwordEncoder.matches(
                LoginTests.getIncorrectPasswordModuleLoginForm().getPassword(),
                LoginTests.getCorrectJwtModuleDto().getHashPassword())
        ).thenReturn(false);
        when(passwordEncoder.matches(
                LoginTests.getCorrectModuleLoginForm().getPassword(),
                LoginTests.getCorrectJwtModuleDto().getHashPassword())
        ).thenReturn(true);
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("login() in working when")
    public class LoginTests {

        @Test
        public void login_with_incorrect_login(){
            assertThrows(IncorrectUserDataException.class,
                    () -> moduleLoginService.login(getIncorrectLoginModuleLoginForm()));
        }

        public static ModuleAuthorizationForm getIncorrectLoginModuleLoginForm() {
            return ModuleAuthorizationForm.builder()
                    .login("incorrect_login")
                    .password("correct_password")
                    .build();
        }

        @Test
        public void login_with_incorrect_password(){
            assertThrows(IncorrectUserDataException.class,
                    () -> moduleLoginService.login(getIncorrectPasswordModuleLoginForm()));
        }

        public static ModuleAuthorizationForm getIncorrectPasswordModuleLoginForm() {
            return ModuleAuthorizationForm.builder()
                    .login("correct_login")
                    .password("incorrect_password")
                    .build();
        }

        @Test
        public void login_with_correct_credentials(){
            RefreshTokenResponse actual = moduleLoginService.login(getCorrectModuleLoginForm());
            assertNotNull(actual.getToken());
            assertNotNull(actual.getExpiredTime());
        }

        public static ModuleAuthorizationForm getCorrectModuleLoginForm() {
            return ModuleAuthorizationForm.builder()
                    .login("correct_login")
                    .password("correct_password")
                    .build();
        }

        public static JwtModuleDto getCorrectJwtModuleDto(){
            return JwtModuleDto.builder()
                    .id(1L)
                    .login("correct_login")
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
            assertThrows(IncorrectJwtException.class, () -> moduleLoginService.authenticate(getIncorrectRefreshToken()));
        }

        @Test
        public void authenticate_with_correct_token(){
            AccessTokenResponse actual = moduleLoginService.authenticate(getCorrectRefreshToken());
            assertNotNull(actual.getToken());
        }

        public static String getIncorrectRefreshToken(){
            return "incorrect-refresh-token";
        }

        public static String getCorrectRefreshToken(){
            return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkFETUlOIiwiZXhwaXJhdGlvbiI6bnVsbCwiaWQiOjEsInN0YXRlIjoiQUNUSVZFIiwibG9naW4iOiJiYWNrZW5kIn0.-rwrnrdLlZXdxvDJmumS2losWORYhnzKzxbRqEjyn4I";
        }

    }

}
