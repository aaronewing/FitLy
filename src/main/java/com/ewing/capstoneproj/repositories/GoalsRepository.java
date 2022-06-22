package com.ewing.capstoneproj.repositories;

import com.ewing.capstoneproj.models.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, Integer> {
    @Query("select u from Goals u where u.user_id=?1 and u.completed = false ")
    List<Goals> GetIncompleteGoals(Integer user_id);

    @Query("select u from Goals u where u.user_id=?1 and u.completed = true")
    List<Goals> GetCompletedGoals(Integer user_id);

    @Query("select u from Goals u where u.goal_id=?1")
    Goals getGoalsByGoal_id(Integer goal_id);

    @Query("SELECT u from Goals u where u.user_id=?1 and u.date=?2 and u.completed = false")
    List<Goals> getincompleteGoalsbyDate(Integer user_id, Date date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Goals where goal_id =?1")
    void deleteGoalsByGoal_id(Integer goal_id);
}
