package com.example.projekt.Model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "task_start_date")
    private Date startDate;
    @Column(name = "task_end_date")
    private Date endDate;
    @Column(name = "task_title")
    private String title;
    @Column(name = "task_priority")
    @ColumnDefault("NORMAL")
    private Priority priority;
    @Column(name = "task_description")
    private String desciption;

    public Task(Date startDate, Date endDate, String title, Priority priority, String desciption) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.priority = priority;
        this.desciption = desciption;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                ", desciption='" + desciption + '\'' +
                '}';
    }
}
