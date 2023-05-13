package ru.itis.visualtasks.jwtserver.models;

import jakarta.persistence.*;
import lombok.*;
import ru.itis.visualtasks.jwtserver.entities.JwtRole;
import ru.itis.visualtasks.jwtserver.entities.JwtState;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "jwt_user")
public class JwtUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String mail;

    @Column(name = "account_id", nullable = false, unique = true)
    private Long accountId;

    @Column(name = "hash_password")
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private JwtState state;

    @Enumerated(value = EnumType.STRING)
    private JwtRole role;

    @Column(name = "redis_id")
    private String redisId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
