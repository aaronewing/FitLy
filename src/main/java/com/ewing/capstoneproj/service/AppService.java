package com.ewing.capstoneproj.service;

import com.ewing.capstoneproj.models.*;
import com.ewing.capstoneproj.repositories.FoodRepository;
import com.ewing.capstoneproj.repositories.UserFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppService {
    @Autowired
    UserService userserv;
    @Autowired
    FoodRepository repo;
    @Autowired
    UserFoodRepository userfoodrepo;

    public Date sortdate;

    public Food findByID(Integer id){
        Food food = repo.getReferenceById(id);
        return food;
    }

    public Food findByName(String name){
        Food food = repo.findFoodByName(name);
        return food;
    }

    public List<Food> GetAllFoods(){
        List<Food> allfoods = repo.findAll();
        return allfoods;
    }

    public Food savefood(Food food){
        Food existing = findByName(food.getName());
        if (existing != null){
            System.out.println("Food already exists!");
        } else{
            return repo.save(food);
        }
        return null;
    }

    public User_Food saveUserFood(User user, Food food, Date date, int calories, int servings){
        savefood(food);
        Food newfood = findByName(food.getName());
        System.out.println(newfood.getId());
        User_Food userFood = new User_Food();
        userFood.setUser(user);
        userFood.setFood_id(newfood.getId());
        userFood.setUser_id(user.getId());
        userFood.setFood(newfood);
        userFood.setDate(date);
        userFood.setCalories(calories);
        userFood.setServings(servings);
        return userfoodrepo.save(userFood);
    }

    public List<User_Food> ListUserFood(User user){
        Integer user_id = user.getId();
        List<User_Food> alluserfoods = userfoodrepo.findUserFoodById(user_id);
        return alluserfoods;
    }

    public List<User_Food> FindByDateId(User user,Date date){
        Integer user_id = user.getId();
        List<User_Food> alluserfoods = userfoodrepo.findUserFoodByDate(user_id,date);
        return alluserfoods;
    }

    public void DeleteByKeys(User user, Date date, Food food){
        Integer user_id = user.getId();
        Integer food_id = food.getId();
        userfoodrepo.deleteUserFoodByKeys(user_id,date,food_id);
    }
}
