CREATE TABLE `USERS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50),
  `email` VARCHAR(50),
  `password` VARCHAR(255),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `SUBJECTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50),
  `description` VARCHAR(200)
);

CREATE TABLE `POSTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(50),
  `date` TIMESTAMP,
  `text` VARCHAR(5000),
  `user_id` INT,
  `subject_id` INT
);
ALTER TABLE `POSTS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`);
ALTER TABLE `POSTS` ADD FOREIGN KEY (`subject_id`) REFERENCES `SUBJECTS` (`id`);

CREATE TABLE `COMMENTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `date` TIMESTAMP,
  `text` VARCHAR(2000),
  `user_id` INT,
  `post_id` INT
);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`post_id`) REFERENCES `POSTS` (`id`);

CREATE TABLE `SUBSCRIPTIONS` (
  `user_id` INT, 
  `subject_id` INT
);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`subject_id`) REFERENCES `SUBJECTS` (`id`);

INSERT INTO `SUBJECTS` (name, description)
VALUES ('Java', 'Programmation en langage Java'),
       ('C#', 'Programmation en langage C#'),
       ('CSS', 'Style et mise en page'),
       ('JavaScript', 'Programmation en langage JavaScript');
