USE `privateschool`;

-- list of all the students
SELECT * FROM `students`;

-- list of all the trainers
SELECT * FROM `trainers`;

-- list of all the assignments
SELECT * FROM `assignments`;

-- list of all the courses
SELECT 
    `courses`.`id`,
    `courses`.`title`,
    `courses`.`stream`,
    `types`.`type`,
    `courses`.`start_date`,
    `courses`.`end_date`
FROM
    `courses`
        INNER JOIN
    `course_types` ON `courses`.`id` = `course_types`.`course_id`
        INNER JOIN
    `types` ON `types`.`id` = `course_types`.`type_id`;

-- list of all the students per course
SELECT 
    `courses`.`title`,
    `courses`.`stream`,
    `types`.`type`,
    `students`.`first_name`,
    `students`.`last_name`
FROM
    `students`
        INNER JOIN
    (`courses`
    INNER JOIN `courses_students` ON (`courses`.`id` = `courses_students`.`course_id`)
        AND (`courses`.`id` = `courses_students`.`course_id`)) ON `students`.`id` = `courses_students`.`student_id`
        INNER JOIN
    `course_types` ON `courses`.`id` = `course_types`.`course_id`
        INNER JOIN
    `types` ON `types`.`id` = `course_types`.`type_id`
    ORDER BY `students`.`last_name`;

-- list of all the trainers per course
SELECT 
    `courses`.`title`,
    `courses`.`stream`,
    `types`.`type`,
    `trainers`.`first_name`,
    `trainers`.`last_name`
FROM
    `trainers`
        INNER JOIN
    (`courses`
    INNER JOIN `courses_trainers` ON (`courses`.`id` = `courses_trainers`.`course_id`)
        AND (`courses`.`id` = `courses_trainers`.`course_id`)) ON `trainers`.`id` = `courses_trainers`.`trainer_id`
        INNER JOIN
    `course_types` ON `courses`.`id` = `course_types`.`course_id`
        INNER JOIN
    `types` ON `types`.`id` = `course_types`.`type_id`
    ORDER BY `trainers`.`last_name`;
    
-- list of all the assignments per course
SELECT 
    `courses`.`title`,
    `courses`.`stream`,
    `types`.`type`,
    `assignments`.`title`,
    `assignments`.`description`,
    `assignments`.`sub_date_time`
FROM
    `assignments`
        INNER JOIN
    `courses` ON `courses`.`id` = `assignments`.`course_id`
        INNER JOIN
    `course_types` ON `courses`.`id` = `course_types`.`course_id`
        INNER JOIN
    `types` ON `types`.`id` = `course_types`.`type_id`
    ORDER BY   `courses`.`title`,
    `courses`.`stream`;

-- list of all the assignments per course per student
SELECT 
    `courses`.`title`,
    `courses`.`stream`,
    `types`.`type`,
    `students`.`first_name`,
    `students`.`last_name`,
    `assignments`.`title`,
    `assignments`.`description`,
    `assignments`.`sub_date_time`,
    `student_grades`.`oral_mark`,
    `student_grades`.`total_mark`
FROM
    `assignments`
        INNER JOIN
    `courses` ON `courses`.`id` = `assignments`.`course_id`
        INNER JOIN
    `course_types` ON `courses`.`id` = `course_types`.`course_id`
        INNER JOIN
    `types` ON `types`.`id` = `course_types`.`type_id`
		INNER JOIN
	`student_grades` ON `student_grades`.`assignment_id` = `assignments`.`id`
		INNER JOIN
	`students` ON `students`.`id` = `student_grades`.`student_id`;
    
    -- list of all students that are enrolled in more than one course
    SELECT 
    `students`.`first_name`,
    `students`.`last_name`,
    COUNT(*) AS `courses_enrolled`
FROM
    `students`
        INNER JOIN
    `courses_students` ON `students`.`id` = `courses_students`.`student_id`
        INNER JOIN
    `courses` ON `courses`.`id` = `courses_students`.`course_id`
        INNER JOIN
    `course_types` ON `courses`.`id` = `course_types`.`course_id`
        INNER JOIN
    `types` ON `types`.`id` = `course_types`.`type_id`
    GROUP BY `students`.`last_name`
    HAVING COUNT(*) >= 2;