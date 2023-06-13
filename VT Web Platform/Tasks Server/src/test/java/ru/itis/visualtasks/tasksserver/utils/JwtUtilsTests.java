package ru.itis.visualtasks.tasksserver.utils;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.tasksserver.exceptions.token.IncorrectJwtException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest()
@TestPropertySource("classpath:test.properties")
@DisplayName("JwtUtils is working when")
public class JwtUtilsTests {

    @Autowired
    private JwtUtils jwtUtils;

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("decodeJwt() in working when")
    public class DecodeJwt{

        @Test
        public void decodeCorrectUserRefreshToken(){
            assertNotNull(jwtUtils.decodeJwt(getAccountRefreshToken()));
        }

        @Test
        public void decodeCorrectUserAccessToken(){
            assertNotNull(jwtUtils.decodeJwt(getAccountAccessToken()));
        }

        @Test
        public void decodeCorrectModuleRefreshToken(){
            assertNotNull(jwtUtils.decodeJwt(getModuleRefreshToken()));
        }

        @Test
        public void decodeCorrectModuleAccessToken(){
            assertNotNull(jwtUtils.decodeJwt(getModuleAccessToken()));
        }

        @Test
        public void decodeIncorrectToken(){
            assertThrows(IncorrectJwtException.class, () -> jwtUtils.decodeJwt(getInvalidToken()));
        }

        public static String getAccountRefreshToken(){
            return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwicmVkaXNfaWQiOiIyNzRkZDZhOC0zYTc0LTQ1ZWQtYWY1My0xMjA4NmE3MjkzYzQiLCJhY2NvdW50X2lkIjo5LCJyb2xlIjoiVVNFUiIsInNhbHQiOiI5MzdkMTM0ZS00MWJkLTQxYTItODQ2OS0zYTRhOWI1OTlhNGQiLCJleHBpcmF0aW9uIjpudWxsLCJzdGF0ZSI6IkFDVElWRSIsImxvZ2luIjoiT25lV2F5RHJlYW0ifQ.696IuoRw0-eR0VabsPQiRBvkvIFeJeP5JD56BV5kNZk";
        }

        public static String getAccountAccessToken(){
            return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwiYWNjb3VudF9pZCI6OSwicm9sZSI6IlVTRVIiLCJzYWx0IjoiMWZjYWZlZGQtNDJiYy00YWRhLWI4YzMtOTliNzMwOGQ5NzM4IiwiZXhwaXJhdGlvbiI6bnVsbCwic3RhdGUiOiJBQ1RJVkUiLCJsb2dpbiI6Ik9uZVdheURyZWFtIn0._aX20py8eNU8249Hd-xebLMo1B1OeNnX_xa0UeSn0Mw";
        }

        public static String getModuleRefreshToken(){
            return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkFETUlOIiwiZXhwaXJhdGlvbiI6IjIwMjMtMDUtMTRUMTg6NTU6MTAuNjc3ODcxMTAwIiwiaWQiOjEsInN0YXRlIjoiQUNUSVZFIiwibG9naW4iOiJiYWNrZW5kIn0.UGfKibn82R6_lx9pd3NI6WK9E90eKSnhkfxkGC9saIc";
        }

        public static String getModuleAccessToken(){
            return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkFETUlOIiwiZXhwaXJhdGlvbiI6IjIwMjMtMDUtMDhUMDY6NTU6MjcuMDY3NTc3NjAwIiwiaWQiOjEsInN0YXRlIjoiQUNUSVZFIiwibG9naW4iOiJiYWNrZW5kIn0.U_fLNf3_JPJHNiCDryW2NrUVKGiRv0c4A1yiFaEJShU";
        }

        public static String getInvalidToken(){
            return "invalid-token";
        }

    }

}
