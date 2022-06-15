package com.ewing.capstoneproj.models;

import com.ewing.capstoneproj.UserExerciseID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(UserExerciseID.class)
@Table(name = "user_exercises")
public class User_Exercises implements Serializable {

    @Id
    @JoinColumn(name = "user_id")
    protected Integer user_id;

    @Id
    @JoinColumn(name = "exercise_id")
    protected Integer exercise_id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    protected User user;


    @ManyToOne
    @JoinColumn(name = "exercise_id",insertable = false,updatable = false)
    private Exercises exercises;

    @Column(name = "weight")
    private Integer weight;

    @Column (name = "sets")
    private int sets;

    @Column (name = "reps")
    private int reps;

    @Id
    @Temporal(TemporalType.DATE)
    @Column (name = "date",insertable = false,updatable = false)
    private Date date;
}
