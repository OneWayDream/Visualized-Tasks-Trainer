package ru.itis.visualtasks.tasksserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.tasksserver.controllers.TasksController;
import ru.itis.visualtasks.tasksserver.redis.repositories.BlacklistRepository;
import ru.itis.visualtasks.tasksserver.repositories.TaskArchiveRepository;
import ru.itis.visualtasks.tasksserver.repositories.TaskDescriptionFileRepository;
import ru.itis.visualtasks.tasksserver.repositories.TaskInfoRepository;
import ru.itis.visualtasks.tasksserver.security.details.UserDetailsServiceImpl;
import ru.itis.visualtasks.tasksserver.security.providers.TokenAuthenticationProvider;
import ru.itis.visualtasks.tasksserver.services.*;
import ru.itis.visualtasks.tasksserver.utils.JwtUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@TestPropertySource("classpath:test.properties")
@DisplayName("Task Server starts up when")
public class SmokeTest {

	@Autowired
	private TasksController tasksController;
	@Autowired
	private BlacklistRepository blacklistRepository;
	@Autowired
	private TaskArchiveRepository taskArchiveRepository;
	@Autowired
	private TaskDescriptionFileRepository taskDescriptionFileRepository;
	@Autowired
	private TaskInfoRepository taskInfoRepository;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private TokenAuthenticationProvider tokenAuthenticationProvider;
	@Autowired
	private JwtBlacklistService jwtBlacklistService;
	@Autowired
	private TaskArchiveService taskArchiveService;
	@Autowired
	private TaskDescriptionFileService taskDescriptionFileService;
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private TasksService tasksService;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void context_loads() {
		checkControllers();
		checkServices();
		checkRepositories();
		checkUtils();
	}

	private void checkControllers(){
		assertThat(tasksController).isNotNull();
	}

	private void checkServices(){
		assertThat(userDetailsService).isNotNull();
		assertThat(jwtBlacklistService).isNotNull();
		assertThat(taskArchiveService).isNotNull();
		assertThat(taskDescriptionFileService).isNotNull();
		assertThat(taskInfoService).isNotNull();
		assertThat(tasksService).isNotNull();
	}

	private void checkRepositories(){
		assertThat(blacklistRepository).isNotNull();
		assertThat(taskArchiveRepository).isNotNull();
		assertThat(taskDescriptionFileRepository).isNotNull();
		assertThat(taskInfoRepository).isNotNull();
	}

	private void checkUtils(){
		assertThat(tokenAuthenticationProvider).isNotNull();
		assertThat(jwtUtils).isNotNull();
		assertThat(passwordEncoder).isNotNull();
	}


}
