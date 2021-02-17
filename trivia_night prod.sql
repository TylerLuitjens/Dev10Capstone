drop database if exists trivia_night;
create database trivia_night;
use trivia_night;

create table category (
	category_id int primary key auto_increment,
    category_name varchar(50)
);

create table question (
	question_id int primary key auto_increment,
    question varchar(500) not null,
    category_id int not null,
    constraint fk_question_category_id
		foreign key (category_id)
        references category(category_id)
);

create table answer (
	answer_id int primary key auto_increment,
    question_id int not null,
    answer varchar(100) not null,
    isCorrect int not null,
    constraint fk_answer_question_id
		foreign key (question_id)
        references question(question_id)
);

create table `user` (
	user_id int primary key auto_increment,
    username varchar(30) not null,
    `password` varchar(45) not null,
    total_questions_answered int,
    total_questions_correct int
);

create table game (
	game_code varchar(4) primary key
);

create table game_question (
	game_code varchar(4) not null,
    question_id int not null,
    constraint fk_game_question_game_code
		foreign key (game_code)
        references game(game_code),
	constraint fk_game_question_question_id
		foreign key (question_id)
        references question(question_id)
);

create table game_user (
	user_id int not null,
    game_code varchar(4) not null,
    num_answered int,
    num_correct int,
    constraint fk_game_user_user_id
		foreign key (user_id)
        references `user`(user_id),
    constraint fk_game_user_game_code
		foreign key (game_code) 
        references game(game_code)
);