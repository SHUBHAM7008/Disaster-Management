package com.example.Disaster.Management.Tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Emails {

    @Id
    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
