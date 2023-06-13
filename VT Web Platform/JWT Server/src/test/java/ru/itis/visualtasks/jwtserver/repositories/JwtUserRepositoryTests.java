package ru.itis.visualtasks.jwtserver.repositories;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.itis.visualtasks.jwtserver.entities.JwtRole;
import ru.itis.visualtasks.jwtserver.entities.JwtState;
import ru.itis.visualtasks.jwtserver.models.JwtUser;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureEmbeddedDatabase(type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES, beanName = "dataSource",
        provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY,
        refresh = AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"classpath:schemas/jwt_user_schema.sql", "classpath:schemas/jwt_user_data.sql"})
@DisplayName("JwtModuleRepository is working when")
@TestPropertySource("classpath:test.properties")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class JwtUserRepositoryTests {

    @Autowired
    private JwtUserRepository jwtUserRepository;

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    @Order(1)
    public class FindAllTests {

        @Test
        public void find_all(){
            List<JwtUser> actual = jwtUserRepository.findAll();
            List<JwtUser> expected = expectedAllJwtUsers();
            Assertions.assertEquals(actual, expected);
        }

        public List<JwtUser> expectedAllJwtUsers(){
            return List.of(
                    JwtUser.builder()
                            .id(1L)
                            .accountId(1L)
                            .login("first_login")
                            .mail("first_mail@gmail.com")
                            .hashPassword("first-hash-password")
                            .state(JwtState.ACTIVE)
                            .role(JwtRole.USER)
                            .isDeleted(false)
                            .build(),
                    JwtUser.builder()
                            .id(2L)
                            .accountId(2L)
                            .login("second_login")
                            .mail("second_mail@gmail.com")
                            .hashPassword("second-hash-password")
                            .state(JwtState.ACTIVE)
                            .role(JwtRole.ADMIN)
                            .isDeleted(false)
                            .redisId("redis-id")
                            .build(),
                    JwtUser.builder()
                            .id(3L)
                            .accountId(3L)
                            .login("to_delete_login")
                            .mail("to_delete_mail@gmail.com")
                            .hashPassword("to-delete-hash-password")
                            .state(JwtState.ACTIVE)
                            .role(JwtRole.USER)
                            .isDeleted(false)
                            .build(),
                    JwtUser.builder()
                            .id(4L)
                            .accountId(4L)
                            .login("another_to_delete_login")
                            .mail("another_to_delete_mail@gmail.com")
                            .hashPassword("another-to-delete-hash-password")
                            .state(JwtState.ACTIVE)
                            .role(JwtRole.USER)
                            .isDeleted(false)
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
        public void delete_existing(){
            jwtUserRepository.deleteById(jwtUserIdToDelete());
        }

        @Test
        public void deleteByAccountId(){
            jwtUserRepository.deleteById(jwtUserAccountIdToDelete());
        }

        public static Long jwtUserIdToDelete(){
            return 3L;
        }

        public static Long jwtUserAccountIdToDelete(){
            return 4L;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("add() in working when")
    @Order(3)
    public class AddTests {

        @Test
        public void add(){
            JwtUser expected = jwtUserAfterAddition();
            JwtUser actual = jwtUserRepository.save(jwtUserToAdd());
            assertEquals(expected, actual);
        }

        public static JwtUser jwtUserToAdd(){
            return JwtUser.builder()
                    .accountId(3L)
                    .login("added_login")
                    .mail("added_mail@gmail.com")
                    .hashPassword("added_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .redisId("added-redis-id")
                    .build();
        }

        public static JwtUser jwtUserAfterAddition(){
            JwtUser jwtUser = jwtUserToAdd();
            jwtUser.setId(5L);
            return jwtUser;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findById() in working when")
    @Order(4)
    public class FindByIdTests {

        @Test
        public void find_existing_by_id(){
            JwtUser expected = expectedExistingJwtUser();
            assertEquals(expected, jwtUserRepository.findById(expected.getId()).orElse(null));
        }

        public JwtUser expectedExistingJwtUser(){
            return JwtUser.builder()
                    .id(1L)
                    .accountId(1L)
                    .login("first_login")
                    .mail("first_mail@gmail.com")
                    .hashPassword("first-hash-password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        @Test
        public void find_not_existing_by_id(){
            assertEquals(Optional.empty(), jwtUserRepository.findById(notExistingJwtUserId()));
        }

        public Long notExistingJwtUserId(){
            return 100L;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findByLogin() in working when")
    @Order(5)
    public class FindByLoginTests {

        @Test
        public void find_existing_by_login(){
            JwtUser expected = expectedExistingJwtUser();
            assertEquals(expected, jwtUserRepository.findByLogin(expected.getLogin()).orElse(null));
        }

        public JwtUser expectedExistingJwtUser(){
            return JwtUser.builder()
                    .id(1L)
                    .accountId(1L)
                    .login("first_login")
                    .mail("first_mail@gmail.com")
                    .hashPassword("first-hash-password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        @Test
        public void find_not_existing_by_login(){
            assertEquals(Optional.empty(), jwtUserRepository.findByLogin(notExistingJwtUserLogin()));
        }

        public String notExistingJwtUserLogin(){
            return "not_existing_login";
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findByMail() in working when")
    @Order(6)
    public class FindByMailTests {

        @Test
        public void find_existing_by_mail(){
            JwtUser expected = expectedExistingJwtUser();
            assertEquals(expected, jwtUserRepository.findByMail(expected.getMail()).orElse(null));
        }

        public JwtUser expectedExistingJwtUser(){
            return JwtUser.builder()
                    .id(1L)
                    .accountId(1L)
                    .login("first_login")
                    .mail("first_mail@gmail.com")
                    .hashPassword("first-hash-password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        @Test
        public void find_not_existing_by_mail(){
            assertEquals(Optional.empty(), jwtUserRepository.findByMail(notExistingJwtUserMail()));
        }

        public String notExistingJwtUserMail(){
            return "not_existing_mail@gmail.com";
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findByAccountId() in working when")
    @Order(7)
    public class FindByAccountIdTests {

        @Test
        public void find_existing_by_account_id(){
            JwtUser expected = expectedExistingJwtUser();
            assertEquals(expected, jwtUserRepository.findByAccountId(expected.getAccountId()).orElse(null));
        }

        public JwtUser expectedExistingJwtUser(){
            return JwtUser.builder()
                    .id(1L)
                    .accountId(1L)
                    .login("first_login")
                    .mail("first_mail@gmail.com")
                    .hashPassword("first-hash-password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        @Test
        public void find_not_existing_by_account_id(){
            assertEquals(Optional.empty(), jwtUserRepository.findByAccountId(notExistingJwtUserAccountId()));
        }

        public Long notExistingJwtUserAccountId(){
            return 100L;
        }

    }

}
