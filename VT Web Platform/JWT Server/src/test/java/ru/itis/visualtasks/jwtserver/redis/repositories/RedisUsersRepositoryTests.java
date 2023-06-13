package ru.itis.visualtasks.jwtserver.redis.repositories;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.jwtserver.redis.TestRedisConfiguration;
import ru.itis.visualtasks.jwtserver.redis.repositories.models.RedisUser;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = TestRedisConfiguration.class)
@DisplayName("RedisUsersRepository is working when")
@TestPropertySource(value = "classpath:test.properties")
public class RedisUsersRepositoryTests {

    @Autowired
    private RedisUsersRepository repository;

    @BeforeEach
    public void setUp(){
        repository.deleteAll();
        repository.saveAll(getDefaultUsers());
    }

    private List<RedisUser> getDefaultUsers() {
        return List.of(
            RedisUser.builder()
                    .id("first_id")
                    .userId(1L)
                    .tokens(List.of("first-token", "second-token"))
                    .build(),
            RedisUser.builder()
                    .id("second_id")
                    .userId(2L)
                    .tokens(List.of("third-token"))
                    .build()
        );
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    public class FindAllTests {

        @Test
        public void find_all(){
            List<RedisUser> actual = repository.findAll();
            assertEquals(getDefaultUsers(), actual);
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("delete() in working when")
    public class DeleteTests {

        @Test
        public void delete_existing(){
            repository.deleteById(redisUserToDelete().getId());
            List<RedisUser> expected = getExpectedRedisUser();
            List<RedisUser> actual = repository.findAll();
            assertEquals(1, actual.size());
            assertEquals(expected, actual);
        }

        public static RedisUser redisUserToDelete(){
            return RedisUser.builder()
                    .id("first_id")
                    .userId(1L)
                    .tokens(List.of("first-token", "second-token"))
                    .build();
        }

        public static List<RedisUser> getExpectedRedisUser() {
            return List.of(
                    RedisUser.builder()
                            .id("second_id")
                            .userId(2L)
                            .tokens(List.of("third-token"))
                            .build()
            );
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("add() in working when")
    public class AddTests {

        @Test
        public void add(){
            RedisUser actual = repository.save(redisUserToAdd());
            assertNotNull(actual.getId());
            actual.setId(null);
            assertEquals(redisUserToAdd(), actual);
        }


        public static RedisUser redisUserToAdd(){
            return RedisUser.builder()
                    .userId(3L)
                    .tokens(List.of("forth-token"))
                    .build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findById() in working when")
    public class FindByIdTests {

        @Test
        public void find_existing_by_id(){
            RedisUser expected = existingTaskArchive();
            RedisUser actual = repository.findById(existingTaskArchive().getId()).orElse(null);
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_id(){
            assertEquals(Optional.empty(), repository.findById(notExistingId()));
        }

        public RedisUser existingTaskArchive(){
            return repository.findAll().get(0);
        }

        public static String notExistingId(){
            return "not_existing_id";
        }

    }

}
