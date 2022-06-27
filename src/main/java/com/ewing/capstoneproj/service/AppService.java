package com.ewing.capstoneproj.service;

import com.ewing.capstoneproj.models.*;
import com.ewing.capstoneproj.repositories.ExerciseRepository;
import com.ewing.capstoneproj.repositories.FoodRepository;
import com.ewing.capstoneproj.repositories.UserExerciseRepository;
import com.ewing.capstoneproj.repositories.UserFoodRepository;
import org.apache.commons.lang3.text.WordUtils;
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

    @Autowired
    ExerciseRepository exerepo;

    @Autowired
    UserExerciseRepository userexerepo;

    public Date sortdate;

    public Food findByFoodId(Integer id){
        Food food = repo.getReferenceById(id);
        return food;
    }

    public Food findFoodByName(String name){
        Food food = repo.findFoodByName(name);
        return food;
    }

    public List<Food> GetAllFoods(){
        List<Food> allfoods = repo.findAll();
        return allfoods;
    }

    public Food savefood(Food food){
        Food existing = findFoodByName(food.getName());
        if (existing != null){
            return null;
        } else{
            String foodname = WordUtils.capitalizeFully(food.getName());
            food.setName(foodname);
            return repo.save(food);
        }
    }

    public User_Food saveUserFood(User user, Food food, Date date, int calories, int servings){
        savefood(food);
        Food newfood = findFoodByName(food.getName());
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
    public Exercises findexeByID(Integer id){
        Exercises exercise = exerepo.getReferenceById(id);
        return exercise;
    }

    public Exercises findExeByName(String name){
        Exercises exercise = exerepo.findExerciseByName(name);
        return exercise;
    }

    public List<Exercises> GetAllExercises(){
        List<Exercises> allexercises = exerepo.findAll();
        return allexercises;
    }

    public Exercises saveExercise(Exercises exercise){
        Exercises existing = findExeByName(exercise.getName());
        if (existing != null){
            return null;
        } else{
            String exename = WordUtils.capitalizeFully(exercise.getName());
            exercise.setName(exename);
            return exerepo.save(exercise);
        }
    }

    public User_Exercises saveUserExercises(User user, Exercises exercises, Date date, Integer weight, int sets, int reps){
        saveExercise(exercises);
        Exercises newexercise = findExeByName(exercises.getName());
        System.out.println(newexercise.getId());
        User_Exercises userExercises = new User_Exercises();
        userExercises.setUser(user);
        userExercises.setExercise_id(newexercise.getId());
        userExercises.setUser_id(user.getId());
        userExercises.setExercises(newexercise);
        userExercises.setDate(date);
        userExercises.setWeight(weight);
        userExercises.setSets(sets);
        userExercises.setReps(reps);
        return userexerepo.save(userExercises);
    }
    public List<User_Exercises> ListUserExercise(User user){
        Integer user_id = user.getId();
        List<User_Exercises> alluserexercises = userexerepo.findUserExerciseById(user_id);
        return alluserexercises;
    }

    public List<User_Exercises> FindByExeDateId(User user,Date date){
        Integer user_id = user.getId();
        List<User_Exercises> alluserexercises = userexerepo.findUserExerciseByDate(user_id,date);
        return alluserexercises;
    }

    public void exeDeleteByKeys(User user, Date date, Exercises exercises){
        Integer user_id = user.getId();
        Integer exercises_id = exercises.getId();
        userexerepo.deleteUserExerciseByKeys(user_id,date,exercises_id);
    }

    public User_Exercises getUserExeByKeys(User user, Date date, Exercises findid){
        if (findid.getId() == null){
            Exercises exercise_id = exerepo.findExerciseByName(findid.getName());
            if(exercise_id == null){
                return null;
            }
            User_Exercises exercises = userexerepo.getUserExerciseByKeys(user.getId(),date,exercise_id.getId());
            return exercises;
        }else{
            User_Exercises exercises = userexerepo.getUserExerciseByKeys(user.getId(),date,findid.getId());
            return exercises;
        }
    }

    public User_Exercises updateUserExercises(User_Exercises exercises){
        Exercises exercise = findexeByID(exercises.getExercise_id());
        User user = userserv.findById(exercises.getUser_id());
        exercises.setExercises(exercise);
        exercises.setUser(user);
        return userexerepo.save(exercises);
    }

    public User_Food getUserFoodByKeys(User user, Date date, Food food){
        if (food.getId()==null){
            Food newfood = repo.findFoodByName(food.getName());
            User_Food userfood = userfoodrepo.getUserFoodByKeys(user.getId(),date,newfood.getId());
            return userfood;
        }else{
            User_Food userfood = userfoodrepo.getUserFoodByKeys(user.getId(),date, food.getId());
            return userfood;
        }
    }

    public User_Food updateUserFood(User_Food userfood){
        Food food = findByFoodId(userfood.getFood_id());
        User user = userserv.findById(userfood.getUser_id());
        userfood.setFood(food);
        userfood.setUser(user);
        return userfoodrepo.save(userfood);
    }


}
