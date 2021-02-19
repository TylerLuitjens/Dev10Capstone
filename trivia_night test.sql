drop database if exists trivia_night_test;
create database trivia_night_test;
use trivia_night_test;

create table question (
	question_id int primary key auto_increment,
    question varchar(500) not null,
    category_name varchar(50)
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
    `password` varchar(512) not null,
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

create table error_log (
	error_log_id int primary key auto_increment,
    `description` varchar(2048),
    error_timestamp varchar(64)
);

delimiter //
create procedure set_known_good_state()
begin 

    delete from game_question;
    alter table game_question auto_increment = 1;
    delete from game_user;
    alter table game_user auto_increment = 1;
	delete from answer;
    alter table answer auto_increment = 1;
    delete from question;
    alter table question auto_increment = 1;
    delete from `user`;
    alter table `user` auto_increment = 1;
    delete from game;
    alter table game auto_increment = 1;
 
        
        /*
        "Paul McCartney has always used his middle name. What is his real first name? ","correct_answer":"James","incorrect_answers":["John","Jack","Justin"]}
		"Where was Kanye West born?", "correct_answer":"Atlanta, Georgia", "incorrect_answers":["Chicago, Illinois","Los Angeles, California","Detroit, Michigan"]
		"Approximately how many Apple I personal computers were created?","correct_answer":"200","incorrect_answers":["100","500","1000"]
        "What five letter word is the motto of the IBM Computer company?","correct_answer":"Think","incorrect_answers":["Click","Logic","Pixel"]
		"What is the world's most expensive spice by weight?", correct_answer	"Saffron", "incorrect_answers":["Cinnamon", Cardamom","Vanilla"]
        "What is the full title of the Prime Minister of the UK?","correct_answer":"First Lord of the Treasury","incorrect_answers":["Duke of Cambridge","Her Majesty&#039;s Loyal Opposition","Manager of the Crown Estate"]
       */
        
	insert into question (question_id, question, category_name) values
		(1, 'Paul McCartney has always used his middle name. What is his real first name?', 'Celebrities'), 
        (2, 'Where was Kanye West born?', 'Celebrities'),
        (3, 'Approximately how many Apple I personal computers were created?', 'Computer Science'),
        (4, 'What five letter word is the motto of the IBM Computer company', 'Computer Science'),
        (5, "What is the world's most expensive spice by weight?", 'General Knowledge'),
        (6, 'What is the full title of the Prime Minister of the UK?', 'General Knowledge');
        
	insert into answer (answer_id, question_id, answer, isCorrect) values
		(1, 1, 'James', 1), (2, 1, 'John', 0), (3, 1, 'Jack', 0), (4, 1, 'Justin', 0),
        (5, 2, 'Atlanta, Georgia', 1), (6, 2, 'Chicago, Illinois', 0), (7, 2, 'Los Angeles, California', 0), (8, 2, 'Detroit, Michigan', 0),
        (9, 3, '200', 1), (10, 3, '100', 0), (11, 3, '500', 0), (12, 3, '1000', 0),
        (13, 4, 'Think', 1), (14, 4, 'Click', 0), (15, 4, 'Logic', 0), (16, 4, 'Pixel', 0),
        (17, 5, 'Saffron', 1), (18, 5, 'Cinnamon', 0), (19, 5, 'Cardamom', 0), (20, 5, 'Vanilla', 0),
        (21, 6, 'First Lord of the Treasury', 1), (22, 6, 'Duke of Cambridge', 0), (23, 6, "Her Majesty's Loyal Opposition", 0), (24, 6, 'Manager of the Crown Estate', 0);
        
	insert into user (user_id, username, `password`, total_questions_answered, total_questions_correct) values
		(1, 'First User', 'Clear_text', 5, 2),
        (2, 'Second User', 'Unsafe_password', 10, 8),
        (3, 'Third User', 'Poor_practice', 8, 4);
        
	insert into game (game_code) values
		('ABCD'),
        ('DCBA');
        
	insert into game_user (game_code, user_id) values
		('ABCD',1);
	
    insert into game_question (game_code, question_id) values
		('ABCD', 1),
        ('ABCD', 2),
        ('ABCD', 3),
        ('ABCD', 4),
        ('ABCD', 5);
        
	end //
    
    delimiter ;
        
        
	