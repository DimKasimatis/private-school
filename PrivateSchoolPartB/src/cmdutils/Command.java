/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmdutils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Dim.Kasimatis
 */
public class Command {
     private static final String DATE_FORMAT = "yyyy-MM-dd";
   
     
    public String getField(Scanner sc, String message) {
        System.out.println(message);
        return (sc.nextLine());
    }

    public double getDoubleField(Scanner sc, String message) {
        System.out.println(message);
        return (sc.nextDouble());
    }

     public LocalDate getDateInput(Scanner sc, String message) {
        System.out.println(message);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String line = sc.next();
        try {
            return LocalDate.parse(line, formatter);
        } catch (Exception e) {
            System.err.println("Invalid date value:: " + line);
        }
        return null;
    }
     
   


    public int getIntField(Scanner sc, String message) {
        System.out.println(message);
        return (sc.nextInt());
    }

    
}
