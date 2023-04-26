package ru.itis.visualtasks.jwtserver.services;

public interface JwtBlacklistService {

    void add(String token);

    boolean exists(String token);

}