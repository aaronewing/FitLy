package com.ewing.capstoneproj;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
@Embeddable
public class GoalsID implements Serializable {
    protected Integer user_id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer goal_id;
}
