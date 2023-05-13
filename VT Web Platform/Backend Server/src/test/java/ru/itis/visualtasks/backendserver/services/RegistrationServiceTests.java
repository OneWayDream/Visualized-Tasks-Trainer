package ru.itis.visualtasks.backendserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.backendserver.dto.AccountDto;
import ru.itis.visualtasks.backendserver.dto.forms.SignUpForm;
import ru.itis.visualtasks.backendserver.models.UserRole;
import ru.itis.visualtasks.backendserver.models.UserState;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest()
@DisplayName("RegistrationService is working when")
@TestPropertySource(value = "classpath:test.properties")
public class RegistrationServiceTests {

    @Autowired
    private RegistrationService registrationService;

    @MockBean
    private AccountService accountService;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp(){
        when(passwordEncoder.encode(RegisterNewAccountTests.getNewAccountDto().getPassword()))
                .thenReturn(RegisterNewAccountTests.getExpectedAccountDto().getPassword());
        when(accountService.add(RegisterNewAccountTests.getNewAccountDto()))
                .thenReturn(RegisterNewAccountTests.getExpectedAccountDto());
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("registerNewAccountTests() in working when")
    public class RegisterNewAccountTests{

        @Test
        public void register(){
            AccountDto expected = getExpectedAccountDto();
            AccountDto actual = registrationService.registerNewAccount(getSignUpForm());
            assertEquals(expected, actual);
        }

        public static SignUpForm getSignUpForm(){
            return SignUpForm.builder()
                    .login("login")
                    .email("test@gmail.com")
                    .password("password")
                    .repeatedPassword("password")
                    .build();
        }

        public static AccountDto getNewAccountDto(){
            return AccountDto.builder()
                    .login(getSignUpForm().getLogin())
                    .mail(getSignUpForm().getEmail())
                    .password(getSignUpForm().getPassword())
                    .state(UserState.ACTIVE)
                    .role(UserRole.USER)
                    .registrationDate(LocalDate.now())
                    .build();
        }

        public static AccountDto getExpectedAccountDto(){
            return AccountDto.builder()
                    .id(1L)
                    .login(getSignUpForm().getLogin())
                    .mail(getSignUpForm().getEmail())
                    .password("hash-password")
                    .state(UserState.ACTIVE)
                    .role(UserRole.USER)
                    .registrationDate(LocalDate.now())
                    .build();
        }

    }

}
