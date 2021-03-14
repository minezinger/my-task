package com.example.demo.model.json;

import com.example.demo.persistence.dao.json.Choices;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SurveyQuestionInfo {
	/*
	 * Present data for SurveyMasterDao
	 */

	private String surveyCode;
	private String surveyType;
	private String surveyName;
	private String surveyDesc;
	private String questionText;
	private Choices options;

	public SurveyQuestionInfo(String surveyCode, String questionText) {
		this.surveyCode = surveyCode;
		this.questionText = questionText;
	}

	public SurveyQuestionInfo(String surveyCode, String questionText, Choices options) {
		this.surveyCode = surveyCode;
		this.questionText = questionText;
		this.options = options;
	}
	
	public SurveyQuestionInfo(String surveyCode, String surveyType, String surveyName, String surveyDesc, String questionText, Choices options) {
		this.surveyCode = surveyCode;
		this.surveyType = surveyCode;
		this.surveyName = surveyName;
		this.surveyDesc = surveyDesc;
		this.questionText = questionText;
		this.options = options;
	}

}