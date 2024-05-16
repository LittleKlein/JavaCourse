package ru.kalinichenko.spring.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;

@Entity
@Table(name = "logins")
public class Logins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private Date access_date;

    @Column (nullable = false)
    private Long user_id;
    @Column (nullable = false)
    private String application;

    public Logins() {}

    public Logins(Long id, Date access_date, Long user_id, String application) {
        this.id = id;
        this.access_date = access_date;
        this.user_id = user_id;
        this.application = application;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAccess_date() {
        return access_date;
    }

    public void setAccess_date(Date access_date) {
        this.access_date = access_date;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}
