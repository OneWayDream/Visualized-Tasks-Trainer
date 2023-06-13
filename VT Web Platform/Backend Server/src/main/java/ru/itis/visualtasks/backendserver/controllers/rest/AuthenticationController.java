package ru.itis.visualtasks.backendserver.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.visualtasks.backendserver.dto.AccountDto;
import ru.itis.visualtasks.backendserver.dto.forms.SignInForm;
import ru.itis.visualtasks.backendserver.dto.forms.SignUpForm;
import ru.itis.visualtasks.backendserver.exceptions.jwtserver.JwtRegistrationException;
import ru.itis.visualtasks.backendserver.exceptions.registration.LoginAlreadyInUseException;
import ru.itis.visualtasks.backendserver.exceptions.registration.MailAlreadyInUseException;
import ru.itis.visualtasks.backendserver.services.RegistrationService;

import java.util.Objects;

@RestController
@RequestMapping()
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final RegistrationService service;

    @Operation(summary = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success registration", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = AccountDto.class)
                    ))
            }),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "456", description = "This login is already in use"),
            @ApiResponse(responseCode = "457", description = "This mail is already in use"),
            @ApiResponse(responseCode = "502", description = "Something wrong with jwt server connection")
    })
    @PostMapping("/sign-up")
    public ModelAndView registerNewUser(Model model, @Valid SignUpForm signUpForm,
                                        BindingResult bindingResult){
        ModelAndView modelAndView;
        if (bindingResult.hasErrors()){
            modelAndView = new ModelAndView("main");
            modelAndView.addAllObjects(model.asMap());
            bindingResult.getAllErrors().stream().allMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("signUpForm.ValidPasswords")){
                    modelAndView.addObject("message", error.getDefaultMessage());
                }
                return true;
            });
            signUpForm.setPassword("");
            signUpForm.setRepeatedPassword("");
            modelAndView.addObject("signInForm", new SignInForm());
            modelAndView.addObject("signUpForm", signUpForm);
            modelAndView.addObject("pageState", "sign-up");
            return modelAndView;
        } else {
            try{
                service.registerNewAccount(signUpForm);
                return new ModelAndView("redirect:/?success");
            } catch (LoginAlreadyInUseException | MailAlreadyInUseException | JwtRegistrationException ex){
                modelAndView = new ModelAndView("main");
                modelAndView.addAllObjects(model.asMap());
                signUpForm.setPassword("");
                signUpForm.setRepeatedPassword("");
                modelAndView.addObject("signInForm", new SignInForm());
                modelAndView.addObject("signUpForm", signUpForm);
                modelAndView.addObject("pageState", "sign-up");
                modelAndView.addObject("spring_message", ex.getMessage());
                return modelAndView;
            }
        }
    }

}
