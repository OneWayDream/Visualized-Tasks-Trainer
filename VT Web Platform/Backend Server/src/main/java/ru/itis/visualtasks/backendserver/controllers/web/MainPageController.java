package ru.itis.visualtasks.backendserver.controllers.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.visualtasks.backendserver.dto.forms.SignInForm;

import java.util.Objects;

@Controller
public class MainPageController extends WebController {

    @GetMapping("/")
    public ModelAndView getMainPage(@Valid @RequestBody(required = false) SignInForm signInForm,
                                    @RequestParam(value = "sign-in", required = false) String signInErrors){
        ModelAndView modelAndView = new ModelAndView("main");
        addAuthenticationAttributes(modelAndView);
        modelAndView.addObject("signInForm", Objects.requireNonNullElseGet(signInForm, SignInForm::new));
        if (signInErrors!=null){
            modelAndView.addObject("signInError", "errors.sign-in.incorrect-data");
        }
        return modelAndView;
    }

}
