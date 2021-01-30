/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dbutils.Database;
import java.util.Scanner;

/**
 *
 * @author Dim.Kasimatis
 */
public class Trainer {
    private String firstName;
    private String lastName;
    private String subject;

    public Trainer() {
    }

    public Trainer(String firstName, String lastName, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Trainer{firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", subject=").append(subject);
        sb.append('}');
        return sb.toString();
    }
    
    public int ChooseTrainerId(Scanner sc) {
        Database db = new Database();
        int trainerid = 0;
        db.selectTrainers();
        System.out.println("Type the id of the trainer you want to assign:");
        trainerid = sc.nextInt();
        return (trainerid);
    }
    
    
}

