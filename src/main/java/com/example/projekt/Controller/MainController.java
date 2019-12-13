package com.example.projekt.Controller;


import com.example.projekt.CalendarDao;
import com.example.projekt.Model.HtmlInput;
import com.example.projekt.Model.Priority;
import com.example.projekt.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class MainController {

    private CalendarDao calendarDao;

    List<Task> tasks;

    @Autowired
    public MainController(CalendarDao calendarDao) {
        this.calendarDao = calendarDao;

        calendarDao.saveTask("2019/11/11/10/10", "2019/11/11/11/10", "title1", Priority.NORMALP, "description1");
        calendarDao.saveTask("2019/12/12/10/10", "2019/12/13/11/10", "title2", Priority.LOWP, "description2");
        calendarDao.saveTask("2019/12/13/10/10", "2019/12/14/11/10", "title3", Priority.NORMALP, "description3");
        calendarDao.saveTask("2019/12/14/10/10", "2019/12/15/11/10", "title4", Priority.HIGHP, "description4");
    }

    @GetMapping(path = "/tasks")
    public String getAllTasks(Model model){

        tasks = calendarDao.findAllTasks();

        model.addAttribute("tasks", tasks);
        model.addAttribute("newTask", new Task());
        model.addAttribute("taskToUpdate", new Task());
        model.addAttribute("htmlInpit", new HtmlInput());

        return "template";
    }

    @PostMapping(path = "/add-task")
    public String addNewTask(@ModelAttribute Task task){

        calendarDao.saveTask(task.getStartDate(), task.getEndDate(), task.getTitle(), task.getPriority(), task.getDesciption());

        return "redirect:/tasks";
    }

    @PostMapping(path = "/delete-task")
    public String deleteTask(@ModelAttribute HtmlInput htmlInput){

        calendarDao.deleteTask(htmlInput.getLongType());

        return "redirect:/tasks";
    }

    @PostMapping(path = "/update-task")
    public String updateTask(@ModelAttribute Task task){
        calendarDao.updateTask(task.getId(), task.getStartDate(), task.getEndDate(), task.getTitle(), task.getPriority(), task.getDesciption());
        return "redirect:/tasks";
    }

}


