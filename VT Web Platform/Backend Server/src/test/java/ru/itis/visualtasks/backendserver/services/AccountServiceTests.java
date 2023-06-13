package ru.itis.visualtasks.backendserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.backendserver.dto.AccountDto;
import ru.itis.visualtasks.backendserver.dto.forms.JwtUpdateForm;
import ru.itis.visualtasks.backendserver.dto.forms.TokenRegistrationForm;
import ru.itis.visualtasks.backendserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.backendserver.models.Account;
import ru.itis.visualtasks.backendserver.models.UserRole;
import ru.itis.visualtasks.backendserver.models.UserState;
import ru.itis.visualtasks.backendserver.repositories.AccountRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest()
@DisplayName("AccountService is working when")
@TestPropertySource(value = "classpath:test.properties")
public class AccountServiceTests {

    @Autowired
    private AccountService accountService;
    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private JwtModuleService jwtModuleService;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        when(accountRepository.findAll()).thenReturn(FindAllTests.expectedAllAccounts());
        doNothing().when(accountRepository).delete(DeleteTests.accountToDelete());
        doNothing().when(jwtModuleService).deleteUserOnAuthorizationServer(DeleteTests.accountToDelete().getId());
        when(passwordEncoder.encode(AddTests.accountDtoToAdd().getPassword()))
                .thenReturn(AddTests.accountToAdd().getHashPassword());
        when(accountRepository.save(AddTests.accountToAdd())).thenReturn(AddTests.accountAddResponse());
        doNothing().when(jwtModuleService).registerOnAuthorizationServer(any(TokenRegistrationForm.class));
        when(accountRepository.findById(FindByIdTests.existingAccount().getId())).thenReturn(
                Optional.ofNullable(FindByIdTests.existingAccount())
        );
        when(passwordEncoder.encode(UpdateTests.updatedAccountDto().getPassword()))
                .thenReturn(UpdateTests.updatedAccount().getHashPassword());
        when(accountRepository.findById(FindByIdTests.notExistingId())).thenThrow(EntityNotFoundException.class);
        when(accountRepository.save(UpdateTests.updatedAccount())).thenReturn(
                UpdateTests.updatedAccount()
        );
        doNothing().when(jwtModuleService).updateUserOnAuthorizationServer(any(JwtUpdateForm.class));
        when(accountRepository.findByLogin(FindByLoginTests.existingAccount().getLogin())).thenReturn(
                Optional.ofNullable(FindByLoginTests.existingAccount())
        );
        when(accountRepository.findByLogin(FindByLoginTests.notExistingLogin()))
                .thenThrow(EntityNotFoundException.class);
        when(accountRepository.findByMail(FindByMailTests.existingAccount().getMail())).thenReturn(
                Optional.ofNullable(FindByMailTests.existingAccount())
        );
        when(accountRepository.findByMail(FindByMailTests.notExistingMail()))
                .thenThrow(EntityNotFoundException.class);
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    public class FindAllTests {

        @Test
        public void find_all(){
            List<AccountDto> actual = accountService.findAll();
            assertEquals(expectedAllAccountDtos(), actual);
        }

        public static List<Account> expectedAllAccounts(){
            return List.of(
                    Account.builder()
                            .id(1L)
                            .login("first")
                            .mail("first@gmail.com")
                            .hashPassword("first-hash-password")
                            .state(UserState.ACTIVE)
                            .role(UserRole.USER)
                            .registrationDate(LocalDate.parse("2023-04-16"))
                            .build(),
                    Account.builder()
                            .id(2L)
                            .login("second")
                            .mail("second@gmail.com")
                            .hashPassword("second-hash-password")
                            .state(UserState.ACTIVE)
                            .role(UserRole.USER)
                            .registrationDate(LocalDate.parse("2023-04-16"))
                            .build()
            );
        }

        public static List<AccountDto> expectedAllAccountDtos(){
            return AccountDto.from(expectedAllAccounts());
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("delete() in working when")
    public class DeleteTests {

        @Test
        public void delete_existing(){
            accountService.delete(accountDtoToDelete());
        }

        public static Account accountToDelete(){
            return Account.builder()
                    .id(3L)
                    .build();
        }

        public static AccountDto accountDtoToDelete(){
            return AccountDto.from(accountToDelete());
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("add() in working when")
    public class AddTests {

        @Test
        public void add(){
            AccountDto expected = accountDtoAddResponse();
            AccountDto actual = accountService.add(accountDtoToAdd());
            assertEquals(expected, actual);
        }


        public static AccountDto accountDtoToAdd(){
            return AccountDto.builder()
                    .id(1L)
                    .login("added-login")
                    .mail("added-mail@gmail.com")
                    .password("added-password")
                    .state(UserState.ACTIVE)
                    .role(UserRole.USER)
                    .registrationDate(LocalDate.parse("2023-04-16"))
                    .build();
        }

        public static Account accountToAdd(){
            return Account.builder()
                    .id(1L)
                    .login("added-login")
                    .mail("added-mail@gmail.com")
                    .hashPassword("added-hash-password")
                    .state(UserState.ACTIVE)
                    .role(UserRole.USER)
                    .registrationDate(LocalDate.parse("2023-04-16"))
                    .build();
        }

        public static Account accountAddResponse(){
            Account account = accountToAdd();
            account.setId(3L);
            return account;
        }

        public static AccountDto accountDtoAddResponse(){
            return AccountDto.from(accountAddResponse());
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findById() in working when")
    public class FindByIdTests {

        @Test
        public void find_existing_by_id(){
            AccountDto expected = existingAccountDto();
            AccountDto actual = accountService.findById(existingAccount().getId());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_id(){
            assertThrows(EntityNotFoundException.class, () -> accountService.findById(notExistingId()));
        }

        public static Account existingAccount(){
            return FindAllTests.expectedAllAccounts().get(0);
        }

        public static AccountDto existingAccountDto(){
            return AccountDto.from(existingAccount());
        }

        public static Long notExistingId(){
            return 100L;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("update() in working when")
    public class UpdateTests {

        @Test
        public void update_existing(){
            AccountDto expected = expectedUpdatedAccount();
            AccountDto actual = accountService.update(updatedAccountDto());
            assertEquals(expected, actual);
        }

        @Test
        public void update_not_existing(){
            assertThrows(EntityNotFoundException.class, () -> accountService.update(notExistingUpdatedTAccountDto()));
        }

        public static Account updatedAccount(){
            return Account.builder()
                    .id(1L)
                    .login("new-first")
                    .mail("first@gmail.com")
                    .hashPassword("first-hash-password")
                    .state(UserState.ACTIVE)
                    .role(UserRole.USER)
                    .registrationDate(LocalDate.parse("2023-04-16"))
                    .build();
        }

        public static AccountDto updatedAccountDto(){
            return AccountDto.builder()
                    .id(1L)
                    .login("new-first")
                    .mail("first@gmail.com")
                    .password("first-password")
                    .state(UserState.ACTIVE)
                    .role(UserRole.USER)
                    .registrationDate(LocalDate.parse("2023-04-16"))
                    .build();
        }

        public static AccountDto expectedUpdatedAccount(){
            return AccountDto.from(updatedAccount());
        }

        public static AccountDto notExistingUpdatedTAccountDto(){
            return AccountDto.builder().id(FindByIdTests.notExistingId()).build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findByLogin() in working when")
    public class FindByLoginTests {

        @Test
        public void find_existing_by_login(){
            AccountDto expected = existingAccountDto();
            AccountDto actual = accountService.findByLogin(existingAccount().getLogin());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_login(){
            assertThrows(EntityNotFoundException.class, () -> accountService.findByLogin(notExistingLogin()));
        }

        public static Account existingAccount(){
            return FindAllTests.expectedAllAccounts().get(0);
        }

        public static AccountDto existingAccountDto(){
            return AccountDto.from(existingAccount());
        }

        public static String notExistingLogin(){
            return "not_existing_login";
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findByMail() in working when")
    public class FindByMailTests {

        @Test
        public void find_existing_by_mail(){
            AccountDto expected = existingAccountDto();
            AccountDto actual = accountService.findByMail(existingAccount().getMail());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_mail(){
            assertThrows(EntityNotFoundException.class, () -> accountService.findByLogin(notExistingMail()));
        }

        public static Account existingAccount(){
            return FindAllTests.expectedAllAccounts().get(0);
        }

        public static AccountDto existingAccountDto(){
            return AccountDto.from(existingAccount());
        }

        public static String notExistingMail(){
            return "not_existing_mail@gmail.com";
        }

    }
    
}
