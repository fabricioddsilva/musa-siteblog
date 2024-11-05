package com.kaos.musa.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Document
public class Lead implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    private String id;
    private String nome;
    private String email;

    public Lead() {
    }

    public Lead(String nome, String email) {
        this.id = null;
        this.nome = nome;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lead lead = (Lead) o;
        return Objects.equals(getId(), lead.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
