package ru.itis.visualtasks.jwtserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.visualtasks.jwtserver.dto.JwtUserDto;
import ru.itis.visualtasks.jwtserver.services.JwtUserService;

@RestController()
@RequestMapping("/account")
@RequiredArgsConstructor
@CrossOrigin
public class JwtAccountController {

    private final JwtUserService service;

    @Operation(summary = "Register new jwt user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success registration", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = JwtUserDto.class)
                    ))
            }),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "457", description = "Access denied")
    })
    @PostMapping(
            value = "/register",
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<JwtUserDto> registerNewUser(@RequestBody JwtUserDto jwtUserDto){
        return ResponseEntity.ok(service.add(jwtUserDto));
    }

    @Operation(summary = "Updating jwt user by account id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success updating", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = JwtUserDto.class)
                    ))
            }),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "404", description = "Entity does not exists"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "457", description = "Access denied"),
    })
    @PatchMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<JwtUserDto> updateById(@RequestBody JwtUserDto jwtUserDto){
        return ResponseEntity.ok(service.updateByAccountId(jwtUserDto));
    }

    @Operation(summary = "Deleting jwt user by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success deleting"),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "404", description = "Entity does not exists"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "458", description = "Access denied")
    })
    @DeleteMapping(
            value = "/{id}",
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<?> deleteByAccountId(@PathVariable Long id){
        service.deleteByAccountId(id);
        return ResponseEntity.ok().build();
    }

}
