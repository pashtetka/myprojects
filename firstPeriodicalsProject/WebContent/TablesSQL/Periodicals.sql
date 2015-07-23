CREATE DATABASE periodicals_project;
USE periodicals_project;

CREATE TABLE `periodicals_project`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `e-mail` VARCHAR(45) NOT NULL,
  `user_Type` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `login`, `e-mail`))
ENGINE = InnoDB;

CREATE TABLE `periodicals_project`.`periodicals` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `periodical_Name` VARCHAR(45) NOT NULL,
  `cost` INT NOT NULL,
  `outputs_In_Month` INT NOT NULL,
  `topic` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE `periodicals_project`.`subscription` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `periodical_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `status_Of_The_Publication` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

ALTER TABLE `subscription` ADD CONSTRAINT `subs_priod` FOREIGN KEY (`periodical_id`) REFERENCES `periodicals` (`id`);


ALTER TABLE `subscription` ADD CONSTRAINT `subs_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);






INSERT INTO `periodicals_project`.`users` (`login`, `password`, `e-mail`, `user_Type`, `name`, `surname`) VALUES ('admin', 'admin123', 'tut@mail.ru', 'admin', 'Alexandra', 'Sinkevich');

CREATE TABLE `periodicals_project`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `periodical_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `comment` LONGTEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

ALTER TABLE `comments` ADD CONSTRAINT `comm_priod` FOREIGN KEY (`periodical_id`) REFERENCES `periodicals` (`id`);
ALTER TABLE `comments` ADD CONSTRAINT `comm_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);