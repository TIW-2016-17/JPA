drop table if exists `coursesobjects`;
drop table if exists `objects`;
drop table if exists `courses`;
drop table if exists `sections`;
drop table if exists `modules`;

CREATE  TABLE `Modules` (
  `id_module` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id_module`) );
  
  CREATE TABLE `Sections` (
  `id_section` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `module` INT NOT NULL,
  PRIMARY KEY (`id_section`),
  KEY `FK_section_1` (`module`),
  CONSTRAINT `FK_section_1` FOREIGN KEY (`module`) REFERENCES `Modules` (`id_module`)
);

CREATE TABLE `Courses` (
  `id_course` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `section` INT NOT NULL,
  PRIMARY KEY (`id_course`),
  KEY `FK_course_1` (`section`),
  CONSTRAINT `FK_course_1` FOREIGN KEY (`section`) REFERENCES `Sections` (`id_section`)
  );
  
  
CREATE  TABLE `Objects` (
  `id_object` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NOT NULL ,
  `route` VARCHAR(200) NOT NULL ,
  PRIMARY KEY (`id_object`) );
  
  CREATE  TABLE `CoursesObjects` (
  `id_course` INT NOT NULL ,
  `id_object` INT NOT NULL ,
  PRIMARY KEY (`id_course`, `id_object`) ,
  INDEX `a_idx` (`id_object` ASC) ,
  INDEX `FK_CoursesObjects_1_idx` (`id_course` ASC) ,
  CONSTRAINT `FK_CoursesObjects_2`
    FOREIGN KEY (`id_object` )
    REFERENCES `Objects` (`id_object` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CoursesObjects_1`
    FOREIGN KEY (`id_course` )
    REFERENCES `Courses` (`id_course` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
  
INSERT INTO `Modules` (`id_module`, `name`) VALUES ('1', 'Grammar');
INSERT INTO `Modules` (`id_module`, `name`) VALUES ('2', 'Vocabulary');
INSERT INTO `Modules` (`id_module`, `name`) VALUES ('3', 'Listening');
INSERT INTO `Modules` (`id_module`, `name`) VALUES ('4', 'Reading');

INSERT INTO `Sections` (id_section,name,module) VALUES (1,'Confusing verb tenses',1);
INSERT INTO `Sections` (id_section,name,module) VALUES (2,'If-clauses',1);
INSERT INTO `Sections` (id_section,name,module) VALUES (3,'Modal verbs',1);
INSERT INTO `Sections` (id_section,name,module) VALUES (4,'Phrasal verbs',1);
INSERT INTO `Sections` (id_section,name,module) VALUES (5,'Confusing pairs',2);
INSERT INTO `Sections` (id_section,name,module) VALUES (6,'Phrasal verbs',2);
INSERT INTO `Sections` (id_section,name,module) VALUES (7,'Topics',2);
INSERT INTO `Sections` (id_section,name,module) VALUES (8,'News',3);
INSERT INTO `Sections` (id_section,name,module) VALUES (9,'Confusing sounds',3);
INSERT INTO `Sections` (id_section,name,module) VALUES (10,'Interviews',3);
INSERT INTO `Sections` (id_section,name,module) VALUES (11,'Pictures',3);
INSERT INTO `Sections` (id_section,name,module) VALUES (12,'Quotations',4);
INSERT INTO `Sections` (id_section,name,module) VALUES (13,'Riddles',4);

INSERT INTO `Courses` (id_course,name,section) VALUES (1,'Confusing verb tenses',1);
INSERT INTO `Courses` (id_course,name,section) VALUES (2,'If-clauses',2);
INSERT INTO `Courses` (id_course,name,section) VALUES (3,'Modal verbs',3);
INSERT INTO `Courses` (id_course,name,section) VALUES (4,'Phrasal verbs',4);
INSERT INTO `Courses` (id_course,name,section) VALUES (5,'Confusing adjectives',5);
INSERT INTO `Courses` (id_course,name,section) VALUES (6,'Do or make',5);
INSERT INTO `Courses` (id_course,name,section) VALUES (7,'Good or well',5);
INSERT INTO `Courses` (id_course,name,section) VALUES (8,'Hope or wish',5);
INSERT INTO `Courses` (id_course,name,section) VALUES (9,'Like or as',5);
INSERT INTO `Courses` (id_course,name,section) VALUES (10,'Say or tell',5);
INSERT INTO `Courses` (id_course,name,section) VALUES (11,'Sensitive or sensible',5);
INSERT INTO `Courses` (id_course,name,section) VALUES (12,'Phrasal verbs',6);
INSERT INTO `Courses` (id_course,name,section) VALUES (13,'Environment',7);
INSERT INTO `Courses` (id_course,name,section) VALUES (14,'Mass media',7);
INSERT INTO `Courses` (id_course,name,section) VALUES (15,'Business and economics',7);
INSERT INTO `Courses` (id_course,name,section) VALUES (16,'Weather and climate',7);
INSERT INTO `Courses` (id_course,name,section) VALUES (17,'News',8);
INSERT INTO `Courses` (id_course,name,section) VALUES (18,'Confusing sounds',9);
INSERT INTO `Courses` (id_course,name,section) VALUES (19,'Interviews',10);
INSERT INTO `Courses` (id_course,name,section) VALUES (20,'Pictures',11);
INSERT INTO `Courses` (id_course,name,section) VALUES (21,'Quotations',12);
INSERT INTO `Courses` (id_course,name,section) VALUES (22,'Riddles',13);


INSERT INTO `Objects`  (id_object,name,description,route) VALUES (1,'Prerequisites','Prerrequisitos','Objetos/Activities/Prerequisites/CS-chj-Prerequisites.swf');
INSERT INTO `Objects`  (id_object,name,description,route) VALUES (2,'Introduction','Introduccion','Objetos/Activities/Introduction/CS-chj-Introduction.swf');
INSERT INTO `Objects`  (id_object,name,description,route) VALUES 
(3,'Exercise','Ejercicio','Recursos/Objetos/Activities/Exercise/CS-chj-Exercise.swf');

INSERT INTO `CoursesObjects` (id_course,id_object) VALUES (18,1);
INSERT INTO `CoursesObjects` (id_course,id_object) VALUES (18,2);
INSERT INTO `CoursesObjects` (id_course,id_object) VALUES (18,3);

commit;
