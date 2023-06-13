package ru.itis.visualtasks.backendserver.dto.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.visualtasks.backendserver.validation.ValidPasswords;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidPasswords(
        password = "password",
        repeatedPassword = "repeatedPassword",
        message = "{errors.sign-up.different-passwords}"
)
public class SignUpForm implements Serializable {

    @NotBlank(message = "{errors.sign-up.no-login}")
    @Size(min = 4, max = 30, message = "{errors.sign-up.incorrect-login-size}")
    @Pattern(regexp = "[\\w]+", message = "{errors.sign-up.incorrect-login-symbols}")
    private String login;

    @NotBlank(message = "{errors.sign-up.blank-email}")
    @Size(min = 3, max = 30, message = "{errors.sign-up.incorrect-email-size}")
    @Email(message = "{errors.sign-up.incorrect-email}")
    private String email;

    @NotBlank(message = "{errors.sign-up.blank-password}")
    @Size(min = 8, max = 50, message = "{errors.sign-up.incorrect-password-size}")
    private String password;

    @NotBlank(message = "{errors.sign-up.blank-repeated-password}")
    @Size(min = 8, max = 50, message = "{errors.sign-up.incorrect-repeated-password-size}")
    private String repeatedPassword;

    @NotBlank(message = "{errors.sign-up.no-user-terms-consent}")
    private String userAccess;

}
