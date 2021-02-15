drop database if exists trivia_night;
create database trivia_night;
use trivia_night;

create table category (
	category_id int primary key auto_increment
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
    category_id int not null,
    answer varchar(100) not null,
    isCorrect int not null,
    constraint fk_answer_question_id
		foreign key (question_id)
        references question(question_id),
	constraint fk_answer_category_id
		foreign key(category_id)
        references category(category_id)
);

create table user (
	user_id int primary key auto_increment,
    username varchar(30) not null,
    `password` varchar(45) not null,
    questions_answered int,
    questions_correct int
);