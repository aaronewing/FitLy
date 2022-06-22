package com.ewing.capstoneproj.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "goals")
public class Goals implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id", insertable = false, updatable = false)
    protected Integer goal_id;


    @JoinColumn(name = "user_id")
    protected Integer user_id;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    protected User user;

    @Column(name = "goal_text")
    private String text;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "completed",nullable = false)
    Boolean completed;

    @Column(name = "completedate", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date completedate;

}
