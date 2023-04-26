package ru.itis.visualtasks.backendserver.services;

import ru.itis.visualtasks.backendserver.dto.AccountDto;
import ru.itis.visualtasks.backendserver.dto.forms.SignUpForm;

public interface RegistrationService {

    AccountDto registerNewAccount(SignUpForm signUpForm);

}
