package ru.itis.visualtasks.backendserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtServerResponse {

    private String token;
    private String expirationDate;

}
