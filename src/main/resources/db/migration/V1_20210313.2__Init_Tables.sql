-- Insert sample data
INSERT INTO survey_master(id, survey_code, survey_type, survey_name, question_text, choices) 
VALUES (NEXTVAL('survey_seq'), 'TS-001', 'R', 'favourite_fruit', 'Favourite Fruit', '
{ "choices": [
	{"choiceKey": 1, "choiceName": "Apple"},
	{"choiceKey": 2, "choiceName": "Grape"},
	{"choiceKey": 3, "choiceName": "Melon"},
	{"choiceKey": 4, "choiceName": "Pear"},
	{"choiceKey": 5, "choiceName": "Orange"}
]}
');


INSERT INTO survey_master(id, survey_code, survey_type, survey_name, question_text, choices) 
VALUES (NEXTVAL('survey_seq'), 'TS-002', 'R', 'favourite_vehicle', 'Favourite Vehicle', '
{ "choices": [
	{"choiceKey": 1, "choiceName": "Ford"},
	{"choiceKey": 2, "choiceName": "Holden"},
	{"choiceKey": 3, "choiceName": "Mazda"},
	{"choiceKey": 4, "choiceName": "Toyota"},
	{"choiceKey": 5, "choiceName": "Hyundai"}
]}
');

COMMIT;