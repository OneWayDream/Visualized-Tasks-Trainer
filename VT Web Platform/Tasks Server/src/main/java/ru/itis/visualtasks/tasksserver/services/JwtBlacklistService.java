package ru.itis.visualtasks.tasksserver.services;

public interface JwtBlacklistService {

    boolean exists(String token);

}