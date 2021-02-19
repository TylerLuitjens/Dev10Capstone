use trivia_night;

 /*
        "Paul McCartney has always used his middle name. What is his real first name? ","correct_answer":"James","incorrect_answers":["John","Jack","Justin"]}
		"Where was Kanye West born?", "correct_answer":"Atlanta, Georgia", "incorrect_answers":["Chicago, Illinois","Los Angeles, California","Detroit, Michigan"]
		"Approximately how many Apple I personal computers were created?","correct_answer":"200","incorrect_answers":["100","500","1000"]
        "What five letter word is the motto of the IBM Computer company?","correct_answer":"Think","incorrect_answers":["Click","Logic","Pixel"]
		"What is the world's most expensive spice by weight?", correct_answer	"Saffron", "incorrect_answers":["Cinnamon", Cardamom","Vanilla"]
        "What is the full title of the Prime Minister of the UK?","correct_answer":"First Lord of the Treasury","incorrect_answers":["Duke of Cambridge","Her Majesty&#039;s Loyal Opposition","Manager of the Crown Estate"]
        "Joseph Stalin had a criminal past doing what?","correct_answer":"Robbing Trains","incorrect_answers":["Murder for Hire","Tax Evasion","Identity Fraud"]
        "In what year did Texas secede from Mexico?","correct_answer":"1836","incorrect_answers":["1838","1845","1844"]
        "Which of these Roman gods doesn&#039;t have a counterpart in Greek mythology?","correct_answer":"Janus","incorrect_answers":["Vulcan","Juno","Mars"]
        "What is the name of the Greek god of blacksmiths?","correct_answer":"Hephaestus","incorrect_answers":["Dyntos","Vulcan","Artagatus"]
        "Which NBA player won Most Valuable Player for the 1999-2000 season?","correct_answer":"Shaquille O&#039;Neal","incorrect_answers":["Allen Iverson","Kobe Bryant","Paul Pierce"]
        "What country hosted the 2014 Winter Olympics?","correct_answer":"Russia","incorrect_answers":["Canada","United States","Germany"]}
       */

-- insert into category (category_id, category_name) values
-- 	(1, 'Celebrities'), (2, 'Computer Science'), (3, 'General Knowledge'), (4, 'History'), (5, 'Mythology'), (6, 'Sports');

insert into question (question_id, question, category_name) values
	(1, 'Paul McCartney has always used his middle name. What is his real first name?', 'Celebrities'), 
	(2, 'Where was Kanye West born?', 'Celebrities'),
	(3, 'Approximately how many Apple I personal computers were created?', 'Computer Science'),
	(4, 'What five letter word is the motto of the IBM Computer company', 'Computer Science'),
	(5, "What is the world's most expensive spice by weight?", 'General Knowledge'),
	(6, 'What is the full title of the Prime Minister of the UK?', 'General Knowledge'),
    (7, 'Joseph Stalin had a criminal past doing what?', 'History'),
    (8, 'In what year did Texas secede from Mexico?', 'History'),
    (9, "Which of these Roman gods doesn't have a counterpart in Greek mythology?", 'Mythology'),
    (10, 'What is the name of the Greek god of blacksmiths?', 'Mythology'),
    (11, 'Which NBA player won Most Valuable Player for the 1999-2000 season?', 'Sports'),
    (12, 'What country hosted the 2014 Winter Olympics?', 'Sports');
    
	
insert into answer (answer_id, question_id, answer, isCorrect) values
	(1, 1, 'James', 1), (2, 1, 'John', 0), (3, 1, 'Jack', 0), (4, 1, 'Justin', 0),
	(5, 2, 'Atlanta, Georgia', 1), (6, 2, 'Chicago, Illinois', 0), (7, 2, 'Los Angeles, California', 0), (8, 2, 'Detroit, Michigan', 0),
	(9, 3, '200', 1), (10, 3, '100', 0), (11, 3, '500', 0), (12, 3, '1000', 0),
	(13, 4, 'Think', 1), (14, 4, 'Click', 0), (15, 4, 'Logic', 0), (16, 4, 'Pixel', 0),
	(17, 5, 'Saffron', 1), (18, 5, 'Cinnamon', 0), (19, 5, 'Cardamom', 0), (20, 5, 'Vanilla', 0),
	(21, 6, 'First Lord of the Treasury', 1), (22, 6, 'Duke of Cambridge', 0), (23, 6, "Her Majesty's Loyal Opposition", 0), (24, 6, 'Manager of the Crown Estate', 0),
    (25, 7, 'Robbing Trains', 1), (26, 7, 'Murder for Hire', 0), (27, 7, 'Tax Evasion', 0), (28, 7, 'Identity Fraud', 0),
    (29, 8, '1836', 1), (30, 8, '1838', 0), (31, 8, '1845', 0), (32, 8, '1844', 0),
    (33, 9, 'Janus', 1), (34, 9, 'Vulcan', 0), (35, 9, 'Juno', 0), (36, 9, 'Mars', 0),
    (37, 10, 'Hephaestus', 1), (38, 10, 'Dyntos', 0), (39, 10, 'Vulcan', 0), (40, 10, 'Artagatus', 0),
    (41, 11, "Shaquille O'Neal", 1), (42, 11, 'Allen Iverson', 0), (43, 11, 'Kobe Bryant', 0), (44, 11, 'Paul Pierce', 0),
    (45, 12, 'Russia', 1), (46, 12, 'Canada', 0), (47, 12, 'United States', 0), (48, 12, 'Germany', 0);
    
	
insert into user (user_id, username, `password`, total_questions_answered, total_questions_correct) values
	(1, 'First User', 'Clear_text', 5, 2),
	(2, 'Second User', 'Unsafe_password', 10, 8),
	(3, 'Third User', 'Poor_practice', 8, 4);