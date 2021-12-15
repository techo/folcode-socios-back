package com.folcode.techoapi.models.entities.dto;

import com.google.firebase.auth.ExportedUserRecord;

import java.util.Map;

public class UserDto {
    private final String mail;
    private final String uid;
    private final String name;
    private final Map<String, Object> claims;
  /*  */

    public UserDto(String mail, String uid, String name, Map<String, Object> claims) {
        this.mail = mail;
        this.uid = uid;
        this.name = name;
        this.claims = claims;
    }

    public UserDto(ExportedUserRecord user) {
        this.mail = user.getEmail();
        this.uid = user.getUid();
        this.name = user.getDisplayName();
        this.claims = user.getCustomClaims();
    }

    public Map<String, Object> getClaims() {
        return claims;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getUid() {
        return uid;
    }
}
