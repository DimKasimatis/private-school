/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Assignment;
import models.Course;
import models.Student;
import models.Trainer;

/**
 *
 * @author Dim.Kasimatis
 */
public class Database {

    public String server;
    public String username;
    public String password;
    public String database;

    Connection con;
    Statement statement;
    PreparedStatement prStatement;
    ResultSet rs;
    ResultSetMetaData rsmd;

    public Database() {

        username = "root";
        password = "root";
        database = "privateschool";
        server = "jdbc:mysql://localhost/" + database + "?useSSL=false&serverTimeZone=Europe/Athens";
        try {
            con = DriverManager.getConnection(server, username, password);
            System.out.println("Connected!");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection is not established");
        }
    }

    public void selectStudents() {
        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM students");
            rsmd = rs.getMetaData();
            System.out.println("---List of all Students---");
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
    }

    public void selectTrainers() {
        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM trainers");
            rsmd = rs.getMetaData();
            System.out.println("---List of all Trainers---");
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }

    }

    public void selectAssignments() {
        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM assignments");
            rsmd = rs.getMetaData();
            System.out.println("---List of all Assignments---");
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }

    }

    public void selectCourses() {
        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT \n"
                    + "    `courses`.`id`,\n"
                    + "    `courses`.`title`,\n"
                    + "    `courses`.`stream`,\n"
                    + "    `types`.`type`,\n"
                    + "    `courses`.`start_date`,\n"
                    + "    `courses`.`end_date`\n"
                    + "FROM\n"
                    + "    `courses`\n"
                    + "        INNER JOIN\n"
                    + "    `course_types` ON `courses`.`id` = `course_types`.`course_id`\n"
                    + "        INNER JOIN\n"
                    + "    `types` ON `types`.`id` = `course_types`.`type_id`");
            rsmd = rs.getMetaData();
            System.out.println("---List of all Courses---");
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }

    }

    public void selectStudentsPerCourse() {
        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT \n"
                    + "    `courses`.`title`,\n"
                    + "    `courses`.`stream`,\n"
                    + "    `types`.`type`,\n"
                    + "    `students`.`first_name`,\n"
                    + "    `students`.`last_name`\n"
                    + "FROM\n"
                    + "    `students`\n"
                    + "        INNER JOIN\n"
                    + "    (`courses`\n"
                    + "    INNER JOIN `courses_students` ON (`courses`.`id` = `courses_students`.`course_id`)\n"
                    + "        AND (`courses`.`id` = `courses_students`.`course_id`)) ON `students`.`id` = `courses_students`.`student_id`\n"
                    + "        INNER JOIN\n"
                    + "    `course_types` ON `courses`.`id` = `course_types`.`course_id`\n"
                    + "        INNER JOIN\n"
                    + "    `types` ON `types`.`id` = `course_types`.`type_id`\n"
                    + "    ORDER BY `students`.`last_name`");
            rsmd = rs.getMetaData();
            System.out.println("---List of all Students Per Course---");
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }

    }

    public void selectTrainersPerCourse() {
        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT \n"
                    + "    `courses`.`title`,\n"
                    + "    `courses`.`stream`,\n"
                    + "    `types`.`type`,\n"
                    + "    `trainers`.`first_name`,\n"
                    + "    `trainers`.`last_name`\n"
                    + "FROM\n"
                    + "    `trainers`\n"
                    + "        INNER JOIN\n"
                    + "    (`courses`\n"
                    + "    INNER JOIN `courses_trainers` ON (`courses`.`id` = `courses_trainers`.`course_id`)\n"
                    + "        AND (`courses`.`id` = `courses_trainers`.`course_id`)) ON `trainers`.`id` = `courses_trainers`.`trainer_id`\n"
                    + "        INNER JOIN\n"
                    + "    `course_types` ON `courses`.`id` = `course_types`.`course_id`\n"
                    + "        INNER JOIN\n"
                    + "    `types` ON `types`.`id` = `course_types`.`type_id`\n"
                    + "    ORDER BY `trainers`.`last_name`");
            rsmd = rs.getMetaData();
            System.out.println("---List of all Trainers Per Course---");
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }

    }

    public void selectAssignmentsPerCourse() {
        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT \n"
                    + "    `courses`.`title`,\n"
                    + "    `courses`.`stream`,\n"
                    + "    `types`.`type`,\n"
                    + "    `assignments`.`title`,\n"
                    + "    `assignments`.`description`,\n"
                    + "    `assignments`.`sub_date_time`\n"
                    + "FROM\n"
                    + "    `assignments`\n"
                    + "        INNER JOIN\n"
                    + "    `courses` ON `courses`.`id` = `assignments`.`course_id`\n"
                    + "        INNER JOIN\n"
                    + "    `course_types` ON `courses`.`id` = `course_types`.`course_id`\n"
                    + "        INNER JOIN\n"
                    + "    `types` ON `types`.`id` = `course_types`.`type_id`\n"
                    + "    ORDER BY   `courses`.`title`,\n"
                    + "    `courses`.`stream`");
            rsmd = rs.getMetaData();
            System.out.println("---List of all Assignments Per Course---");
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }

    }

    public void selectAssignmentsPerCoursePerStudent() {
        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT \n"
                    + "    `courses`.`title`,\n"
                    + "    `courses`.`stream`,\n"
                    + "    `types`.`type`,\n"
                    + "    `students`.`first_name`,\n"
                    + "    `students`.`last_name`,\n"
                    + "    `assignments`.`title`,\n"
                    + "    `assignments`.`description`,\n"
                    + "    `assignments`.`sub_date_time`,\n"
                    + "    `student_grades`.`oral_mark`,\n"
                    + "    `student_grades`.`total_mark`\n"
                    + "FROM\n"
                    + "    `assignments`\n"
                    + "        INNER JOIN\n"
                    + "    `courses` ON `courses`.`id` = `assignments`.`course_id`\n"
                    + "        INNER JOIN\n"
                    + "    `course_types` ON `courses`.`id` = `course_types`.`course_id`\n"
                    + "        INNER JOIN\n"
                    + "    `types` ON `types`.`id` = `course_types`.`type_id`\n"
                    + "		INNER JOIN\n"
                    + "	`student_grades` ON `student_grades`.`assignment_id` = `assignments`.`id`\n"
                    + "		INNER JOIN\n"
                    + "	`students` ON `students`.`id` = `student_grades`.`student_id`");
            rsmd = rs.getMetaData();
            System.out.println("---List of all Assignments Per Course Per Student---");
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }

    }

    public void selectMultipleEnrolledStudents() {
        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT \n"
                    + "    `students`.`first_name`,\n"
                    + "    `students`.`last_name`,\n"
                    + "    COUNT(*) AS `courses_enrolled`\n"
                    + "FROM\n"
                    + "    `students`\n"
                    + "        INNER JOIN\n"
                    + "    `courses_students` ON `students`.`id` = `courses_students`.`student_id`\n"
                    + "        INNER JOIN\n"
                    + "    `courses` ON `courses`.`id` = `courses_students`.`course_id`\n"
                    + "        INNER JOIN\n"
                    + "    `course_types` ON `courses`.`id` = `course_types`.`course_id`\n"
                    + "        INNER JOIN\n"
                    + "    `types` ON `types`.`id` = `course_types`.`type_id`\n"
                    + "    GROUP BY `students`.`last_name`\n"
                    + "    HAVING COUNT(*) >= 2");
            rsmd = rs.getMetaData();
            System.out.println("---List of all Students enrolled in more than one Course---");
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }

    }
    
    public int insertStudent(Student s, String procedure) {
        int result = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("CALL ");
        sb.append("`"); sb.append(procedure); sb.append("`");
        sb.append("(");
        sb.append("\""); sb.append(s.getFirstName()); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(s.getLastName()); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(s.getDateOfBirth()); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(s.getTuitionFee()); sb.append("\"");
        sb.append(")");
        try {
            statement = con.createStatement();
            result = statement.executeUpdate(sb.toString());
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return(result);
    }
    
    public int insertCourse(Course c, String procedure) {
        int result = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("CALL ");
        sb.append("`"); sb.append(procedure); sb.append("`");
        sb.append("(");
        sb.append("\""); sb.append(c.getTitle()); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(c.getStream()); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(c.getType()); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(c.getStart_date()); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(c.getEnd_date()); sb.append("\"");
        sb.append(")");
        try {
            statement = con.createStatement();
            result = statement.executeUpdate(sb.toString());
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return(result);
    }
    
    public int insertTrainer(Trainer t, String procedure) {
        int result = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("CALL ");
        sb.append("`"); sb.append(procedure); sb.append("`");
        sb.append("(");
        sb.append("\""); sb.append(t.getFirstName()); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(t.getLastName()); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(t.getSubject()); sb.append("\"");
        sb.append(")");
        try {
            statement = con.createStatement();
            result = statement.executeUpdate(sb.toString());

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return(result);
    }
        
    public int insertAssignment(Assignment a, String procedure) {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("CALL ");
        sb.append("`"); sb.append(procedure); sb.append("`");
        sb.append("(");
        sb.append("\""); sb.append(a.ChooseCourseId(sc)); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(a.getTitle()); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(a.getDecription()); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(a.getSubDate()); sb.append("\"");
        sb.append(")");
        try {
            statement = con.createStatement();
            result = statement.executeUpdate(sb.toString());

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return(result);
    }

    public int ChooseCourseId(Scanner sc) {
        int courseid = 0;
        selectCourses();
        System.out.println("Type the id of the course you want your assignment to belong to:");
        courseid = sc.nextInt();
        return(courseid);
    }
    
    public int assignTrainer(String procedure) {
        Course c = new Course();
        Trainer t = new Trainer();
        Scanner sc = new Scanner(System.in);
        int result = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("CALL ");
        sb.append("`"); sb.append(procedure); sb.append("`");
        sb.append("(");
        sb.append("\""); sb.append(c.ChooseCourseId(sc)); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(t.ChooseTrainerId(sc)); sb.append("\"");
        sb.append(")");
        try {
            statement = con.createStatement();
            result = statement.executeUpdate(sb.toString());

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return(result);
    }
    
    public int assignStudent(String procedure) {
        Course c = new Course();
        Student s = new Student();
        Scanner sc = new Scanner(System.in);
        int result = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("CALL ");
        sb.append("`"); sb.append(procedure); sb.append("`");
        sb.append("(");
        sb.append("\""); sb.append(c.ChooseCourseId(sc)); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(s.ChooseStudentId(sc)); sb.append("\"");
        sb.append(")");
        try {
            statement = con.createStatement();
            result = statement.executeUpdate(sb.toString());

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return(result);
    }
    
    public int assignAssignment(String tableName) {
    int result = 0;
    Course c = new Course();
    Assignment a = new Assignment();
    Scanner sc = new Scanner(System.in);
    
    StringBuilder sb = new StringBuilder();
    sb.append("UPDATE ");
    sb.append("`"); sb.append(tableName); sb.append("`");
    sb.append(" SET ");
    sb.append("course_id = "); 
    sb.append("\""); sb.append(c.ChooseCourseId(sc)); sb.append("\"");
    sb.append(" WHERE ");
    sb.append("id = "); 
    sb.append("\""); sb.append(a.ChooseAssignId(sc)); sb.append("\"");
        
    try {
        statement = con.createStatement();
        result = statement.executeUpdate(sb.toString());

    } catch (SQLException ex) {
        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                prStatement.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
    return(result);
    }
}
