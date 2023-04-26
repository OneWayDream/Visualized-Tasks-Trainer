package ru.itis.visualtasks.backendserver.services;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.visualtasks.backendserver.dto.forms.JwtUpdateForm;
import ru.itis.visualtasks.backendserver.dto.forms.TokenRegistrationForm;
import ru.itis.visualtasks.backendserver.exceptions.jwtserver.JwtRegistrationException;
import ru.itis.visualtasks.backendserver.exceptions.jwtserver.JwtUpdateException;
import ru.itis.visualtasks.backendserver.security.managers.TokenManager;

@Service
@RequiredArgsConstructor
public class JwtModuleServiceImpl implements JwtModuleService {

    protected final OkHttpClient client;
    protected final Gson gson;
    protected final TokenManager tokenManager;

    protected final String TOKEN_SERVER_URL;
    protected final String REGISTRATION_URL;
    protected final String UPDATE_URL;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Autowired
    public JwtModuleServiceImpl(
            TokenManager tokenManager,
            @Value("${security.jwt.token-server-host}") String serverHost,
            @Value("${security.jwt.token-server-port}") String serverPort,
            @Value("${security.jwt.registration-url}") String registrationUrl,
            @Value("${security.jwt.update-url}") String updateUrl
    ){
        client = new OkHttpClient();
        gson = new Gson();
        this.tokenManager = tokenManager;
        TOKEN_SERVER_URL = serverHost + ":" + serverPort;
        REGISTRATION_URL = registrationUrl;
        UPDATE_URL = updateUrl;

    }

    @Override
    public void deleteUserOnAuthorizationServer(Long id){
        try{
            Request request = new Request.Builder()
                    .url(TOKEN_SERVER_URL + UPDATE_URL + "/" + id)
                    .addHeader("JWT", tokenManager.getAccessToken())
                    .delete()
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() != 200){
                throw new JwtUpdateException();
            }
        } catch (Exception ex){
            throw new JwtUpdateException(ex);
        }
    }

    @Override
    public void updateUserOnAuthorizationServer(JwtUpdateForm form){
        try{
            String body = gson.toJson(form);
            RequestBody requestBody = RequestBody.create(body, JSON);
            Request request = new Request.Builder()
                    .url(TOKEN_SERVER_URL + UPDATE_URL)
                    .addHeader("JWT", tokenManager.getAccessToken())
                    .patch(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() != 200){
                throw new JwtUpdateException();
            }
        } catch (Exception ex){
            throw new JwtUpdateException(ex);
        }

    }

    @Override
    public void registerOnAuthorizationServer(TokenRegistrationForm form){
        try{
            String body = gson.toJson(form);
            RequestBody requestBody = RequestBody.create(body, JSON);
            Request request = new Request.Builder()
                    .url(TOKEN_SERVER_URL + REGISTRATION_URL)
                    .addHeader("JWT", tokenManager.getAccessToken())
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() != 200){
                throw new JwtRegistrationException();
            }
        } catch (Exception ex) {
            throw new JwtRegistrationException("error.sign-up.jwt-server-registration", ex);
        }
    }

}
