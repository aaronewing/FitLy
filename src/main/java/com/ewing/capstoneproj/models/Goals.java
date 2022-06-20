package com.ewing.capstoneproj.models;

import com.ewing.capstoneproj.GoalsID;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@IdClass(GoalsID.class)
@Table(name = "goals")
public class Goals implements Serializable {
    @Id
    @JoinColumn(name = "user_id")
    protected Integer user_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id",insertable = false, updatable = false)
    protected Integer goal_id;

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
}
