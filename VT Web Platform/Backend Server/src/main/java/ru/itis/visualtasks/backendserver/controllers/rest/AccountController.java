package ru.itis.visualtasks.backendserver.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.visualtasks.backendserver.dto.AccountDto;
import ru.itis.visualtasks.backendserver.services.AccountService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class AccountController {

    private final AccountService service;

    @Operation(summary = "Getting user by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success getting", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = AccountDto.class)
                    ))
            }),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "404", description = "Entity not found"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "458", description = "Access denied")
    })
    @GetMapping(
            value = "/by-id/{id}"
    )
    public ResponseEntity<AccountDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id).prepareToSend());
    }

    @Operation(summary = "Getting user by login.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success getting", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = AccountDto.class)
                    ))
            }),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "404", description = "Entity not found"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "458", description = "Access denied")
    })
    @GetMapping(
            value = "/by-login/{login}"
    )
    public ResponseEntity<AccountDto> getByLogin(@PathVariable String login){
        return ResponseEntity.ok(service.findByLogin(login).prepareToSend());
    }

    @Operation(summary = "Getting user by mail.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success getting", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = AccountDto.class)
                    ))
            }),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "404", description = "Entity not found"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "458", description = "Access denied")
    })
    @GetMapping(
            value = "/by-mail/{mail}"
    )
    public ResponseEntity<AccountDto> getByMail(@PathVariable String mail){
        return ResponseEntity.ok(service.findByMail(mail).prepareToSend());
    }

    @Operation(summary = "Updating user by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success updating", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = AccountDto.class)
                    ))
            }),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "404", description = "Entity does not exists"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "456", description = "This login is already in use"),
            @ApiResponse(responseCode = "457", description = "This mail is already in use"),
            @ApiResponse(responseCode = "458", description = "Access denied"),
            @ApiResponse(responseCode = "502", description = "Something wrong with jwt server connection")
    })
    @PatchMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AccountDto> updateById(@Valid @RequestBody AccountDto accountDto){
        return ResponseEntity.ok(service.update(accountDto));
    }

    @Operation(summary = "Deleting user by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success deleting"),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "404", description = "Entity does not exists"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "458", description = "Access denied"),
            @ApiResponse(responseCode = "502", description = "Something wrong with jwt server connection")
    })
    @DeleteMapping(
            value = "/{id}",
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.delete(AccountDto.builder().id(id).build());
        return ResponseEntity.ok().build();
    }

}
