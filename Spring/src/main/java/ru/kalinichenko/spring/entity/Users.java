package ru.kalinichenko.spring.entity;

import jakarta.persistence.*;
import ru.kalinichenko.spring.entity.Logins;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String username;
    @Column (nullable = false)
    private String fio;

    @OneToMany
    @JoinColumn(name = "user_id")
    List<Logins> logins = new ArrayList<>();

    public Users() {}

    public Users(Long id, String username, String fio) {
        this.id = id;
        this.username = username;
        this.fio = fio;
    }

    public List<Logins> getLogins() {
        return logins;
    }

    public void setLogins(List<Logins> logins) {
        this.logins = logins;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

}
