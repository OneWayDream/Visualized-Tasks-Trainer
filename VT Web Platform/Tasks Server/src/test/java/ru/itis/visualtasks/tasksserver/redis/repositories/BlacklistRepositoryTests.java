package ru.itis.visualtasks.tasksserver.redis.repositories;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest()
@DisplayName("BlacklistRepository is working when")
@TestPropertySource("classpath:test.properties")
public class BlacklistRepositoryTests {

    @MockBean
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @Autowired
    private BlacklistRepository blacklistRepository;

    @BeforeEach
    public void setUp(){
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(redisTemplate.hasKey(ExistsCheckTests.existingToken())).thenReturn(true);
        when(redisTemplate.hasKey(ExistsCheckTests.notExistingToken())).thenReturn(false);
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("save() in working when")
    public class SaveTests {

        @Test
        public void save_token(){
            blacklistRepository.save(tokenValue());
        }

        public static String tokenValue(){
            return "token";
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("exists() in working when")
    public class ExistsCheckTests {

        @Test
        public void check_existing(){
            assertTrue(blacklistRepository.exists(existingToken()));
        }

        @Test
        public void check_not_existing(){
            assertFalse(blacklistRepository.exists(notExistingToken()));
        }

        public static String existingToken(){
            return "existing-token";
        }

        public static String notExistingToken(){
            return "not-existing-token";
        }

    }

}
