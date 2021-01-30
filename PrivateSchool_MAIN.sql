/* Database and table creation begins */
CREATE DATABASE `privateschool`;

USE `privateschool`;

CREATE TABLE IF NOT EXISTS `courses` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(20) NOT NULL,
    `stream` VARCHAR(20) NOT NULL,
    `start_date` DATETIME,
    `end_date` DATETIME
);
    
CREATE TABLE IF NOT EXISTS `types` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `type` VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS `course_types` (
    `course_id` INT NOT NULL,
    `type_id` INT NOT NULL,
    PRIMARY KEY (`course_id` , `type_id`),
    CONSTRAINT `fk_course_types_course_id__courses_id` FOREIGN KEY (`course_id`)
        REFERENCES `courses` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_course_types_type_id__types_id` FOREIGN KEY (`type_id`)
        REFERENCES `types` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS `trainers` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(30),
    `last_name` VARCHAR(30),
    `subject` VARCHAR(30)
);

    
CREATE TABLE IF NOT EXISTS `students` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(30),
    `last_name` VARCHAR(30),
    `date_of_birth` DATE,
    `tuition_fees` DOUBLE
);
    
    
CREATE TABLE IF NOT EXISTS `assignments` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `course_id` INT NOT NULL,
    `title` VARCHAR(30),
    `description` VARCHAR(255),
    `sub_date_time` DATETIME,
    CONSTRAINT `fk_assignments_course_id__courses_id` FOREIGN KEY (`course_id`)
        REFERENCES `courses` (`id`)
);

CREATE TABLE IF NOT EXISTS `student_grades` (
    `student_id` INT NOT NULL,
    `assignment_id` INT NOT NULL,
    `oral_mark` TINYINT DEFAULT 0,
    `total_mark` TINYINT DEFAULT 0,
    PRIMARY KEY (`student_id` , `assignment_id`),
    CONSTRAINT `fk_student_grades_student_id__students_id` FOREIGN KEY (`student_id`)
        REFERENCES `students` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_student_grades_assignment_id__assignment_id` FOREIGN KEY (`assignment_id`)
        REFERENCES `assignments` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `courses_trainers` (
    `course_id` INT NOT NULL,
    `trainer_id` INT NOT NULL,
    PRIMARY KEY (`course_id` , `trainer_id`),
    CONSTRAINT `fk_courses_trainers_course_id__courses_id` FOREIGN KEY (`course_id`)
        REFERENCES `courses` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_courses_trainers_trainer_id__trainer_id` FOREIGN KEY (`trainer_id`)
        REFERENCES `trainers` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
);
    
CREATE TABLE IF NOT EXISTS `courses_students` ( 
    `course_id` INT NOT NULL,
    `student_id` INT NOT NULL,
    PRIMARY KEY (`course_id` , `student_id`),
    CONSTRAINT `fk_courses_students_course_id__courses_id` FOREIGN KEY (`course_id`)
        REFERENCES `courses` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_courses_students_student_id__student_id` FOREIGN KEY (`student_id`)
        REFERENCES `students` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
); 
/* Database and table creation begins */

/* Trigger creation begins */
DELIMITER //
CREATE TRIGGER `oral_mark_trigger`
  BEFORE INSERT
  ON `student_grades`
  FOR EACH ROW
BEGIN
  IF NEW.`oral_mark`<0 OR NEW.`oral_mark`>100 THEN
    CALL `Error: Wrong values for oral mark`;
  END IF;
END;//
DELIMITER ;

DELIMITER //
CREATE TRIGGER `total_mark_trigger`
  BEFORE INSERT
  ON `student_grades`
  FOR EACH ROW
BEGIN
  IF NEW.`total_mark`<0 OR NEW.`total_mark`>100 THEN
    CALL `Error: Wrong values for total mark`;
  END IF;
END;//
DELIMITER ;
/* Trigger creation ends */

/* Data creation begins */

/* Insert the 2 choices for course_types */
INSERT INTO `types` (`type`)
VALUES ('Full-Time'),
	('Part-Time');


/* Stored Procedure to create new courses */
DELIMITER //

CREATE PROCEDURE `CreateCourse`(
    `ctitle` VARCHAR(20), 
    `cstream` VARCHAR(20),
    `ctype` INT,
    `cstart_date` DATETIME,
    `cend_date` DATETIME
)

BEGIN
    DECLARE `l_course_id` INT DEFAULT 0;
    
    START TRANSACTION;
    -- Insert course data
    INSERT INTO `courses`(`title`, `stream`, `start_date`, `end_date`)
    VALUES(`ctitle`, `cstream`, `cstart_date`, `cend_date`);
    
    -- get course id
    SET `l_course_id` = LAST_INSERT_ID();
    
    -- insert type for the course
    IF `l_course_id` > 0 THEN
	INSERT INTO `course_types`(`course_id`, `type_id`)
        VALUES(`l_course_id`,`ctype`);
        -- commit
        COMMIT;
     ELSE
	ROLLBACK;
    END IF;
END//
DELIMITER ;

/* Sample courses created */
CALL `CreateCourse` ('CB13', 'Java', '1' , '2020-10-19' , '2021-01-20');
CALL `CreateCourse` ('CB13', 'Java', '2', '2020-10-25', '2021-06-20');
CALL `CreateCourse` ('CB13', 'C#', '1', '2020-11-10', '2021-02-15');
CALL `CreateCourse` ('CB14', 'C#', '2', '2020-12-11', '2021-07-15');
CALL `CreateCourse` ('CB14', 'Javascript', '1', '2020-12-17', '2021-04-17');

/* Stored Procedure to create new assignments */
DELIMITER //

CREATE PROCEDURE `CreateAssignment`(
    `courseid` INT, 
    `atitle` VARCHAR(30),
    `adescr` VARCHAR(255),
    `asub_date` DATETIME
)

BEGIN
 -- Catch exceptions and warnings to rollback
 DECLARE exit handler for SQLEXCEPTION
        BEGIN
        ROLLBACK;
    END;
    DECLARE exit handler for SQLWARNING
        BEGIN
    ROLLBACK;
    END;    
    START TRANSACTION;
    -- Insert assignment data
    INSERT INTO `assignments`(`course_id`, `title`, `description`, `sub_date_time`)
    VALUES(`courseid`, `atitle`, `adescr`, `asub_date`);
    
	-- commit
	COMMIT;
     
END//
DELIMITER ;

/* Sample assignments created */
CALL `CreateAssignment` ('1','Java FT Assignment 1','The first assignment for the full time java course','2020-11-10');
CALL `CreateAssignment` ('1','Java FT Assignment 2','The second assignment for the full time java course','2020-12-10');
CALL `CreateAssignment` ('2','Java PT Assignment 1','The first assignment for the part time java course','2020-12-10');
CALL `CreateAssignment` ('2','Java PT Assignment 2','The second assignment for the part time java course','2021-01-10');
CALL `CreateAssignment` ('3','C# FT Assignment 1','The first assignment for the full time c# course','2020-12-07');
CALL `CreateAssignment` ('3','C# FT Assignment 2','The second assignment for the full time c# course','2021-01-07');
CALL `CreateAssignment` ('4','C# PT Assignment 1','The first assignment for the part time c# course','2020-12-20');
CALL `CreateAssignment` ('4','C# PT Assignment 2','The second assignment for the part time c# course','2021-01-20');
CALL `CreateAssignment` ('5','JS FT Assignment 1','The first assignment for the full time javascript course','2020-12-19');
CALL `CreateAssignment` ('5','JS FT Assignment 2','The second assignment for the full time javascript course','2021-01-19');

/* Stored Procedure to create new trainers */
DELIMITER //



CREATE PROCEDURE `CreateTrainer`(
    `fname` VARCHAR(30), 
    `lname` VARCHAR(30),
    `subject` VARCHAR(30)
)

BEGIN
 -- Catch exceptions and warnings to rollback
 DECLARE exit handler for SQLEXCEPTION
        BEGIN
        ROLLBACK;
    END;
    DECLARE exit handler for SQLWARNING
        BEGIN
    ROLLBACK;
    END;    
    START TRANSACTION;
    -- Insert trainer data
    INSERT INTO `trainers`(`first_name`, `last_name`, `subject`)
    VALUES(`fname`, `lname`, `subject`);
    
	-- commit
	COMMIT;
     
END//
DELIMITER ;

/* Sample trainers created */
CALL `CreateTrainer` ('John', 'Doe', 'Java');
CALL `CreateTrainer` ('Bill', 'Billakos', 'Java');
CALL `CreateTrainer` ('Maria', 'Papadopoulou', 'C#');
CALL `CreateTrainer` ('John', 'Johnakos', 'C#');
CALL `CreateTrainer` ('George', 'Georgiou', 'Javascript');

/* Stored Procedure to assign a trainer to a course */

DELIMITER //

CREATE PROCEDURE `AssignTrainer`(
    `courseid` INT, 
    `trainerid` INT
)

BEGIN
 -- Catch exceptions and warnings to rollback
 DECLARE exit handler for SQLEXCEPTION
        BEGIN
        ROLLBACK;
    END;
    DECLARE exit handler for SQLWARNING
        BEGIN
    ROLLBACK;
    END;    
    START TRANSACTION;
    -- Insert trainer and course id to link table
    INSERT INTO `courses_trainers`(`course_id`, `trainer_id`)
    VALUES(`courseid`, `trainerid`);
    
	-- commit
	COMMIT;
     
END//
DELIMITER ;

/* Sample trainers assigned to courses */
CALL `AssignTrainer` ('1', '1');
CALL `AssignTrainer` ('2', '2');
CALL `AssignTrainer` ('3', '3');
CALL `AssignTrainer` ('4', '4');
CALL `AssignTrainer` ('5', '5');

/* Stored Procedure to create new students */
DELIMITER //

CREATE PROCEDURE `CreateStudent`(
    `fname` VARCHAR(30), 
    `lname` VARCHAR(30),
    `birthday` DATE,
    `fees` DOUBLE
)

BEGIN
 -- Catch exceptions and warnings to rollback
 DECLARE exit handler for SQLEXCEPTION
        BEGIN
        ROLLBACK;
    END;
    DECLARE exit handler for SQLWARNING
        BEGIN
    ROLLBACK;
    END;    
    START TRANSACTION;
    -- Insert student data
    INSERT INTO `students`(`first_name`, `last_name`, `date_of_birth`, `tuition_fees`)
    VALUES(`fname`, `lname`, `birthday`, `fees`);
    
	-- commit
	COMMIT;
     
END//
DELIMITER ;

/* Sample students created */
CALL `CreateStudent` ('Pavlos', 'Papadopoulos', '1990-10-09', '5000.50');
CALL `CreateStudent` ('Spyros', 'Spyropoulos', '1991-11-10', '6500.40');    
CALL `CreateStudent` ('Matina', 'Tinopoulou', '1989-07-10', '3750.30');
CALL `CreateStudent` ('Marios', 'Marianopoulos', '1990-05-04', '7850.10');
CALL `CreateStudent` ('Dimitris', 'Dimitriou', '1992-09-10', '9800');

/* Stored Procedure to enroll a student to a course */
DELIMITER //

CREATE PROCEDURE `EnrollStudent`(
    `courseid` INT, 
    `studentid` INT
)

BEGIN
 -- Catch exceptions and warnings to rollback
  DECLARE exit handler for SQLEXCEPTION
         BEGIN
         ROLLBACK;
     END;
     DECLARE exit handler for SQLWARNING
         BEGIN
     ROLLBACK;
     END;    
     START TRANSACTION;
    -- Insert student and course id to link table
    INSERT INTO `courses_students`(`course_id`, `student_id`)
    VALUES(`courseid`, `studentid`);
    
    INSERT INTO `student_grades`(`student_id`, `assignment_id`)
		SELECT `students`.`id`, `assignments`.`id`
		FROM `students` 
		INNER JOIN `courses_students` 
		ON `courses_students`.`student_id` = `students`.`id` 
		INNER JOIN `assignments` 
		ON `assignments`.`course_id` = `courses_students`.`course_id`
        WHERE `student_id` = `studentid`
        AND `assignments`.`course_id` = `courseid`;
	-- commit
	COMMIT;
     
END//
DELIMITER ;

/* Sample trainers enrolled to courses */
CALL `EnrollStudent` ('1','1');
CALL `EnrollStudent` ('1','2');
CALL `EnrollStudent` ('2','1');
CALL `EnrollStudent` ('2','3');
CALL `EnrollStudent` ('2','4');
CALL `EnrollStudent` ('3','2');
CALL `EnrollStudent` ('3','3');
CALL `EnrollStudent` ('4','2');
CALL `EnrollStudent` ('4','4');
CALL `EnrollStudent` ('5','1');
CALL `EnrollStudent` ('5','2');
CALL `EnrollStudent` ('5','5');
