package ru.itis.visualtasks.jwtserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.jwtserver.dto.JwtModuleDto;
import ru.itis.visualtasks.jwtserver.entities.JwtRole;
import ru.itis.visualtasks.jwtserver.entities.JwtState;
import ru.itis.visualtasks.jwtserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.jwtserver.models.JwtModule;
import ru.itis.visualtasks.jwtserver.repositories.JwtModuleRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest()
@DisplayName("JwtModuleService is working when")
@TestPropertySource(value = "classpath:test.properties")
public class JwtModuleServiceTests {

    @Autowired
    private JwtModuleService jwtModuleService;

    @MockBean
    private JwtModuleRepository jwtModuleRepository;

    @BeforeEach
    public void setUp() {
        when(jwtModuleRepository.findAll()).thenReturn(FindAllTests.expectedAllJwtModules());
        Long jwtModuleToDeleteId = DeleteTests.jwtModuleDtoToDelete().getId();
        when(jwtModuleRepository.findById(jwtModuleToDeleteId))
                .thenReturn(Optional.ofNullable(DeleteTests.jwtModuleToDelete()));
        when(jwtModuleRepository.save(DeleteTests.jwtModuleToDelete())).thenReturn(DeleteTests.jwtModuleToDelete());
        doNothing().when(jwtModuleRepository).delete(DeleteTests.jwtModuleToDelete());
        when(jwtModuleRepository.save(AddTests.jwtModuleToAdd())).thenReturn(
                AddTests.jwtModuleAddResponse()
        );
        when(jwtModuleRepository.findById(FindByIdTests.existingJwtModule().getId())).thenReturn(
                Optional.ofNullable(FindByIdTests.existingJwtModule())
        );
        when(jwtModuleRepository.findById(FindByIdTests.notExistingId())).thenThrow(EntityNotFoundException.class);
        when(jwtModuleRepository.save(UpdateTests.updatedJwtModule())).thenReturn(
                UpdateTests.updatedJwtModule()
        );
        when(jwtModuleRepository.findByLogin(FindByLoginTests.existingJwtModule().getLogin())).thenReturn(
                Optional.ofNullable(FindByLoginTests.existingJwtModule())
        );
        when(jwtModuleRepository.findByLogin(FindByLoginTests.notExistingLogin()))
                .thenThrow(EntityNotFoundException.class);
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    public class FindAllTests {

        @Test
        public void find_all(){
            List<JwtModuleDto> actual = jwtModuleService.findAll();
            assertEquals(expectedAllJwtModuleDtos(), actual);
        }

        public static List<JwtModule> expectedAllJwtModules(){
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
                            .redisId("redis-id")
                            .isDeleted(false)
                            .build()
            );
        }

        public static List<JwtModuleDto> expectedAllJwtModuleDtos(){
            return JwtModuleDto.from(expectedAllJwtModules());
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("delete() in working when")
    public class DeleteTests {

        @Test
        public void delete_existing(){
            jwtModuleService.delete(jwtModuleDtoToDelete());
        }

        public static JwtModule jwtModuleToDelete(){
            return JwtModule.builder()
                    .id(3L)
                    .isDeleted(false)
                    .build();
        }

        public static JwtModuleDto jwtModuleDtoToDelete(){
            return JwtModuleDto.from(jwtModuleToDelete());
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("add() in working when")
    public class AddTests {

        @Test
        public void add(){
            JwtModuleDto expected = jwtModuleDtoAddResponse();
            JwtModuleDto actual = jwtModuleService.add(jwtModuleDtoToAdd());
            assertEquals(expected, actual);
        }


        public static JwtModule jwtModuleToAdd(){
            return JwtModule.builder()
                    .id(3L)
                    .login("to_add_login")
                    .hashPassword("to_add_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        public static JwtModuleDto jwtModuleDtoToAdd(){
            return JwtModuleDto.from(jwtModuleToAdd());
        }

        public static JwtModule jwtModuleAddResponse(){
            return JwtModule.builder()
                    .id(3L)
                    .login("to_add_login")
                    .hashPassword("to_add_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        public static JwtModuleDto jwtModuleDtoAddResponse(){
            return JwtModuleDto.from(jwtModuleAddResponse());
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findById() in working when")
    public class FindByIdTests {

        @Test
        public void find_existing_by_id(){
            JwtModuleDto expected = existingJwtModuleDto();
            JwtModuleDto actual = jwtModuleService.findById(existingJwtModule().getId());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_id(){
            assertThrows(EntityNotFoundException.class, () -> jwtModuleService.findById(notExistingId()));
        }

        public static JwtModule existingJwtModule(){
            return JwtModule.builder()
                    .id(1L)
                    .login("first_login")
                    .hashPassword("first_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        public static JwtModuleDto existingJwtModuleDto(){
            return JwtModuleDto.from(existingJwtModule());
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
            JwtModuleDto expected = updatedJwtModuleDto();
            JwtModuleDto actual = jwtModuleService.update(updatedJwtModuleDto());
            assertEquals(expected, actual);
        }

        @Test
        public void update_not_existing(){
            assertThrows(EntityNotFoundException.class, () -> jwtModuleService.update(notExistingUpdatedTJwtModuleDto()));
        }

        public static JwtModule updatedJwtModule(){
            return JwtModule.builder()
                    .id(1L)
                    .login("new_first_login")
                    .hashPassword("first_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        public static JwtModuleDto updatedJwtModuleDto(){
            return JwtModuleDto.from(updatedJwtModule());
        }


        public static JwtModuleDto notExistingUpdatedTJwtModuleDto(){
            return JwtModuleDto.builder().id(FindByIdTests.notExistingId()).build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findByLogin() in working when")
    public class FindByLoginTests {

        @Test
        public void find_existing_by_login(){
            JwtModuleDto expected = existingJwtModuleDto();
            JwtModuleDto actual = jwtModuleService.findByLogin(existingJwtModule().getLogin());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_login(){
            assertThrows(EntityNotFoundException.class, () -> jwtModuleService.findByLogin(notExistingLogin()));
        }

        public static JwtModule existingJwtModule(){
            return JwtModule.builder()
                    .id(1L)
                    .login("first_login")
                    .hashPassword("first_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        public static JwtModuleDto existingJwtModuleDto(){
            return JwtModuleDto.from(existingJwtModule());
        }

        public static String notExistingLogin(){
            return "not_existing_login";
        }

    }


}
