package ru.itis.visualtasks.backendserver.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.visualtasks.backendserver.dto.AccountDto;
import ru.itis.visualtasks.backendserver.services.AccountService;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AccountDto accountDto = accountService.findByLogin(login);
        return new UserDetailsImpl(accountDto);
    }
}
