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
public class AppService { //contains methods for handling food, exercises, user_food and user exercises
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


    public Food findByFoodId(Integer id){ //finds the food by id
        Food food = repo.getReferenceById(id);
        return food;
    }

    public Food findFoodByName(String name){ //finds the food by name
        Food food = repo.findFoodByName(name);
        return food;
    }

    public List<Food> GetAllFoods(){ //gets all the foods
        List<Food> allfoods = repo.findAll();
        return allfoods;
    }

    public Food savefood(Food food){ //saves the food
        Food existing = findFoodByName(food.getName()); //trys to find food by name
        if (existing != null){ //if the food exists dont add it because it will be a duplicate entry
            return null; //return nothing
        } else{ //if it doesnt exist then add the food
            String foodname = WordUtils.capitalizeFully(food.getName()); //capitalize each word of the food for view formatting
            food.setName(foodname); //sets the food name to the capitalized name
            return repo.save(food); //saves the food
        }
    }

    public User_Food saveUserFood(User user, Food food, Date date, int calories, int servings){
        savefood(food); //saves the food incase its a new food
        Food newfood = findFoodByName(food.getName()); //finds the saved food to get its id
        User_Food userFood = new User_Food(); //new user food object
        userFood.setUser(user); //sets the user object
        userFood.setFood_id(newfood.getId()); //sets the food id
        userFood.setUser_id(user.getId()); //sets the user id
        userFood.setFood(newfood); //sets the food object
        userFood.setDate(date);  //sets the date
        userFood.setCalories(calories); //sets the calories
        userFood.setServings(servings); //sets the servings
        return userfoodrepo.save(userFood); //saves the user_food
    }

    public List<User_Food> ListUserFood(User user){ //list all food by user
        List<User_Food> alluserfoods = userfoodrepo.findUserFoodById(user.getId());
        return alluserfoods;
    }

    public List<User_Food> FindByDateId(User user,Date date){ //finds all food by user and date
        List<User_Food> alluserfoods = userfoodrepo.findUserFoodByDate(user.getId(),date);
        return alluserfoods;
    }

    public void DeleteByKeys(User user, Date date, Food food){ //deletes a user food by keys
        userfoodrepo.deleteUserFoodByKeys(user.getId(),date,food.getId());
    }
    public Exercises findexeByID(Integer id){ //finds an exercise by id
        return exerepo.getReferenceById(id);
    }

    public Exercises findExeByName(String name){ //finds and exercise by name
        return exerepo.findExerciseByName(name);
    }

    public List<Exercises> GetAllExercises(){ //gets all exercises
        return exerepo.findAll();
    }

    public Exercises saveExercise(Exercises exercise){ //saves an exercise
        Exercises existing = findExeByName(exercise.getName()); // checks to see if the exercise exists
        if (existing != null){ //if it does return null
            return null;
        } else{ //if it doesnt save it to the database
            String exename = WordUtils.capitalizeFully(exercise.getName());
            exercise.setName(exename);
            return exerepo.save(exercise);
        }
    }

    public User_Exercises saveUserExercises(User user, Exercises exercises, Date date, Integer weight, int sets, int reps){
        saveExercise(exercises); //saves the exercise incase its not in database
        Exercises newexercise = findExeByName(exercises.getName()); //gets saved exercise
        User_Exercises userExercises = new User_Exercises(); //new exercise
        userExercises.setUser(user); //sets user object
        userExercises.setExercise_id(newexercise.getId()); //sets exercise id
        userExercises.setUser_id(user.getId()); //sets user id
        userExercises.setExercises(newexercise); //sets exercise object
        userExercises.setDate(date); //sets date
        userExercises.setWeight(weight); //sets weight
        userExercises.setSets(sets); //sets sets
        userExercises.setReps(reps); //sets reps
        return userexerepo.save(userExercises); //saves the user_exercises in database
    }
    public List<User_Exercises> ListUserExercise(User user){ //lists all user exercises
        return userexerepo.findUserExerciseById(user.getId());
    }

    public List<User_Exercises> FindByExeDateId(User user,Date date){ //lists all user exercises by date
        return userexerepo.findUserExerciseByDate(user.getId(),date);
    }

    public void exeDeleteByKeys(User user, Date date, Exercises exercises){ //deletes user exercises by keys
        userexerepo.deleteUserExerciseByKeys(user.getId(),date,exercises.getId());
    }

    public User_Exercises getUserExeByKeys(User user, Date date, Exercises findid){ //gets user exercises by keys
        if (findid.getId() == null){ //if the exercises is null it means it comes from error checking
            Exercises exercise_id = exerepo.findExerciseByName(findid.getName()); //find the exercise by the name
            if(exercise_id == null){ //if the exercise found by name is empty, return null (no error)
                return null;
            }
            User_Exercises exercises = userexerepo.getUserExerciseByKeys(user.getId(),date,exercise_id.getId()); //if exercise id is not null find a user exercise by those keys
            return exercises; //return the exercise (if its null it wont error, if it is not null it will error)
        }else{ //all other non error checking cases
            User_Exercises exercises = userexerepo.getUserExerciseByKeys(user.getId(),date,findid.getId());
            return exercises;
        }
    }

    public User_Exercises updateUserExercises(User_Exercises exercises){ //update user exercises
        Exercises exercise = findexeByID(exercises.getExercise_id()); //gets the exercies
        User user = userserv.findById(exercises.getUser_id()); //gets the user
        exercises.setExercises(exercise); //sets the new exercise
        exercises.setUser(user); //sets the new user
        return userexerepo.save(exercises); //saves the user exercise
    }

    public User_Food getUserFoodByKeys(User user, Date date, Food food){ //gets the user food by keys
        if (food.getId()==null){//if the food id is null it means it comes from error checking
            Food newfood = repo.findFoodByName(food.getName());
            if(newfood == null){ //if the new food found by name is empty, return null (no error)
                return null;
            }
            User_Food userfood = userfoodrepo.getUserFoodByKeys(user.getId(),date,newfood.getId()); //if food id is not null find a user food by those keys
            return userfood; //return the user food (if its null it wont error, if it is not null it will error)
        }else{ //all other non error checking cases
            User_Food userfood = userfoodrepo.getUserFoodByKeys(user.getId(),date, food.getId());
            return userfood;
        }
    }

    public User_Food updateUserFood(User_Food userfood){ //update user food
        Food food = findByFoodId(userfood.getFood_id()); //gets the food by the id
        User user = userserv.findById(userfood.getUser_id()); //gets the user by the id
        userfood.setFood(food); //sets the objects into the userfood object
        userfood.setUser(user);
        return userfoodrepo.save(userfood); //saves to database
    }


}
