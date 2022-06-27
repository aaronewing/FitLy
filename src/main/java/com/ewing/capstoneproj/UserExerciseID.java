package com.ewing.capstoneproj;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Data
@Embeddable
public class UserExerciseID implements Serializable { //key class for the user_exercise model/table
    protected Integer user_id;
    protected Integer exercise_id;
    protected Date date;
}
