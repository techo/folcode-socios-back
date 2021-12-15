package com.folcode.techoapi.services;


import com.folcode.techoapi.models.entities.dto.UserDto;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.*;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {


    @PostConstruct
    public void init() throws IOException {
        String secret = "{\n" +
                "  \"type\": \"#######\",\n" +
                "  \"project_id\": \"########\",\n" +
                "  \"private_key_id\": \"##########\",\n" +
                "  \"private_key\": \"############",\"n" +
                "  \"client_email\": \"#########\",\n" +
                "  \"client_id\": \"#########\",\n" +
                "  \"auth_uri\": \"#########\",\n" +
                "  \"token_uri\": \"######\",\n" +
                "  \"auth_provider_x509_cert_url\": \"#######\",\n" +
                "  \"client_x509_cert_url\": \" ######\"\n" +
                "}";

        FirebaseOptions options = new FirebaseOptions
                .Builder()
                .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(StandardCharsets.UTF_16.encode(secret).array())))
                .setDatabaseUrl("#######")
                .build();

        FirebaseApp.initializeApp(options);
    }

    public List<UserDto> findAll() {
        List<UserDto> users = new ArrayList<>();
        ListUsersPage page = null;
        try {
            page = FirebaseAuth.getInstance().listUsers(null);
        } catch (FirebaseAuthException e) {
            return users;
        }
        while (page != null) {
            for (ExportedUserRecord user : page.getValues()) {
                users.add(new UserDto(user));
            }
            page = page.getNextPage();
        }

        return users;
    }

    public static String setClaims(String uid, Map<String, Object> claims) throws FirebaseAuthException {

        FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
        return ("seteado el claim  para el user " + uid);
    }

    public static String getUserById(String uid) throws FirebaseAuthException {

        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);

        return ("Successfully fetched user data: " + userRecord.getUid() + userRecord.getCustomClaims().get("admin"));

    }

    public static String verifyIdToken(String idToken) throws FirebaseAuthException {

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getEmail();

        return ("Decoded ID token from user: " + uid);
    }

    public static String createCustomToken(String uid) throws FirebaseAuthException {


        String customToken = FirebaseAuth.getInstance().createCustomToken(uid);

        return ("Created custom token: " + customToken);
    }


    public static void deleteUser(String uid) throws FirebaseAuthException {
        // [START delete_user]
        FirebaseAuth.getInstance().deleteUser(uid);
        // [END delete_user]
    }



}
