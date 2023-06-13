package ru.itis.visualtasks.jwtserver.controllers;

import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.visualtasks.jwtserver.dto.AccessTokenResponse;
import ru.itis.visualtasks.jwtserver.dto.ModuleAuthorizationForm;
import ru.itis.visualtasks.jwtserver.dto.RefreshTokenResponse;
import ru.itis.visualtasks.jwtserver.dto.UserAuthorizationForm;
import ru.itis.visualtasks.jwtserver.exceptions.auth.IncorrectUserDataException;
import ru.itis.visualtasks.jwtserver.services.JwtBlacklistService;
import ru.itis.visualtasks.jwtserver.services.ModuleLoginService;
import ru.itis.visualtasks.jwtserver.services.UserLoginService;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("LoginController is working when")
@TestPropertySource(value = "classpath:test.properties")
public class LoginControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModuleLoginService moduleLoginService;
    @MockBean
    private UserLoginService userLoginService;
    @MockBean
    private JwtBlacklistService jwtBlacklistService;
    private final Gson gson = new Gson();

    @BeforeEach
    public void setUp(){
        when(moduleLoginService.login(LoginModuleTests.getCorrectModuleAuthorizationForm()))
                .thenReturn(LoginModuleTests.getExpectedRefreshResponse());
        when(moduleLoginService.login(LoginModuleTests.getIncorrectModuleAuthorizationForm()))
                .thenThrow(IncorrectUserDataException.class);
        when(moduleLoginService.authenticate(AuthenticateModuleTests.getCorrectRefreshToken()))
                .thenReturn(AuthenticateModuleTests.getExpectedAccessResponse());
        when(userLoginService.login(LoginUserTests.getCorrectUserAuthorizationForm()))
                .thenReturn(LoginUserTests.getExpectedRefreshResponse());
        when(userLoginService.login(LoginUserTests.getIncorrectUserAuthorizationForm()))
                .thenThrow(IncorrectUserDataException.class);
        when(userLoginService.authenticate(AuthenticateUserTests.getCorrectRefreshToken()))
                .thenReturn(AuthenticateUserTests.getExpectedAccessResponse());
        doNothing().when(jwtBlacklistService).add(LogOutTests.getTokenToLogOut());
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("loginModule() in working when")
    public class LoginModuleTests {

        @Test
        public void log_in_with_correct_data() throws Exception{
            mockMvc.perform(
                        post("/login-module")
                                .contentType("application/json")
                                .content(getCorrectAuthorizationData())
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token", is(getExpectedRefreshResponse().getToken())))
                    .andExpect(jsonPath("$.expiredTime",
                            is(getExpectedRefreshResponse().getExpiredTime().toString())));
        }

        public String getCorrectAuthorizationData(){
            return gson.toJson(getCorrectModuleAuthorizationForm());
        }

        public static ModuleAuthorizationForm getCorrectModuleAuthorizationForm() {
            return ModuleAuthorizationForm.builder()
                    .login("correct-login")
                    .password("correct-password")
                    .build();
        }

        public static RefreshTokenResponse getExpectedRefreshResponse(){
            return RefreshTokenResponse.builder()
                    .token("refresh-token")
                    .expiredTime(LocalDateTime.parse("2023-05-05T17:19:21.862496"))
                    .build();
        }

        @Test
        public void log_in_with_incorrect_data() throws Exception{
            mockMvc.perform(
                            post("/login-module")
                                    .contentType("application/json")
                                    .content(getIncorrectAuthorizationData())
                    )
                    .andDo(print())
                    .andExpect(status().is(457));
        }

        public String getIncorrectAuthorizationData(){
            return gson.toJson(getIncorrectModuleAuthorizationForm());
        }

        public static ModuleAuthorizationForm getIncorrectModuleAuthorizationForm() {
            return ModuleAuthorizationForm.builder()
                    .login("incorrect-login")
                    .password("incorrect-password")
                    .build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("authenticateModule() in working when")
    public class AuthenticateModuleTests {

        @Test
        public void auth_with_correct_data() throws Exception{
            mockMvc.perform(
                            post("/auth-module")
                                    .header("refresh-token", getCorrectRefreshToken())
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token", is(getExpectedAccessResponse().getToken())))
                    .andExpect(jsonPath("$.expiredTime",
                            is(getExpectedAccessResponse().getExpiredTime().toString())));
        }

        public static String getCorrectRefreshToken(){
            return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkFETUlOIiwiZXhwaXJhdGlvbiI6bnVsbCwiaWQiOjEsInN0YXRlIjoiQUNUSVZFIiwibG9naW4iOiJiYWNrZW5kIn0.-rwrnrdLlZXdxvDJmumS2losWORYhnzKzxbRqEjyn4I";
        }

        public static AccessTokenResponse getExpectedAccessResponse(){
            return AccessTokenResponse.builder()
                    .token("access-token")
                    .expiredTime(LocalDateTime.parse("2023-05-05T17:19:21.862496"))
                    .build();
        }

        @Test
        public void auth_with_invalid_token() throws Exception {
            mockMvc.perform(
                            post("/auth-module")
                                    .header("refresh-token", getInvalidToken())
                    )
                    .andDo(print())
                    .andExpect(status().is(454));
        }

        public static String getInvalidToken(){
            return "invalid-refresh-token";
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("loginUser() in working when")
    public class LoginUserTests {

        @Test
        public void log_in_with_correct_data() throws Exception{
            mockMvc.perform(
                            post("/login-user")
                                    .contentType("application/json")
                                    .content(getCorrectAuthorizationData())
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token", is(getExpectedRefreshResponse().getToken())))
                    .andExpect(jsonPath("$.expiredTime",
                            is(getExpectedRefreshResponse().getExpiredTime().toString())));
        }

        public String getCorrectAuthorizationData(){
            return gson.toJson(getCorrectUserAuthorizationForm());
        }

        public static UserAuthorizationForm getCorrectUserAuthorizationForm() {
            return UserAuthorizationForm.builder()
                    .login("correct-login")
                    .password("correct-password")
                    .build();
        }

        public static RefreshTokenResponse getExpectedRefreshResponse(){
            return RefreshTokenResponse.builder()
                    .token("refresh-token")
                    .expiredTime(LocalDateTime.parse("2023-05-05T17:19:21.862496"))
                    .build();
        }

        @Test
        public void log_in_with_incorrect_data() throws Exception{
            mockMvc.perform(
                            post("/login-user")
                                    .contentType("application/json")
                                    .content(getIncorrectAuthorizationData())
                    )
                    .andDo(print())
                    .andExpect(status().is(457));
        }

        public String getIncorrectAuthorizationData(){
            return gson.toJson(getIncorrectUserAuthorizationForm());
        }

        public static UserAuthorizationForm getIncorrectUserAuthorizationForm() {
            return UserAuthorizationForm.builder()
                    .login("incorrect-login")
                    .password("incorrect-password")
                    .build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("authenticateUser() in working when")
    public class AuthenticateUserTests {

        @Test
        public void auth_with_correct_data() throws Exception{
            mockMvc.perform(
                            post("/auth-user")
                                    .header("refresh-token", getCorrectRefreshToken())
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token", is(getExpectedAccessResponse().getToken())))
                    .andExpect(jsonPath("$.expiredTime",
                            is(getExpectedAccessResponse().getExpiredTime().toString())));
        }

        public static String getCorrectRefreshToken(){
            return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwicmVkaXNfaWQiOiIyNzRkZDZhOC0zYTc0LTQ1ZWQtYWY1My0xMjA4NmE3MjkzYzQiLCJhY2NvdW50X2lkIjo5LCJyb2xlIjoiVVNFUiIsInNhbHQiOiI5MzdkMTM0ZS00MWJkLTQxYTItODQ2OS0zYTRhOWI1OTlhNGQiLCJleHBpcmF0aW9uIjpudWxsLCJzdGF0ZSI6IkFDVElWRSIsImxvZ2luIjoiT25lV2F5RHJlYW0ifQ.696IuoRw0-eR0VabsPQiRBvkvIFeJeP5JD56BV5kNZk";
        }

        public static AccessTokenResponse getExpectedAccessResponse(){
            return AccessTokenResponse.builder()
                    .token("access-token")
                    .expiredTime(LocalDateTime.parse("2023-05-05T17:19:21.862496"))
                    .build();
        }

        @Test
        public void auth_with_invalid_token() throws Exception {
            mockMvc.perform(
                            post("/auth-module")
                                    .header("refresh-token", getInvalidToken())
                    )
                    .andDo(print())
                    .andExpect(status().is(454));
        }

        public static String getInvalidToken(){
            return "invalid-refresh-token";
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("logOut() in working when")
    public class LogOutTests {

        @Test
        public void log_out_token() throws Exception {
            mockMvc.perform(
                            get("/logout")
                                    .header("JWT", getTokenToLogOut())
                    )
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        public static String getTokenToLogOut(){
            return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwicmVkaXNfaWQiOiIyNzRkZDZhOC0zYTc0LTQ1ZWQtYWY1My0xMjA4NmE3MjkzYzQiLCJhY2NvdW50X2lkIjo5LCJyb2xlIjoiVVNFUiIsInNhbHQiOiI5MzdkMTM0ZS00MWJkLTQxYTItODQ2OS0zYTRhOWI1OTlhNGQiLCJleHBpcmF0aW9uIjpudWxsLCJzdGF0ZSI6IkFDVElWRSIsImxvZ2luIjoiT25lV2F5RHJlYW0ifQ.696IuoRw0-eR0VabsPQiRBvkvIFeJeP5JD56BV5kNZk";
        }

    }

}
