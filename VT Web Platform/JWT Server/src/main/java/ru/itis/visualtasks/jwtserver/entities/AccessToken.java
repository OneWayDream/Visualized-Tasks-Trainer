package ru.itis.visualtasks.jwtserver.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessToken {

    private Long accountId;
    private String login;
    private Date expiration;
    private JwtRole role;
    private JwtState state;

    public boolean isActive() {
        return this.state == JwtState.ACTIVE;
    }

    public boolean isBanned() {
        return this.state == JwtState.BANNED;
    }

    public boolean isAdmin() {
        return this.role == JwtRole.ADMIN;
    }
}
