package ru.itis.visualtasks.backendserver.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.visualtasks.backendserver.dto.AccountDto;
import ru.itis.visualtasks.backendserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.backendserver.models.UserRole;
import ru.itis.visualtasks.backendserver.models.UserState;
import ru.itis.visualtasks.backendserver.services.AccountService;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(value = "classpath:test.properties")
@DisplayName("AdministrationController is working when")
public class AccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @BeforeEach
    public void setUp(){
        when(accountService.findById(GetByIdTests.getExistingId())).thenReturn(GetByIdTests.getExpectedAccountDto());
        when(accountService.findById(GetByIdTests.getNotExistingId())).thenThrow(EntityNotFoundException.class);
        when(accountService.findByLogin(GetByLoginTests.getExistingLogin()))
                .thenReturn(GetByLoginTests.getExpectedAccountDto());
        when(accountService.findByLogin(GetByLoginTests.getNotExistingLogin()))
                .thenThrow(EntityNotFoundException.class);
        when(accountService.findByMail(GetByMailTests.getExistingMail()))
                .thenReturn(GetByMailTests.getExpectedAccountDto());
        when(accountService.findByMail(GetByMailTests.getNotExistingMail()))
                .thenThrow(EntityNotFoundException.class);
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getById() in working when")
    public class GetByIdTests{

        @Test
        public void get_by_existing_id() throws Exception {
            AccountDto expected = getExpectedAccountDto();
            mockMvc.perform(
                            get("/user/by-id/" + getExistingId())
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(expected.getId().intValue())))
                    .andExpect(jsonPath("$.login", is(expected.getLogin())))
                    .andExpect(jsonPath("$.mail", is(expected.getMail())))
                    .andExpect(jsonPath("$.role", is(expected.getRole().toString())))
                    .andExpect(jsonPath("$.state", is(expected.getState().toString())))
                    .andExpect(jsonPath("$.registrationDate", is(expected.getRegistrationDate().toString())));
        }

        public static Long getExistingId(){
            return 1L;
        }

        public static AccountDto getExpectedAccountDto(){
            return AccountDto.builder()
                    .id(1L)
                    .login("existing_login")
                    .mail("existing_mail@gmail.com")
                    .role(UserRole.USER)
                    .state(UserState.ACTIVE)
                    .registrationDate(LocalDate.now())
                    .build();
        }

        @Test
        public void get_by_not_existing_id() throws Exception {
            mockMvc.perform(
                            get("/user/by-id/" + getNotExistingId())
                    )
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }

        public static Long getNotExistingId(){
            return 100L;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getByLogin() in working when")
    public class GetByLoginTests{

        @Test
        public void get_by_existing_login() throws Exception {
            AccountDto expected = getExpectedAccountDto();
            mockMvc.perform(
                            get("/user/by-login/" + getExistingLogin())
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(expected.getId().intValue())))
                    .andExpect(jsonPath("$.login", is(expected.getLogin())))
                    .andExpect(jsonPath("$.mail", is(expected.getMail())))
                    .andExpect(jsonPath("$.role", is(expected.getRole().toString())))
                    .andExpect(jsonPath("$.state", is(expected.getState().toString())))
                    .andExpect(jsonPath("$.registrationDate", is(expected.getRegistrationDate().toString())));
        }

        public static String getExistingLogin(){
            return "existing_login";
        }

        public static AccountDto getExpectedAccountDto(){
            return AccountDto.builder()
                    .id(1L)
                    .login("existing_login")
                    .mail("existing_mail@gmail.com")
                    .role(UserRole.USER)
                    .state(UserState.ACTIVE)
                    .registrationDate(LocalDate.now())
                    .build();
        }

        @Test
        public void get_by_not_existing_login() throws Exception {
            mockMvc.perform(
                            get("/user/by-login/" + getNotExistingLogin())
                    )
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }

        public static String getNotExistingLogin(){
            return "not_existing_login";
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getByMail() in working when")
    public class GetByMailTests{

        @Test
        public void get_by_existing_mail() throws Exception {
            AccountDto expected = getExpectedAccountDto();
            mockMvc.perform(
                            get("/user/by-mail/" + getExistingMail())
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(expected.getId().intValue())))
                    .andExpect(jsonPath("$.login", is(expected.getLogin())))
                    .andExpect(jsonPath("$.mail", is(expected.getMail())))
                    .andExpect(jsonPath("$.role", is(expected.getRole().toString())))
                    .andExpect(jsonPath("$.state", is(expected.getState().toString())))
                    .andExpect(jsonPath("$.registrationDate", is(expected.getRegistrationDate().toString())));
        }

        public static String getExistingMail(){
            return "existing_mail@gmail.com";
        }

        public static AccountDto getExpectedAccountDto(){
            return AccountDto.builder()
                    .id(1L)
                    .login("existing_login")
                    .mail("existing_mail@gmail.com")
                    .role(UserRole.USER)
                    .state(UserState.ACTIVE)
                    .registrationDate(LocalDate.now())
                    .build();
        }

        @Test
        public void get_by_not_existing_mail() throws Exception {
            mockMvc.perform(
                            get("/user/by-mail/" + getNotExistingMail())
                    )
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }

        public static String getNotExistingMail(){
            return "not_existing_mail@gmail.com";
        }

    }

}
