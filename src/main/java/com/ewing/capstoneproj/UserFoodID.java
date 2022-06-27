package com.ewing.capstoneproj;

import com.ewing.capstoneproj.models.Food;
import com.ewing.capstoneproj.models.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.Date;

@Data
@Embeddable
public class UserFoodID implements Serializable { //key class for the user_food model/table
    protected Integer user_id;
    protected Integer food_id;
    protected Date date;
}
