package ru.itis.visualtasks.backendserver.security.managers;

import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.visualtasks.backendserver.dto.forms.JwtServerAuthorizationForm;
import ru.itis.visualtasks.backendserver.dto.JwtServerResponse;
import ru.itis.visualtasks.backendserver.exceptions.jwtserver.JwtAuthorizationFaultException;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class TokenManager {

    private String refreshToken;
    private String accessToken;
    private LocalDateTime refreshTokenExpiration;
    private LocalDateTime accessTokenExpiration;
    private final String LOGIN;
    private final String PASSWORD;
    private final String TOKEN_SERVER_URL;
    private final String TOKEN_REFRESH_URL;
    private final String TOKEN_ACCESS_URL;
    private final String REFRESH_TOKEN_HEADER_NAME;

    private final OkHttpClient client;
    private final Gson gson;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Autowired
    public TokenManager(
            @Value("${security.jwt.user-name}") String userName,
            @Value("${security.jwt.password}") String password,
            @Value("${security.jwt.token-server-host}") String serverHost,
            @Value("${security.jwt.token-server-port}") String serverPort,
            @Value("${security.jwt.token-server-refresh-url}") String refreshUrl,
            @Value("${security.jwt.token-server-access-url}") String accessUrl,
            @Value("${security.jwt.refresh-token-header-name}") String refreshHeaderName
    ){
        client = new OkHttpClient();
        gson = new Gson();
        LOGIN = userName;
        PASSWORD = password;
        TOKEN_SERVER_URL = serverHost + ":" + serverPort;
        TOKEN_REFRESH_URL = refreshUrl;
        TOKEN_ACCESS_URL = accessUrl;
        REFRESH_TOKEN_HEADER_NAME = refreshHeaderName;
    }

    public String getAccessToken(){
        if (isTokenNotInitialized(accessToken)
                || isTokenNotInfinitive(accessToken, accessTokenExpiration)
                && isTokenExpired(accessTokenExpiration)
        ) {
            getRefreshToken();
            updateAccessToken();
        }
        return accessToken;
    }

    public void dropAccessToken(){
        this.accessToken = null;
    }

    public void dropRefreshToken(){
        this.refreshToken = null;
    }

    private String getRefreshToken(){
        if (isTokenNotInitialized(refreshToken)
                || isTokenNotInfinitive(refreshToken, refreshTokenExpiration)
                && isTokenExpired(refreshTokenExpiration)
        ) {
            updateRefreshToken();
        }
        return refreshToken;
    }

    private boolean isTokenNotInitialized(String token){
        return token == null;
    }

    private boolean isTokenNotInfinitive(String token, LocalDateTime expiration){
        return token != null && expiration != null;
    }

    private boolean isTokenExpired(LocalDateTime expiration){
        LocalDateTime date = LocalDateTime.now();
        return date.isBefore(expiration);
    }

    private void updateAccessToken(){
        try{
            RequestBody requestBody = RequestBody.create("", JSON);
            Request request = new Request.Builder()
                    .url(TOKEN_SERVER_URL + TOKEN_ACCESS_URL)
                    .post(requestBody)
                    .header(REFRESH_TOKEN_HEADER_NAME, refreshToken)
                    .build();
            Response response = client.newCall(request).execute();
            JwtServerResponse jwtServerResponse = gson.fromJson(Objects.requireNonNull(response.body()).string(),
                    JwtServerResponse.class);
            accessToken = jwtServerResponse.getToken();
            accessTokenExpiration = jwtServerResponse.getExpirationDate() == null ? null :
                    LocalDateTime.parse(jwtServerResponse.getExpirationDate());
        } catch (Exception ex) {
            throw new JwtAuthorizationFaultException(ex);
        }
    }

    private void updateRefreshToken(){
        try{
            String body = gson.toJson(new JwtServerAuthorizationForm(LOGIN, PASSWORD));
            RequestBody requestBody = RequestBody.create(body, JSON);
            Request request = new Request.Builder()
                    .url(TOKEN_SERVER_URL + TOKEN_REFRESH_URL)
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            JwtServerResponse jwtServerResponse = gson.fromJson(Objects.requireNonNull(response.body()).string(),
                    JwtServerResponse.class);
            refreshToken = jwtServerResponse.getToken();
            refreshTokenExpiration = jwtServerResponse.getExpirationDate() == null ? null :
                    LocalDateTime.parse(jwtServerResponse.getExpirationDate());
        } catch (Exception ex) {
            throw new JwtAuthorizationFaultException(ex);
        }
    }

}
