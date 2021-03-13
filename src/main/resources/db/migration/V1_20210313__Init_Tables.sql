-- Create Sequence
CREATE SEQUENCE survey_seq START 1 INCREMENT 1;

-- Create Table
CREATE TABLE survey_master (
	id BIGINT NOT NULL,
	survey_code VARCHAR(32) NOT NULL, 
	survey_type VARCHAR(32) NOT NULL, 
	survey_name VARCHAR(255) NOT NULL,
	survey_desc VARCHAR(255),
	question_text TEXT NOT NULL,
	choices JSONB 
);

