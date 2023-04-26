package ru.itis.visualtasks.backendserver.dto.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInForm {

    @Size(min = 3, max = 30)
    @NotBlank
    private String login;

    @Size(min = 8, max = 50)
    @NotBlank
    private String password;

    private String rememberMe;

}
