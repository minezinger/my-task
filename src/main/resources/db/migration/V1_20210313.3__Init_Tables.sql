CREATE SEQUENCE logbook_seq START 1 INCREMENT 1;

-- Create Table
CREATE TABLE survey_logbook (
	id BIGINT NOT NULL,
	survey_code VARCHAR(32) NOT NULL, 
	survey_source VARCHAR(255) NOT NULL,
	selected_choice VARCHAR(32) NOT NULL
);

INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-001', 'Nine', '1');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-001', 'SMH', '2');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-001', 'Portal', '3');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-001', 'Nine', '1');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-001', 'Community', '4');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-001', 'Twitter', '2');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-001', 'Facebook', '5');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-001', 'Seven', '2');

INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-002', 'Seven', '2');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-002', 'SMH', '2');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-002', 'SMH', '1');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-002', 'Instgram', '1');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-002', 'ABC', '4');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-002', 'Twitter', '3');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-002', 'Facebook', '1');
INSERT INTO survey_logbook (id, survey_code, survey_source, selected_choice) VALUES (NEXTVAL('survey_seq'), 'TS-002', 'ABC', '5');

COMMIT;