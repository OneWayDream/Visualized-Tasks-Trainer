package ru.itis.visualtasks.jwtserver.repositories;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.itis.visualtasks.jwtserver.entities.JwtRole;
import ru.itis.visualtasks.jwtserver.entities.JwtState;
import ru.itis.visualtasks.jwtserver.models.JwtModule;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureEmbeddedDatabase(type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES, beanName = "dataSource",
        provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY,
        refresh = AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"classpath:schemas/jwt_module_schema.sql", "classpath:schemas/jwt_module_data.sql"})
@DisplayName("JwtModuleRepository is working when")
@TestPropertySource("classpath:test.properties")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class JwtModuleRepositoryTests {

    @Autowired
    private JwtModuleRepository jwtModuleRepository;

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    @Order(1)
    public class FindAllTests {

        @Test
        public void find_all(){
            List<JwtModule> actual = jwtModuleRepository.findAll();
            List<JwtModule> expected = expectedAllJwtModules();
            Assertions.assertEquals(actual, expected);
        }

        public List<JwtModule> expectedAllJwtModules(){
            return List.of(
                    JwtModule.builder()
                            .id(1L)
                            .login("first_login")
                            .hashPassword("first_hash_password")
                            .state(JwtState.ACTIVE)
                            .role(JwtRole.USER)
                            .isDeleted(false)
                            .build(),
                    JwtModule.builder()
                            .id(2L)
                            .login("second_login")
                            .hashPassword("second_hash_password")
                            .state(JwtState.ACTIVE)
                            .role(JwtRole.ADMIN)
                            .isDeleted(false)
                            .redisId("redis-id")
                            .build(),
                    JwtModule.builder()
                            .id(3L)
                            .login("to_delete_login")
                            .hashPassword("to_delete_hash_password")
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
            jwtModuleRepository.deleteById(jwtModuleIdToDelete());
        }


        public static Long jwtModuleIdToDelete(){
            return 3L;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("add() in working when")
    @Order(3)
    public class AddTests {

        @Test
        public void add(){
            JwtModule expected = jwtModuleAfterAddition();
            JwtModule actual = jwtModuleRepository.save(jwtModuleToAdd());
            assertEquals(expected, actual);
        }

        public static JwtModule jwtModuleToAdd(){
            return JwtModule.builder()
                    .login("added_login")
                    .hashPassword("added_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .redisId("added-redis-id")
                    .build();
        }

        public static JwtModule jwtModuleAfterAddition(){
            JwtModule jwtModule = jwtModuleToAdd();
            jwtModule.setId(4L);
            return jwtModule;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findById() in working when")
    @Order(4)
    public class FindByIdTests {

        @Test
        public void find_existing_by_id(){
            JwtModule expected = expectedExistingJwtModule();
            assertEquals(expected, jwtModuleRepository.findById(expected.getId()).orElse(null));
        }

        public JwtModule expectedExistingJwtModule(){
            return JwtModule.builder()
                    .id(1L)
                    .login("first_login")
                    .hashPassword("first_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        @Test
        public void find_not_existing_by_id(){
            assertEquals(Optional.empty(), jwtModuleRepository.findById(notExistingJwtModuleId()));
        }

        public Long notExistingJwtModuleId(){
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
            JwtModule expected = expectedExistingJwtModule();
            assertEquals(expected, jwtModuleRepository.findByLogin(expected.getLogin()).orElse(null));
        }

        public JwtModule expectedExistingJwtModule(){
            return JwtModule.builder()
                    .id(1L)
                    .login("first_login")
                    .hashPassword("first_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        @Test
        public void find_not_existing_by_login(){
            assertEquals(Optional.empty(), jwtModuleRepository.findByLogin(notExistingJwtModuleLogin()));
        }

        public String notExistingJwtModuleLogin(){
            return "not_existing_login";
        }

    }

}
