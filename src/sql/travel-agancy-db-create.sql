-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema travel-agency-db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema travel-agency-db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `travel-agency-db` DEFAULT CHARACTER SET utf8 ;
USE `travel-agency-db` ;

-- -----------------------------------------------------
-- Table `travel-agency-db`.`tours`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel-agency-db`.`tours` ;

CREATE TABLE IF NOT EXISTS `travel-agency-db`.`tours` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tour_name_ukr` VARCHAR(255) NOT NULL,
  `tour_name_eng` VARCHAR(255) NOT NULL,
  `tour_type` ENUM('REST', 'EXCURSION', 'SHOPPING') NOT NULL,
  `tour_price` DECIMAL(10,2) NOT NULL,
  `number_of_persons` INT(100) NOT NULL,
  `hotel_type` ENUM('ONE_STAR', 'TWO_STAR', 'THREE_STAR', 'FOUR_STAR', 'FIVE_STAR') NOT NULL,
  `is_tour_hot` TINYINT NOT NULL,
  `discount` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `tour_name_ukr_UNIQUE` (`tour_name_ukr` ASC) VISIBLE,
  UNIQUE INDEX `tour_name_eng_UNIQUE` (`tour_name_eng` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `travel-agency-db`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel-agency-db`.`orders` ;

CREATE TABLE IF NOT EXISTS `travel-agency-db`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DECIMAL(10,2) NOT NULL,
  `status` ENUM('REGISTERED', 'PAID', 'CANCELED') NOT NULL,
  `notes` VARCHAR(500) NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `travel-agency-db`.`order_has_tour`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel-agency-db`.`order_has_tour` ;

CREATE TABLE IF NOT EXISTS `travel-agency-db`.`order_has_tour` (
  `order_id` INT NOT NULL,
  `tour_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `tour_id`),
  INDEX `fk_order_has_tour_tour1_idx` (`tour_id` ASC) VISIBLE,
  INDEX `fk_order_has_tour_order_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_tour_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `travel-agency-db`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_tour_tour1`
    FOREIGN KEY (`tour_id`)
    REFERENCES `travel-agency-db`.`tours` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `travel-agency-db`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel-agency-db`.`users` ;

CREATE TABLE IF NOT EXISTS `travel-agency-db`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email_login` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `instagram` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(100) NOT NULL,
  `role` ENUM('GUEST', 'MANAGER', 'ADMINISTRATOR') NOT NULL,
  `is_blocked` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_login_UNIQUE` (`email_login` ASC) VISIBLE,
  UNIQUE INDEX `instagram_UNIQUE` (`instagram` ASC) VISIBLE,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `travel-agency-db`.`order_has_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel-agency-db`.`order_has_user` ;

CREATE TABLE IF NOT EXISTS `travel-agency-db`.`order_has_user` (
  `order_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `user_id`),
  INDEX `fk_order_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_order_has_user_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_user_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `travel-agency-db`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `travel-agency-db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
