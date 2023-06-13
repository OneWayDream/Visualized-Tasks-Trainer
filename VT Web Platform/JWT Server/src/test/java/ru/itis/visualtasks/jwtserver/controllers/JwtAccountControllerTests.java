package ru.itis.visualtasks.jwtserver.controllers;

import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.visualtasks.jwtserver.dto.JwtUserDto;
import ru.itis.visualtasks.jwtserver.entities.JwtRole;
import ru.itis.visualtasks.jwtserver.entities.JwtState;
import ru.itis.visualtasks.jwtserver.services.JwtUserService;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("JwtAccountController is working when")
@TestPropertySource(value = "classpath:test.properties")
public class JwtAccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUserService service;
    private final Gson gson = new Gson();

    @BeforeEach
    public void setUp(){
        when(service.add(RegisterNewUser.getNewUserData())).thenReturn(RegisterNewUser.getSavedNewUserData());
        when(service.updateByAccountId(UpdateByAccountId.getUpdatedUserData()))
                .thenReturn(UpdateByAccountId.getSavedUserData());
        doNothing().when(service).deleteByAccountId(DeleteByAccountId.getAccountIdToDelete());
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("registerNewUser() in working when")
    public class RegisterNewUser{

        @Test
        public void register() throws Exception {
            mockMvc.perform(
                        post("/account/register")
                                .header("JWT", getValidModuleToken())
                                .contentType("application/json")
                                .content(getNewUserContent())
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)));
        }

        private String getNewUserContent(){
            return gson.toJson(getNewUserData());
        }

        public static JwtUserDto getNewUserData() {
            return JwtUserDto.builder()
                    .accountId(1L)
                    .login("test")
                    .mail("test@gmail.com")
                    .hashPassword("hash-password")
                    .role(JwtRole.USER)
                    .state(JwtState.ACTIVE)
                    .build();
        }

        public static JwtUserDto getSavedNewUserData() {
            JwtUserDto jwtUserDto = getNewUserData();
            jwtUserDto.setId(1L);
            return jwtUserDto;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("updateByAccountId() in working when")
    public class UpdateByAccountId{

        @Test
        public void update() throws Exception {
            mockMvc.perform(
                            patch("/account")
                                    .header("JWT", getValidModuleToken())
                                    .contentType("application/json")
                                    .content(getUpdatedUserContent())
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.hashPassword", is(getSavedUserData().getHashPassword())));
        }

        private String getUpdatedUserContent(){
            return gson.toJson(getUpdatedUserData());
        }

        public static JwtUserDto getUpdatedUserData(){
            return JwtUserDto.builder()
                    .accountId(1L)
                    .login("test")
                    .mail("test@gmail.com")
                    .hashPassword("new-hash-password")
                    .role(JwtRole.USER)
                    .state(JwtState.ACTIVE)
                    .build();
        }

        public static JwtUserDto getSavedUserData(){
            JwtUserDto jwtUserDto = getUpdatedUserData();
            jwtUserDto.setId(1L);
            return jwtUserDto;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("deleteByAccountId() in working when")
    public class DeleteByAccountId{

        @Test
        public void delete_account() throws Exception {
            mockMvc.perform(
                            delete("/account/" + getAccountIdToDelete())
                                    .header("JWT", getValidModuleToken())
                    )
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        public static Long getAccountIdToDelete(){
            return 1L;
        }

    }

    public static String getValidModuleToken() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkFETUlOIiwiZXhwaXJhdGlvbiI6bnVsbCwiaWQiOjEsInN0YXRlIjoiQUNUSVZFIiwibG9naW4iOiJiYWNrZW5kIn0.hrQza1fRP9YMxWz5-luYuGPqyKR6cbILkgfOKpDmGJw";
    }

}
