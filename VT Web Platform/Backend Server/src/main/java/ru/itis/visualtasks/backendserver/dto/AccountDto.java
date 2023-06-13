package ru.itis.visualtasks.backendserver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.visualtasks.backendserver.models.Account;
import ru.itis.visualtasks.backendserver.models.UserRole;
import ru.itis.visualtasks.backendserver.models.UserState;
import ru.itis.visualtasks.backendserver.utils.EncryptionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private Long id;

    @Email
    @Size(max = 30)
    private String mail;

    @Size(min = 4, max = 30)
    private String login;

    @Size(min = 8, max = 50)
    private String password;
    private UserState state;
    private UserRole role;
    private LocalDate registrationDate;

    public static AccountDto from(Account account){
        return (account == null) ? null : AccountDto.builder()
                .id(account.getId())
                .mail(account.getMail())
                .login(account.getLogin())
                .password(account.getHashPassword())
                .registrationDate(account.getRegistrationDate())
                .state(account.getState())
                .role(account.getRole())
                .build();
    }

    public static Account to(AccountDto account){
        return (account == null) ? null : Account.builder()
                .id(account.getId())
                .mail(account.getMail())
                .login(account.getLogin())
                .hashPassword((account.getPassword() == null) ? null
                        : EncryptionUtils.encryptPassword(account.getPassword()))
                .state(account.getState())
                .role(account.getRole())
                .registrationDate(account.getRegistrationDate())
                .build();
    }

    public static List<AccountDto> from(List<Account> accounts){
        return accounts.stream()
                .map(AccountDto::from)
                .collect(Collectors.toList());
    }

    public static List<Account> to(List<AccountDto> accounts){
        return accounts.stream()
                .map(AccountDto::to)
                .collect(Collectors.toList());
    }

    public AccountDto prepareToSend(){
        password = null;
        return this;
    }

    public boolean isPresent(){
        return state == UserState.ACTIVE;
    }

}
