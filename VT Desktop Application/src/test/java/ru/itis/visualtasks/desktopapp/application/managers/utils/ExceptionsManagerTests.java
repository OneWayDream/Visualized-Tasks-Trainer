package ru.itis.visualtasks.desktopapp.application.managers.utils;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.core.TestPageFrame;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class ExceptionsManagerTests {

    @BeforeAll
    public static void beforeAll(){
        LocalizationManager.setLocale(Locale.EN);
        ExceptionsManager.resetExecutor();
    }

    @BeforeEach
    public void beforeEach(){
        Application.changePage(new TestPageFrame());
    }

    @Test
    public void add_delayed_exception_handling_task() throws InterruptedException {
        AtomicReference<String> str = new AtomicReference<>();
        ExceptionsManager.addDelayedException(() -> str.set("value"));
        Thread.sleep(400);
        Assertions.assertEquals("value", str.get());
    }

    @Test
    public void add_delayed_exception_handling_task_with_time() throws InterruptedException {
        AtomicReference<String> str = new AtomicReference<>();
        ExceptionsManager.addDelayedException(() -> str.set("value"), 500, TimeUnit.MILLISECONDS);
        Assertions.assertNull(str.get());
        Thread.sleep(200);
        Assertions.assertNull(str.get());
        Thread.sleep(400);
        Assertions.assertEquals("value", str.get());
    }

    @Test
    public void handle_error_exception_with_localization() throws InterruptedException {
        AtomicReference<String> str = new AtomicReference<>();
        ExceptionsManager.addDelayedException(() -> {
            new Thread(() -> ExceptionsManager.handleErrorExceptionWithLocalization(
                    "exceptions.file-creation-exception.message")
            ).start();
            str.set("value");
        });
        Thread.sleep(400);
        Assertions.assertEquals("value", str.get());
    }

    @Test
    public void handle_error_exception_with_localization_and_message_arg() throws InterruptedException {
        AtomicReference<String> str = new AtomicReference<>();
        ExceptionsManager.addDelayedException(() -> {
            new Thread(() -> ExceptionsManager.handleErrorExceptionWithLocalization(
                    "exceptions.file-reading-exception.message", "fileName")
            ).start();
            str.set("value");
        });
        Thread.sleep(400);
        Assertions.assertEquals("value", str.get());
    }

    @Test
    public void handle_error_exception() throws InterruptedException {
        AtomicReference<String> str = new AtomicReference<>();
        ExceptionsManager.addDelayedException(() -> {
            new Thread(() -> ExceptionsManager.handleErrorException("message")).start();
            str.set("value");
        });
        Thread.sleep(400);
        Assertions.assertEquals("value", str.get());
    }

    @Test
    public void handle_warning_exception_with_localization() throws InterruptedException {
        AtomicReference<String> str = new AtomicReference<>();
        ExceptionsManager.addDelayedException(() -> {
            new Thread(() -> ExceptionsManager.handleWarningExceptionWithLocalization(
                    "exceptions.file-creation-exception.message")
            ).start();
            str.set("value");
        });
        Thread.sleep(400);
        Assertions.assertEquals("value", str.get());
    }

    @Test
    public void handle_warning_exception_with_localization_and_message_arg() throws InterruptedException {
        AtomicReference<String> str = new AtomicReference<>();
        ExceptionsManager.addDelayedException(() -> {
            new Thread(() -> ExceptionsManager.handleWarningExceptionWithLocalization(
                    "exceptions.file-reading-exception.message", "fileName")
            ).start();
            str.set("value");
        });
        Thread.sleep(400);
        Assertions.assertEquals("value", str.get());
    }

    @Test
    public void handle_informational_exception() throws InterruptedException {
        AtomicReference<String> str = new AtomicReference<>();
        ExceptionsManager.addDelayedException(() -> {
            new Thread(() -> ExceptionsManager.handleInformationalException("message")
            ).start();
            str.set("value");
        });
        Thread.sleep(400);
        Assertions.assertEquals("value", str.get());
    }

    @Test
    public void handle_informational_exception_with_localization() throws InterruptedException {
        AtomicReference<String> str = new AtomicReference<>();
        ExceptionsManager.addDelayedException(() -> {
            new Thread(() -> ExceptionsManager.handleInformationalExceptionWithLocalization(
                    "exceptions.file-creation-exception.message")
            ).start();
            str.set("value");
        });
        Thread.sleep(400);
        Assertions.assertEquals("value", str.get());
    }

    @Test
    public void handle_informational_exception_with_localization_and_message_arg() throws InterruptedException {
        AtomicReference<String> str = new AtomicReference<>();
        ExceptionsManager.addDelayedException(() -> {
            new Thread(() -> ExceptionsManager.handleInformationalExceptionWithLocalization (
                    "exceptions.file-reading-exception.message", "fileName")
            ).start();
            str.set("value");
        });
        Thread.sleep(400);
        Assertions.assertEquals("value", str.get());
    }

    @AfterEach
    public void afterEach(){
        Application.closePageFrame();
    }

}
