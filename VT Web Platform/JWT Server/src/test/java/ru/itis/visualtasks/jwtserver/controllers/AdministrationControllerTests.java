package ru.itis.visualtasks.jwtserver.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.visualtasks.jwtserver.services.JwtBlacklistService;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(value = "classpath:test.properties")
@DisplayName("AdministrationController is working when")
public class AdministrationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtBlacklistService jwtBlacklistService;

    @BeforeEach
    public void setUp(){
        doNothing().when(jwtBlacklistService).add(BanTokenTests.getTokenToBan());
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("banToken() in working when")
    public class BanTokenTests {

        @Test
        public void wrong_role_access() throws Exception {
            mockMvc.perform(
                        post("/ban-token")
                                .header("JWT", getInvalidRoleToken())
                                .content(getTokenToBan())
                    )
                    .andDo(print())
                    .andExpect(status().is(455));
        }

        public static String getInvalidRoleToken(){
            return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwiYWNjb3VudF9pZCI6OSwicm9sZSI6IlVTRVIiLCJzYWx0IjoiMWZjYWZlZGQtNDJiYy00YWRhLWI4YzMtOTliNzMwOGQ5NzM4IiwiZXhwaXJhdGlvbiI6bnVsbCwic3RhdGUiOiJBQ1RJVkUiLCJsb2dpbiI6Ik9uZVdheURyZWFtIn0._aX20py8eNU8249Hd-xebLMo1B1OeNnX_xa0UeSn0Mw";
        }

        @Test
        public void pass_null_token() throws Exception {
            mockMvc.perform(
                            post("/ban-token")
                                    .header("JWT", getValidRoleToken())
                    )
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }

        public static String getValidRoleToken(){
            return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwiYWNjb3VudF9pZCI6OSwicm9sZSI6IkFETUlOIiwic2FsdCI6IjJiNzg0MjNlLThhMWEtNDU3YS04OGFkLTdiMGQzNzFiYTJlYSIsImV4cGlyYXRpb24iOm51bGwsInN0YXRlIjoiQUNUSVZFIiwibG9naW4iOiJPbmVXYXlEcmVhbSJ9.jWbdwK3F7IsiM7VJJcYMXyQgcDK4x4jpES1Cyiwlt80";
        }

        @Test
        public void correct_token_banishment() throws Exception {
            mockMvc.perform(
                            post("/ban-token")
                                    .header("JWT", getValidRoleToken())
                                    .content(getTokenToBan())
                    )
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        public static String getTokenToBan(){
            return "token-to-ban";
        }

    }

}
