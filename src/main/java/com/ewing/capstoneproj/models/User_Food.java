package com.ewing.capstoneproj.models;

import com.ewing.capstoneproj.UserFoodID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(UserFoodID.class)
@Table(name = "user_food")

public class User_Food implements Serializable {
    @Id
    @JoinColumn(name = "user_id")
    protected Integer user_id;

    @Id
    @JoinColumn(name = "food_id")
    protected Integer food_id;

    @ManyToOne(cascade = { CascadeType.ALL})
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    protected User user;

    @ManyToOne
    @JoinColumn(name = "food_id",insertable = false, updatable = false)
    protected Food food;

    @Column (name = "Calories")
    private int calories;

    @Column (name = "Servings")
    private int servings;

    @Id
    @Temporal(TemporalType.DATE)
    @Column (name = "date", insertable = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
