package ru.itis.visualtasks.backendserver.services;

import ru.itis.visualtasks.backendserver.dto.AccountDto;

public interface AccountService extends CrudService<AccountDto, Long> {

    AccountDto findByLogin(String login);
    AccountDto findByMail(String mail);

}
