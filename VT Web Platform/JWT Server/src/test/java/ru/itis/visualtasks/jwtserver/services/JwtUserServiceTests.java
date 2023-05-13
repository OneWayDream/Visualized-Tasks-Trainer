package ru.itis.visualtasks.jwtserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.jwtserver.dto.JwtUserDto;
import ru.itis.visualtasks.jwtserver.entities.JwtRole;
import ru.itis.visualtasks.jwtserver.entities.JwtState;
import ru.itis.visualtasks.jwtserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.jwtserver.models.JwtUser;
import ru.itis.visualtasks.jwtserver.repositories.JwtUserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest()
@DisplayName("JwtUserService is working when")
@TestPropertySource(value = "classpath:test.properties")
public class JwtUserServiceTests {

    @Autowired
    private JwtUserService jwtUserService;

    @MockBean
    private JwtUserRepository jwtUserRepository;

    @BeforeEach
    public void setUp() {
        when(jwtUserRepository.findAll()).thenReturn(FindAllTests.expectedAllJwtUsers());
        Long jwtUserToDeleteId = DeleteTests.jwtUserDtoToDelete().getId();
        when(jwtUserRepository.findById(jwtUserToDeleteId))
                .thenReturn(Optional.ofNullable(DeleteTests.jwtUserToDelete()));
        when(jwtUserRepository.save(DeleteTests.jwtUserToDelete())).thenReturn(DeleteTests.jwtUserToDelete());
        doNothing().when(jwtUserRepository).delete(DeleteTests.jwtUserToDelete());

        doNothing().when(jwtUserRepository).deleteByAccountId(DeleteByAccountIdTests.jwtUserToDelete().getAccountId());

        when(jwtUserRepository.save(AddTests.jwtUserToAdd())).thenReturn(
                AddTests.jwtUserAddResponse()
        );
        when(jwtUserRepository.findById(FindByIdTests.existingJwtUser().getId())).thenReturn(
                Optional.ofNullable(FindByIdTests.existingJwtUser())
        );
        when(jwtUserRepository.findById(FindByIdTests.notExistingId())).thenThrow(EntityNotFoundException.class);
        when(jwtUserRepository.findByAccountId(FindByAccountIdTests.existingJwtUser().getAccountId())).thenReturn(
                Optional.ofNullable(FindByAccountIdTests.existingJwtUser())
        );
        when(jwtUserRepository.findByAccountId(FindByAccountIdTests.notExistingAccountId()))
                .thenThrow(EntityNotFoundException.class);
        when(jwtUserRepository.save(UpdateTests.updatedJwtUser())).thenReturn(
                UpdateTests.updatedJwtUser()
        );
        when(jwtUserRepository.findByLogin(FindByLoginTests.existingJwtUser().getLogin())).thenReturn(
                Optional.ofNullable(FindByLoginTests.existingJwtUser())
        );
        when(jwtUserRepository.findByLogin(FindByLoginTests.notExistingLogin()))
                .thenThrow(EntityNotFoundException.class);
        when(jwtUserRepository.findByMail(FindByMailTests.existingJwtUser().getMail())).thenReturn(
                Optional.ofNullable(FindByMailTests.existingJwtUser())
        );
        when(jwtUserRepository.findByMail(FindByMailTests.notExistingMail()))
                .thenThrow(EntityNotFoundException.class);
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    public class FindAllTests {

        @Test
        public void find_all(){
            List<JwtUserDto> actual = jwtUserService.findAll();
            assertEquals(expectedAllJwtUserDtos(), actual);
        }

        public static List<JwtUser> expectedAllJwtUsers(){
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
                            .build()
            );
        }

        public static List<JwtUserDto> expectedAllJwtUserDtos(){
            return JwtUserDto.from(expectedAllJwtUsers());
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("delete() in working when")
    public class DeleteTests {

        @Test
        public void delete_existing(){
            jwtUserService.delete(jwtUserDtoToDelete());
        }

        public static JwtUser jwtUserToDelete(){
            return JwtUser.builder()
                    .id(3L)
                    .isDeleted(false)
                    .build();
        }

        public static JwtUserDto jwtUserDtoToDelete(){
            return JwtUserDto.from(jwtUserToDelete());
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("deleteByAccountId() in working when")
    public class DeleteByAccountIdTests {

        @Test
        public void delete_existing(){
            jwtUserService.deleteByAccountId(jwtUserToDelete().getAccountId());
        }

        public static JwtUser jwtUserToDelete(){
            return JwtUser.builder()
                    .accountId(3L)
                    .isDeleted(false)
                    .build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("add() in working when")
    public class AddTests {

        @Test
        public void add(){
            JwtUserDto expected = jwtUserDtoAddResponse();
            JwtUserDto actual = jwtUserService.add(jwtUserDtoToAdd());
            assertEquals(expected, actual);
        }


        public static JwtUser jwtUserToAdd(){
            return JwtUser.builder()
                    .id(3L)
                    .login("to_add_login")
                    .mail("to_add_mail@gmail.com")
                    .hashPassword("to_add_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .redisId("to_add_redis_id")
                    .build();
        }

        public static JwtUserDto jwtUserDtoToAdd(){
            return JwtUserDto.from(jwtUserToAdd());
        }

        public static JwtUser jwtUserAddResponse(){
            return JwtUser.builder()
                    .id(3L)
                    .login("to_add_login")
                    .mail("to_add_mail@gmail.com")
                    .hashPassword("to_add_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .redisId("to_add_redis_id")
                    .build();
        }

        public static JwtUserDto jwtUserDtoAddResponse(){
            return JwtUserDto.from(jwtUserAddResponse());
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findById() in working when")
    public class FindByIdTests {

        @Test
        public void find_existing_by_id(){
            JwtUserDto expected = existingJwtUserDto();
            JwtUserDto actual = jwtUserService.findById(existingJwtUser().getId());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_id(){
            assertThrows(EntityNotFoundException.class, () -> jwtUserService.findById(notExistingId()));
        }

        public static JwtUser existingJwtUser(){
            return JwtUser.builder()
                    .id(1L)
                    .login("first_login")
                    .mail("first_mail@gmail.com")
                    .hashPassword("first_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        public static JwtUserDto existingJwtUserDto(){
            return JwtUserDto.from(existingJwtUser());
        }

        public static Long notExistingId(){
            return 100L;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findByAccountId() in working when")
    public class FindByAccountIdTests {

        @Test
        public void find_existing_by_account_id(){
            JwtUserDto expected = existingJwtUserDto();
            JwtUserDto actual = jwtUserService.findByAccountId(existingJwtUser().getAccountId());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_account_id(){
            assertThrows(EntityNotFoundException.class, () -> jwtUserService.findByAccountId(notExistingAccountId()));
        }

        public static JwtUser existingJwtUser(){
            return JwtUser.builder()
                    .id(1L)
                    .login("first_login")
                    .mail("first_mail@gmail.com")
                    .hashPassword("first_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        public static JwtUserDto existingJwtUserDto(){
            return JwtUserDto.from(existingJwtUser());
        }

        public static Long notExistingAccountId(){
            return 100L;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("update() in working when")
    public class UpdateTests {

        @Test
        public void update_existing(){
            JwtUserDto expected = updatedJwtUserDto();
            JwtUserDto actual = jwtUserService.update(updatedJwtUserDto());
            assertEquals(expected, actual);
        }

        @Test
        public void update_not_existing(){
            assertThrows(EntityNotFoundException.class, () -> jwtUserService.update(notExistingUpdatedTJwtUserDto()));
        }

        public static JwtUser updatedJwtUser(){
            return JwtUser.builder()
                    .id(1L)
                    .login("new_first_login")
                    .mail("first_mail@gmail.com")
                    .hashPassword("first_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        public static JwtUserDto updatedJwtUserDto(){
            return JwtUserDto.from(updatedJwtUser());
        }


        public static JwtUserDto notExistingUpdatedTJwtUserDto(){
            return JwtUserDto.builder().id(JwtUserServiceTests.FindByIdTests.notExistingId()).build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("updateByAccountId() in working when")
    public class UpdateByAccountIdTests {

        @Test
        public void update_existing(){
            JwtUserDto expected = updatedJwtUserDto();
            JwtUserDto actual = jwtUserService.updateByAccountId(updatedJwtUserDto());
            assertEquals(expected, actual);
        }

        @Test
        public void update_not_existing(){
            assertThrows(EntityNotFoundException.class,
                    () -> jwtUserService.updateByAccountId(notExistingUpdatedTJwtUserDto()));
        }

        public static JwtUser updatedJwtUser(){
            return JwtUser.builder()
                    .id(1L)
                    .login("new_first_login")
                    .mail("first_mail@gmail.com")
                    .hashPassword("first_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        public static JwtUserDto updatedJwtUserDto(){
            return JwtUserDto.from(updatedJwtUser());
        }


        public static JwtUserDto notExistingUpdatedTJwtUserDto(){
            return JwtUserDto.builder().accountId(FindByAccountIdTests.notExistingAccountId()).build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findByLogin() in working when")
    public class FindByLoginTests {

        @Test
        public void find_existing_by_login(){
            JwtUserDto expected = existingJwtUserDto();
            JwtUserDto actual = jwtUserService.findByLogin(existingJwtUser().getLogin());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_login(){
            assertThrows(EntityNotFoundException.class, () -> jwtUserService.findByLogin(notExistingLogin()));
        }

        public static JwtUser existingJwtUser(){
            return JwtUser.builder()
                    .id(1L)
                    .login("first_login")
                    .mail("first_mail@gmail.con")
                    .hashPassword("first_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        public static JwtUserDto existingJwtUserDto(){
            return JwtUserDto.from(existingJwtUser());
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
            JwtUserDto expected = existingJwtUserDto();
            JwtUserDto actual = jwtUserService.findByMail(existingJwtUser().getMail());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_mail(){
            assertThrows(EntityNotFoundException.class, () -> jwtUserService.findByMail(notExistingMail()));
        }

        public static JwtUser existingJwtUser(){
            return JwtUser.builder()
                    .id(1L)
                    .login("first_login")
                    .mail("first_mail@gmail.con")
                    .hashPassword("first_hash_password")
                    .state(JwtState.ACTIVE)
                    .role(JwtRole.USER)
                    .isDeleted(false)
                    .build();
        }

        public static JwtUserDto existingJwtUserDto(){
            return JwtUserDto.from(existingJwtUser());
        }

        public static String notExistingMail(){
            return "not_existing_mail@gmail.com";
        }

    }
    
}
