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
public class Student {

    Scanner sc;

    public Student(Scanner sc) {
        this.sc = sc;
    }

    public models.Student askData() {
        Command cmd = new Command();
        models.Student student = new models.Student();

        student.setFirstName(cmd.getField(sc, "Type the student's First Name:"));
        student.setLastName(cmd.getField(sc, "Type the student's Last Name:"));
        student.setDateOfBirth(cmd.getDateInput(sc, "Type student's date of birth. Use strictly the format 'yyyy-MM-dd'!:"));
        student.setTuitionFee(cmd.getDoubleField(sc, "Type the student's tuition fees:"));

        return (student);
    }
}
