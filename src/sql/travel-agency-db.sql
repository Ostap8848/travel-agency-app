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
  `discount` DECIMAL(5,2) NULL,
  `description` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `tour_name_ukr_UNIQUE` (`tour_name_ukr` ASC) VISIBLE,
  UNIQUE INDEX `tour_name_eng_UNIQUE` (`tour_name_eng` ASC) VISIBLE);


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
  `role` ENUM('GUEST', 'CLIENT', 'MANAGER', 'ADMINISTRATOR') NOT NULL,
  `is_blocked` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_login_UNIQUE` (`email_login` ASC) VISIBLE,
  UNIQUE INDEX `instagram_UNIQUE` (`instagram` ASC) VISIBLE,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `travel-agency-db`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel-agency-db`.`orders` ;

CREATE TABLE IF NOT EXISTS `travel-agency-db`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DECIMAL(10,2) NOT NULL,
  `status` ENUM('REGISTERED', 'PAID', 'CANCELED') NOT NULL,
  `notes` VARCHAR(500) NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `travel-agency-db`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `travel-agency-db`.`order_has_tour`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel-agency-db`.`order_has_tour` ;

CREATE TABLE IF NOT EXISTS `travel-agency-db`.`order_has_tour` (
  `order_id` INT NOT NULL,
  `tour_id` INT NOT NULL,
  `persons_to_go` INT NOT NULL,
  PRIMARY KEY (`order_id`, `tour_id`),
  INDEX `fk_order_has_tour_tour1_idx` (`tour_id` ASC) VISIBLE,
  INDEX `fk_order_has_tour_order_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_tour_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `travel-agency-db`.`orders` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_has_tour_tour1`
    FOREIGN KEY (`tour_id`)
    REFERENCES `travel-agency-db`.`tours` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- users
INSERT INTO users (id, first_name, last_name, email_login, password, instagram, phone_number, role, is_blocked) VALUES (DEFAULT, 'admin', 'admin', 'iamadmin@gmail.com', 'secretpassword', 'adminishere', '+380969379992', 'ADMINISTRATOR', '0');
INSERT INTO users (id, first_name, last_name, email_login, password, instagram, phone_number, role, is_blocked) VALUES (DEFAULT, 'Mistar', 'Guk', 'gukishere@gmail.com', 'qwerty1234', 'byebyemilikanets', '+380671113007', 'MANAGER', '0');
INSERT INTO users (id, first_name, last_name, email_login, password, instagram, phone_number, role, is_blocked) VALUES (DEFAULT, 'Itachi', 'Uchiha', 'konohastyle@gmail.com', 'lastfromclan84', 'shadowhokage', '+380687899825', 'MANAGER', '0');
INSERT INTO users (id, first_name, last_name, email_login, password, instagram, phone_number, role, is_blocked) VALUES (DEFAULT, 'Julia', 'Clash', 'barbiegirl37@gmail.com', 'leavetheproblem148963', 'pretty78', '+380506667666', 'MANAGER', '0');
INSERT INTO users (id, first_name, last_name, email_login, password, instagram, phone_number, role, is_blocked) VALUES (DEFAULT, 'Ostap', 'Solonynka', 'ostapben84@gmail.com', 'ostapostap1488', 'itachi8848', '+380634432945', 'CLIENT', '0');
INSERT INTO users (id, first_name, last_name, email_login, password, instagram, phone_number, role, is_blocked) VALUES (DEFAULT, 'Dmutro', 'Trots', 'bigdata0402@gmail.com', 'hustlermagerov1996', 'trots_trots', '+380964569321', 'CLIENT', '0');


-- tours
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Відвідай Кіпр', 'Visit Cyprus', 'EXCURSION', '489.99', '8', 'FOUR_STAR', '0', '6.00', 'Start date - "20.08.22", finish date - "31.08.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Чарівна Франція', 'Charming France', 'REST', '458.79', '10', 'FOUR_STAR', '0', '7.00', 'Start date - "22.08.22", finish date - "30.08.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Незвідана Стрийщина', 'Unexplored Stryishchyna', 'EXCURSION', '50.99', '15', 'ONE_STAR', '0', null, 'Start date - "01.08.22", finish date - "30.09.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Найкращі бутіки Мілану', 'The best boutiques in Milan', 'SHOPPING', '399.49', '8', 'THREE_STAR', '1', '20.00', 'Start date - "01.08.22", finish date - "15.09.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Краще від Анталії, тільки Анталія "Все включено"', 'Better than Antalya, is only Antalya "All Inclusive"', 'REST', '250.99', '2', 'FIVE_STAR', '1', '25.00', 'Start date - "14.08.22", finish date - "25.09.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Закупися в Лондоні', 'Shop in London', 'SHOPPING', '543.69', '5', 'FOUR_STAR', '0', null, 'Start date - "31.08.22", finish date - "30.09.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Сонячна Іспанія', 'Sunny Spain', 'REST', '300.99', '2', 'FIVE_STAR', '1', '18.00', 'Start date - "01.08.22", finish date - "31.10.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Таємниці країни Сонця що сходить', 'Secrets of the Land of the Rising Sun', 'EXCURSION', '88.49', '8', 'FIVE_STAR', '0', null, 'Start date - "01.07.22", finish date - "31.10.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Арктична риболовля', 'Arctic fishing', 'REST', '725.99', '4', 'ONE_STAR', '0', '6.00', 'Start date - "01.09.22", finish date - "31.10.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Незабутній "Октобер Фест" в Баварії', 'Unforgettable "October Fest" in Bavaria', 'EXCURSION', '633.79', '15', 'FIVE_STAR', '1', '10.00', 'Start date - "30.09.22", finish date - "01.11.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Екскурсія в Освенцим', 'Excursion to Oswiecim', 'EXCURSION', '149.99', '15', 'THREE_STAR', '0', null, 'Start date - "24.08.22", finish date - "30.11.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Голлівуд - один шанс в житті', 'Hollywood is a once-in-a-lifetime opportunity', 'EXCURSION', '1487.99', '5', 'FIVE_STAR', '0', '10.00', 'Start date - "01.08.22", finish date - "24.09.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'ЯМАААААААЙКААААААА', 'JAMAAAAAAICAAAAAAA', 'REST', '1000.49', '2', 'FOUR_STAR', '1', '22.00', 'Start date - "01.09.22", finish date - "30.10.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Старовинні вулички Львова', 'Ancient streets of Lviv', 'EXCURSION', '30.99', '20', 'FOUR_STAR', '0', '8.00', 'Start date - "29.08.22", finish date - "31.12.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Краків - перлина польської культури', 'Krakow - the pearl of Polish culture', 'EXCURSION', '342.99', '15', 'THREE_STAR', '0', null, 'Start date - "14.08.22", finish date - "14.09.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Відкрий для себе Казахстан', 'Discover Kazakhstan for yourself', 'EXCURSION', '533.99', '10', 'THREE_STAR', '0', '5.00', 'Start date - "01.09.22", finish date - "25.09.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Єгипет, Єгипет і ще раз Єгипет', 'Egypt, Egypt and Egypt again', 'REST', '269.19', '7', 'FIVE_STAR', '1', '15.00', 'Start date - "03.09.22", finish date - "30.09.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Блошині ринки Індії', 'Flea markets of India', 'SHOPPING', '700.99', '10', 'TWO_STAR', '0', '18.00', 'Start date - "22.08.22", finish date - "31.10.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Греція для тебе', 'Greece for you', 'REST', '315.59', '2', 'FIVE_STAR', '1', '20.00', 'Start date - "18.08.22", finish date - "18.09.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Екстримальний відпочинок в Швейцарії', 'Extreme vacation in Switzerland', 'REST', '500.49', '11', 'FOUR_STAR', '0', '6.00', 'Start date - "31.10.22", finish date - "31.12.22"');
INSERT INTO tours (id, tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount, description) VALUES (DEFAULT, 'Мальовничі Карпати', 'Picturesque Carpathians', 'EXCURSION', '199.99', '17', 'TWO_STAR', '0', null, 'Start date - "22.08.22", finish date - "30.09.22"');



