package com.ewing.capstoneproj.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_excercises")
public class User_Excercises implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "excercise_id")
    private Excercises excercises;

    @Column (name = "sets")
    private int sets;

    @Column (name = "reps")
    private int reps;

    @Temporal(TemporalType.DATE)
    @Column (name = "date")
    private Date date;
}
