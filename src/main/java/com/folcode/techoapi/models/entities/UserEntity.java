package com.folcode.techoapi.models.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name= "users")

public class UserEntity {
    @Id
    private Long id;
    private String mail;
    private String uid;

    public UserEntity(Long id) {
        this.id = id;
    }

    public UserEntity(Long id, String mail, String uid) {
        this.id = id;

        this.mail = mail;
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getUid() {
        return uid;
    }


}
