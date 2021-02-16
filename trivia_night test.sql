drop database if exists trivia_night_test;
create database trivia_night_test;
use trivia_night_test;

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

delimiter //
create procedure set_known_good_state()
begin 
	delete from category;
    alter table category auto_increment = 1;
    delete from question;
    alter table question auto_increment = 1;
    delete from answer;
    alter table answer auto_increment = 1;
    delete from user;
    alter table user auto_increment = 1;
    
    insert into category (category_id, category_name) values
		(1, 'Celebrities'),(2, 'Computer Science'),(3, 'General Knowledge');
        
        /*
        "Paul McCartney has always used his middle name. What is his real first name? ","correct_answer":"James","incorrect_answers":["John","Jack","Justin"]}
		"Where was Kanye West born?", "correct_answer":"Atlanta, Georgia", "incorrect_answers":["Chicago, Illinois","Los Angeles, California","Detroit, Michigan"]
		"Approximately how many Apple I personal computers were created?","correct_answer":"200","incorrect_answers":["100","500","1000"]
        "What five letter word is the motto of the IBM Computer company?","correct_answer":"Think","incorrect_answers":["Click","Logic","Pixel"]
		"What is the world's most expensive spice by weight?", correct_answer	"Saffron", "incorrect_answers":["Cinnamon", Cardamom","Vanilla"]
        "What is the full title of the Prime Minister of the UK?","correct_answer":"First Lord of the Treasury","incorrect_answers":["Duke of Cambridge","Her Majesty&#039;s Loyal Opposition","Manager of the Crown Estate"]
       */
        
	insert into question (question_id, question, category_id) values
		(1, 'Paul McCartney has always used his middle name. What is his real first name?', 1), 
        (2, 'Where was Kanye West born?', 1),
        (3, 'Approximately how many Apple I personal computers were created?', 2),
        (4, 'What five letter word is the motto of the IBM Computer company', 2),
        (5, "What is the world's most expensive spice by weight?", 3),
        (6, 'What is the full title of the Prime Minister of the UK?', 3);
        
	insert into answer (answer_id, question_id, category_id, answer, isCorrect) values
		(1, 1, 1, 'James', 1), (2, 1, 1, 'John', 0), (3, 1, 1, 'Jack', 0), (4, 1, 1, 'Justin', 0),
        (5, 2, 1, 'Atlanta, Georgia', 1), (6, 2, 1, 'Chicago, Illinois', 0), (7, 2, 1, 'Los Angeles, California', 0), (8, 2, 1, 'Detroit, Michigan', 0),
        (9, 3, 2, '200', 1), (10, 3, 2, '100', 0), (11, 3, 2, '500', 0), (12, 3, 2, '1000', 0),
        (13, 4, 2, 'Think', 1), (14, 4, 2, 'Click', 0), (15, 4, 2, 'Logic', 0), (16, 4, 2, 'Pixel', 0),
        (17, 5, 3, 'Saffron', 1), (18, 5, 3, 'Cinnamon', 0), (19, 5, 3, 'Cardamom', 0), (20, 5, 3, 'Vanilla', 0),
        (21, 6, 3, 'First Lord of the Treasury', 1), (22, 6, 3, 'Duke of Cambridge', 0), (23, 6, 3, "Her Majesty's Loyal Opposition", 0), (24, 6, 3, 'Manager of the Crown Estate', 0);
        
	insert into user (user_id, username, `password`, questions_answered, questions_correct) values
		(1, 'First User', 'Clear_text', 5, 2),
        (2, 'Second User', 'Unsafe_password', 10, 8),
        (3, 'Third User', 'Poor_practice', 8, 4);
        
	end //
    
    delimiter ;
        
        
	