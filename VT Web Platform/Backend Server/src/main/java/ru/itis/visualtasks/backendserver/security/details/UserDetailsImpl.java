package ru.itis.visualtasks.backendserver.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.visualtasks.backendserver.dto.AccountDto;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    protected AccountDto accountDto;

    public UserDetailsImpl(AccountDto accountDto) {
        this.accountDto = accountDto;
    }

    public AccountDto getAccountDto(){
        return accountDto;
    }

    public void setUser(AccountDto user){
        this.accountDto = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(accountDto.getRole().toString());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return accountDto.getPassword();
    }

    @Override
    public String getUsername() {
        return accountDto.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountDto.isPresent();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return accountDto.isPresent();
    }
}
