/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschoolpartb;

import dbutils.Database;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author Dim.Kasimatis
 */
public class Main {

    private static Scanner sc;
    private static String input;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here+
        sc = new Scanner(System.in);
        int choice = chooseValuesInput();

        switch (choice) {
            case 0:
                System.out.println("Thank you! Exiting program.");
                System.exit(0);
            case 1:
                choosePrintMenu();
                break;
            case 2:
                chooseCreateMenu();
                break;
            case 3:
                chooseAssociateMenu();
                break;
            default:
                chooseOperation();
                break;
        }
    }

    public static int chooseValuesInput() throws ParseException {
        System.out.println("Choose operation:");
        System.out.println("Insert 0 to exit program");
        System.out.println("Insert 1 to Print a list");
        System.out.println("Insert 2 to Create an entity");
        System.out.println("Insert 3 Associate two entities (Trainer or Student or Assignment to course)");
        System.out.println("--------------------");

        input = sc.nextLine();

        if (input.equals("0")) {
            return 0;
        } else if (input.equals("1")) {
            return 1;
        } else if (input.equals("2")) {
            return 2;
        } else {
            return 3;
        }
    }

    public static void chooseOperation() throws ParseException {

        while (true) {
            System.out.println("Choose operation:");
            System.out.println("Insert 0 to exit program");
            System.out.println("Insert 1 to Print a list");
            System.out.println("Insert 2 to Create an entity");
            System.out.println("Insert 3 Associate two entities (Trainer or Student or Assignment to course)");
            System.out.println("--------------------");

            input = sc.nextLine().trim();

            switch (input) {
                case "0":
                    System.exit(0);
                case "1":
                    choosePrintMenu();
                    break;
                case "2":
                    chooseCreateMenu();
                    break;
                case "3":
                    chooseAssociateMenu();
                    break;
                default:
                    System.err.println("Wrong input. Please try again");
                    chooseOperation();
            }
            break;
        }
    }

    public static void choosePrintMenu() throws ParseException {
        Database db = new Database();
        System.out.println("Choose a printing option.");
        System.out.println("Insert 0 to exit program");
        System.out.println("Insert 1 to print all students");
        System.out.println("Insert 2 to print all trainers");
        System.out.println("Insert 3 to print all assignments");
        System.out.println("Insert 4 to print all courses");
        System.out.println("Insert 5 to print all students per course");
        System.out.println("Insert 6 to print all trainers per course");
        System.out.println("Insert 7 to print all assignments per course");
        System.out.println("Insert 8 to print all assignments per student");
        System.out.println("Insert 9 to print all students who are enrolled to more than one courses");
        System.out.println("-------------");

        input = sc.nextLine();

        switch (input) {
            case "0":
                System.out.println("Thank you! Exiting program.");
                System.exit(0);
            case "1":
                db.selectStudents();
                break;
            case "2":
                db.selectTrainers();
                break;
            case "3":
                db.selectAssignments();
                break;
            case "4":
                db.selectCourses();
                break;
            case "5":
                db.selectStudentsPerCourse();
                break;
            case "6":
                db.selectTrainersPerCourse();
                break;
            case "7":
                db.selectAssignmentsPerCourse();
                break;
            case "8":
                db.selectAssignmentsPerCoursePerStudent();
                break;
            case "9":
                db.selectMultipleEnrolledStudents();
                break;
            default:
                System.err.println("Wrong input. Please try again.");
                System.err.println("-------------");
        }
        System.out.println("-------------");
        insertAnyInput();

    }

    public static void chooseCreateMenu() throws ParseException {
        Database db = new Database();
        System.out.println("What would you like to create?");
        System.out.println("Insert 0 to exit the program");
        System.out.println("Insert 1 to create a course");
        System.out.println("Insert 2 to create a trainer");
        System.out.println("Insert 3 to create a student");
        System.out.println("Insert 4 to create an assignment");
        System.out.println("-----------");

        input = sc.nextLine().trim();

        switch (input) {
            case "0":
                System.out.println("Thank you! Exiting program");
                System.exit(0);
                break;
            case "1":
                cmdutils.Course cmdCourse = new cmdutils.Course(sc);
                db.insertCourse(cmdCourse.askData(), "CreateCourse") ;
                break;
            case "2":
                cmdutils.Trainer cmdTrainer = new cmdutils.Trainer(sc);
                db.insertTrainer(cmdTrainer.askData(), "CreateTrainer") ;
                break;
            case "3":
                cmdutils.Student cmdStudent = new cmdutils.Student(sc);
                db.insertStudent(cmdStudent.askData(), "CreateStudent") ;
                break;
            case "4":
                cmdutils.Assignment cmdAssignment = new cmdutils.Assignment(sc);
                db.insertAssignment(cmdAssignment.askData(), "CreateAssignment") ;
                break;

            default:
                System.err.println("Wrong input. Please try again");
                break;
        }

        insertAnyInput();
    }

    public static void chooseAssociateMenu() throws ParseException {
        Database db = new Database();
        System.out.println("What entity would you like to associate?");
        System.out.println("Insert 0 to exit program");
        System.out.println("Insert 1 to associate a trainer");
        System.out.println("Insert 2 to associate a student");
        System.out.println("Insert 3 to associate an assignment");
        System.out.println("-------------");

        input = sc.nextLine().trim();

        switch (input) {
            case "0":
                System.out.println("Thank you! Exiting program.");
                break;

            case "1":
                    db.assignTrainer("AssignTrainer");
                    break;
 
            case "2":
                    db.assignStudent("EnrollStudent");  
                break;
                
            case "3":
                db.assignAssignment("Assignments") ;
                break;
        }
        System.out.println("-------------");
        insertAnyInput();
    }

    public static void insertAnyInput() throws ParseException {
        System.out.println("Insert any key to continue");
        input = sc.nextLine();
        chooseOperation();
    }

}
