package ru.itis.visualtasks.backendserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.backendserver.dto.AccountDto;
import ru.itis.visualtasks.backendserver.dto.forms.JwtUpdateForm;
import ru.itis.visualtasks.backendserver.dto.forms.TokenRegistrationForm;
import ru.itis.visualtasks.backendserver.models.Account;
import ru.itis.visualtasks.backendserver.models.UserRole;
import ru.itis.visualtasks.backendserver.models.UserState;
import ru.itis.visualtasks.backendserver.repositories.AccountRepository;

import java.time.LocalDate;

@SpringBootTest()
@DisplayName("AccountService is working when")
@TestPropertySource(value = "classpath:test.properties")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class JwtModuleServiceTests {

    @Autowired
    private JwtModuleService jwtModuleService;
    @Autowired
    private AccountRepository accountRepository;

    private static Long accountId;

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("registerOnAuthorizationServer() in working when")
    @Order(1)
    public class RegisterOnAuthorizationServerTests{

        @Test
        public void register(){
            Account savedAccount = accountRepository.save(accountToSave());
            accountId = savedAccount.getId();
            jwtModuleService.registerOnAuthorizationServer(TokenRegistrationForm.builder()
                    .accountId(savedAccount.getId())
                    .login(savedAccount.getLogin())
                    .mail(savedAccount.getMail())
                    .hashPassword(savedAccount.getHashPassword())
                    .state(savedAccount.getState())
                    .role(savedAccount.getRole())
                    .build());
        }

        public static Account accountToSave(){
            return AccountDto.to(AccountDto.builder()
                            .mail("test")
                            .login("test")
                            .password("password")
                            .state(UserState.ACTIVE)
                            .role(UserRole.USER)
                            .registrationDate(LocalDate.now())
                    .build());
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("updateUserOnAuthorizationServer() in working when")
    @Order(2)
    public class UpdateUserOnAuthorizationServerTests{

        @Test
        public void update(){
            Account accountToUpdate = accountToUpdate();
            jwtModuleService.updateUserOnAuthorizationServer(JwtUpdateForm.builder()
                    .accountId(accountToUpdate.getId())
                    .login(accountToUpdate.getLogin())
                    .mail(accountToUpdate.getMail())
                    .hashPassword(accountToUpdate.getHashPassword())
                    .role(accountToUpdate.getRole())
                    .state(accountToUpdate.getState())
                    .build());
        }

        public static Account accountToUpdate(){
            Account account = RegisterOnAuthorizationServerTests.accountToSave();
            account.setLogin("newLogin");
            account.setId(accountId);
            return account;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("deleteUserOnAuthorizationServer() in working when")
    @Order(3)
    public class DeleteUserOnAuthorizationServerTests{

        @Test
        public void delete(){
            jwtModuleService.deleteUserOnAuthorizationServer(accountId);
            accountRepository.deleteById(accountId);
        }

    }

}
