package com.example.demo.model.json;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SurveyLogStat {
	
	private String surveyCode;
	private String surveyName;
	private String selectedChoiceName;
	private String selectedChoice;
	private Integer countNum;
	
	public SurveyLogStat(String selectedChoice, Integer countNum) {
		this.selectedChoice = selectedChoice;
		this.countNum = countNum;
	}
	
	public SurveyLogStat(String surveyCode, String selectedChoice, Integer countNum) {
		this.surveyCode = surveyCode;
		this.selectedChoice = selectedChoice;
		this.countNum = countNum;
	}

	public SurveyLogStat(String surveyCode, String surveyName, String selectedChoice, Integer countNum) {
		this.surveyCode = surveyCode;
		this.surveyName = surveyName;
		this.selectedChoice = selectedChoice;
		this.countNum = countNum;
	}

	public SurveyLogStat(String surveyCode, String surveyName, String selectedChoiceName, String selectedChoice, Integer countNum) {
		this.surveyCode = surveyCode;
		this.surveyName = surveyName;
		this.selectedChoiceName = selectedChoiceName;
		this.selectedChoice = selectedChoice;
		this.countNum = countNum;
	}

}
