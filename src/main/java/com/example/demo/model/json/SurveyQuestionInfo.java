package com.example.demo.model.json;

import com.example.demo.persistence.dao.json.Choices;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SurveyQuestionInfo {

	private String surveyCode;
	private String questionText;
	private Choices options;

	public SurveyQuestionInfo(String surveyCode, String questionText, Choices options) {
		this.surveyCode = surveyCode;
		this.questionText = questionText;
		this.options = options;
	}
}