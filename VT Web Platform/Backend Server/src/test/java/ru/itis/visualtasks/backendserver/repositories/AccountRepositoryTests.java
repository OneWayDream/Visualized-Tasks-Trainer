package ru.itis.visualtasks.backendserver.repositories;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.itis.visualtasks.backendserver.models.Account;
import ru.itis.visualtasks.backendserver.models.UserRole;
import ru.itis.visualtasks.backendserver.models.UserState;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureEmbeddedDatabase(type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES, beanName = "dataSource",
        provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY,
        refresh = AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"classpath:schemas/account_schema.sql", "classpath:schemas/account_data.sql"})
@DisplayName("AccountRepository is working when")
@TestPropertySource("classpath:test.properties")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    @Order(1)
    public class FindAllTests {

        @Test
        public void find_all() {
            List<Account> actual = accountRepository.findAll();
            List<Account> expected = expectedAllAccounts();
            Assertions.assertEquals(actual, expected);
        }

        public List<Account> expectedAllAccounts() {
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
                            .build(),
                    Account.builder()
                            .id(3L)
                            .login("to-delete")
                            .mail("to-delete@gmail.com")
                            .hashPassword("to-delete-hash-password")
                            .state(UserState.ACTIVE)
                            .role(UserRole.USER)
                            .registrationDate(LocalDate.parse("2023-04-16"))
                            .build()
            );
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("delete() in working when")
    @Order(2)
    public class DeleteTests {

        @Test
        public void delete_existing() {
            accountRepository.deleteById(accountIdToDelete());
        }


        public static Long accountIdToDelete() {
            return 3L;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("add() in working when")
    @Order(3)
    public class AddTests {

        @Test
        public void add() {
            Account expected = accountAfterAddition();
            Account actual = accountRepository.save(accountToAdd());
            assertEquals(expected, actual);
        }

        public static Account accountToAdd() {
            return Account.builder()
                    .login("added")
                    .mail("added@gmail.com")
                    .hashPassword("added-hash-password")
                    .state(UserState.ACTIVE)
                    .role(UserRole.USER)
                    .registrationDate(LocalDate.now())
                    .build();
        }

        public static Account accountAfterAddition() {
            Account account = accountToAdd();
            account.setId(4L);
            return account;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findById() in working when")
    @Order(4)
    public class FindByIdTests {

        @Test
        public void find_existing_by_id() {
            Account expected = expectedExistingAccount();
            assertEquals(expected, accountRepository.findById(expected.getId()).orElse(null));
        }

        public Account expectedExistingAccount() {
            return Account.builder()
                    .id(1L)
                    .login("first")
                    .mail("first@gmail.com")
                    .hashPassword("first-hash-password")
                    .state(UserState.ACTIVE)
                    .role(UserRole.USER)
                    .registrationDate(LocalDate.parse("2023-04-16"))
                    .build();
        }

        @Test
        public void find_not_existing_by_id() {
            assertEquals(Optional.empty(), accountRepository.findById(notExistingAccountId()));
        }

        public Long notExistingAccountId() {
            return 100L;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findByLogin() in working when")
    @Order(5)
    public class FindByLoginTests {

        @Test
        public void find_existing_by_login() {
            Account expected = expectedExistingAccount();
            assertEquals(expected, accountRepository.findByLogin(expected.getLogin()).orElse(null));
        }

        public Account expectedExistingAccount() {
            return Account.builder()
                    .id(1L)
                    .login("first")
                    .mail("first@gmail.com")
                    .hashPassword("first-hash-password")
                    .state(UserState.ACTIVE)
                    .role(UserRole.USER)
                    .registrationDate(LocalDate.parse("2023-04-16"))
                    .build();
        }

        @Test
        public void find_not_existing_by_login() {
            assertEquals(Optional.empty(), accountRepository.findByLogin(notExistingAccountLogin()));
        }

        public String notExistingAccountLogin() {
            return "not-existing-login";
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findByMail() in working when")
    @Order(6)
    public class FindByMailTests {

        @Test
        public void find_existing_by_mail() {
            Account expected = expectedExistingAccount();
            assertEquals(expected, accountRepository.findByMail(expected.getMail()).orElse(null));
        }

        public Account expectedExistingAccount() {
            return Account.builder()
                    .id(1L)
                    .login("first")
                    .mail("first@gmail.com")
                    .hashPassword("first-hash-password")
                    .state(UserState.ACTIVE)
                    .role(UserRole.USER)
                    .registrationDate(LocalDate.parse("2023-04-16"))
                    .build();
        }

        @Test
        public void find_not_existing_by_mail() {
            assertEquals(Optional.empty(), accountRepository.findByMail(notExistingAccountMail()));
        }

        public String notExistingAccountMail() {
            return "not-existing-mail";
        }

    }
    
}
