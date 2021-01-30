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
public class Assignment {

    private String title;
    private String decription;
    private LocalDate subDate;

    public Assignment() {
    }

    public Assignment(String title, String decription, LocalDate subDate) {
        this.title = title;
        this.decription = decription;
        this.subDate = subDate;
    }

    public LocalDate getSubDate() {
        return subDate;
    }

    public void setSubDate(LocalDate subDate) {
        this.subDate = subDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Assignment{title=").append(title);
        sb.append(", decription=").append(decription);
        sb.append(", subDate=").append(subDate);
        sb.append('}');
        return sb.toString();
    }

    public int ChooseAssignId(Scanner sc) {
        Database db = new Database();
        int assignid = 0;
        db.selectAssignments();
        System.out.println("Type the id of the assignment you want to assign:");
        assignid = sc.nextInt();
        return (assignid);
    }

    public int ChooseCourseId(Scanner sc) {
        Database db = new Database();
        int courseid = 0;
        db.selectCourses();
        System.out.println("Type the id of the course you want your assignment to belong to:");
        courseid = sc.nextInt();
        return (courseid);
    }

}
