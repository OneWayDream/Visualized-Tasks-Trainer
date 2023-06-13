package ru.itis.visualtasks.backendserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.backendserver.controllers.rest.AccountController;
import ru.itis.visualtasks.backendserver.controllers.rest.AuthenticationController;
import ru.itis.visualtasks.backendserver.controllers.web.ContentPagesController;
import ru.itis.visualtasks.backendserver.controllers.web.MainPageController;
import ru.itis.visualtasks.backendserver.repositories.AccountRepository;
import ru.itis.visualtasks.backendserver.repositories.TaskInfoRepository;
import ru.itis.visualtasks.backendserver.services.AccountService;
import ru.itis.visualtasks.backendserver.services.JwtModuleService;
import ru.itis.visualtasks.backendserver.services.RegistrationService;
import ru.itis.visualtasks.backendserver.services.TaskInfoServiceImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(value = "classpath:test.properties")
@DisplayName("Backend Server starts up when")
class SmokeTest {

	@Autowired
	private AccountController accountController;
	@Autowired
	private AuthenticationController authenticationController;
	@Autowired
	private ContentPagesController contentPagesController;
	@Autowired
	private MainPageController mainPageController;
	@Autowired
	private AccountService accountService;
	@Autowired
	private JwtModuleService jwtModuleService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private TaskInfoServiceImpl taskInfoService;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TaskInfoRepository taskInfoRepository;

	@Test
	void contextLoads() {
		checkControllers();
		checkServices();
		checkRepositories();
	}

	private void checkControllers() {
		assertThat(accountController).isNotNull();
		assertThat(authenticationController).isNotNull();
		assertThat(contentPagesController).isNotNull();
		assertThat(mainPageController).isNotNull();
	}

	private void checkServices() {
		assertThat(accountService).isNotNull();
		assertThat(jwtModuleService).isNotNull();
		assertThat(registrationService).isNotNull();
		assertThat(taskInfoService).isNotNull();
	}

	private void checkRepositories() {
		assertThat(accountRepository).isNotNull();
		assertThat(taskInfoRepository).isNotNull();
	}

}
