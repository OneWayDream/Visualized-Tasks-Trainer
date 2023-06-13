package ru.itis.visualtasks.jwtserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.visualtasks.jwtserver.dto.AccessTokenResponse;
import ru.itis.visualtasks.jwtserver.dto.ModuleAuthorizationForm;
import ru.itis.visualtasks.jwtserver.dto.RefreshTokenResponse;
import ru.itis.visualtasks.jwtserver.dto.UserAuthorizationForm;
import ru.itis.visualtasks.jwtserver.services.ModuleLoginService;
import ru.itis.visualtasks.jwtserver.services.UserLoginService;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class LoginController {

    private final ModuleLoginService loginService;
    private final UserLoginService userLoginService;

    @Operation(summary = "Login a module(get refresh token by login and password)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success getting", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = RefreshTokenResponse.class)
                    ))
            }),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "456", description = "The module is banned"),
            @ApiResponse(responseCode = "457", description = "Incorrect module data")
    })
    @PostMapping("/login-module")
    public ResponseEntity<?> loginModule(@RequestBody ModuleAuthorizationForm moduleAuthorizationForm){
        return ResponseEntity.ok(loginService.login(moduleAuthorizationForm));
    }

    @Operation(summary = "Authenticate a module (get access token by refresh token)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success getting", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = AccessTokenResponse.class)
                    ))
            }),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "452", description = "The token is banned"),
            @ApiResponse(responseCode = "453", description = "The token is expired"),
            @ApiResponse(responseCode = "454", description = "The token is incorrect")
    })
    @PostMapping(
            value = "/auth-module",
            headers = {"refresh-token"}
    )
    public ResponseEntity<?> authenticateModule(HttpServletRequest request){
        return ResponseEntity.ok(loginService.authenticate(request.getHeader("refresh-token")));
    }

    @Operation(summary = "Login a user (get refresh token by login and password)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success getting", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = RefreshTokenResponse.class)
                    ))
            }),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "456", description = "The user is banned"),
            @ApiResponse(responseCode = "457", description = "Incorrect user data")
    })
    @PostMapping(
            value = "/login-user"
    )
    public ResponseEntity<?> loginUser(@RequestBody UserAuthorizationForm form){
        return ResponseEntity.ok(userLoginService.login(form));
    }

    @Operation(summary = "Authenticate a user (get access token by refresh token)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success getting", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = AccessTokenResponse.class)
                    ))
            }),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "452", description = "The token is banned"),
            @ApiResponse(responseCode = "453", description = "The token is expired"),
            @ApiResponse(responseCode = "454", description = "The token is incorrect")
    })
    @PostMapping(
            value = "/auth-user",
            headers = {"refresh-token"}
    )
    public ResponseEntity<?> authenticateUser(HttpServletRequest request){
        return ResponseEntity.ok(userLoginService.authenticate(request.getHeader("refresh-token")));
    }

    @Operation(summary = "Log out a token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success logging out"),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "452", description = "The token is banned"),
            @ApiResponse(responseCode = "453", description = "The token is expired"),
            @ApiResponse(responseCode = "454", description = "The token is incorrect")
    })
    @GetMapping(
            value = "/logout",
            headers = {"JWT"}
    )
    public ResponseEntity<?> logOut(){
        return ResponseEntity.ok().build();
    }

}
