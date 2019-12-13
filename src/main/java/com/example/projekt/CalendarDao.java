package com.example.projekt;

import com.example.projekt.Model.Priority;
import com.example.projekt.Model.Task;
import com.example.projekt.Repo.TaskRepo;
import com.example.projekt.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CalendarDao {

    private TaskRepo taskRepo;
    private UserRepo userRepo;

    @Autowired
    public CalendarDao(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    public boolean saveTask(String startDate, String endDate, String title, Priority priority, String description){
        Task newTask = new Task(startDate, endDate, title, priority, description);
        taskRepo.save(newTask);
        if (taskRepo.existsById(newTask.getId())){
            return true;
        }
        else return false;
    }

    public List<Task> findAllTasks(){
        return taskRepo.findAll();
    }

    public void updateTask(Long id, String startDate, String endDate, String title, Priority priority, String description){
        Task taskToChange = new Task();
        taskToChange.setStartDate(startDate);
        taskToChange.setEndDate(endDate);
        taskToChange.setTitle(title);
        taskToChange.setPriority(priority);
        taskToChange.setDesciption(description);

        Task taskToUpdate = taskRepo.findById(id).get();
        taskToUpdate.merge(taskToChange);
    }

    public void deleteTask(Long id){
        taskRepo.deleteById(id);
    }

}
