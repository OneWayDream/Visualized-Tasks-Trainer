package ru.itis.visualtasks.backendserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.visualtasks.backendserver.dto.AccountDto;
import ru.itis.visualtasks.backendserver.dto.forms.SignUpForm;
import ru.itis.visualtasks.backendserver.exceptions.registration.LoginAlreadyInUseException;
import ru.itis.visualtasks.backendserver.exceptions.registration.MailAlreadyInUseException;
import ru.itis.visualtasks.backendserver.models.UserRole;
import ru.itis.visualtasks.backendserver.models.UserState;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final AccountService accountService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public AccountDto registerNewAccount(SignUpForm signUpForm) {
        try {
            AccountDto newAccount = AccountDto.builder()
                    .login(signUpForm.getLogin())
                    .mail(signUpForm.getEmail())
                    .password(signUpForm.getPassword())
                    .state(UserState.ACTIVE)
                    .role(UserRole.USER)
                    .registrationDate(LocalDate.now())
                    .build();
            newAccount = accountService.add(newAccount);

            return newAccount;
        } catch (Exception ex){
            try{
                String message = ex.getCause().getCause().getMessage();
                if (message.contains("account_mail_key")){
                    throw new MailAlreadyInUseException("errors.sign-up.email-already-in-use");
                } else if (message.contains("account_login_key")){
                    throw new LoginAlreadyInUseException("errors.sign-up.login-already-in-use");
                }
            } catch (NullPointerException exception){
                //ignore
            }
            throw ex;
        }
    }
}
