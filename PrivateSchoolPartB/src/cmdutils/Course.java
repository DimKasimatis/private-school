/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmdutils;

import java.util.Scanner;

/**
 *
 * @author Dim.Kasimatis
 */
public class Course {
    Scanner sc;

    public Course(Scanner sc) {
        this.sc = sc;
    }

    public models.Course askData() {
        Command cmd = new Command();
        models.Course course = new models.Course();

        course.setTitle(cmd.getField(sc, "Type the course's title:"));
        course.setStream(cmd.getField(sc, "Type the course's stream:"));
        course.setType(cmd.getIntField(sc, "Type the course's type. Enter 1 for Full-Time or 2 for Part-Time:"));
        course.setStart_date(cmd.getDateInput(sc, "Type the course's start date. Use strictly the format 'yyyy-MM-dd'!:"));
        course.setEnd_date(cmd.getDateInput(sc, "Type the course's end date. Use strictly the format 'yyyy-MM-dd'!:"));
        
        return (course);
    }
}
