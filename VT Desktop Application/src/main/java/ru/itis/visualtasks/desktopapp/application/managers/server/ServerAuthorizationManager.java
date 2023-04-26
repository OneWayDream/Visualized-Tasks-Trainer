package ru.itis.visualtasks.desktopapp.application.managers.server;

import com.google.gson.Gson;
import okhttp3.*;
import ru.itis.visualtasks.desktopapp.application.entities.server.AuthenticationInformation;
import ru.itis.visualtasks.desktopapp.application.entities.server.JwtServerResponse;
import ru.itis.visualtasks.desktopapp.application.entities.server.SignInInformation;
import ru.itis.visualtasks.desktopapp.application.entities.server.UserAuthorizationForm;
import ru.itis.visualtasks.desktopapp.exceptions.server.*;
import ru.itis.visualtasks.desktopapp.utils.PropertiesUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ServerAuthorizationManager {

    private static final OkHttpClient client;
    private static final Gson gson;
    private static final String REFRESH_TOKEN_URL;
    private static final String ACCESS_TOKEN_URL;
    private static final String LOG_OUT_URL;
    private static final String REFRESH_TOKEN_HEADER_NAME;
    private static final String LOG_OUT_TOKEN_HEADER_NAME;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    static {
        client = new OkHttpClient();
        gson = new Gson();
        Properties properties = PropertiesUtils.getInstance().getProperties();
        String serverUrl = properties.getProperty("token-server-url");
        REFRESH_TOKEN_URL = serverUrl + properties.getProperty("token-refresh-url");
        ACCESS_TOKEN_URL = serverUrl + properties.getProperty("token-access-url");
        LOG_OUT_URL = serverUrl + properties.getProperty("log-out-url");
        REFRESH_TOKEN_HEADER_NAME = properties.getProperty("refresh-token-header-name");
        LOG_OUT_TOKEN_HEADER_NAME = properties.getProperty("log-out-token-header-name");
    }

    public static void signIn(UserAuthorizationForm userAuthorizationForm){
        Response response = null;
        try{
            String body = gson.toJson(userAuthorizationForm);
            RequestBody requestBody = RequestBody.create(body, JSON);
            Request request = new Request.Builder()
                    .url(REFRESH_TOKEN_URL)
                    .post(requestBody)
                    .build();
            response = client.newCall(request).execute();
            int httpCode = response.code();
            switch (httpCode) {
                case 200 -> {
                    JwtServerResponse jwtServerResponse = handleResponseBody(Objects.requireNonNull(response.body()).string());
                    String refreshToken = jwtServerResponse.getToken();
                    String refreshTokenExpiration = jwtServerResponse.getExpirationDate();
                    String login = userAuthorizationForm.getLogin();
                    AccountInformationManager.updateSignInInformation(SignInInformation.builder()
                            .login(login)
                            .refreshToken(refreshToken)
                            .refreshExpirationDate(refreshTokenExpiration)
                            .build());
                }
                case 456 -> throw new BannedAccountException();
                case 457 -> throw new IncorrectUserDataException();
                default -> throw new AuthenticationRequestExecutionException();
            }

        } catch (IOException ex) {
            throw new AuthenticationRequestExecutionException(ex);
        } finally {
            if (response != null){
                response.close();
            }
        }
    }

    private static JwtServerResponse handleResponseBody(String bodyContent) {
        return gson.fromJson(bodyContent, JwtServerResponse.class);
    }

    public static void authenticate(String refreshToken){
        Response response = null;
        try{
            RequestBody requestBody = RequestBody.create("body", JSON);
            Request request = new Request.Builder()
                    .url(ACCESS_TOKEN_URL)
                    .post(requestBody)
                    .header(REFRESH_TOKEN_HEADER_NAME, refreshToken)
                    .build();
            response = client.newCall(request).execute();
            int httpCode = response.code();
            switch (httpCode){
                case 200 -> {
                    JwtServerResponse jwtServerResponse = handleResponseBody(Objects.requireNonNull(response.body()).string());
                    String accessToken = jwtServerResponse.getToken();
                    String  accessTokenExpiration = jwtServerResponse.getExpirationDate();
                    AccountInformationManager.updateAuthenticationInformation(AuthenticationInformation.builder()
                            .accessToken(accessToken)
                            .accessExpirationDate(accessTokenExpiration)
                            .build());
                }
                case 452 -> throw new BannedTokenException();
                case 453 -> throw new ExpiredTokenException();
                case 454 -> throw new IncorrectTokenException();
                default -> throw new AuthenticationRequestExecutionException();
            }

        } catch (IOException ex) {
            throw new AuthenticationRequestExecutionException(ex);
        } finally {
            if (response != null){
                response.close();
            }
        }
    }

    public static void logOut(String accessToken, String refreshToken){
        banToken(accessToken);
        banToken(refreshToken);
        AccountInformationManager.resetAuthenticationInformation();
    }

    private static void banToken(String token) {
        Response response = null;
        try{
            Request request = new Request.Builder()
                    .url(LOG_OUT_URL)
                    .header(LOG_OUT_TOKEN_HEADER_NAME, token)
                    .get()
                    .build();
            response = client.newCall(request).execute();
            int httpCode = response.code();
            switch (httpCode) {
                case 200 -> {
                    // Token has been blocked.
                }
                case 452 -> {
                    // This token is already blocked
                }
                case 453 -> {
                    // This token is expired - don't need to block it
                }
                case 454 -> {
                    // Incorrect token - there is no reason for it, but uhm okey, we still don't need to utilize it c:
                }
                default -> throw new ServerLogOutException();
            }
        } catch (IOException ex) {
            throw new AuthenticationRequestExecutionException(ex);
        } finally {
            if (response != null){
                response.close();
            }
        }
    }

}
