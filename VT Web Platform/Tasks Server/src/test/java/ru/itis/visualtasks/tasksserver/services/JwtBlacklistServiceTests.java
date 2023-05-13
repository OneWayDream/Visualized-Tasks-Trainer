package ru.itis.visualtasks.tasksserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.tasksserver.redis.repositories.BlacklistRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest()
@TestPropertySource("classpath:test.properties")
@DisplayName("JwtBlacklistService is working when")
public class JwtBlacklistServiceTests {

    @Autowired
    private JwtBlacklistService jwtBlacklistService;

    @MockBean
    private BlacklistRepository blacklistRepository;

    @BeforeEach
    public void setUp() {
        when(blacklistRepository.exists(ExistsTests.getExistingToken())).thenReturn(true);
        when(blacklistRepository.exists(ExistsTests.getNotExistingToken())).thenReturn(false);
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("exists() in working when")
    public class ExistsTests{

        @Test
        public void check_for_existing(){
            assertTrue(jwtBlacklistService.exists(getExistingToken()));
        }

        @Test
        public void check_for_not_existing(){
            assertFalse(jwtBlacklistService.exists(getNotExistingToken()));
        }

        public static String getExistingToken(){
            return "existing-token";
        }

        public static String getNotExistingToken(){
            return "not-existing-token";
        }

    }

}
