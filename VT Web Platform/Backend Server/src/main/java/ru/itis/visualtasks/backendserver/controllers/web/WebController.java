package ru.itis.visualtasks.backendserver.controllers.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.visualtasks.backendserver.dto.forms.SignInForm;
import ru.itis.visualtasks.backendserver.dto.forms.SignUpForm;
import ru.itis.visualtasks.backendserver.security.details.UserDetailsImpl;

public abstract class WebController {

    protected void addAuthenticationAttributes(ModelAndView modelAndView){
        modelAndView.addObject("signInForm", new SignInForm());
        modelAndView.addObject("signUpForm", new SignUpForm());
        Object authenticationPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authenticationPrincipal instanceof UserDetailsImpl) {
            modelAndView.addObject("account", ((UserDetailsImpl) authenticationPrincipal)
                    .getAccountDto().prepareToSend());
        }
    }

}
