package ru.itis.visualtasks.tasksserver.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.visualtasks.tasksserver.entities.ArchiveInfo;
import ru.itis.visualtasks.tasksserver.entities.NewTaskInfo;
import ru.itis.visualtasks.tasksserver.exceptions.tasks.TaskArchiveReadingException;
import ru.itis.visualtasks.tasksserver.models.TaskArchive;
import ru.itis.visualtasks.tasksserver.services.TasksService;
import ru.itis.visualtasks.tasksserver.utils.JwtUtils;

import java.io.IOException;

@RequiredArgsConstructor
@RestController("/tasks")
@CrossOrigin
public class TasksController {

    private final TasksService tasksService;
    private final JwtUtils jwtUtils;

    @Operation(summary = "Get tasks information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success request handling"),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception")
    })
    @GetMapping("/all")
    public ResponseEntity<?> getTasksInfos(){
        return ResponseEntity.ok()
                .body(tasksService.getTasksInfos());
    }

    @Operation(summary = "Upload a new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success upload"),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "452", description = "The token is banned"),
            @ApiResponse(responseCode = "453", description = "The token is expired"),
            @ApiResponse(responseCode = "454", description = "The token is incorrect"),
            @ApiResponse(responseCode = "455", description = "Access denied"),
            @ApiResponse(responseCode = "458", description = "Unknown programming language"),
            @ApiResponse(responseCode = "459", description = "Incorrect archive extension"),
            @ApiResponse(responseCode = "460", description = "Unknown visualization type"),
            @ApiResponse(responseCode = "461", description = "Fault to read task archive"),
            @ApiResponse(responseCode = "462", description = "Fault to read config"),
            @ApiResponse(responseCode = "463", description = "Config not found"),
            @ApiResponse(responseCode = "464", description = "Config is incomplete")
    })
    @PostMapping(
            value = "/upload",
            headers = {"JWT"},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> uploadNewTask(@RequestPart(name = "task") MultipartFile task,
                                           @RequestHeader("JWT") String token){
        NewTaskInfo newTaskInfo = getNewTaskInfo(token);
        ArchiveInfo archiveInfo = getArchiveInfo(task);
        byte[] archiveContent = parseArchive(task);
        tasksService.saveTask(archiveContent, newTaskInfo, archiveInfo);
        return ResponseEntity.ok().build();
    }

    private NewTaskInfo getNewTaskInfo(String token) {
        DecodedJWT decodedJWT = jwtUtils.decodeJwt(token);
        return NewTaskInfo.builder()
                .authorId(decodedJWT.getClaim("account_id").asLong())
                .authorName(decodedJWT.getClaim("login").asString())
                .build();
    }

    private ArchiveInfo getArchiveInfo(MultipartFile task) {
        return ArchiveInfo.builder()
                .archiveName(task.getOriginalFilename())
                .build();
    }

    private byte[] parseArchive(MultipartFile file) {
        try{
            return file.getBytes();
        } catch (IOException e) {
            throw new TaskArchiveReadingException(e);
        }
    }

    @Operation(summary = "Get task archive by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success upload",
                    content = @Content(mediaType = "application/zip", schema = @Schema(type = "string", format = "binary"))
            ),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "458", description = "Access denied")
    })
    @GetMapping(
            value = "/by-id/{id}",
            produces = {
                    "application/zip; charset=utf-8"
            }
    )
    public ResponseEntity<?> getTaskArchiveById(@PathVariable String id) {
        TaskArchive taskArchive = tasksService.getTaskArchiveById(id);
        return packTaskToResponse(taskArchive);
    }

    @Operation(summary = "Get task archive by task id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success upload",
                    content = @Content(mediaType = "application/zip", schema = @Schema(type = "string", format = "binary"))
            ),
            @ApiResponse(responseCode = "403", description = "Unexpected exception in the branch being handled"),
            @ApiResponse(responseCode = "418", description = "Unexpected exception"),
            @ApiResponse(responseCode = "458", description = "Access denied")
    })
    @GetMapping(
            value = "/by-task-id/{id}",
            produces = {
                    "application/zip; charset=utf-8"
            }
    )
    public ResponseEntity<?> getTaskArchiveByTaskId(@PathVariable Long id) {
        TaskArchive taskArchive = tasksService.getTaskArchiveByTaskId(id);
        return packTaskToResponse(taskArchive);
    }

    private ResponseEntity<?> packTaskToResponse(TaskArchive taskArchive){
        final ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(taskArchive.getArchiveName()).build();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .body(taskArchive.getArchive());
    }

}
