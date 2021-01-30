/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dbutils.Database;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Dim.Kasimatis
 */
public class Course {
    private String title;
    private String stream;
    private int type;
    private LocalDate start_date;
    private LocalDate end_date;
    

    public Course() {
    }

    public Course(String title, String stream, int type, LocalDate start_date, LocalDate end_date) {
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course{title=").append(title);
        sb.append(", stream=").append(stream);
        sb.append(", type=").append(type);
        sb.append(", start_date=").append(start_date);
        sb.append(", end_date=").append(end_date);
        sb.append('}');
        return sb.toString();
    }
    
    public int ChooseCourseId(Scanner sc) {
        Database db = new Database();
        int courseid = 0;
        db.selectCourses();
        System.out.println("Type the id of the course:");
        courseid = sc.nextInt();
        return (courseid);
    }
    
}
