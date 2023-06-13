package ru.itis.visualtasks.jwtserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.visualtasks.jwtserver.controllers.AdministrationController;
import ru.itis.visualtasks.jwtserver.controllers.JwtAccountController;
import ru.itis.visualtasks.jwtserver.controllers.LoginController;
import ru.itis.visualtasks.jwtserver.redis.repositories.RedisUsersRepository;
import ru.itis.visualtasks.jwtserver.redis.services.RedisUsersService;
import ru.itis.visualtasks.jwtserver.repositories.JwtModuleRepository;
import ru.itis.visualtasks.jwtserver.repositories.JwtUserRepository;
import ru.itis.visualtasks.jwtserver.security.providers.TokenAuthenticationProvider;
import ru.itis.visualtasks.jwtserver.services.*;
import ru.itis.visualtasks.jwtserver.utils.JwtUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DisplayName("Jwt Server starts up when")
class SmokeTest {

	@Autowired
	private AdministrationController administrationController;
	@Autowired
	private JwtAccountController jwtAccountController;
	@Autowired
	private LoginController loginController;
	@Autowired
	private JwtBlacklistService jwtBlacklistService;
	@Autowired
	private JwtModuleService jwtModuleService;
	@Autowired
	private JwtUserService jwtUserService;
	@Autowired
	private ModuleLoginService moduleLoginService;
	@Autowired
	private UserLoginService userLoginService;
	@Autowired
	private RedisUsersService redisUsersService;
	@Autowired
	private JwtModuleRepository jwtModuleRepository;
	@Autowired
	private JwtUserRepository jwtUserRepository;
	@Autowired
	private RedisUsersRepository redisUsersRepository;
	@Autowired
	private TokenAuthenticationProvider tokenAuthenticationProvider;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
		checkControllers();
		checkServices();
		checkRepositories();
		checkUtils();
	}

	private void checkControllers() {
		assertThat(administrationController).isNotNull();
		assertThat(jwtAccountController).isNotNull();
		assertThat(loginController).isNotNull();
	}

	private void checkServices() {
		assertThat(jwtBlacklistService).isNotNull();
		assertThat(jwtModuleService).isNotNull();
		assertThat(jwtUserService).isNotNull();
		assertThat(moduleLoginService).isNotNull();
		assertThat(userLoginService).isNotNull();
		assertThat(redisUsersService).isNotNull();
	}

	private void checkRepositories() {
		assertThat(jwtModuleRepository).isNotNull();
		assertThat(jwtUserRepository).isNotNull();
		assertThat(redisUsersRepository).isNotNull();
	}

	private void checkUtils() {
		assertThat(tokenAuthenticationProvider).isNotNull();
		assertThat(jwtUtils).isNotNull();
		assertThat(passwordEncoder).isNotNull();
	}


}
