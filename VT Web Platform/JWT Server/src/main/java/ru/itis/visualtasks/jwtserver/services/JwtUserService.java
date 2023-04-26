package ru.itis.visualtasks.jwtserver.services;

import ru.itis.visualtasks.jwtserver.dto.JwtUserDto;

public interface JwtUserService extends CrudService<JwtUserDto, Long> {

    JwtUserDto findByLogin(String login);
    JwtUserDto findByMail(String mail);
    JwtUserDto findByAccountId(Long accountId);
    JwtUserDto updateByAccountId(JwtUserDto jwtUserDto);
    void deleteByAccountId(Long accountId);

}
