package ru.itis.visualtasks.backendserver.services;

import ru.itis.visualtasks.backendserver.exceptions.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.visualtasks.backendserver.dto.AccountDto;
import ru.itis.visualtasks.backendserver.dto.forms.JwtUpdateForm;
import ru.itis.visualtasks.backendserver.dto.forms.TokenRegistrationForm;
import ru.itis.visualtasks.backendserver.exceptions.registration.LoginAlreadyInUseException;
import ru.itis.visualtasks.backendserver.exceptions.registration.MailAlreadyInUseException;
import ru.itis.visualtasks.backendserver.models.Account;
import ru.itis.visualtasks.backendserver.repositories.AccountRepository;
import ru.itis.visualtasks.backendserver.utils.PropertiesUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final JwtModuleService jwtModuleService;

    @Override
    public AccountDto findByLogin(String login) {
        return AccountDto.from(repository.findByLogin(login)
                .filter(Account::isPresent)
                .orElseThrow(() -> new EntityNotFoundException(" account.")));
    }

    @Override
    public AccountDto findByMail(String mail) {
        return AccountDto.from(repository.findByMail(mail)
                .filter(Account::isPresent)
                .orElseThrow(() -> new EntityNotFoundException(" account.")));
    }

    @Override
    public List<AccountDto> findAll() {
        return AccountDto.from(repository.findAll());
    }

    @Override
    public void delete(AccountDto accountDto) {
        repository.deleteById(accountDto.getId());
    }

    @Override
    public AccountDto add(AccountDto accountDto) {
        Account savedAccount = repository.save(AccountDto.to(accountDto));
        jwtModuleService.registerOnAuthorizationServer(TokenRegistrationForm.builder()
                .accountId(savedAccount.getId())
                .login(savedAccount.getLogin())
                .mail(savedAccount.getMail())
                .hashPassword(savedAccount.getHashPassword())
                .state(savedAccount.getState())
                .role(savedAccount.getRole())
                .build());
        return AccountDto.from(savedAccount);
    }

    @Override
    public AccountDto findById(Long aLong) {
        return AccountDto.from(repository.findById(aLong)
                .filter(Account::isPresent)
                .orElseThrow(() -> new EntityNotFoundException(" account.")));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public AccountDto update(AccountDto accountDto) {
        try{
            AccountDto entity = AccountDto.from(repository.findById(accountDto.getId())
                    .filter(Account::isPresent)
                    .orElseThrow(() -> new EntityNotFoundException(" account.")));
            accountDto.setState(null);
            accountDto.setRegistrationDate(null);
            accountDto.setRole(null);
            PropertiesUtils.copyNonNullProperties(accountDto, entity);
            Account updatedAccount =  repository.save(AccountDto.to(entity));
            jwtModuleService.updateUserOnAuthorizationServer(JwtUpdateForm.builder()
                    .accountId(updatedAccount.getId())
                    .login(updatedAccount.getLogin())
                    .mail(updatedAccount.getMail())
                    .hashPassword(updatedAccount.getHashPassword())
                    .role(updatedAccount.getRole())
                    .state(updatedAccount.getState())
                    .build());
            return AccountDto.from(updatedAccount);
        } catch (Exception ex){
            try{
                String message = ex.getCause().getCause().getMessage();
                if (message.contains("account_mail_key")){
                    throw new MailAlreadyInUseException(ex);
                } else if (message.contains("account_login_key")){
                    throw new LoginAlreadyInUseException(ex);
                }
            } catch (NullPointerException exception){
                //ignore
            }
            throw ex;
        }
    }

}
