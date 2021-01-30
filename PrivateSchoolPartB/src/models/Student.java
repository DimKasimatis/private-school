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
public class Student {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Double tuitionFee;

    public Student() {
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth, Double tuitionFee) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFee = tuitionFee;
    }

    public Double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(Double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student{firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", tuitionFee=").append(tuitionFee);
        sb.append('}');
        return sb.toString();
    }
    
    public int ChooseStudentId(Scanner sc) {
        Database db = new Database();
        int studentid = 0;
        db.selectStudents();
        System.out.println("Type the id of the student you want to assign:");
        studentid = sc.nextInt();
        return (studentid);
    }
    
}
