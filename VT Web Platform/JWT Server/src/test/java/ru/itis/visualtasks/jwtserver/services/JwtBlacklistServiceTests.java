package ru.itis.visualtasks.jwtserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.jwtserver.redis.repositories.BlacklistRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest()
@DisplayName("JwtBlacklistService is working when")
@TestPropertySource(value = "classpath:test.properties")
public class JwtBlacklistServiceTests {

    @Autowired
    private JwtBlacklistService jwtBlacklistService;

    @MockBean
    private BlacklistRepository blacklistRepository;

    @BeforeEach
    public void setUp() {
        when(blacklistRepository.exists(ExistsTests.getExistingToken())).thenReturn(true);
        when(blacklistRepository.exists(ExistsTests.getNotExistingToken())).thenReturn(false);
        doNothing().when(blacklistRepository).save(AddTests.getToken());
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

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("add() in working when")
    public class AddTests{

        @Test
        public void add_token(){
            jwtBlacklistService.add(getToken());
        }

        public static String getToken(){
            return "token-to-ban";
        }

    }

}
