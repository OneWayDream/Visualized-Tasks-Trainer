package ru.itis.visualtasks.jwtserver.services;

import ru.itis.visualtasks.jwtserver.dto.JwtModuleDto;

public interface JwtModuleService extends CrudService<JwtModuleDto, Long> {

    JwtModuleDto findByLogin(String login);

}
